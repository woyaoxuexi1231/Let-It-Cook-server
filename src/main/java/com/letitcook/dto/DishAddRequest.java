package com.letitcook.dto;

import lombok.Data;

/**
 * 菜谱新增请求
 *
 * @author hulei
 * @since 2026/4/17
 */
@Data
public class DishAddRequest {
    /**
     * 菜谱ID（更新时必填）
     */
    private Long id;

    /**
     * 菜谱名称
     */
    private String name;

    /**
     * 菜谱图片URL（可选）
     */
    private String image;

    /**
     * 菜系（如：川菜、粤菜、湘菜等）
     */
    private String cuisine;
}