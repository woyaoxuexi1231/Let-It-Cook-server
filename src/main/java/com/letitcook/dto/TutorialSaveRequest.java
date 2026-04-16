package com.letitcook.dto;

import lombok.Data;

/**
 * 教程保存请求
 * 教程必须属于某个菜谱
 * 
 * @author hulei
 * @since 2026/4/16
 */
@Data
public class TutorialSaveRequest {
    /**
     * 教程ID(更新时必填)
     */
    private Long id;
    
    /**
     * 所属菜谱ID(必填)
     */
    private Long dishId;
    
    /**
     * 教程标题
     */
    private String title;
    
    /**
     * 教程类型(video/link/text)
     */
    private String type;
    
    /**
     * 教程URL
     */
    private String url;
    
    /**
     * 排序顺序
     */
    private Integer sortOrder;
}
