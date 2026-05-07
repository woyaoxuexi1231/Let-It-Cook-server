package com.letitcook.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * 菜谱表
 *
 * @author hulei
 * @since 2026/4/16
 */
@Data
@TableName("cook_dish")
public class Dish {

    /**
     * 主键ID
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 菜谱名称
     */
    @TableField("name")
    private String name;

    /**
     * 菜谱图片URL
     */
    @TableField("image")
    private String image;

    /**
     * 菜系（如：川菜、粤菜、湘菜等）
     */
    @TableField("cuisine")
    private String cuisine;

    /**
     * 烹饪时长（如：大概15分钟、15分钟到20分钟）
     */
    @TableField("cooking_time")
    private String cookingTime;

    /**
     * 需要原材料
     */
    @TableField("ingredients")
    private String ingredients;

    /**
     * 创建时间
     */
    @TableField("create_time")
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    @TableField("update_time")
    private LocalDateTime updateTime;

    /**
     * 逻辑删除 0-未删除 1-已删除
     */
    @TableLogic
    @TableField("is_deleted")
    private Integer isDeleted;

    /**
     * 创建人ID
     */
    @TableField("create_by")
    private Long createBy;

    /**
     * 更新人ID
     */
    @TableField("update_by")
    private Long updateBy;
}