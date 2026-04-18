package com.letitcook.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.letitcook.entity.RandomDishRecord;
import org.apache.ibatis.annotations.Mapper;

/**
 * 随机菜谱记录 Mapper
 *
 * @author hulei
 * @since 2026/4/17
 */
@Mapper
public interface RandomDishRecordMapper extends BaseMapper<RandomDishRecord> {
}
