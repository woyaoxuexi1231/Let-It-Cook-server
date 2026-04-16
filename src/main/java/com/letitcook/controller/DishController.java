package com.letitcook.controller;

import com.letitcook.common.Result;
import com.letitcook.dto.DishDTO;
import com.letitcook.dto.DishDeleteRequest;
import com.letitcook.dto.DishDetailRequest;
import com.letitcook.dto.DishRandomRequest;
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
        log.info("收到获取所有菜谱请求");
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
     * 根据ID获取菜谱详情（包含教程列表）
     */
    @PostMapping("/detail")
    public Result<DishDTO> getDishById(@RequestBody DishDetailRequest request) {
        log.info("收到获取菜谱详情请求, ID: {}", request.getId());
        
        Dish dish = dishService.getById(request.getId());
        if (dish == null) {
            log.warn("⚠️ 菜谱不存在, ID: {}", request.getId());
            return Result.error("菜谱不存在");
        }

        // 获取菜谱关联的教程
        List<Tutorial> tutorials = dishService.getTutorialsByDishId(request.getId());

        DishDTO dishDTO = new DishDTO();
        dishDTO.setId(dish.getId());
        dishDTO.setName(dish.getName());
        dishDTO.setImage(dish.getImage());
        dishDTO.setTutorials(tutorials);
        
        log.info("✅ 菜谱详情获取成功, ID: {}", request.getId());
        return Result.success(dishDTO);
    }

    /**
     * 新增菜谱
     */
    @PostMapping("/add")
    public Result<DishDTO> addDish(@RequestBody DishDTO dishDTO) {
        log.info("收到新增菜谱请求, 名称: {}", dishDTO.getName());
        
        // 创建菜谱
        Dish dish = new Dish();
        dish.setName(dishDTO.getName());
        dish.setImage(dishDTO.getImage());
        dish = dishService.createDish(dish);

        // 创建教程并关联
        if (dishDTO.getTutorials() != null) {
            int order = 1;
            for (Tutorial tutorial : dishDTO.getTutorials()) {
                // 创建教程
                Tutorial newTutorial = new Tutorial();
                newTutorial.setTitle(tutorial.getTitle());
                newTutorial.setType(tutorial.getType());
                newTutorial.setUrl(tutorial.getUrl());
                newTutorial = dishService.createTutorial(newTutorial);

                // 关联菜谱和教程
                dishService.createDishTutorial(dish.getId(), newTutorial.getId(), order++);
            }
        }

        log.info("✅ 菜谱新增成功, ID: {}", dish.getId());
        return Result.success(getDishByIdFromService(dish.getId()));
    }

    /**
     * 更新菜谱
     */
    @PostMapping("/update")
    public Result<DishDTO> updateDish(@RequestBody DishDTO dishDTO) {
        log.info("收到更新菜谱请求, ID: {}", dishDTO.getId());
        
        if (dishDTO.getId() == null) {
            return Result.error("菜谱ID不能为空");
        }
        
        Dish dish = dishService.getById(dishDTO.getId());
        if (dish == null) {
            log.warn("⚠️ 菜谱不存在, ID: {}", dishDTO.getId());
            return Result.error("菜谱不存在");
        }

        // 更新菜谱信息
        dish.setName(dishDTO.getName());
        dish.setImage(dishDTO.getImage());
        dishService.updateDish(dish);

        // 删除原有关联
        dishService.deleteDishTutorialsByDishId(dishDTO.getId());

        // 创建新的教程并关联
        if (dishDTO.getTutorials() != null) {
            int order = 1;
            for (Tutorial tutorial : dishDTO.getTutorials()) {
                // 检查教程是否已存在
                Tutorial existingTutorial = dishService.findExistingTutorial(
                    tutorial.getTitle(), 
                    tutorial.getType(), 
                    tutorial.getUrl()
                );

                if (existingTutorial == null) {
                    // 创建新教程
                    Tutorial newTutorial = new Tutorial();
                    newTutorial.setTitle(tutorial.getTitle());
                    newTutorial.setType(tutorial.getType());
                    newTutorial.setUrl(tutorial.getUrl());
                    existingTutorial = dishService.createTutorial(newTutorial);
                }

                // 关联菜谱和教程
                dishService.createDishTutorial(dish.getId(), existingTutorial.getId(), order++);
            }
        }

        log.info("✅ 菜谱更新成功, ID: {}", dishDTO.getId());
        return Result.success(getDishByIdFromService(dishDTO.getId()));
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

    /**
     * 获取随机菜谱
     */
    @PostMapping("/random")
    public Result<List<Map<String, Object>>> getRandomDishes(@RequestBody DishRandomRequest request) {
        int count = request.getCount() != null ? request.getCount() : 3;
        log.info("收到获取随机菜谱请求, 数量: {}", count);
        
        List<Dish> randomDishes = dishService.getRandomDishes(count);
        
        List<Map<String, Object>> result = randomDishes.stream()
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
     * 从 Service 获取菜谱详情（内部方法）
     */
    private DishDTO getDishByIdFromService(Long id) {
        Dish dish = dishService.getById(id);
        if (dish == null) {
            return null;
        }

        List<Tutorial> tutorials = dishService.getTutorialsByDishId(id);

        DishDTO dishDTO = new DishDTO();
        dishDTO.setId(dish.getId());
        dishDTO.setName(dish.getName());
        dishDTO.setImage(dish.getImage());
        dishDTO.setTutorials(tutorials);
        
        return dishDTO;
    }
}
