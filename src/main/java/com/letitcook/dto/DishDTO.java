package com.letitcook.dto;

import com.letitcook.entity.Tutorial;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * 菜谱DTO，用于前端传输数据
 *
 * @author hulei
 * @since 2026/4/16
 */

@Data
public class DishDTO {
    private Long id;
    private String name;
    private String image;
    
    /**
     * 分页参数 - 当前页码
     */
    private Integer pageNum;
    
    /**
     * 分页参数 - 每页数量
     */
    private Integer pageSize;
}
