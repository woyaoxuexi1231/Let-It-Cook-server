package com.letitcook.dto;

import lombok.Data;

/**
 * 菜谱添加图片请求
 * 
 * @author hulei
 * @since 2026/4/17
 */
@Data
public class DishAddImageRequest {
    /**
     * 菜谱ID
     */
    private Long id;
    
    /**
     * 图片URL
     */
    private String image;
}
