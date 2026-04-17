package com.letitcook.dto;

import lombok.Data;

/**
 * 分页查询请求
 * 
 * @author hulei
 * @since 2026/4/17
 */
@Data
public class PageRequest {
    /**
     * 当前页码（从1开始）
     */
    private Integer pageNum = 1;
    
    /**
     * 每页数量
     */
    private Integer pageSize = 10;
}
