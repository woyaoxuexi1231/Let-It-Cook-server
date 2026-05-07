-- ==================== Let-It-Cook 数据库初始化脚本 ====================
-- 数据库：test
-- 创建时间：2026-04-16
-- 说明：菜谱管理系统数据表结构

-- ==================== 1. 菜谱表 ====================
CREATE TABLE IF NOT EXISTS `cook_dish` (
    `id` BIGINT UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键ID',
    `name` VARCHAR(100) NOT NULL COMMENT '菜谱名称',
    `image` VARCHAR(500) DEFAULT NULL COMMENT '菜谱图片URL',
    `cuisine` VARCHAR(50) DEFAULT NULL COMMENT '菜系（如：川菜、粤菜、湘菜等）',
    `cooking_time` VARCHAR(50) DEFAULT NULL COMMENT '烹饪时长（如：大概15分钟、15分钟到20分钟）',
    `ingredients` TEXT DEFAULT NULL COMMENT '需要原材料',
    `create_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `is_deleted` TINYINT NOT NULL DEFAULT 0 COMMENT '逻辑删除 0-未删除 1-已删除',
    `create_by` BIGINT DEFAULT NULL COMMENT '创建人ID',
    `update_by` BIGINT DEFAULT NULL COMMENT '更新人ID',
    PRIMARY KEY (`id`),
    KEY `idx_create_time` (`create_time`),
    KEY `idx_update_time` (`update_time`),
    KEY `idx_name` (`name`),
    KEY `idx_cuisine` (`cuisine`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='菜谱表';

-- ==================== 2. 教程表 ====================
-- 教程必须属于某个菜谱,不能独立存在
CREATE TABLE IF NOT EXISTS `cook_tutorial` (
    `id` BIGINT UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键ID',
    `dish_id` BIGINT UNSIGNED NOT NULL COMMENT '所属菜谱ID',
    `title` VARCHAR(200) NOT NULL COMMENT '教程标题',
    `type` VARCHAR(50) NOT NULL COMMENT '教程类型(video-视频, link-链接, text-文本)',
    `url` VARCHAR(1000) DEFAULT NULL COMMENT '教程URL',
    `sort_order` INT NOT NULL DEFAULT 0 COMMENT '排序顺序',
    `create_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `is_deleted` TINYINT NOT NULL DEFAULT 0 COMMENT '逻辑删除 0-未删除 1-已删除',
    `create_by` BIGINT DEFAULT NULL COMMENT '创建人ID',
    `update_by` BIGINT DEFAULT NULL COMMENT '更新人ID',
    PRIMARY KEY (`id`),
    KEY `idx_dish_id` (`dish_id`),
    KEY `idx_create_time` (`create_time`),
    KEY `idx_update_time` (`update_time`),
    KEY `idx_type` (`type`),
    CONSTRAINT `fk_tutorial_dish` FOREIGN KEY (`dish_id`) REFERENCES `cook_dish` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='教程表(必须属于某个菜谱)';

