/*
 Navicat Premium Data Transfer

 Source Server         : 本地数据库
 Source Server Type    : MySQL
 Source Server Version : 50710
 Source Host           : localhost:3306
 Source Schema         : lamp_defaults

 Target Server Type    : MySQL
 Target Server Version : 50710
 File Encoding         : 65001

 Date: 13/08/2021 10:12:33
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for c_datasource_config
-- ----------------------------
DROP TABLE IF EXISTS `c_datasource_config`;
CREATE TABLE `c_datasource_config`  (
  `id` bigint(20) NOT NULL COMMENT '主键ID',
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '名称',
  `username` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '账号',
  `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '密码',
  `url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '链接',
  `url_lan` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '内网链接',
  `driver_class_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '驱动',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `created_by` bigint(20) NULL DEFAULT NULL COMMENT '创建人',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
  `updated_by` bigint(20) NULL DEFAULT NULL COMMENT '修改人',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '数据源' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of c_datasource_config
-- ----------------------------
INSERT INTO `c_datasource_config` VALUES (1394923952341516288, '0000-登录获取平台信息-切勿删除', 'root', 'root', 'jdbc:mysql://localhost:3306/lamp_defaults?serverTimezone=CTT&characterEncoding=utf8&useUnicode=true&useSSL=false&autoReconnect=true&zeroDateTimeBehavior=convertToNull&allowMultiQueries=true', '', 'com.mysql.cj.jdbc.Driver', '2021-05-19 15:52:41', NULL, '2021-06-01 15:47:54', 3);
INSERT INTO `c_datasource_config` VALUES (1418411326190911488, 'SY_SX0001-远程数据源', 'root', 'root', 'jdbc:mysql://10.5.31.2:3306/hst_base_SY_SX0001?serverTimezone=CTT&characterEncoding=utf8&useUnicode=true&useSSL=false&autoReconnect=true&zeroDateTimeBehavior=convertToNull&allowMultiQueries=true', 'jdbc:mysql://10.5.31.2:3306/hst_base_SY_SX0001?serverTimezone=CTT&characterEncoding=utf8&useUnicode=true&useSSL=false&autoReconnect=true&zeroDateTimeBehavior=convertToNull&allowMultiQueries=true', 'com.mysql.cj.jdbc.Driver', '2021-07-23 11:23:08', 3, '2021-07-23 11:23:08', 3);
INSERT INTO `c_datasource_config` VALUES (1418411769004556288, 'SY_FW0002-远程数据源', 'root', 'root', 'jdbc:mysql://10.5.31.2:3306/hst_base_SY_FW0002?serverTimezone=CTT&characterEncoding=utf8&useUnicode=true&useSSL=false&autoReconnect=true&zeroDateTimeBehavior=convertToNull&allowMultiQueries=true', 'jdbc:mysql://10.5.31.2:3306/hst_base_SY_FW0002?serverTimezone=CTT&characterEncoding=utf8&useUnicode=true&useSSL=false&autoReconnect=true&zeroDateTimeBehavior=convertToNull&allowMultiQueries=true', 'com.mysql.cj.jdbc.Driver', '2021-07-23 11:24:53', 3, '2021-07-23 11:24:53', 3);
INSERT INTO `c_datasource_config` VALUES (1418412158399545344, 'SY_HB0003-远程数据源', 'root', 'abc123', 'jdbc:mysql://192.168.174.128:3306/hst_base_SY_HB0003?serverTimezone=CTT&characterEncoding=utf8&useUnicode=true&useSSL=false&autoReconnect=true&zeroDateTimeBehavior=convertToNull&allowMultiQueries=true', 'jdbc:mysql://192.168.174.128:3306/hst_base_SY_HB0003?serverTimezone=CTT&characterEncoding=utf8&useUnicode=true&useSSL=false&autoReconnect=true&zeroDateTimeBehavior=convertToNull&allowMultiQueries=true', 'com.mysql.cj.jdbc.Driver', '2021-07-23 11:26:26', 3, '2021-07-23 11:26:26', 3);
INSERT INTO `c_datasource_config` VALUES (1418414096931356672, 'SY_ERP01-本机数据源', 'root', 'root', 'jdbc:mysql://10.5.31.2:3306/hst_base_SY_ERP01?serverTimezone=CTT&characterEncoding=utf8&useUnicode=true&useSSL=false&autoReconnect=true&zeroDateTimeBehavior=convertToNull&allowMultiQueries=true', 'jdbc:mysql://10.5.31.2:3306/hst_base_SY_ERP01?serverTimezone=CTT&characterEncoding=utf8&useUnicode=true&useSSL=false&autoReconnect=true&zeroDateTimeBehavior=convertToNull&allowMultiQueries=true', 'com.mysql.cj.jdbc.Driver', '2021-07-23 11:34:08', 3, '2021-07-23 11:46:59', 3);
INSERT INTO `c_datasource_config` VALUES (1418414713934446592, 'SY_ERP02-Docker数据源', 'root', 'abc123', 'jdbc:mysql://192.168.174.128:3306/hst_base_SY_ERP02?serverTimezone=CTT&characterEncoding=utf8&useUnicode=true&useSSL=false&autoReconnect=true&zeroDateTimeBehavior=convertToNull&allowMultiQueries=true', 'jdbc:mysql://192.168.174.128:3306/hst_base_SY_ERP02?serverTimezone=CTT&characterEncoding=utf8&useUnicode=true&useSSL=false&autoReconnect=true&zeroDateTimeBehavior=convertToNull&allowMultiQueries=true', 'com.mysql.cj.jdbc.Driver', '2021-07-23 11:36:35', 3, '2021-07-23 11:39:49', 3);

-- ----------------------------
-- Table structure for c_tenant
-- ----------------------------
DROP TABLE IF EXISTS `c_tenant`;
CREATE TABLE `c_tenant`  (
  `id` bigint(20) NOT NULL COMMENT '主键ID',
  `code` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '企业编码',
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '企业名称',
  `type` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '类型 \n#{CREATE:创建;REGISTER:注册}',
  `connect_type` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '链接类型 \n#TenantConnectTypeEnum{LOCAL:本地;REMOTE:远程}',
  `status` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '状态 \n#{NORMAL:正常;WAIT_INIT:待初始化;FORBIDDEN:禁用;WAITING:待审核;REFUSE:拒绝;DELETE:已删除}',
  `tenant_type` char(2) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '租户类型 \n@InjectionField(api = DICTIONARY_ITEM_CLASS, method = DICTIONARY_ITEM_METHOD, dictType = DictionaryType.TENANT_TYPE) RemoteData<String, String>',
  `readonly_` bit(1) NULL DEFAULT b'0' COMMENT '内置',
  `duty_options` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '责任人选择器',
  `duty` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '责任人',
  `expiration_time` datetime(0) NULL DEFAULT NULL COMMENT '有效期 \n为空表示永久',
  `logo` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT 'logo地址',
  `describe_` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '企业简介',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `created_by` bigint(20) NULL DEFAULT NULL COMMENT '创建人',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
  `updated_by` bigint(20) NULL DEFAULT NULL COMMENT '修改人',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `uk_code`(`code`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '企业' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of c_tenant
-- ----------------------------
INSERT INTO `c_tenant` VALUES (1, '0000', 'SaaS云平台内置运营&超级租户', 'CREATE', 'LOCAL', 'NORMAL', '04', b'1', '[1399896885921054720, 1399897126057541632, 1399897885520166912, 1415192270981824512]', 'HST', NULL, NULL, '内置租户,用于测试租户系统所有功能, 用于管理整个系统.请勿删除', '2019-08-29 16:50:35', 1, '2021-06-01 16:50:35', 1);
INSERT INTO `c_tenant` VALUES (1418410128188637184, 'SY_SX0001', '沈阳生鲜0001', 'CREATE', 'REMOTE', 'NORMAL', '02', b'0', '[1399896885921054720, 1399897126057541632, 1399897885520166912, 1418106360523915264]', '周九', '2022-07-30 00:00:00', 'http://hst-dev-oss.oss-cn-beijing.aliyuncs.com/0000/2021/07/7a2be1eb-cf75-4b16-a740-95cfe7fb3077.jpg', '企业简介', '2021-07-23 11:18:22', 3, '2021-07-23 11:18:22', 3);
INSERT INTO `c_tenant` VALUES (1418410562462679040, 'SY_FW0002', '沈阳服务0002', 'CREATE', 'REMOTE', 'NORMAL', '01', b'0', '[1399896885921054720, 1399897126057541632, 1399898229113356288, 1418121093608112128]', '吴三', NULL, 'http://hst-dev-oss.oss-cn-beijing.aliyuncs.com/0000/2021/07/e8960ea4-e331-4512-9d18-2af9e07aebe0.jpg', '企业简介', '2021-07-23 11:20:06', 3, '2021-07-23 11:20:06', 3);
INSERT INTO `c_tenant` VALUES (1418410842344390656, 'SY_HB0003', '沈阳烘焙0003', 'CREATE', 'REMOTE', 'NORMAL', '03', b'0', '[1399896885921054720, 1399897126057541632, 1399898339138338816, 1418105834482696192]', '孙七', NULL, 'http://hst-dev-oss.oss-cn-beijing.aliyuncs.com/0000/2021/07/605f5e8d-e692-4a50-9a8a-0c2ce13f098b.jpg', '企业简介', '2021-07-23 11:21:12', 3, '2021-07-23 11:21:12', 3);
INSERT INTO `c_tenant` VALUES (1418413722145128448, 'SY_ERP02', '测试ERP02', 'CREATE', 'REMOTE', 'NORMAL', '01', b'0', '[1399896885921054720, 1399897126057541632, 1399898600351203328, 1418120918189735936]', '钱五', '2021-07-30 07:11:00', 'http://hst-dev-oss.oss-cn-beijing.aliyuncs.com/0000/2021/07/a3f61cb3-698f-4b64-8c8a-9947838b554f.jpg', '企业简介\n', '2021-07-23 11:32:39', 3, '2021-07-23 11:32:39', 3);
INSERT INTO `c_tenant` VALUES (1418418344406548480, 'SY_ERP01', '测试ERP01', 'CREATE', 'REMOTE', 'NORMAL', '01', b'0', '[1399896885921054720, 1399897126057541632, 1399897885520166912, 1418106360523915264]', '周九', NULL, '', '', '2021-07-23 11:51:01', 3, '2021-07-23 11:51:01', 3);

-- ----------------------------
-- Table structure for c_tenant_datasource_config
-- ----------------------------
DROP TABLE IF EXISTS `c_tenant_datasource_config`;
CREATE TABLE `c_tenant_datasource_config`  (
  `id` bigint(20) NOT NULL COMMENT 'ID',
  `tenant_id` bigint(20) NOT NULL COMMENT '租户id',
  `datasource_config_id` bigint(20) NOT NULL COMMENT '数据源id',
  `application` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '服务',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `created_by` bigint(20) NULL DEFAULT NULL COMMENT '创建人',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `uk_tenan_application`(`tenant_id`, `datasource_config_id`, `application`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '租户数据源关系' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of c_tenant_datasource_config
-- ----------------------------
INSERT INTO `c_tenant_datasource_config` VALUES (1418412472309645312, 1418410128188637184, 1418411326190911488, 'SY_SX0001-租户基础服务', '2021-07-23 11:27:41', 3);
INSERT INTO `c_tenant_datasource_config` VALUES (1418412801516371968, 1418410562462679040, 1418411769004556288, 'SY_FW0002-租户基础服务', '2021-07-23 11:28:59', 3);
INSERT INTO `c_tenant_datasource_config` VALUES (1418412833254670336, 1418410842344390656, 1418412158399545344, 'SY_HB0003-租户基础服务', '2021-07-23 11:29:07', 3);
INSERT INTO `c_tenant_datasource_config` VALUES (1418416636783755264, 1418413722145128448, 1418414713934446592, 'SY_ERP02-租户基础服务', '2021-07-23 11:44:14', 3);
INSERT INTO `c_tenant_datasource_config` VALUES (1418418481237327872, 1418418344406548480, 1418414096931356672, 'SY_ERP01-租户基础服务', '2021-07-23 11:51:34', 3);

-- ----------------------------
-- Table structure for database_detail
-- ----------------------------
DROP TABLE IF EXISTS `database_detail`;
CREATE TABLE `database_detail`  (
  `id` int(11) NULL DEFAULT NULL,
  `tenant_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `username` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `driver_class_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of database_detail
-- ----------------------------
INSERT INTO `database_detail` VALUES (1, 'SY_LJ00999', 'jdbc:mysql://127.0.0.1:3306/jsh_erp?serverTimezone=CTT&characterEncoding=utf8&useUnicode=true&useSSL=false&autoReconnect=true&zeroDateTimeBehavior=convertToNull&allowMultiQueries=true', 'root', 'root', 'com.mysql.cj.jdbc.Driver');
INSERT INTO `database_detail` VALUES (2, 'SY_FJ00123', 'jdbc:mysql://192.168.174.128:3306/jsh_erp1?serverTimezone=CTT&characterEncoding=utf8&useUnicode=true&useSSL=false&autoReconnect=true&zeroDateTimeBehavior=convertToNull&allowMultiQueries=true', 'root', 'abc123', 'com.mysql.cj.jdbc.Driver');

-- ----------------------------
-- Table structure for undo_log
-- ----------------------------
DROP TABLE IF EXISTS `undo_log`;
CREATE TABLE `undo_log`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'increment id',
  `branch_id` bigint(20) NOT NULL COMMENT 'branch transaction id',
  `xid` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT 'global transaction id',
  `context` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT 'undo_log context,such as serialization',
  `rollback_info` longblob NOT NULL COMMENT 'rollback info',
  `log_status` int(11) NOT NULL COMMENT '0:normal status,1:defense status',
  `log_created` datetime(6) NOT NULL COMMENT 'create datetime',
  `log_modified` datetime(6) NOT NULL COMMENT 'modify datetime',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `ux_undo_log`(`xid`, `branch_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = 'AT transaction mode undo table' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of undo_log
-- ----------------------------

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `tenant_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (1, 'jock', 'SY_LJ00999');
INSERT INTO `user` VALUES (2, 'petty', 'SY_FJ00123');
INSERT INTO `user` VALUES (3, 'jet', 'SY_FJ00123');

-- ----------------------------
-- Table structure for worker_node
-- ----------------------------
DROP TABLE IF EXISTS `worker_node`;
CREATE TABLE `worker_node`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'auto increment id',
  `host_name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '主机名',
  `port` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '端口',
  `type` int(11) NOT NULL COMMENT '节点类型: ACTUAL 或者 CONTAINER',
  `launch_date` date NOT NULL COMMENT '上线日期',
  `modified` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '修改时间',
  `created` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = 'DB WorkerID Assigner for UID Generator' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of worker_node
-- ----------------------------

-- ----------------------------
-- Table structure for xxl_job_group
-- ----------------------------
DROP TABLE IF EXISTS `xxl_job_group`;
CREATE TABLE `xxl_job_group`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `app_name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '执行器AppName',
  `title` varchar(12) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '执行器名称',
  `address_type` tinyint(4) NOT NULL DEFAULT 0 COMMENT '执行器地址类型：0=自动注册、1=手动录入',
  `address_list` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL COMMENT '执行器地址列表，多地址逗号分隔',
  `update_time` datetime(0) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of xxl_job_group
-- ----------------------------
INSERT INTO `xxl_job_group` VALUES (1, 'lamp-base-executor', '基础库执行器', 0, 'http://127.0.0.1:8777/', '2021-06-05 09:14:01');
INSERT INTO `xxl_job_group` VALUES (2, 'lamp-extend-executor', '扩展库执行器', 0, 'http://127.0.0.1:8775/', '2021-06-05 09:14:01');

-- ----------------------------
-- Table structure for xxl_job_info
-- ----------------------------
DROP TABLE IF EXISTS `xxl_job_info`;
CREATE TABLE `xxl_job_info`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `job_group` int(11) NOT NULL COMMENT '执行器主键ID',
  `job_desc` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `add_time` datetime(0) NULL DEFAULT NULL,
  `update_time` datetime(0) NULL DEFAULT NULL,
  `author` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '作者',
  `alarm_email` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '报警邮件',
  `schedule_type` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT 'NONE' COMMENT '调度类型',
  `schedule_conf` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '调度配置，值含义取决于调度类型',
  `misfire_strategy` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT 'DO_NOTHING' COMMENT '调度过期策略',
  `executor_route_strategy` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '执行器路由策略',
  `executor_handler` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '执行器任务handler',
  `executor_param` varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '执行器任务参数',
  `executor_block_strategy` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '阻塞处理策略',
  `executor_timeout` int(11) NOT NULL DEFAULT 0 COMMENT '任务执行超时时间，单位秒',
  `executor_fail_retry_count` int(11) NOT NULL DEFAULT 0 COMMENT '失败重试次数',
  `glue_type` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT 'GLUE类型',
  `glue_source` mediumtext CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL COMMENT 'GLUE源代码',
  `glue_remark` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT 'GLUE备注',
  `glue_updatetime` datetime(0) NULL DEFAULT NULL COMMENT 'GLUE更新时间',
  `child_jobid` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '子任务ID，多个逗号分隔',
  `trigger_status` tinyint(4) NOT NULL DEFAULT 0 COMMENT '调度状态：0-停止，1-运行',
  `trigger_last_time` bigint(13) NOT NULL DEFAULT 0 COMMENT '上次调度时间',
  `trigger_next_time` bigint(13) NOT NULL DEFAULT 0 COMMENT '下次调度时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of xxl_job_info
-- ----------------------------
INSERT INTO `xxl_job_info` VALUES (1, 1, '测试任务1', '2018-11-03 22:21:31', '2021-06-01 13:04:42', 'XXL', '', 'CRON', '0 0 0 * * ? *', 'DO_NOTHING', 'FIRST', 'demoJobHandler', '', 'SERIAL_EXECUTION', 0, 0, 'BEAN', '', 'GLUE代码初始化', '2018-11-03 22:21:31', '', 0, 0, 0);
INSERT INTO `xxl_job_info` VALUES (3, 2, '定时发送', '2021-06-01 15:59:19', '2021-06-01 16:14:43', 'liqishan', '', 'CRON', '0 * * * * ?', 'DO_NOTHING', 'FIRST', 'smsSendJobHandler', '', 'SERIAL_EXECUTION', 0, 0, 'BEAN', '', 'GLUE代码初始化', '2021-06-01 15:59:19', '', 0, 0, 0);

-- ----------------------------
-- Table structure for xxl_job_lock
-- ----------------------------
DROP TABLE IF EXISTS `xxl_job_lock`;
CREATE TABLE `xxl_job_lock`  (
  `lock_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '锁名称',
  PRIMARY KEY (`lock_name`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of xxl_job_lock
-- ----------------------------
INSERT INTO `xxl_job_lock` VALUES ('schedule_lock');

-- ----------------------------
-- Table structure for xxl_job_log
-- ----------------------------
DROP TABLE IF EXISTS `xxl_job_log`;
CREATE TABLE `xxl_job_log`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `job_group` int(11) NOT NULL COMMENT '执行器主键ID',
  `job_id` int(11) NOT NULL COMMENT '任务，主键ID',
  `executor_address` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '执行器地址，本次执行的地址',
  `executor_handler` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '执行器任务handler',
  `executor_param` varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '执行器任务参数',
  `executor_sharding_param` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '执行器任务分片参数，格式如 1/2',
  `executor_fail_retry_count` int(11) NOT NULL DEFAULT 0 COMMENT '失败重试次数',
  `trigger_time` datetime(0) NULL DEFAULT NULL COMMENT '调度-时间',
  `trigger_code` int(11) NOT NULL COMMENT '调度-结果',
  `trigger_msg` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL COMMENT '调度-日志',
  `handle_time` datetime(0) NULL DEFAULT NULL COMMENT '执行-时间',
  `handle_code` int(11) NOT NULL COMMENT '执行-状态',
  `handle_msg` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL COMMENT '执行-日志',
  `alarm_status` tinyint(4) NOT NULL DEFAULT 0 COMMENT '告警状态：0-默认、1-无需告警、2-告警成功、3-告警失败',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `I_trigger_time`(`trigger_time`) USING BTREE,
  INDEX `I_handle_code`(`handle_code`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 17 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of xxl_job_log
-- ----------------------------
INSERT INTO `xxl_job_log` VALUES (1, 1, 2, NULL, '', '', NULL, 0, '2021-06-01 13:07:00', 500, '任务触发类型：Cron触发<br>调度机器：192.168.65.1<br>执行器-注册方式：自动注册<br>执行器-地址列表：null<br>路由策略：第一个<br>阻塞处理策略：单机串行<br>任务超时时间：0<br>失败重试次数：0<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br>调度失败：执行器地址为空<br><br>', NULL, 0, NULL, 1);
INSERT INTO `xxl_job_log` VALUES (2, 2, 3, NULL, 'smsSendJobHandler', '', NULL, 0, '2021-06-01 16:00:00', 500, '任务触发类型：Cron触发<br>调度机器：192.168.65.1<br>执行器-注册方式：自动注册<br>执行器-地址列表：null<br>路由策略：第一个<br>阻塞处理策略：单机串行<br>任务超时时间：0<br>失败重试次数：0<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br>调度失败：执行器地址为空<br><br>', NULL, 0, NULL, 1);
INSERT INTO `xxl_job_log` VALUES (3, 2, 3, NULL, 'smsSendJobHandler', '', NULL, 0, '2021-06-01 16:01:00', 500, '任务触发类型：Cron触发<br>调度机器：192.168.65.1<br>执行器-注册方式：自动注册<br>执行器-地址列表：null<br>路由策略：第一个<br>阻塞处理策略：单机串行<br>任务超时时间：0<br>失败重试次数：0<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br>调度失败：执行器地址为空<br><br>', NULL, 0, NULL, 1);
INSERT INTO `xxl_job_log` VALUES (4, 2, 3, NULL, 'smsSendJobHandler', '', NULL, 0, '2021-06-01 16:02:00', 500, '任务触发类型：Cron触发<br>调度机器：192.168.65.1<br>执行器-注册方式：自动注册<br>执行器-地址列表：null<br>路由策略：第一个<br>阻塞处理策略：单机串行<br>任务超时时间：0<br>失败重试次数：0<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br>调度失败：执行器地址为空<br><br>', NULL, 0, NULL, 1);
INSERT INTO `xxl_job_log` VALUES (5, 2, 3, NULL, 'smsSendJobHandler', '', NULL, 0, '2021-06-01 16:03:00', 500, '任务触发类型：Cron触发<br>调度机器：192.168.65.1<br>执行器-注册方式：自动注册<br>执行器-地址列表：null<br>路由策略：第一个<br>阻塞处理策略：单机串行<br>任务超时时间：0<br>失败重试次数：0<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br>调度失败：执行器地址为空<br><br>', NULL, 0, NULL, 1);
INSERT INTO `xxl_job_log` VALUES (6, 2, 3, NULL, 'smsSendJobHandler', '', NULL, 0, '2021-06-01 16:04:00', 500, '任务触发类型：Cron触发<br>调度机器：192.168.65.1<br>执行器-注册方式：自动注册<br>执行器-地址列表：null<br>路由策略：第一个<br>阻塞处理策略：单机串行<br>任务超时时间：0<br>失败重试次数：0<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br>调度失败：执行器地址为空<br><br>', NULL, 0, NULL, 1);
INSERT INTO `xxl_job_log` VALUES (7, 2, 3, NULL, 'smsSendJobHandler', '', NULL, 0, '2021-06-01 16:05:00', 500, '任务触发类型：Cron触发<br>调度机器：192.168.65.1<br>执行器-注册方式：自动注册<br>执行器-地址列表：null<br>路由策略：第一个<br>阻塞处理策略：单机串行<br>任务超时时间：0<br>失败重试次数：0<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br>调度失败：执行器地址为空<br><br>', NULL, 0, NULL, 1);
INSERT INTO `xxl_job_log` VALUES (8, 2, 3, NULL, 'smsSendJobHandler', '', NULL, 0, '2021-06-01 16:06:00', 500, '任务触发类型：Cron触发<br>调度机器：192.168.65.1<br>执行器-注册方式：自动注册<br>执行器-地址列表：null<br>路由策略：第一个<br>阻塞处理策略：单机串行<br>任务超时时间：0<br>失败重试次数：0<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br>调度失败：执行器地址为空<br><br>', NULL, 0, NULL, 1);
INSERT INTO `xxl_job_log` VALUES (9, 2, 3, NULL, 'smsSendJobHandler', '', NULL, 0, '2021-06-01 16:07:00', 500, '任务触发类型：Cron触发<br>调度机器：192.168.65.1<br>执行器-注册方式：自动注册<br>执行器-地址列表：null<br>路由策略：第一个<br>阻塞处理策略：单机串行<br>任务超时时间：0<br>失败重试次数：0<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br>调度失败：执行器地址为空<br><br>', NULL, 0, NULL, 1);
INSERT INTO `xxl_job_log` VALUES (10, 2, 3, NULL, 'smsSendJobHandler', '', NULL, 0, '2021-06-01 16:08:00', 500, '任务触发类型：Cron触发<br>调度机器：192.168.65.1<br>执行器-注册方式：自动注册<br>执行器-地址列表：null<br>路由策略：第一个<br>阻塞处理策略：单机串行<br>任务超时时间：0<br>失败重试次数：0<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br>调度失败：执行器地址为空<br><br>', NULL, 0, NULL, 1);
INSERT INTO `xxl_job_log` VALUES (11, 2, 3, NULL, 'smsSendJobHandler', '', NULL, 0, '2021-06-01 16:09:00', 500, '任务触发类型：Cron触发<br>调度机器：192.168.65.1<br>执行器-注册方式：自动注册<br>执行器-地址列表：null<br>路由策略：第一个<br>阻塞处理策略：单机串行<br>任务超时时间：0<br>失败重试次数：0<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br>调度失败：执行器地址为空<br><br>', NULL, 0, NULL, 1);
INSERT INTO `xxl_job_log` VALUES (12, 2, 3, NULL, 'smsSendJobHandler', '', NULL, 0, '2021-06-01 16:10:00', 500, '任务触发类型：Cron触发<br>调度机器：192.168.65.1<br>执行器-注册方式：自动注册<br>执行器-地址列表：null<br>路由策略：第一个<br>阻塞处理策略：单机串行<br>任务超时时间：0<br>失败重试次数：0<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br>调度失败：执行器地址为空<br><br>', NULL, 0, NULL, 1);
INSERT INTO `xxl_job_log` VALUES (13, 2, 3, NULL, 'smsSendJobHandler', '', NULL, 0, '2021-06-01 16:11:00', 500, '任务触发类型：Cron触发<br>调度机器：192.168.65.1<br>执行器-注册方式：自动注册<br>执行器-地址列表：null<br>路由策略：第一个<br>阻塞处理策略：单机串行<br>任务超时时间：0<br>失败重试次数：0<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br>调度失败：执行器地址为空<br><br>', NULL, 0, NULL, 1);
INSERT INTO `xxl_job_log` VALUES (14, 2, 3, 'http://127.0.0.1:8775/', 'smsSendJobHandler', '', NULL, 0, '2021-06-01 16:12:00', 200, '任务触发类型：Cron触发<br>调度机器：192.168.65.1<br>执行器-注册方式：自动注册<br>执行器-地址列表：[http://127.0.0.1:8775/]<br>路由策略：第一个<br>阻塞处理策略：单机串行<br>任务超时时间：0<br>失败重试次数：0<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br>触发调度：<br>address：http://127.0.0.1:8775/<br>code：200<br>msg：null', '2021-06-01 16:12:00', 500, 'java.lang.reflect.InvocationTargetException\r\n	at sun.reflect.NativeMethodAccessorImpl.invoke0(Native Method)\r\n	at sun.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:62)\r\n	at sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)\r\n	at java.lang.reflect.Method.invoke(Method.java:498)\r\n	at com.xxl.job.core.handler.impl.MethodJobHandler.execute(MethodJobHandler.java:31)\r\n	at com.xxl.job.core.thread.JobThread.run(JobThread.java:163)\r\nCaused by: java.lang.NullPointerException\r\n	at com.xxl.job.executor.service.jobhandler.MsgJob.smsSend(MsgJob.java:26)\r\n	... 6 more\r\n', 1);
INSERT INTO `xxl_job_log` VALUES (15, 2, 3, 'http://127.0.0.1:8775/', 'smsSendJobHandler', '', NULL, 0, '2021-06-01 16:13:00', 200, '任务触发类型：Cron触发<br>调度机器：192.168.65.1<br>执行器-注册方式：自动注册<br>执行器-地址列表：[http://127.0.0.1:8775/]<br>路由策略：第一个<br>阻塞处理策略：单机串行<br>任务超时时间：0<br>失败重试次数：0<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br>触发调度：<br>address：http://127.0.0.1:8775/<br>code：200<br>msg：null', '2021-06-01 16:13:00', 500, 'java.lang.reflect.InvocationTargetException\r\n	at sun.reflect.NativeMethodAccessorImpl.invoke0(Native Method)\r\n	at sun.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:62)\r\n	at sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)\r\n	at java.lang.reflect.Method.invoke(Method.java:498)\r\n	at com.xxl.job.core.handler.impl.MethodJobHandler.execute(MethodJobHandler.java:31)\r\n	at com.xxl.job.core.thread.JobThread.run(JobThread.java:163)\r\nCaused by: java.lang.NullPointerException\r\n	at com.xxl.job.executor.service.jobhandler.MsgJob.smsSend(MsgJob.java:26)\r\n	... 6 more\r\n', 1);
INSERT INTO `xxl_job_log` VALUES (16, 2, 3, 'http://127.0.0.1:8775/', 'smsSendJobHandler', '', NULL, 0, '2021-06-01 16:14:00', 200, '任务触发类型：Cron触发<br>调度机器：192.168.65.1<br>执行器-注册方式：自动注册<br>执行器-地址列表：[http://127.0.0.1:8775/]<br>路由策略：第一个<br>阻塞处理策略：单机串行<br>任务超时时间：0<br>失败重试次数：0<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br>触发调度：<br>address：http://127.0.0.1:8775/<br>code：200<br>msg：null', '2021-06-01 16:14:00', 500, 'java.lang.reflect.InvocationTargetException\r\n	at sun.reflect.NativeMethodAccessorImpl.invoke0(Native Method)\r\n	at sun.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:62)\r\n	at sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)\r\n	at java.lang.reflect.Method.invoke(Method.java:498)\r\n	at com.xxl.job.core.handler.impl.MethodJobHandler.execute(MethodJobHandler.java:31)\r\n	at com.xxl.job.core.thread.JobThread.run(JobThread.java:163)\r\nCaused by: java.lang.NullPointerException\r\n	at com.xxl.job.executor.service.jobhandler.MsgJob.smsSend(MsgJob.java:26)\r\n	... 6 more\r\n', 1);

-- ----------------------------
-- Table structure for xxl_job_log_report
-- ----------------------------
DROP TABLE IF EXISTS `xxl_job_log_report`;
CREATE TABLE `xxl_job_log_report`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `trigger_day` datetime(0) NULL DEFAULT NULL COMMENT '调度-时间',
  `running_count` int(11) NOT NULL DEFAULT 0 COMMENT '运行中-日志数量',
  `suc_count` int(11) NOT NULL DEFAULT 0 COMMENT '执行成功-日志数量',
  `fail_count` int(11) NOT NULL DEFAULT 0 COMMENT '执行失败-日志数量',
  `update_time` datetime(0) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `i_trigger_day`(`trigger_day`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 8 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of xxl_job_log_report
-- ----------------------------
INSERT INTO `xxl_job_log_report` VALUES (1, '2021-06-01 00:00:00', 0, 0, 16, NULL);
INSERT INTO `xxl_job_log_report` VALUES (2, '2021-05-31 00:00:00', 0, 0, 0, NULL);
INSERT INTO `xxl_job_log_report` VALUES (3, '2021-05-30 00:00:00', 0, 0, 0, NULL);
INSERT INTO `xxl_job_log_report` VALUES (4, '2021-06-02 00:00:00', 0, 0, 0, NULL);
INSERT INTO `xxl_job_log_report` VALUES (5, '2021-06-04 00:00:00', 0, 0, 0, NULL);
INSERT INTO `xxl_job_log_report` VALUES (6, '2021-06-03 00:00:00', 0, 0, 0, NULL);
INSERT INTO `xxl_job_log_report` VALUES (7, '2021-06-05 00:00:00', 0, 0, 0, NULL);

-- ----------------------------
-- Table structure for xxl_job_logglue
-- ----------------------------
DROP TABLE IF EXISTS `xxl_job_logglue`;
CREATE TABLE `xxl_job_logglue`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `job_id` int(11) NOT NULL COMMENT '任务，主键ID',
  `glue_type` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT 'GLUE类型',
  `glue_source` mediumtext CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL COMMENT 'GLUE源代码',
  `glue_remark` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT 'GLUE备注',
  `add_time` datetime(0) NULL DEFAULT NULL,
  `update_time` datetime(0) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of xxl_job_logglue
-- ----------------------------

-- ----------------------------
-- Table structure for xxl_job_registry
-- ----------------------------
DROP TABLE IF EXISTS `xxl_job_registry`;
CREATE TABLE `xxl_job_registry`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `registry_group` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `registry_key` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `registry_value` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `update_time` datetime(0) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `i_g_k_v`(`registry_group`, `registry_key`, `registry_value`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 10 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of xxl_job_registry
-- ----------------------------
INSERT INTO `xxl_job_registry` VALUES (8, 'EXECUTOR', 'lamp-extend-executor', 'http://127.0.0.1:8775/', '2021-06-05 09:14:09');
INSERT INTO `xxl_job_registry` VALUES (9, 'EXECUTOR', 'lamp-base-executor', 'http://127.0.0.1:8777/', '2021-06-05 09:14:04');

-- ----------------------------
-- Table structure for xxl_job_user
-- ----------------------------
DROP TABLE IF EXISTS `xxl_job_user`;
CREATE TABLE `xxl_job_user`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '账号',
  `password` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '密码',
  `role` tinyint(4) NOT NULL COMMENT '角色：0-普通用户、1-管理员',
  `permission` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '权限：执行器ID列表，多个逗号分割',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `i_username`(`username`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of xxl_job_user
-- ----------------------------
INSERT INTO `xxl_job_user` VALUES (1, 'hst-pt', 'e99a18c428cb38d5f260853678922e03', 1, NULL);

SET FOREIGN_KEY_CHECKS = 1;
