# 菜谱批量导入指南

## 文件说明

已生成以下 SQL 文件用于批量导入菜谱数据：

1. **insert_dishes_batch.sql** - 第一批数据（100道）
   - 川菜 50道
   - 粤菜 50道

2. **insert_dishes_batch2.sql** - 第二批数据（110道）
   - 湘菜 40道
   - 鲁菜 35道
   - 苏菜 35道

## 如何达到 500+ 道

由于单次回复长度限制，已生成 210道菜谱。要达到 500+ 道，你需要：

### 方案 1：分批执行（推荐）
```bash
# 执行第一批
mysql -u root -p test < insert_dishes_batch.sql

# 执行第二批
mysql -u root -p test < insert_dishes_batch2.sql

# 继续创建更多批次文件...
```

### 方案 2：使用程序生成
可以写一个 Python/Java 程序批量生成更多菜谱数据

## 已包含的菜系

✅ 川菜 (50道)
✅ 粤菜 (50道)  
✅ 湘菜 (40道)
✅ 鲁菜 (35道)
✅ 苏菜 (35道)

还需添加：
⬜ 浙菜 (40道)
⬜ 闽菜 (30道)
⬜ 徽菜 (30道)
⬜ 家常菜 (50道)
⬜ 西餐 (40道)
⬜ 日料 (40道)
⬜ 韩餐 (30道)
⬜ 东南亚菜 (30道)
⬜ 小吃 (30道)
⬜ 汤类 (25道)
⬜ 素菜 (20道)

总计可达 500+ 道

## 数据库字段

所有菜谱包含以下字段：
- name: 菜谱名称
- image: 图片URL (使用 picsum.photos 随机图片)
- cuisine: 菜系
- cooking_time: 烹饪时长
- ingredients: 需要原材料

## 注意事项

1. 执行前确保数据库表已创建
2. 确保已添加新字段（cuisine, cooking_time, ingredients）
3. 如需清空现有数据：`DELETE FROM cook_dish;`
4. 图片使用 picsum.photos 的固定 seed，每次访问图片相同
