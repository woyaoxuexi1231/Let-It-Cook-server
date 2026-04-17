package com.letitcook.controller;

import com.letitcook.common.Result;
import com.letitcook.entity.Dish;
import com.letitcook.entity.Tutorial;
import com.letitcook.service.DishService;
import com.letitcook.vo.DishVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
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

    /**
     * 获取所有菜谱列表（简化版，仅包含id、name、image、tutorialCount）
     */
    @PostMapping("/list")
    public Result<List<Map<String, Object>>> getAllDishes() {
        log.info("✅ Client端收到获取菜谱列表请求");
        List<DishVO> dishes = dishService.getAllDishesWithTutorialCount(1, 10000).getRecords();

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
     * 获取随机菜谱
     */
    @PostMapping("/random")
    public Result<List<Map<String, Object>>> getRandomDishes(@RequestBody Map<String, Object> request) {
        Integer count = (Integer) request.getOrDefault("count", 3);
        log.info("✅ Client端收到获取随机菜谱请求, 数量: {}", count);

        List<DishVO> allDishes = dishService.getAllDishesWithTutorialCount(1, 10000).getRecords();

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

        return Result.success(result);
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