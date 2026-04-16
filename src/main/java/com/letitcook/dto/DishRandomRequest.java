package com.letitcook.dto;

import lombok.Data;

/**
 * 随机菜谱请求
 * 
 * @author hulei
 * @since 2026/4/16
 */
@Data
public class DishRandomRequest {
    /**
     * 获取数量
     */
    private Integer count;
}
