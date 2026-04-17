package com.letitcook.dto;

import lombok.Data;

/**
 * 教程列表查询请求
 * 
 * @author hulei
 * @since 2026/4/17
 */
@Data
public class TutorialListRequest {
    /**
     * 菜谱ID
     */
    private Long dishId;
}
