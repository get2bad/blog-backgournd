/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50527
Source Host           : localhost:3306
Source Database       : blog

Target Server Type    : MYSQL
Target Server Version : 50527
File Encoding         : 65001

Date: 2020-05-07 11:58:02
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for artical
-- ----------------------------
DROP TABLE IF EXISTS `artical`;
CREATE TABLE `artical` (
  `artical_id` int(11) NOT NULL AUTO_INCREMENT,
  `category_id` int(11) NOT NULL DEFAULT '1',
  `status` int(11) DEFAULT '0',
  `post_time` date DEFAULT NULL,
  `view_count` int(11) DEFAULT '0',
  `user_id` int(11) DEFAULT '1',
  `artical_title` varchar(255) DEFAULT NULL,
  `artical_introduce` varchar(255) DEFAULT NULL,
  `pic_introduce_img_url` varchar(255) DEFAULT NULL,
  `artical_content` longtext,
  `is_deny_comment` int(11) DEFAULT '0',
  `is_lock` int(11) DEFAULT '0',
  `is_submit_top` int(11) DEFAULT '0',
  `tag` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`artical_id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of artical
-- ----------------------------
INSERT INTO `artical` VALUES ('3', '4', '1', '2020-03-29', '24', '0', '博客测试标题', '123', 'http://image.tinx.top/2020/3/29/articleTitle/environment.jpg', '```java\nSystem.out.println(\'hello world\')\n```\n以前觉得，谈恋爱，一定要找一个百分百对自己好的人。体贴、温柔的人真的很讨喜呀，他愿意包容我的小脾气，接纳我的小情绪，分享我的不满和委屈，在我被悲伤和失落包围的时候，给我恰到好处的温柔和爱意。这样的他会让我觉得，他是真的很爱很爱我可现实是，对很多女孩子来说，想要找到一个百分百的人，太难了，换句话讲，无限包容和忍让，或许只是爱情的其中一种样子我想，还有一种爱情，可能听起来不够甜蜜，但事实上，却应该是很多女孩子期待的恋爱真实状态：遇到一个愿意好好陪你吵架的男孩好友Andy就是一个性格外向的女孩，她属于那种有什么话一定要说出来，绝对不会憋在心里半句谈恋爱之后，Andy风风火火的脾气没有任何改变，可恰好她的男朋友也是一个直来直去的人，时间久了，我们都很好奇他们两个人该如何相处下去有次我和Andy一起加班到很晚，Andy给男友发微信，希望他可以来接我们，没想到过了半小时也没等到回复Andy有点想发火，但还是忍着拨通了男友的电话“我刚刚没听到，怎么啦？大概是在气头上，Andy不由分说地把男生骂了一顿，也许是为了发泄吧，语气十分很强烈让我吃惊地是，男生没有道歉或者沉默，而是用同样分贝的声音和Andy“吵”了起来：“喂，我也很累呀，给点面子好不好？再说了，你这么大个人不会打车吗，真是的……然后，他们两个人你一言我一语，在电话里展开了辩论，足足持续了二十分钟但挂掉电话，Andy并没有表现出生气的样子结果一会儿，男友把车开到了公司楼下，他朝Andy作出一副恭恭敬敬的手势：“欢迎老婆大人上车，我们打道回府。我和Andy都被逗笑了后来Andy告诉我，吵架其实他们两个人的常态，因为都是脾气火爆的人，所以很多事情根本憋不住，不如干脆把不满说出来，也好过彼此隐瞒“我觉得，吵架也是检验爱情的标准之一，如果他连吵架都懒得跟你吵，两个人的隔阂只会越来越深，时间久了，就再也看不清对方了。深以为然，一直觉得，两个人在一起，就是一个不断暴露自我的过程，双方都会有大大小小的缺点，在吵架的过程中，如果可以把心中的不痛快发泄出来，才能真正看出对方的态度如果确实不能继续相处，三观不合，好聚好散也好过藕断丝连的拖着最怕的不是吵到天翻地覆，而是一味地退让隐忍，或者干脆使用冷暴力知乎上有个答主讲过自己的故事：“和男朋友分手，只是因为他没有好好听我发的语音。我的朋友很多，因为和他们在一起的时候，我总是把委屈和难过憋在心里，因为我觉得这些要讲给最重要的问题，比如家人，闺蜜，恋人可能女孩子觉得，给对方发很长很长一段话，是因为对方在自己心里很重要。但其实对于男生来说，这份重要性往往等同于矫情那天我被老板误会，心里特别委屈，就给他发语音倾诉了自己的不满，结果我等了整整一下午，直到晚上，他才发来一句“嗯”那一刻，我整颗心都是死的也许是心里的委屈还没有消化掉，我打电话过去准备和他大吵一架，没想到，电话接通之后，我自己一个人说得口干舌燥，他只说了一句话：“说完了吗？我还要去开会。那一刻我才清楚的认识到，自己在他心里的地位有多低也许是爱得不够吧，也许是懒得辩解，但我真的很讨厌冷暴力，好像只有你一个人在上演独角戏，对方只是台下的一名观众，事不关己，高高挂起，那种感觉太让人失望了。是啊，爱情里最可怕的不是争吵，而是吵不起来对于女生来说，我不是非要争个谁对谁错，只是问题得不到解决，双方心里都会留下一颗毒瘤，久而久之，总会迎来毒发身亡的那天爱情幸福的秘诀用八个字就可以概括：好好吵架，主动认输她的男朋友是一个木讷寡言的理工男，不会说好人的情话，也不会买漂亮的节日礼物，但是他们结婚好几年，感情一直特别稳定原因是，两个人婚后也会有大大小小的吵架，他们的三观也不是完完全全契合，但是问题出现的时候，总会有人主动解决有一次，他们因为买车的问题产生了分歧，两个人在银行大声争执了几句后，她生气地撇下丈夫回家了，没吃晚饭就钻进卧室倒头大睡第二天早上起床的时候，她闻到了一股早餐的香气，胃也开始咕咕叫，但是一想起昨天的争吵，又觉得拉不下面子正在她犹豫要不要主动说话的时候，突然看到床头柜上放了一张纸条，上面写了两行字第一行：亲爱的老婆，昨天是我不好，向你道歉，记得趁热吃早餐第二行：首相加末项的和乘以项数除以二她突然大笑起来笑了，因为第二句话的意思是：求和不得不说，这样委婉有趣的道歉方式，足以显示出一个人的情商，既好过口诛笔伐的争吵，也胜过互不理睬的冷暴力说实话，我一点不羡慕那些言听计从的爱情，也不羡慕那种百分百包容的恋爱，恋爱应该是一件很丰富的事儿，倘若只有柴米油盐酱醋茶的琐碎，那种恋爱也太无趣了就像之前还有个读者给我留言说，北哥，我真的好想和男朋友吵一架啊，他什么都让着我，包容我，体谅我，可在我犯错的时候，没有人会指出我的错误，在我颓废的时候，没有人告诉我应该努力，我好害怕有一天，自己变成一个废人，这样的爱情让我觉得十分没意思是啊，我个人觉得，爱情里只会使用冷暴力的人，其实是懦弱的表现，因为问题只会越积越多，隔阂只会越积越深，日积月累，两个人的爱情也会被消磨得只剩疲惫。\n', '0', '0', '0', '3,9');
INSERT INTO `artical` VALUES ('5', '1', '1', '2020-03-31', '21', '0', 'Java 天下第一！', '不多说了，牛逼就完事了！', 'http://image.tinx.top/2020/3/31/articleTitle/sea.jpg', '# java天下第一你不知道？\n---\n~~~ php\n你怕是傻屌哦！\n', '0', '0', '0', '1,9');
INSERT INTO `artical` VALUES ('6', '4', '1', '2020-03-31', '66', '0', 'Blog测试标题1', '12312312321321312', 'http://image.tinx.top/2020/3/31/articleTitle/water.jpg', '|1|2|3|\n|-|-|-|\n|测试1|测试2|测试3|\n\n', '0', '0', '0', '3,9');
INSERT INTO `artical` VALUES ('7', '7', '1', '2020-04-09', '0', '0', '博客测试文章', '博客测试文章', 'http://image.tinx.top/2020/4/10/articleTitle/sun.jpg', '# 大家好，我是Wills\n---\n很高兴和大家做朋友\n---\n> 很高兴和大家做朋友哦~！', '0', '0', '0', '9');
INSERT INTO `artical` VALUES ('8', '4', '1', '2020-04-10', '0', '0', '博客测试1', '1231231231', 'http://image.tinx.top/2020/4/11/articleTitle/tree&water.jpg', '# 你好吗？\n---\n我叫大王帅，很高兴见到大家哈~！能和大家做朋友是我的荣幸！good!', '0', '0', '0', '3');

-- ----------------------------
-- Table structure for base
-- ----------------------------
DROP TABLE IF EXISTS `base`;
CREATE TABLE `base` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `base_name` varchar(255) NOT NULL,
  `url` varchar(255) DEFAULT NULL,
  `icon` varchar(255) NOT NULL,
  `parent_id` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8 COMMENT='这是后台的功能菜单';

-- ----------------------------
-- Records of base
-- ----------------------------
INSERT INTO `base` VALUES ('1', '后台首页', '/base', 'el-icon-truck', '0');
INSERT INTO `base` VALUES ('2', '文章管理', null, 'el-icon-document', '0');
INSERT INTO `base` VALUES ('3', '系统管理', null, 'el-icon-setting', '0');
INSERT INTO `base` VALUES ('4', '个人设置', null, 'el-icon-user', '0');
INSERT INTO `base` VALUES ('5', '其他管理', null, 'el-icon-refresh-right', '0');
INSERT INTO `base` VALUES ('6', '新建文章', '/articleWrite', 'el-icon-edit', '2');
INSERT INTO `base` VALUES ('7', '文章管理', '/articleManage', 'el-icon-folder-opened', '2');
INSERT INTO `base` VALUES ('9', '用户管理', '/userManage', 'el-icon-user', '3');
INSERT INTO `base` VALUES ('10', '角色管理', '/roleManage', 'el-icon-pie-chart', '3');
INSERT INTO `base` VALUES ('11', '权限查看', '/authView', 'el-icon-view', '3');
INSERT INTO `base` VALUES ('12', '附件管理', '/fileManage', 'el-icon-folder', '3');
INSERT INTO `base` VALUES ('13', '网站设置', '/websiteManage', 'el-icon-setting', '3');
INSERT INTO `base` VALUES ('14', '操作日志', '/log', 'el-icon-date', '3');
INSERT INTO `base` VALUES ('15', '信息修改', '/changePwd', 'el-icon-lock', '4');
INSERT INTO `base` VALUES ('16', '页面管理', '/pageManage', 'el-icon-s-grid', '5');
INSERT INTO `base` VALUES ('17', '评论管理', '/commentManage', 'el-icon-s-check', '5');

-- ----------------------------
-- Table structure for category
-- ----------------------------
DROP TABLE IF EXISTS `category`;
CREATE TABLE `category` (
  `category_id` int(11) NOT NULL AUTO_INCREMENT,
  `category_name` varchar(255) NOT NULL,
  `category_url` varchar(255) NOT NULL,
  `icon` varchar(255) NOT NULL,
  PRIMARY KEY (`category_id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of category
-- ----------------------------
INSERT INTO `category` VALUES ('1', '首页', 'main', 'el-icon-s-home');
INSERT INTO `category` VALUES ('2', 'Java', 'Java', 'el-icon-user-solid');
INSERT INTO `category` VALUES ('3', 'Node', 'Node', 'el-icon-s-flag');
INSERT INTO `category` VALUES ('4', 'Python', 'Python', 'el-icon-star-on');
INSERT INTO `category` VALUES ('5', 'Vue', 'Vue', 'el-icon-s-promotion');
INSERT INTO `category` VALUES ('6', '心情', 'Emotion', 'el-icon-hot-water');
INSERT INTO `category` VALUES ('7', '生活', 'Life', 'el-icon-s-shop');
INSERT INTO `category` VALUES ('8', '旅行', 'Travel', 'el-icon-camera-solid');

-- ----------------------------
-- Table structure for comment
-- ----------------------------
DROP TABLE IF EXISTS `comment`;
CREATE TABLE `comment` (
  `comment_id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL,
  `comment_content` varchar(255) NOT NULL,
  `post_time` datetime NOT NULL,
  `article_id` int(11) NOT NULL DEFAULT '0',
  `post_ip` varchar(255) DEFAULT '',
  `status` int(255) DEFAULT '1',
  `user_name` varchar(255) DEFAULT '',
  PRIMARY KEY (`comment_id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of comment
-- ----------------------------
INSERT INTO `comment` VALUES ('4', '0', '博主的博客做的太棒了！', '2020-04-10 12:03:45', '3', '123.130.171.211', '1', 'Wills');
INSERT INTO `comment` VALUES ('5', '0', '我是博主！', '2020-04-10 12:15:44', '3', '123.130.171.211', '1', 'Tinx');
INSERT INTO `comment` VALUES ('6', '0', '很棒哦', '2020-04-10 12:16:35', '3', '123.130.171.211', '1', 'Bob');

-- ----------------------------
-- Table structure for config
-- ----------------------------
DROP TABLE IF EXISTS `config`;
CREATE TABLE `config` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `config_name` varchar(255) NOT NULL,
  `config_content` varchar(255) DEFAULT NULL,
  `config_type` varchar(255) NOT NULL DEFAULT 'text',
  `config_if_select` varchar(255) DEFAULT NULL,
  `config_if_multify` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of config
-- ----------------------------

-- ----------------------------
-- Table structure for construct
-- ----------------------------
DROP TABLE IF EXISTS `construct`;
CREATE TABLE `construct` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `construct_name` varchar(255) NOT NULL,
  `construct_url` varchar(255) NOT NULL,
  `level` int(11) DEFAULT '0',
  `parent_id` int(11) DEFAULT '0',
  `description` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of construct
-- ----------------------------
INSERT INTO `construct` VALUES ('1', 'head', '/', '0', '0', '头部导航栏');
INSERT INTO `construct` VALUES ('2', 'carousel', '/', '0', '0', '轮播图');
INSERT INTO `construct` VALUES ('3', 'left', '/', '0', '0', '文章展示页面');
INSERT INTO `construct` VALUES ('4', 'right', '/', '0', '0', '小功能菜单');
INSERT INTO `construct` VALUES ('5', 'footer', '/', '0', '0', '底部版权信息');
INSERT INTO `construct` VALUES ('6', 'welcome', '/welcome', '1', '4', '欢迎信息');
INSERT INTO `construct` VALUES ('7', 'time', '/time', '1', '4', '时间展示');
INSERT INTO `construct` VALUES ('8', 'weather', '/weather', '1', '4', '天气信息');
INSERT INTO `construct` VALUES ('9', 'music', '/music', '1', '4', '音乐播放器');
INSERT INTO `construct` VALUES ('10', 'category', '/category', '1', '4', '文章分类');
INSERT INTO `construct` VALUES ('11', 'link', '/link', '1', '5', '友情链接');

-- ----------------------------
-- Table structure for file
-- ----------------------------
DROP TABLE IF EXISTS `file`;
CREATE TABLE `file` (
  `file_id` int(11) NOT NULL AUTO_INCREMENT,
  `file_path` varchar(255) NOT NULL,
  `file_type` varchar(255) NOT NULL,
  `file_description` varchar(255) DEFAULT NULL,
  `file_key` varchar(255) NOT NULL,
  PRIMARY KEY (`file_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of file
-- ----------------------------
INSERT INTO `file` VALUES ('1', 'http://image.tinx.top/2020/3/31/articleTitle/sea.jpg', 'image/jpeg', '2020/3/31/的文章简略图', '2020/3/31/articleTitle/sea.jpg');
INSERT INTO `file` VALUES ('2', 'http://image.tinx.top/2020/3/31/articleTitle/water.jpg', 'image/jpeg', '2020/3/31/的文章简略图', '2020/3/31/articleTitle/water.jpg');
INSERT INTO `file` VALUES ('3', 'http://image.tinx.top/2020/4/10/articleTitle/sun.jpg', 'image/jpeg', '2020/4/10/的文章简略图', '2020/4/10/articleTitle/sun.jpg');
INSERT INTO `file` VALUES ('4', 'http://image.tinx.top/2020/4/11/articleTitle/tree&water.jpg', 'image/jpeg', '2020/4/11/的文章简略图', '2020/4/11/articleTitle/tree&water.jpg');

-- ----------------------------
-- Table structure for menu
-- ----------------------------
DROP TABLE IF EXISTS `menu`;
CREATE TABLE `menu` (
  `menu_id` int(11) NOT NULL AUTO_INCREMENT,
  `menu_name` varchar(255) NOT NULL,
  `menu_url` varchar(255) DEFAULT NULL,
  `menu_parent` varchar(255) NOT NULL DEFAULT '0',
  PRIMARY KEY (`menu_id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of menu
-- ----------------------------
INSERT INTO `menu` VALUES ('1', '主页', '/', '0');
INSERT INTO `menu` VALUES ('2', '文章分类', '/', '0');
INSERT INTO `menu` VALUES ('3', 'JAVA', '/search/Java', '2');
INSERT INTO `menu` VALUES ('4', 'Cpp', '/search/Cpp', '2');
INSERT INTO `menu` VALUES ('5', 'Python', '/search/Python', '2');
INSERT INTO `menu` VALUES ('6', 'Golang', '/search/Go', '2');
INSERT INTO `menu` VALUES ('7', 'Lua', '/search/Lua', '2');
INSERT INTO `menu` VALUES ('8', 'C', '/search/C', '2');
INSERT INTO `menu` VALUES ('9', '杂谈', '/search/other', '0');
INSERT INTO `menu` VALUES ('10', '生活', '/other/life', '9');
INSERT INTO `menu` VALUES ('11', '旅行', '/other/travel', '9');
INSERT INTO `menu` VALUES ('12', '心情', '/other/emotion', '9');
INSERT INTO `menu` VALUES ('13', '学习', '/other/study', '9');
INSERT INTO `menu` VALUES ('14', '留言板', '/comments', '0');

-- ----------------------------
-- Table structure for menu_permission
-- ----------------------------
DROP TABLE IF EXISTS `menu_permission`;
CREATE TABLE `menu_permission` (
  `id` int(11) NOT NULL,
  `menu_name` varchar(255) NOT NULL,
  `permission_id` int(11) NOT NULL,
  `description` longtext,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of menu_permission
-- ----------------------------

-- ----------------------------
-- Table structure for note
-- ----------------------------
DROP TABLE IF EXISTS `note`;
CREATE TABLE `note` (
  `note_id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL,
  `note_content` varchar(255) NOT NULL,
  `post_ip` varchar(255) NOT NULL,
  `note_post_time` datetime NOT NULL,
  PRIMARY KEY (`note_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of note
-- ----------------------------

-- ----------------------------
-- Table structure for permission
-- ----------------------------
DROP TABLE IF EXISTS `permission`;
CREATE TABLE `permission` (
  `permission_id` int(11) NOT NULL AUTO_INCREMENT,
  `permission_name` varchar(255) DEFAULT NULL,
  `permission_description` varchar(255) DEFAULT NULL,
  `level` int(11) NOT NULL DEFAULT '0',
  `parent_id` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`permission_id`)
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of permission
-- ----------------------------
INSERT INTO `permission` VALUES ('1', '文章管理', '管理文章-写/审核/删除/暂停展示等', '0', '0');
INSERT INTO `permission` VALUES ('2', '评论管理', '管理评论-审核/删除等', '0', '0');
INSERT INTO `permission` VALUES ('3', '文件管理', '管理文件-上传/删除/下载等', '0', '0');
INSERT INTO `permission` VALUES ('4', '用户管理', '管理用户', '0', '0');
INSERT INTO `permission` VALUES ('5', '缓存管理', '管理缓存', '0', '0');
INSERT INTO `permission` VALUES ('6', '图表分析', '图表分析管理', '0', '0');
INSERT INTO `permission` VALUES ('7', '文章写作', '书写文章', '1', '1');
INSERT INTO `permission` VALUES ('8', '文章管理', '增删改查文章', '1', '1');
INSERT INTO `permission` VALUES ('9', '管理评论', '管理评论', '1', '2');
INSERT INTO `permission` VALUES ('10', '上传信息配置', '配置七牛云', '1', '3');
INSERT INTO `permission` VALUES ('11', '删除上传的文件', '删除七牛云空间内文件', '1', '3');
INSERT INTO `permission` VALUES ('12', '管理用户', '用户的基本管理', '1', '4');
INSERT INTO `permission` VALUES ('13', '文章缓存管理', '管理文章缓存', '1', '5');
INSERT INTO `permission` VALUES ('14', 'Redis缓存管理', 'Redis缓存管理', '1', '5');
INSERT INTO `permission` VALUES ('15', 'ElasticSearch缓存管理', 'ElasticSearch缓存管理', '1', '5');
INSERT INTO `permission` VALUES ('16', '文章图表', '文章图表', '1', '6');
INSERT INTO `permission` VALUES ('17', '评论图表', '评论图表', '1', '6');
INSERT INTO `permission` VALUES ('18', '文件图表', '文件图表', '1', '6');
INSERT INTO `permission` VALUES ('19', '用户图表', '用户图表', '1', '6');
INSERT INTO `permission` VALUES ('20', '新增上传文章图表', '新增上传文章图表', '2', '16');
INSERT INTO `permission` VALUES ('21', '文章分类图表', '文章分类图表', '2', '16');
INSERT INTO `permission` VALUES ('22', '评论分析图表', '评论分析图表', '2', '17');
INSERT INTO `permission` VALUES ('23', '文件分析图表', '文件分析图表', '2', '18');
INSERT INTO `permission` VALUES ('24', '用户分析图表', '用户分析图表', '2', '19');

-- ----------------------------
-- Table structure for permission_role
-- ----------------------------
DROP TABLE IF EXISTS `permission_role`;
CREATE TABLE `permission_role` (
  `permission_id` int(11) NOT NULL,
  `role_id` int(11) NOT NULL,
  PRIMARY KEY (`permission_id`,`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of permission_role
-- ----------------------------
INSERT INTO `permission_role` VALUES ('1', '1');
INSERT INTO `permission_role` VALUES ('1', '2');
INSERT INTO `permission_role` VALUES ('2', '1');
INSERT INTO `permission_role` VALUES ('3', '1');
INSERT INTO `permission_role` VALUES ('4', '1');
INSERT INTO `permission_role` VALUES ('4', '2');
INSERT INTO `permission_role` VALUES ('5', '1');
INSERT INTO `permission_role` VALUES ('5', '8');
INSERT INTO `permission_role` VALUES ('6', '1');
INSERT INTO `permission_role` VALUES ('7', '1');
INSERT INTO `permission_role` VALUES ('7', '2');
INSERT INTO `permission_role` VALUES ('8', '1');
INSERT INTO `permission_role` VALUES ('8', '2');
INSERT INTO `permission_role` VALUES ('9', '1');
INSERT INTO `permission_role` VALUES ('10', '1');
INSERT INTO `permission_role` VALUES ('11', '1');
INSERT INTO `permission_role` VALUES ('12', '1');
INSERT INTO `permission_role` VALUES ('12', '2');
INSERT INTO `permission_role` VALUES ('13', '1');
INSERT INTO `permission_role` VALUES ('13', '8');
INSERT INTO `permission_role` VALUES ('14', '1');
INSERT INTO `permission_role` VALUES ('14', '8');
INSERT INTO `permission_role` VALUES ('15', '1');
INSERT INTO `permission_role` VALUES ('15', '8');
INSERT INTO `permission_role` VALUES ('16', '1');
INSERT INTO `permission_role` VALUES ('17', '1');
INSERT INTO `permission_role` VALUES ('18', '1');
INSERT INTO `permission_role` VALUES ('19', '1');
INSERT INTO `permission_role` VALUES ('20', '1');
INSERT INTO `permission_role` VALUES ('21', '1');
INSERT INTO `permission_role` VALUES ('22', '1');
INSERT INTO `permission_role` VALUES ('23', '1');
INSERT INTO `permission_role` VALUES ('24', '1');

-- ----------------------------
-- Table structure for role
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role` (
  `role_id` int(11) NOT NULL AUTO_INCREMENT,
  `role_name` varchar(255) NOT NULL,
  `role_description` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`role_id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of role
-- ----------------------------
INSERT INTO `role` VALUES ('1', '系统管理员', '管理整个系统的运行');
INSERT INTO `role` VALUES ('2', '文章审核员', '审核文章的发布');
INSERT INTO `role` VALUES ('3', '评论审查员', '审查劣质评论');
INSERT INTO `role` VALUES ('4', '读者', '普通用户注册');
INSERT INTO `role` VALUES ('5', '游客', '游客只有普通的阅读文章权限');
INSERT INTO `role` VALUES ('8', '博客测试角色', '12312321');

-- ----------------------------
-- Table structure for role_user
-- ----------------------------
DROP TABLE IF EXISTS `role_user`;
CREATE TABLE `role_user` (
  `user_id` int(11) NOT NULL,
  `role_id` int(11) NOT NULL,
  PRIMARY KEY (`user_id`,`role_id`),
  KEY `role_id_foreign_key1` (`role_id`),
  CONSTRAINT `role_id_foreign_key1` FOREIGN KEY (`role_id`) REFERENCES `role` (`role_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `user_id_foreign_key1` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of role_user
-- ----------------------------
INSERT INTO `role_user` VALUES ('1', '1');
INSERT INTO `role_user` VALUES ('1', '2');
INSERT INTO `role_user` VALUES ('1', '3');
INSERT INTO `role_user` VALUES ('1', '4');
INSERT INTO `role_user` VALUES ('1', '5');

-- ----------------------------
-- Table structure for tag
-- ----------------------------
DROP TABLE IF EXISTS `tag`;
CREATE TABLE `tag` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `tag_name` varchar(255) NOT NULL,
  `tag_type` varchar(255) DEFAULT 'success',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tag
-- ----------------------------
INSERT INTO `tag` VALUES ('1', 'java', 'info');
INSERT INTO `tag` VALUES ('3', 'python', 'info');
INSERT INTO `tag` VALUES ('5', 'cpp', 'info');
INSERT INTO `tag` VALUES ('6', 'life', 'info');
INSERT INTO `tag` VALUES ('7', 'travel', 'info');
INSERT INTO `tag` VALUES ('8', 'emotion', 'info');
INSERT INTO `tag` VALUES ('9', '牛逼', 'info');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `user_id` int(11) NOT NULL AUTO_INCREMENT,
  `user_name` varchar(255) NOT NULL,
  `password` varchar(255) DEFAULT NULL,
  `sign_in_ip` varchar(255) DEFAULT NULL,
  `last_sign_time` datetime DEFAULT NULL,
  `active_id` varchar(255) DEFAULT NULL,
  `status` int(11) DEFAULT '0',
  PRIMARY KEY (`user_id`,`user_name`),
  UNIQUE KEY `userName_unique` (`user_name`) USING BTREE COMMENT 'userName 唯一不重复',
  KEY `user_id` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('1', 'wills', 'fc9ed5f8a39402fae2716c1509d05f27', '0.0.0.0', '2020-02-28 10:47:40', '123', '1');

-- ----------------------------
-- Table structure for user_info
-- ----------------------------
DROP TABLE IF EXISTS `user_info`;
CREATE TABLE `user_info` (
  `user_id` int(11) DEFAULT NULL,
  `sex` int(11) DEFAULT NULL,
  `age` int(11) DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `address` varchar(255) DEFAULT NULL,
  `note` varchar(255) DEFAULT NULL,
  KEY `user_id_foreign_key` (`user_id`),
  CONSTRAINT `user_id_foreign_key` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user_info
-- ----------------------------
INSERT INTO `user_info` VALUES ('1', '0', '23', '17607113011', 'loveing490@qq.com', '山东省烟台市', '我会是那个最重要的人！！');

-- ----------------------------
-- Table structure for websettings
-- ----------------------------
DROP TABLE IF EXISTS `websettings`;
CREATE TABLE `websettings` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `web_key` varchar(255) NOT NULL,
  `web_value` varchar(255) NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of websettings
-- ----------------------------
INSERT INTO `websettings` VALUES ('1', 'qiniuAK', 'QXQpli1NK8PTIag3RGfVDzeH6TClXOB1TN9O5Bza', '七牛云账户AK');
INSERT INTO `websettings` VALUES ('2', 'qiniuSK', 'f2fpCbdmJ7NTFkbCxHlksnJu_K2Iq8SnMU-TQpyY', '七牛云账户SK');
INSERT INTO `websettings` VALUES ('3', 'qiniuBucket', 'wills-blog', '七牛云Bucket');
INSERT INTO `websettings` VALUES ('4', 'qiniuUploadUrl', 'http://up.qiniup.com', '七牛云上传Url');
INSERT INTO `websettings` VALUES ('5', 'imageUrl', 'http://image.tinx.top/', '外网访问地址');
