package com.letitcook.dto;

import lombok.Data;

/**
 * 菜谱列表查询请求
 * 
 * @author hulei
 * @since 2026/4/17
 */
@Data
public class DishListRequest {
    /**
     * 当前页码（从1开始）
     */
    private Integer pageNum = 1;
    
    /**
     * 每页数量
     */
    private Integer pageSize = 10;
    
    /**
     * 搜索关键词（模糊匹配菜谱名称和菜系）
     */
    private String keyword;
}
