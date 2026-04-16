package com.letitcook.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import com.letitcook.entity.Dish;
import com.letitcook.entity.DishTutorial;
import com.letitcook.entity.Tutorial;
import com.letitcook.mapper.DishMapper;
import com.letitcook.mapper.DishTutorialMapper;
import com.letitcook.mapper.TutorialMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 菜谱服务
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
    private final DishTutorialMapper dishTutorialMapper;

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
     * 获取菜谱及其关联的教程列表
     */
    public List<Tutorial> getTutorialsByDishId(Long dishId) {
        // 查询关联关系
        LambdaQueryWrapper<DishTutorial> dtQueryWrapper = new LambdaQueryWrapper<>();
        dtQueryWrapper.eq(DishTutorial::getDishId, dishId)
                     .orderByAsc(DishTutorial::getSortOrder);
        List<DishTutorial> dishTutorials = dishTutorialMapper.selectList(dtQueryWrapper);

        // 获取教程详情
        List<Tutorial> tutorials = new ArrayList<>();
        for (DishTutorial dt : dishTutorials) {
            Tutorial tutorial = tutorialMapper.selectById(dt.getTutorialId());
            if (tutorial != null) {
                tutorials.add(tutorial);
            }
        }
        
        return tutorials;
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
     */
    @Transactional(rollbackFor = Exception.class)
    public void deleteDish(Long id) {
        dishMapper.deleteById(id);
        log.info("✅ 菜谱删除成功, ID: {}", id);
    }

    /**
     * 创建教程
     */
    @Transactional(rollbackFor = Exception.class)
    public Tutorial createTutorial(Tutorial tutorial) {
        tutorial.setCreateTime(LocalDateTime.now());
        tutorial.setUpdateTime(LocalDateTime.now());
        tutorial.setIsDeleted(0);
        tutorialMapper.insert(tutorial);
        log.info("✅ 教程创建成功, ID: {}, 标题: {}", tutorial.getId(), tutorial.getTitle());
        return tutorial;
    }

    /**
     * 创建菜谱-教程关联
     */
    @Transactional(rollbackFor = Exception.class)
    public void createDishTutorial(Long dishId, Long tutorialId, Integer sortOrder) {
        DishTutorial dishTutorial = new DishTutorial();
        dishTutorial.setDishId(dishId);
        dishTutorial.setTutorialId(tutorialId);
        dishTutorial.setSortOrder(sortOrder);
        dishTutorial.setCreateTime(LocalDateTime.now());
        dishTutorial.setUpdateTime(LocalDateTime.now());
        dishTutorial.setIsDeleted(0);
        dishTutorialMapper.insert(dishTutorial);
        log.info("✅ 菜谱-教程关联创建成功, 菜谱ID: {}, 教程ID: {}, 排序: {}", dishId, tutorialId, sortOrder);
    }

    /**
     * 删除菜谱的所有教程关联
     */
    @Transactional(rollbackFor = Exception.class)
    public void deleteDishTutorialsByDishId(Long dishId) {
        LambdaQueryWrapper<DishTutorial> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(DishTutorial::getDishId, dishId);
        dishTutorialMapper.delete(queryWrapper);
        log.info("✅ 菜谱关联教程已删除, 菜谱ID: {}", dishId);
    }

    /**
     * 根据教程信息查找已存在的教程
     */
    public Tutorial findExistingTutorial(String title, String type, String url) {
        LambdaQueryWrapper<Tutorial> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Tutorial::getTitle, title)
                   .eq(Tutorial::getType, type)
                   .eq(Tutorial::getUrl, url);
        return tutorialMapper.selectOne(queryWrapper);
    }

    /**
     * 获取随机菜谱
     */
    public List<Dish> getRandomDishes(int count) {
        List<Dish> allDishes = getAllDishes();
        if (allDishes.isEmpty()) {
            return new ArrayList<>();
        }
        
        // 随机打乱
        List<Dish> shuffled = new ArrayList<>(allDishes);
        java.util.Collections.shuffle(shuffled);
        
        // 返回指定数量
        int actualCount = Math.min(count, shuffled.size());
        return shuffled.subList(0, actualCount);
    }
}
