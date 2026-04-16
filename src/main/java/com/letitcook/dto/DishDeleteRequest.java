package com.letitcook.dto;

import lombok.Data;

/**
 * 菜谱删除请求
 * 
 * @author hulei
 * @since 2026/4/16
 */
@Data
public class DishDeleteRequest {
    /**
     * 菜谱ID
     */
    private Long id;
}
