package com.letitcook.controller;

import com.letitcook.common.Result;
import com.letitcook.dto.TutorialDeleteRequest;
import com.letitcook.dto.TutorialSaveRequest;
import com.letitcook.entity.Tutorial;
import com.letitcook.service.DishService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 教程控制器
 * 教程必须属于某个菜谱,不能独立存在
 * 
 * @author hulei
 * @since 2026/4/16
 */
@Slf4j
@RestController
@RequestMapping("/api/tutorials")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class TutorialController {

    private final DishService dishService;

    /**
     * 获取指定菜谱的教程列表
     */
    @GetMapping("/list")
    public Result<List<Tutorial>> getTutorialsByDishId(@RequestParam(value = "dishId") Long dishId) {
        log.info("获取菜谱教程列表 - dishId: {}", dishId);
        List<Tutorial> tutorials = dishService.getTutorialsByDishId(dishId);
        return Result.success(tutorials);
    }

    /**
     * 新增教程(必须指定所属菜谱)
     */
    @PostMapping("/add")
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
    @PostMapping("/update")
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
    @PostMapping("/delete")
    public Result<Void> deleteTutorial(@RequestBody TutorialDeleteRequest request) {
        log.info("删除教程 - id: {}", request.getId());
        
        dishService.deleteTutorial(request.getId());
        
        log.info("✅ 教程删除成功, ID: {}", request.getId());
        return Result.success(null);
    }
}
