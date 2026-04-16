package com.letitcook.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * 教程表
 *
 * @author hulei
 * @since 2026/4/16
 */
@Data
@TableName("cook_tutorial")
public class Tutorial {
    
    /**
     * 主键ID
     */
    @TableId(type = IdType.AUTO)
    private Long id;
    
    /**
     * 教程标题
     */
    @TableField("title")
    private String title;
    
    /**
     * 教程类型(video-视频, link-链接, text-文本)
     */
    @TableField("type")
    private String type;
    
    /**
     * 教程URL
     */
    @TableField("url")
    private String url;
    
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
