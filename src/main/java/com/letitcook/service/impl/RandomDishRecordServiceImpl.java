package com.letitcook.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.letitcook.entity.RandomDishRecord;
import com.letitcook.mapper.RandomDishRecordMapper;
import com.letitcook.service.RandomDishRecordService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

/**
 * 随机菜谱记录 Service 实现
 *
 * @author hulei
 * @since 2026/4/17
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class RandomDishRecordServiceImpl extends ServiceImpl<RandomDishRecordMapper, RandomDishRecord> implements RandomDishRecordService {

    @Override
    public RandomDishRecord getLatestRecord(String ip) {
        LambdaQueryWrapper<RandomDishRecord> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(RandomDishRecord::getIp, ip)
                .orderByDesc(RandomDishRecord::getCreateTime)
                .last("LIMIT 1");
        return getOne(queryWrapper);
    }

    @Override
    public void createRecord(String ip, String dishIds) {
        RandomDishRecord record = new RandomDishRecord();
        record.setIp(ip);
        record.setDishIds(dishIds);
        record.setIsDeleted(0);
        record.setCreateTime(LocalDateTime.now());
        record.setUpdateTime(LocalDateTime.now());
        save(record);
        log.info("✅ 创建IP随机记录: {} -> {}", ip, dishIds);
    }
}
