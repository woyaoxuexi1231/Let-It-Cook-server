-- ==================== 初始化示例数据 ====================
-- 插入示例菜谱（大量数据）
INSERT INTO `cook_dish` (`name`, `image`, `cuisine`, `cooking_time`, `ingredients`) VALUES
-- 川菜
('宫保鸡丁', 'https://picsum.photos/seed/gongbao/400/300', '川菜', '大概15分钟', '鸡胸肉、花生米、干辣椒、花椒、葱姜蒜、料酒、生抽、醋、糖'),
('鱼香肉丝', 'https://picsum.photos/seed/yuxiang/400/300', '川菜', '15分钟到20分钟', '猪里脊、木耳、胡萝卜、笋、泡椒、葱姜蒜、料酒、生抽、醋、糖、淀粉'),
('麻婆豆腐', 'https://picsum.photos/seed/mapo/400/300', '川菜', '大概10分钟', '豆腐、猪肉末、豆瓣酱、花椒粉、葱姜蒜、生抽、料酒'),
('水煮鱼', 'https://picsum.photos/seed/shuizhu/400/300', '川菜', '20分钟到25分钟', '草鱼、豆芽、干辣椒、花椒、豆瓣酱、葱姜蒜、料酒、淀粉'),
('回锅肉', 'https://picsum.photos/seed/huiguo/400/300', '川菜', '大概20分钟', '五花肉、青蒜苗、豆瓣酱、甜面酱、姜片、料酒、生抽'),
('辣子鸡', 'https://picsum.photos/seed/lazi/400/300', '川菜', '25分钟到30分钟', '鸡腿肉、干辣椒、花椒、葱姜蒜、料酒、生抽、糖、芝麻'),
('夫妻肺片', 'https://picsum.photos/seed/fuqi/400/300', '川菜', '30分钟到40分钟', '牛腱子、牛肚、牛舌、花生碎、花椒粉、辣椒油、生抽、醋、糖'),
('口水鸡', 'https://picsum.photos/seed/koushui/400/300', '川菜', '大概20分钟', '鸡腿、花生碎、花椒、辣椒油、生抽、醋、糖、葱姜蒜'),

-- 粤菜
('白切鸡', 'https://picsum.photos/seed/baiqie/400/300', '粤菜', '大概30分钟', '三黄鸡、姜、葱、盐、料酒、香油'),
('糖醋排骨', 'https://picsum.photos/seed/tangcu/400/300', '粤菜', '25分钟到30分钟', '排骨、白糖、醋、生抽、料酒、姜片、葱'),
('清蒸鲈鱼', 'https://picsum.photos/seed/qingzheng/400/300', '粤菜', '大概15分钟', '鲈鱼、姜、葱、料酒、蒸鱼豉油、热油'),
('叉烧', 'https://picsum.photos/seed/chashao/400/300', '粤菜', '40分钟到50分钟', '梅花肉、叉烧酱、蜂蜜、生抽、料酒、姜、蒜'),
('烧鹅', 'https://picsum.photos/seed/shaoe/400/300', '粤菜', '50分钟到60分钟', '鹅、五香粉、盐、糖、生抽、料酒、麦芽糖'),
('干炒牛河', 'https://picsum.photos/seed/niuhe/400/300', '粤菜', '大概10分钟', '河粉、牛肉、芽菜、葱、生抽、老抽、油'),
('肠粉', 'https://picsum.photos/seed/changfen/400/300', '粤菜', '大概20分钟', '粘米粉、澄面、水、油、生抽、虾/牛肉、生菜'),
('煲仔饭', 'https://picsum.photos/seed/baozai/400/300', '粤菜', '30分钟到40分钟', '大米、腊肠、腊肉、鸡蛋、菜心、生抽、香油'),

-- 湘菜
('剁椒鱼头', 'https://picsum.photos/seed/duojiao/400/300', '湘菜', '大概20分钟', '胖头鱼、剁椒、姜、葱、料酒、蒸鱼豉油'),
('辣椒炒肉', 'https://picsum.photos/seed/lajiao/400/300', '湘菜', '大概15分钟', '猪肉、青红辣椒、蒜、豆豉、生抽、料酒'),
('小炒黄牛肉', 'https://picsum.photos/seed/huangniu/400/300', '湘菜', '大概15分钟', '牛肉、青红辣椒、姜、蒜、生抽、料酒、淀粉'),
('毛氏红烧肉', 'https://picsum.photos/seed/hongshao/400/300', '湘菜', '40分钟到50分钟', '五花肉、冰糖、生抽、老抽、料酒、姜、葱、八角'),
('农家小炒肉', 'https://picsum.photos/seed/nongjia/400/300', '湘菜', '大概15分钟', '五花肉、青辣椒、蒜、豆豉、生抽、盐'),
('湘西外婆菜', 'https://picsum.photos/seed/waipo/400/300', '湘菜', '大概20分钟', '梅干菜、五花肉、辣椒、蒜、生抽、糖'),

-- 鲁菜
('糖醋鲤鱼', 'https://picsum.photos/seed/liyu/400/300', '鲁菜', '25分钟到30分钟', '鲤鱼、白糖、醋、番茄酱、生抽、料酒、淀粉'),
('九转大肠', 'https://picsum.photos/seed/dachang/400/300', '鲁菜', '50分钟到60分钟', '猪大肠、白糖、醋、料酒、生抽、葱姜蒜、香料'),
('葱烧海参', 'https://picsum.photos/seed/haishen/400/300', '鲁菜', '40分钟到50分钟', '海参、大葱、高汤、生抽、老抽、料酒、淀粉'),
('锅塌豆腐', 'https://picsum.photos/seed/guota/400/300', '鲁菜', '大概20分钟', '豆腐、鸡蛋、面粉、葱姜、高汤、盐、生抽'),

-- 苏菜
('松鼠桂鱼', 'https://picsum.photos/seed/guiyu/400/300', '苏菜', '30分钟到40分钟', '桂鱼、番茄酱、白糖、醋、料酒、淀粉、松子'),
('盐水鸭', 'https://picsum.photos/seed/yanshui/400/300', '苏菜', '40分钟到50分钟', '鸭子、盐、八角、花椒、姜、葱、料酒'),
('无锡排骨', 'https://picsum.photos/seed/wuxi/400/300', '苏菜', '40分钟到50分钟', '排骨、白糖、生抽、老抽、料酒、葱姜、八角'),
('大煮干丝', 'https://picsum.photos/seed/gansi/400/300', '苏菜', '大概25分钟', '豆腐干、火腿、虾仁、鸡丝、高汤、盐、白胡椒粉'),

-- 浙菜
('东坡肉', 'https://picsum.photos/seed/dongpo/400/300', '浙菜', '50分钟到60分钟', '五花肉、黄酒、生抽、老抽、冰糖、姜、葱'),
('西湖醋鱼', 'https://picsum.photos/seed/xihu/400/300', '浙菜', '大概20分钟', '草鱼、白糖、醋、生抽、料酒、姜、淀粉'),
('龙井虾仁', 'https://picsum.photos/seed/longjing/400/300', '浙菜', '大概15分钟', '虾仁、龙井茶叶、鸡蛋清、淀粉、盐、料酒'),
('叫化鸡', 'https://picsum.photos/seed/jiaohua/400/300', '浙菜', '60分钟到80分钟', '鸡、荷叶、泥土、葱姜、料酒、盐、八角'),

-- 闽菜
('佛跳墙', 'https://picsum.photos/seed/fotiao/400/300', '闽菜', '80分钟到100分钟', '海参、鲍鱼、鱼翅、瑶柱、花菇、鸡、鸭、猪蹄、高汤'),
('荔枝肉', 'https://picsum.photos/seed/lizhi/400/300', '闽菜', '大概20分钟', '猪里脊、荸荠、番茄酱、白糖、醋、淀粉、鸡蛋'),
('醉排骨', 'https://picsum.photos/seed/zui/400/300', '闽菜', '大概25分钟', '排骨、料酒、生抽、白糖、醋、葱姜蒜'),

-- 徽菜
('臭鳜鱼', 'https://picsum.photos/seed/chougui/400/300', '徽菜', '30分钟到40分钟', '鳜鱼、五花肉、笋、干辣椒、姜、蒜、料酒、生抽'),
('毛豆腐', 'https://picsum.photos/seed/mao/400/300', '徽菜', '大概15分钟', '毛豆腐、辣椒酱、盐、油、葱花'),
('问政山笋', 'https://picsum.photos/seed/wenzheng/400/300', '徽菜', '大概20分钟', '山笋、五花肉、干辣椒、姜、蒜、生抽、盐'),

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

-- 素菜
('麻婆豆腐', 'https://picsum.photos/seed/mapotofu/400/300', '素菜', '大概10分钟', '豆腐、豆瓣酱、花椒粉、葱姜蒜、生抽、淀粉'),
('干煸四季豆', 'https://picsum.photos/seed/sijidou/400/300', '素菜', '大概15分钟', '四季豆、干辣椒、花椒、蒜、盐、生抽'),
('素炒三丝', 'https://picsum.photos/seed/sansi/400/300', '素菜', '大概10分钟', '土豆、胡萝卜、青椒、盐、醋、油'),
('清炒时蔬', 'https://picsum.photos/seed/shishu/400/300', '素菜', '大概8分钟', '时令蔬菜、蒜、盐、油、鸡精'),
('家常豆腐', 'https://picsum.photos/seed/jiachangdoufu/400/300', '素菜', '大概15分钟', '豆腐、木耳、青椒、胡萝卜、豆瓣酱、生抽、淀粉'),

-- 汤类
('西红柿蛋汤', 'https://picsum.photos/seed/dantang/400/300', '汤类', '大概10分钟', '西红柿、鸡蛋、葱、盐、香油、水'),
('紫菜蛋花汤', 'https://picsum.photos/seed/zicai/400/300', '汤类', '大概10分钟', '紫菜、鸡蛋、虾皮、葱、盐、香油'),
('排骨莲藕汤', 'https://picsum.photos/seed/liang/400/300', '汤类', '60分钟到80分钟', '排骨、莲藕、姜、盐、料酒'),
('鸡汤', 'https://picsum.photos/seed/jitang/400/300', '汤类', '50分钟到60分钟', '鸡、姜、葱、盐、料酒、红枣、枸杞'),
('鲫鱼豆腐汤', 'https://picsum.photos/seed/jiyu/400/300', '汤类', '大概25分钟', '鲫鱼、豆腐、姜、葱、盐、料酒'),

-- 小吃
('煎饺', 'https://picsum.photos/seed/jianjiao/400/300', '小吃', '大概15分钟', '饺子、油、水'),
('葱油饼', 'https://picsum.photos/seed/congyou/400/300', '小吃', '大概20分钟', '面粉、葱、油、盐、水'),
('蛋炒饭', 'https://picsum.photos/seed/danchaofan/400/300', '小吃', '大概10分钟', '米饭、鸡蛋、葱、盐、油、火腿/虾仁'),
('春卷', 'https://picsum.photos/seed/chunjuan/400/300', '小吃', '大概25分钟', '春卷皮、韭菜、猪肉、豆芽、盐、生抽、油'),
('煎堆', 'https://picsum.photos/seed/jiandui/400/300', '小吃', '大概20分钟', '糯米粉、白糖、芝麻、油');

-- 插入示例教程(直接关联到菜谱)
INSERT INTO `cook_tutorial` (`dish_id`, `title`, `type`, `url`, `sort_order`) VALUES
                                                                                  (1, '家常做法', 'video', 'https://example.com/video1', 1),
                                                                                  (1, '美食教程', 'link', 'https://example.com/link1', 2),
                                                                                  (2, '正宗做法', 'video', 'https://example.com/video2', 1);