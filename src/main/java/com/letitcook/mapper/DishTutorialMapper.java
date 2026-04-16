package com.letitcook.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.letitcook.entity.DishTutorial;
import org.apache.ibatis.annotations.Mapper;

/**
 * 菜谱-教程关联 Mapper 接口
 * 
 * @author hulei
 * @since 2026/4/16
 */
@Mapper
public interface DishTutorialMapper extends BaseMapper<DishTutorial> {
    // BaseMapper 已提供 insert/select/update/delete 通用方法
}
