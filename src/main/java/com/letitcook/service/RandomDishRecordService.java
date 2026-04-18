package com.letitcook.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.letitcook.entity.RandomDishRecord;

/**
 * 随机菜谱记录 Service
 *
 * @author hulei
 * @since 2026/4/17
 */
public interface RandomDishRecordService extends IService<RandomDishRecord> {

    /**
     * 获取IP最新的随机记录
     */
    RandomDishRecord getLatestRecord(String ip);

    /**
     * 创建新的随机记录
     */
    void createRecord(String ip, String dishIds);
}
