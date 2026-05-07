package com.letitcook.controller;

import com.letitcook.common.Result;
import com.letitcook.entity.Dish;
import com.letitcook.entity.RandomDishRecord;
import com.letitcook.entity.Tutorial;
import com.letitcook.service.DishService;
import com.letitcook.service.RandomDishRecordService;
import com.letitcook.vo.DishVO;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Client端菜谱控制器
 * 提供给客户端使用的接口
 *
 * @author hulei
 * @since 2026/4/17
 */
@Slf4j
@RestController
@RequestMapping("/api/client/dishes")
@RequiredArgsConstructor
public class ClientDishController {

    private final DishService dishService;
    private final RandomDishRecordService randomDishRecordService;

    /**
     * 获取所有菜谱列表（简化版，仅包含id、name、image、tutorialCount）
     */
    @PostMapping("/list")
    public Result<List<Map<String, Object>>> getAllDishes() {
        log.info("✅ Client端收到获取菜谱列表请求");
        List<DishVO> dishes = dishService.getAllDishesWithTutorialCount(1, 10000, null).getRecords();

        List<Map<String, Object>> result = dishes.stream()
                .map(dish -> {
                    Map<String, Object> dishInfo = new HashMap<>();
                    dishInfo.put("id", dish.getId());
                    dishInfo.put("name", dish.getName());
                    dishInfo.put("image", dish.getImage());
                    dishInfo.put("tutorialCount", dish.getTutorialCount());
                    return dishInfo;
                })
                .collect(Collectors.toList());

        return Result.success(result);
    }

    /**
     * 获取上次随机结果（进页面时调用）
     */
    @PostMapping("/last-result")
    public Result<List<Map<String, Object>>> getLastResult(@RequestBody Map<String, Object> request, HttpServletRequest httpRequest) {
        // 优先使用前端传递的IP，其次使用请求头
        String ip = (String) request.get("clientIp");
        if (ip == null || ip.isEmpty() || "unknown".equalsIgnoreCase(ip)) {
            ip = getClientIp(httpRequest);
        }
        log.info("✅ Client端查询上次随机结果, IP: {}", ip);

        // 查询该IP的最新历史记录
        RandomDishRecord record = randomDishRecordService.getLatestRecord(ip);
        
        // 如果没有历史记录，返回空列表
        if (record == null || record.getDishIds() == null || record.getDishIds().isEmpty()) {
            log.info("✅ 该IP无历史记录");
            return Result.success(new ArrayList<>());
        }

        log.info("✅ 返回IP的历史随机记录: {}", record.getDishIds());
        List<Long> dishIdList = Arrays.stream(record.getDishIds().split(","))
                .map(String::trim)
                .filter(s -> !s.isEmpty())
                .map(Long::parseLong)
                .collect(Collectors.toList());
        
        List<DishVO> allDishes = dishService.getAllDishesWithTutorialCount(1, 10000,null).getRecords();
        Map<Long, DishVO> dishMap = allDishes.stream()
                .collect(Collectors.toMap(DishVO::getId, d -> d));
        
        List<Map<String, Object>> result = dishIdList.stream()
                .filter(dishMap::containsKey)
                .map(id -> {
                    DishVO dish = dishMap.get(id);
                    Map<String, Object> dishInfo = new HashMap<>();
                    dishInfo.put("id", dish.getId());
                    dishInfo.put("name", dish.getName());
                    dishInfo.put("image", dish.getImage());
                    dishInfo.put("tutorialCount", dish.getTutorialCount());
                    return dishInfo;
                })
                .collect(Collectors.toList());
        
        return Result.success(result);
    }

    /**
     * 获取随机菜谱（每次都生成新的）
     */
    @PostMapping("/random")
    public Result<List<Map<String, Object>>> getRandomDishes(@RequestBody Map<String, Object> request, HttpServletRequest httpRequest) {
        Integer count = (Integer) request.getOrDefault("count", 3);
        
        // 优先使用前端传递的IP，其次使用请求头
        String ip = (String) request.get("clientIp");
        if (ip == null || ip.isEmpty() || "unknown".equalsIgnoreCase(ip)) {
            ip = getClientIp(httpRequest);
        }
        log.info("✅ Client端获取随机菜谱, IP: {}, 数量: {}", ip, count);

        List<DishVO> allDishes = dishService.getAllDishesWithTutorialCount(1, 10000, null).getRecords();

        // 随机打乱
        List<DishVO> shuffled = new ArrayList<>(allDishes);
        java.util.Collections.shuffle(shuffled);

        // 取指定数量
        List<Map<String, Object>> result = shuffled.stream()
                .limit(count)
                .map(dish -> {
                    Map<String, Object> dishInfo = new HashMap<>();
                    dishInfo.put("id", dish.getId());
                    dishInfo.put("name", dish.getName());
                    dishInfo.put("image", dish.getImage());
                    dishInfo.put("tutorialCount", dish.getTutorialCount());
                    return dishInfo;
                })
                .collect(Collectors.toList());

        // 保存随机结果到数据库
        String dishIds = shuffled.stream()
                .limit(count)
                .map(dish -> String.valueOf(dish.getId()))
                .collect(Collectors.joining(","));
        randomDishRecordService.createRecord(ip, dishIds);

        return Result.success(result);
    }

    /**
     * 获取客户端IP
     */
    private String getClientIp(HttpServletRequest request) {
        String ip = request.getHeader("X-Forwarded-For");
        if (ip == null || ip.isEmpty() || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("X-Real-IP");
        }
        if (ip == null || ip.isEmpty() || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        // 如果是多个IP，取第一个
        if (ip != null && ip.contains(",")) {
            ip = ip.split(",")[0].trim();
        }
        return ip;
    }

    /**
     * 获取菜谱详情（包含教程信息）
     */
    @PostMapping("/detail")
    public Result<Map<String, Object>> getDishDetail(@RequestBody Map<String, Object> request) {
        Long id = Long.parseLong(String.valueOf(request.get("id")));
        log.info("✅ Client端收到获取菜谱详情请求, ID: {}", id);

        Dish dish = dishService.getById(id);
        if (dish == null) {
            log.warn("⚠️ 菜谱不存在, ID: {}", id);
            return Result.error("❌ 菜谱不存在");
        }

        // 获取教程列表
        List<Tutorial> tutorials = dishService.getTutorialsByDishId(id);

        // 构建返回结果
        Map<String, Object> result = new HashMap<>();
        result.put("id", dish.getId());
        result.put("name", dish.getName());
        result.put("image", dish.getImage());
        result.put("tutorials", tutorials);

        return Result.success(result);
    }
}
