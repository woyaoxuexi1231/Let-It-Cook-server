package com.letitcook.vo;

import com.letitcook.entity.Dish;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 菜谱VO（包含教程数量）
 *
 * @author hulei
 * @since 2026/4/18
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class DishVO extends Dish {

    /**
     * 教程数量
     */
    private Integer tutorialCount;
}