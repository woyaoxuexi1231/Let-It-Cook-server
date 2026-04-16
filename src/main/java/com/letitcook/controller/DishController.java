package com.letitcook.controller;

import com.letitcook.common.Result;
import com.letitcook.dto.DishDTO;
import com.letitcook.dto.DishDeleteRequest;
import com.letitcook.entity.Dish;
import com.letitcook.entity.Tutorial;
import com.letitcook.service.DishService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 菜谱控制器
 * 
 * @author hulei
 * @since 2026/4/16
 */
@Slf4j
@RestController
@RequestMapping("/api/dishes")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class DishController {

    private final DishService dishService;

    /**
     * 获取所有菜谱列表（仅包含id、name、image）
     */
    @GetMapping("/list")
    public Result<List<Map<String, Object>>> getAllDishes() {
        log.info("✅ 收到获取所有菜谱请求");
        List<Dish> dishes = dishService.getAllDishes();
        
        List<Map<String, Object>> result = dishes.stream()
                .map(dish -> {
                    Map<String, Object> dishInfo = new HashMap<>();
                    dishInfo.put("id", dish.getId());
                    dishInfo.put("name", dish.getName());
                    dishInfo.put("image", dish.getImage());
                    return dishInfo;
                })
                .collect(Collectors.toList());
        
        return Result.success(result);
    }



    /**
     * 新增菜谱
     */
    @PostMapping("/add")
    public Result<Dish> addDish(@RequestBody DishDTO dishDTO) {
        log.info("收到新增菜谱请求, 名称: {}", dishDTO.getName());
        
        // 创建菜谱
        Dish dish = new Dish();
        dish.setName(dishDTO.getName());
        dish.setImage(dishDTO.getImage());
        dish = dishService.createDish(dish);

        log.info("✅ 菜谱新增成功, ID: {}", dish.getId());
        return Result.success(dish);
    }

    /**
     * 更新菜谱
     */
    @PostMapping("/update")
    public Result<Dish> updateDish(@RequestBody DishDTO dishDTO) {
        log.info("收到更新菜谱请求, ID: {}", dishDTO.getId());
        
        if (dishDTO.getId() == null) {
            return Result.error("❌ 菜谱ID不能为空");
        }
        
        Dish dish = dishService.getById(dishDTO.getId());
        if (dish == null) {
            log.warn("⚠️ 菜谱不存在, ID: {}", dishDTO.getId());
            return Result.error("❌ 菜谱不存在");
        }

        // 更新菜谱信息
        dish.setName(dishDTO.getName());
        dish.setImage(dishDTO.getImage());
        dishService.updateDish(dish);

        log.info("✅ 菜谱更新成功, ID: {}", dishDTO.getId());
        return Result.success(dish);
    }

    /**
     * 删除菜谱（逻辑删除）
     */
    @PostMapping("/delete")
    public Result<Void> deleteDish(@RequestBody DishDeleteRequest request) {
        log.info("收到删除菜谱请求, ID: {}", request.getId());
        
        Dish dish = dishService.getById(request.getId());
        if (dish == null) {
            log.warn("⚠️ 菜谱不存在, ID: {}", request.getId());
            return Result.error("菜谱不存在");
        }
        
        dishService.deleteDish(request.getId());

        log.info("✅ 菜谱删除成功, ID: {}", request.getId());
        return Result.success(null);
    }




}
