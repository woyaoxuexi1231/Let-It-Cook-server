package com.letitcook.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * 随机菜谱记录实体
 *
 * @author hulei
 * @since 2026/4/17
 */
@Data
@TableName("random_dish_record")
public class RandomDishRecord {

    @TableId(type = IdType.AUTO)
    private Long id;

    private String ip;

    private String dishIds;

    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;

    @TableLogic
    private Integer isDeleted;
}
