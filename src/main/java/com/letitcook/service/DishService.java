package com.letitcook.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import com.letitcook.entity.Dish;
import com.letitcook.entity.Tutorial;
import com.letitcook.mapper.DishMapper;
import com.letitcook.mapper.TutorialMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 菜谱服务
 * 教程直接通过 dish_id 关联到菜谱,不需要中间表
 * 
 * @author hulei
 * @since 2026/4/16
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class DishService {

    private final DishMapper dishMapper;
    private final TutorialMapper tutorialMapper;

    /**
     * 获取所有菜谱列表
     */
    public List<Dish> getAllDishes() {
        LambdaQueryWrapper<Dish> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.orderByDesc(Dish::getCreateTime);
        return dishMapper.selectList(queryWrapper);
    }

    /**
     * 根据ID获取菜谱
     */
    public Dish getById(Long id) {
        return dishMapper.selectById(id);
    }

    /**
     * 根据ID获取教程
     */
    public Tutorial getById_Tutorial(Long id) {
        return tutorialMapper.selectById(id);
    }

    /**
     * 获取指定菜谱的教程列表
     * 教程直接通过 dish_id 关联
     */
    public List<Tutorial> getTutorialsByDishId(Long dishId) {
        LambdaQueryWrapper<Tutorial> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Tutorial::getDishId, dishId)
                   .orderByAsc(Tutorial::getSortOrder);
        return tutorialMapper.selectList(queryWrapper);
    }

    /**
     * 创建菜谱
     */
    @Transactional(rollbackFor = Exception.class)
    public Dish createDish(Dish dish) {
        dish.setCreateTime(LocalDateTime.now());
        dish.setUpdateTime(LocalDateTime.now());
        dish.setIsDeleted(0);
        dishMapper.insert(dish);
        log.info("✅ 菜谱创建成功, ID: {}, 名称: {}", dish.getId(), dish.getName());
        return dish;
    }

    /**
     * 更新菜谱
     */
    @Transactional(rollbackFor = Exception.class)
    public Dish updateDish(Dish dish) {
        dish.setUpdateTime(LocalDateTime.now());
        dishMapper.updateById(dish);
        log.info("✅ 菜谱更新成功, ID: {}", dish.getId());
        return dish;
    }

    /**
     * 删除菜谱(逻辑删除)
     * 数据库设置了 ON DELETE CASCADE,会自动删除关联的教程
     */
    @Transactional(rollbackFor = Exception.class)
    public void deleteDish(Long id) {
        dishMapper.deleteById(id);
        log.info("✅ 菜谱删除成功, ID: {}, 关联教程已自动删除", id);
    }

    /**
     * 创建教程(必须指定所属菜谱)
     */
    @Transactional(rollbackFor = Exception.class)
    public Tutorial createTutorial(Tutorial tutorial) {
        if (tutorial.getDishId() == null) {
            throw new IllegalArgumentException("❌ 教程必须指定所属菜谱ID");
        }
        
        tutorial.setCreateTime(LocalDateTime.now());
        tutorial.setUpdateTime(LocalDateTime.now());
        tutorial.setIsDeleted(0);
        tutorialMapper.insert(tutorial);
        log.info("✅ 教程创建成功, ID: {}, 标题: {}, 菜谱ID: {}", 
            tutorial.getId(), tutorial.getTitle(), tutorial.getDishId());
        return tutorial;
    }

    /**
     * 更新教程
     */
    @Transactional(rollbackFor = Exception.class)
    public Tutorial updateTutorial(Tutorial tutorial) {
        tutorial.setUpdateTime(LocalDateTime.now());
        tutorialMapper.updateById(tutorial);
        log.info("✅ 教程更新成功, ID: {}", tutorial.getId());
        return tutorial;
    }

    /**
     * 删除教程(逻辑删除)
     */
    @Transactional(rollbackFor = Exception.class)
    public void deleteTutorial(Long id) {
        tutorialMapper.deleteById(id);
        log.info("✅ 教程删除成功, ID: {}", id);
    }


}
