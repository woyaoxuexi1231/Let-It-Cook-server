-- ==================== Let-It-Cook 数据库初始化脚本 ====================
-- 数据库：test
-- 创建时间：2026-04-16
-- 说明：菜谱管理系统数据表结构

-- ==================== 1. 菜谱表 ====================
CREATE TABLE IF NOT EXISTS `cook_dish` (
    `id` BIGINT UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键ID',
    `name` VARCHAR(100) NOT NULL COMMENT '菜谱名称',
    `image` VARCHAR(500) DEFAULT NULL COMMENT '菜谱图片URL',
    `create_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `is_deleted` TINYINT NOT NULL DEFAULT 0 COMMENT '逻辑删除 0-未删除 1-已删除',
    `create_by` BIGINT DEFAULT NULL COMMENT '创建人ID',
    `update_by` BIGINT DEFAULT NULL COMMENT '更新人ID',
    PRIMARY KEY (`id`),
    KEY `idx_create_time` (`create_time`),
    KEY `idx_update_time` (`update_time`),
    KEY `idx_name` (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='菜谱表';

-- ==================== 2. 教程表 ====================
CREATE TABLE IF NOT EXISTS `cook_tutorial` (
    `id` BIGINT UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键ID',
    `title` VARCHAR(200) NOT NULL COMMENT '教程标题',
    `type` VARCHAR(50) NOT NULL COMMENT '教程类型(video-视频, link-链接, text-文本)',
    `url` VARCHAR(1000) DEFAULT NULL COMMENT '教程URL',
    `create_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `is_deleted` TINYINT NOT NULL DEFAULT 0 COMMENT '逻辑删除 0-未删除 1-已删除',
    `create_by` BIGINT DEFAULT NULL COMMENT '创建人ID',
    `update_by` BIGINT DEFAULT NULL COMMENT '更新人ID',
    PRIMARY KEY (`id`),
    KEY `idx_create_time` (`create_time`),
    KEY `idx_update_time` (`update_time`),
    KEY `idx_type` (`type`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='教程表';

-- ==================== 3. 菜谱-教程关联表 ====================
CREATE TABLE IF NOT EXISTS `cook_dish_tutorial` (
    `id` BIGINT UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键ID',
    `dish_id` BIGINT UNSIGNED NOT NULL COMMENT '菜谱ID',
    `tutorial_id` BIGINT UNSIGNED NOT NULL COMMENT '教程ID',
    `sort_order` INT NOT NULL DEFAULT 0 COMMENT '排序顺序',
    `create_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `is_deleted` TINYINT NOT NULL DEFAULT 0 COMMENT '逻辑删除 0-未删除 1-已删除',
    `create_by` BIGINT DEFAULT NULL COMMENT '创建人ID',
    `update_by` BIGINT DEFAULT NULL COMMENT '更新人ID',
    PRIMARY KEY (`id`),
    KEY `idx_dish_id` (`dish_id`),
    KEY `idx_tutorial_id` (`tutorial_id`),
    KEY `idx_create_time` (`create_time`),
    KEY `idx_update_time` (`update_time`),
    UNIQUE KEY `uk_dish_tutorial` (`dish_id`, `tutorial_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='菜谱-教程关联表';

-- ==================== 初始化示例数据 ====================
-- 插入示例菜谱
INSERT INTO `cook_dish` (`name`, `image`) VALUES
('宫保鸡丁', 'https://example.com/gongbaojiding.jpg'),
('鱼香肉丝', 'https://example.com/yuxiangrousi.jpg'),
('糖醋排骨', 'https://example.com/tangcupaigu.jpg');

-- 插入示例教程
INSERT INTO `cook_tutorial` (`title`, `type`, `url`) VALUES
('家常做法', 'video', 'https://example.com/video1'),
('美食教程', 'link', 'https://example.com/link1'),
('正宗做法', 'video', 'https://example.com/video2');

-- 关联菜谱和教程
-- 宫保鸡丁 (id=1) 关联 2 个教程
INSERT INTO `cook_dish_tutorial` (`dish_id`, `tutorial_id`, `sort_order`) VALUES
(1, 1, 1),
(1, 2, 2);

-- 鱼香肉丝 (id=2) 关联 1 个教程
INSERT INTO `cook_dish_tutorial` (`dish_id`, `tutorial_id`, `sort_order`) VALUES
(2, 3, 1);
