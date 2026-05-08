-- ==================== 初始化示例数据 ====================
-- 插入示例菜谱（仅最常见家常菜）
INSERT INTO `cook_dish` (`name`, `image`, `cuisine`, `cooking_time`, `ingredients`) VALUES
-- 川菜
('宫保鸡丁', 'https://picsum.photos/seed/gongbao/400/300', '川菜', '大概15分钟', '鸡胸肉、花生米、干辣椒、花椒、葱姜蒜、料酒、生抽、醋、糖'),
('鱼香肉丝', 'https://picsum.photos/seed/yuxiang/400/300', '川菜', '15分钟到20分钟', '猪里脊、木耳、胡萝卜、笋、泡椒、葱姜蒜、料酒、生抽、醋、糖、淀粉'),
('麻婆豆腐', 'https://picsum.photos/seed/mapo/400/300', '川菜', '大概10分钟', '豆腐、猪肉末、豆瓣酱、花椒粉、葱姜蒜、生抽、料酒'),
('水煮鱼', 'https://picsum.photos/seed/shuizhu/400/300', '川菜', '20分钟到25分钟', '草鱼、豆芽、干辣椒、花椒、豆瓣酱、葱姜蒜、料酒、淀粉'),
('回锅肉', 'https://picsum.photos/seed/huiguo/400/300', '川菜', '大概20分钟', '五花肉、青蒜苗、豆瓣酱、甜面酱、姜片、料酒、生抽'),
('辣子鸡', 'https://picsum.photos/seed/lazi/400/300', '川菜', '25分钟到30分钟', '鸡腿肉、干辣椒、花椒、葱姜蒜、料酒、生抽、糖、芝麻'),
('口水鸡', 'https://picsum.photos/seed/koushui/400/300', '川菜', '大概20分钟', '鸡腿、花生碎、花椒、辣椒油、生抽、醋、糖、葱姜蒜'),
('酸菜鱼', 'https://picsum.photos/seed/suancaiyu/400/300', '川菜', '25分钟到30分钟', '草鱼、酸菜、泡椒、干辣椒、花椒、姜蒜、料酒、淀粉'),

-- 粤菜
('白切鸡', 'https://picsum.photos/seed/baiqie/400/300', '粤菜', '大概30分钟', '三黄鸡、姜、葱、盐、料酒、香油'),
('糖醋排骨', 'https://picsum.photos/seed/tangcu/400/300', '粤菜', '25分钟到30分钟', '排骨、白糖、醋、生抽、料酒、姜片、葱'),
('清蒸鲈鱼', 'https://picsum.photos/seed/qingzheng/400/300', '粤菜', '大概15分钟', '鲈鱼、姜、葱、料酒、蒸鱼豉油、热油'),
('干炒牛河', 'https://picsum.photos/seed/niuhe/400/300', '粤菜', '大概10分钟', '河粉、牛肉、芽菜、葱、生抽、老抽、油'),
('煲仔饭', 'https://picsum.photos/seed/baozai/400/300', '粤菜', '30分钟到40分钟', '大米、腊肠、腊肉、鸡蛋、菜心、生抽、香油'),

-- 湘菜
('辣椒炒肉', 'https://picsum.photos/seed/lajiao/400/300', '湘菜', '大概15分钟', '猪肉、青红辣椒、蒜、豆豉、生抽、料酒'),
('小炒黄牛肉', 'https://picsum.photos/seed/huangniu/400/300', '湘菜', '大概15分钟', '牛肉、青红辣椒、姜、蒜、生抽、料酒、淀粉'),
('农家小炒肉', 'https://picsum.photos/seed/nongjia/400/300', '湘菜', '大概15分钟', '五花肉、青辣椒、蒜、豆豉、生抽、盐'),

-- 家常菜
('西红柿炒鸡蛋', 'https://picsum.photos/seed/xihongshi/400/300', '家常菜', '大概10分钟', '西红柿、鸡蛋、葱、盐、糖、油'),
('红烧肉', 'https://picsum.photos/seed/hongshaorou/400/300', '家常菜', '40分钟到50分钟', '五花肉、冰糖、生抽、老抽、料酒、姜、葱、八角'),
('可乐鸡翅', 'https://picsum.photos/seed/kele/400/300', '家常菜', '大概25分钟', '鸡翅、可乐、生抽、老抽、料酒、姜、葱'),
('酸辣土豆丝', 'https://picsum.photos/seed/tudou/400/300', '家常菜', '大概10分钟', '土豆、干辣椒、花椒、醋、盐、葱、蒜'),
('地三鲜', 'https://picsum.photos/seed/disaxian/400/300', '家常菜', '大概20分钟', '土豆、茄子、青椒、蒜、生抽、盐、淀粉'),
('鱼香茄子', 'https://picsum.photos/seed/qiezi/400/300', '家常菜', '大概15分钟', '茄子、猪肉末、泡椒、葱姜蒜、料酒、生抽、醋、糖'),
('蒜蓉西兰花', 'https://picsum.photos/seed/xilan/400/300', '家常菜', '大概10分钟', '西兰花、蒜、盐、油、鸡精'),
('干锅菜花', 'https://picsum.photos/seed/caihua/400/300', '家常菜', '大概15分钟', '菜花、五花肉、干辣椒、蒜、生抽、盐'),
('红烧茄子', 'https://picsum.photos/seed/hongshaoqie/400/300', '家常菜', '大概15分钟', '茄子、蒜、生抽、老抽、糖、盐、淀粉'),
('青椒肉丝', 'https://picsum.photos/seed/qingjiao/400/300', '家常菜', '大概10分钟', '猪肉、青椒、姜、蒜、生抽、料酒、淀粉'),
('土豆炖牛肉', 'https://picsum.photos/seed/tudouniurou/400/300', '家常菜', '40分钟到50分钟', '牛肉、土豆、胡萝卜、葱姜、八角、生抽、老抽'),
('红烧排骨', 'https://picsum.photos/seed/hongshaopg/400/300', '家常菜', '40分钟到50分钟', '排骨、冰糖、生抽、老抽、料酒、姜、葱、八角'),
('蒜苔炒肉', 'https://picsum.photos/seed/suantai/400/300', '家常菜', '大概10分钟', '蒜苔、猪肉、姜、蒜、生抽、盐'),
('糖醋里脊', 'https://picsum.photos/seed/tangculiji/400/300', '家常菜', '大概20分钟', '猪里脊、白糖、醋、番茄酱、淀粉、鸡蛋'),
('葱爆羊肉', 'https://picsum.photos/seed/congbao/400/300', '家常菜', '大概10分钟', '羊肉、大葱、姜、蒜、料酒、生抽'),
('水煮肉片', 'https://picsum.photos/seed/shuizhuroupian/400/300', '家常菜', '20分钟到25分钟', '猪里脊、豆芽、干辣椒、花椒、豆瓣酱、葱姜蒜、淀粉'),
('干煸豆角', 'https://picsum.photos/seed/ganbian/400/300', '家常菜', '大概15分钟', '四季豆、干辣椒、花椒、蒜、盐、生抽'),
('家常豆腐', 'https://picsum.photos/seed/jiachangdoufu/400/300', '家常菜', '大概15分钟', '豆腐、木耳、青椒、胡萝卜、豆瓣酱、生抽、淀粉'),
('蚂蚁上树', 'https://picsum.photos/seed/mayi/400/300', '家常菜', '大概15分钟', '粉丝、猪肉末、豆瓣酱、葱姜蒜'),
('蛋炒饭', 'https://picsum.photos/seed/danchaofan/400/300', '家常菜', '大概10分钟', '米饭、鸡蛋、葱、盐、油'),

-- 汤类
('西红柿蛋汤', 'https://picsum.photos/seed/dantang/400/300', '汤类', '大概10分钟', '西红柿、鸡蛋、葱、盐、香油、水'),
('紫菜蛋花汤', 'https://picsum.photos/seed/zicai/400/300', '汤类', '大概10分钟', '紫菜、鸡蛋、虾皮、葱、盐、香油'),
('排骨莲藕汤', 'https://picsum.photos/seed/liang/400/300', '汤类', '60分钟到80分钟', '排骨、莲藕、姜、盐、料酒'),
('鸡汤', 'https://picsum.photos/seed/jitang/400/300', '汤类', '50分钟到60分钟', '鸡、姜、葱、盐、料酒、红枣、枸杞'),
('鲫鱼豆腐汤', 'https://picsum.photos/seed/jiyu/400/300', '汤类', '大概25分钟', '鲫鱼、豆腐、姜、葱、盐、料酒');

-- 插入示例教程(直接关联到菜谱)
INSERT INTO `cook_tutorial` (`dish_id`, `title`, `type`, `url`, `sort_order`) VALUES
(1, '家常做法', 'video', 'https://example.com/video1', 1),
(1, '美食教程', 'link', 'https://example.com/link1', 2),
(2, '正宗做法', 'video', 'https://example.com/video2', 1);
