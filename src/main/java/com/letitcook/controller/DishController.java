package com.letitcook.controller;

import com.letitcook.common.Result;
import com.letitcook.dto.DishAddImageRequest;
import com.letitcook.dto.DishAddRequest;
import com.letitcook.dto.DishDeleteRequest;
import com.letitcook.dto.DishListRequest;
import com.letitcook.dto.TutorialDeleteRequest;
import com.letitcook.dto.TutorialListRequest;
import com.letitcook.dto.TutorialSaveRequest;
import com.letitcook.entity.Dish;
import com.letitcook.entity.Tutorial;
import com.letitcook.service.DishService;
import com.letitcook.vo.DishVO;
import com.baomidou.mybatisplus.core.metadata.IPage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 菜谱控制器
 *
 * @author hulei
 * @since 2026/4/16
 */
@Slf4j
@RestController
@RequestMapping("/api/dishes")
@RequiredArgsConstructor
public class DishController {

    private final DishService dishService;

    /**
     * 分页获取所有菜谱列表（包含教程数量）
     */
    @PostMapping("/list")
    public Result<IPage<DishVO>> getAllDishes(@RequestBody DishListRequest request) {
        log.info("✅ 收到获取菜谱列表请求, 页码: {}, 每页数量: {}", request.getPageNum(), request.getPageSize());
        IPage<DishVO> dishes = dishService.getAllDishesWithTutorialCount(request.getPageNum(), request.getPageSize());
        return Result.success(dishes);
    }

    /**
     * 新增菜谱
     */
    @PostMapping("/add")
    public Result<Dish> addDish(@RequestBody DishAddRequest request) {
        log.info("收到新增菜谱请求, 名称: {}", request.getName());

        // 创建菜谱
        Dish dish = new Dish();
        dish.setName(request.getName());
        dish.setImage(request.getImage());
        dish.setCuisine(request.getCuisine());
        dish = dishService.createDish(dish);

        log.info("✅ 菜谱新增成功, ID: {}", dish.getId());
        return Result.success(dish);
    }

    /**
     * 更新菜谱
     */
    @PostMapping("/update")
    public Result<Dish> updateDish(@RequestBody DishAddRequest request) {
        log.info("收到更新菜谱请求, ID: {}, 名称: {}", request.getId(), request.getName());

        if (request.getId() == null) {
            return Result.error("❌ 菜谱ID不能为空");
        }

        Dish dish = dishService.getById(request.getId());
        if (dish == null) {
            log.warn("⚠️ 菜谱不存在, ID: {}", request.getId());
            return Result.error("❌ 菜谱不存在");
        }

        // 更新信息
        dish.setName(request.getName());
        dish.setImage(request.getImage());
        dish.setCuisine(request.getCuisine());
        dish = dishService.updateDish(dish);

        log.info("✅ 菜谱更新成功, ID: {}", dish.getId());
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
            return Result.error("❌ 菜谱不存在");
        }

        dishService.deleteDish(request.getId());

        log.info("✅ 菜谱删除成功, ID: {}", request.getId());
        return Result.success(null);
    }

    /**
     * 为菜谱添加图片
     */
    @PostMapping("/addImage")
    public Result<Dish> addImageToDish(@RequestBody DishAddImageRequest request) {
        log.info("收到为菜谱添加图片请求, ID: {}, 图片URL: {}", request.getId(), request.getImage());

        if (request.getId() == null) {
            return Result.error("❌ 菜谱ID不能为空");
        }

        if (request.getImage() == null || request.getImage().trim().isEmpty()) {
            return Result.error("❌ 图片地址不能为空");
        }

        Dish dish = dishService.getById(request.getId());
        if (dish == null) {
            log.warn("⚠️ 菜谱不存在, ID: {}", request.getId());
            return Result.error("❌ 菜谱不存在");
        }

        // 更新图片
        dish.setImage(request.getImage());
        dishService.updateDish(dish);

        log.info("✅ 菜谱图片添加成功, ID: {}", dish.getId());
        return Result.success(dish);
    }

    /**
     * 获取指定菜谱的教程列表
     */
    @PostMapping("/tutorials/list")
    public Result<List<Tutorial>> getTutorialsByDishId(@RequestBody TutorialListRequest request) {
        log.info("获取菜谱教程列表 - dishId: {}", request.getDishId());
        List<Tutorial> tutorials = dishService.getTutorialsByDishId(request.getDishId());
        return Result.success(tutorials);
    }

    /**
     * 新增教程(必须指定所属菜谱)
     */
    @PostMapping("/tutorials/add")
    public Result<Tutorial> addTutorial(@RequestBody TutorialSaveRequest request) {
        log.info("新增教程 - title: {}, dishId: {}", request.getTitle(), request.getDishId());

        if (request.getDishId() == null) {
            return Result.error("❌ 必须指定所属菜谱ID");
        }

        Tutorial tutorial = new Tutorial();
        tutorial.setDishId(request.getDishId());
        tutorial.setTitle(request.getTitle());
        tutorial.setType(request.getType());
        tutorial.setUrl(request.getUrl());
        tutorial.setSortOrder(request.getSortOrder());

        Tutorial created = dishService.createTutorial(tutorial);

        log.info("✅ 教程新增成功, ID: {}", created.getId());
        return Result.success(created);
    }

    /**
     * 更新教程
     */
    @PostMapping("/tutorials/update")
    public Result<Tutorial> updateTutorial(@RequestBody TutorialSaveRequest request) {
        log.info("更新教程 - id: {}, dishId: {}", request.getId(), request.getDishId());

        if (request.getId() == null) {
            return Result.error("❌ 教程ID不能为空");
        }

        Tutorial tutorial = dishService.getById_Tutorial(request.getId());
        if (tutorial == null) {
            return Result.error("❌ 教程不存在");
        }

        tutorial.setTitle(request.getTitle());
        tutorial.setType(request.getType());
        tutorial.setUrl(request.getUrl());
        tutorial.setSortOrder(request.getSortOrder());

        Tutorial updated = dishService.updateTutorial(tutorial);

        log.info("✅ 教程更新成功, ID: {}", updated.getId());
        return Result.success(updated);
    }

    /**
     * 删除教程
     */
    @PostMapping("/tutorials/delete")
    public Result<Void> deleteTutorial(@RequestBody TutorialDeleteRequest request) {
        log.info("删除教程 - id: {}", request.getId());

        dishService.deleteTutorial(request.getId());

        log.info("✅ 教程删除成功, ID: {}", request.getId());
        return Result.success(null);
    }
}