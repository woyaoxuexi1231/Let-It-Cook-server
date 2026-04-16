package com.letitcook.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.letitcook.entity.Tutorial;
import org.apache.ibatis.annotations.Mapper;

/**
 * 教程 Mapper 接口
 * 
 * @author hulei
 * @since 2026/4/16
 */
@Mapper
public interface TutorialMapper extends BaseMapper<Tutorial> {
    // BaseMapper 已提供 insert/select/update/delete 通用方法
}
