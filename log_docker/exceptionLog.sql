###导出 logging 的数据库结构
CREATE DATABASE IF NOT EXISTS `logging` DEFAULT CHARSET utf8 COLLATE utf8_general_ci;
USE `logging`;
SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for APPLICATION_LOG
-- ----------------------------
DROP TABLE IF EXISTS `APPLICATION_LOG`;
CREATE TABLE `APPLICATION_LOG`  (
  `event_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `event_date` datetime(0) NOT NULL COMMENT '时间',
  `level` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '级别',
  `log_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '日志名称',
  `message` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '信息',
  `log_throwable` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '全部信息',
  `log_file` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '日志文件路径',
  `log_line` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '行数',
  `system_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '系统名',
  `env` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '环境',
  `thread_con` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '线程内容',
  PRIMARY KEY (`event_id`, `event_date`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1890245 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;


###导出 zkhc_exception_admin 的数据库结构
CREATE DATABASE IF NOT EXISTS `zkhc_exception_admin` DEFAULT CHARSET utf8 COLLATE utf8_general_ci;
USE `zkhc_exception_admin`;
SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for code_dbinfo
-- ----------------------------
DROP TABLE IF EXISTS `code_dbinfo`;
CREATE TABLE `code_dbinfo`  (
  `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '别名',
  `db_driver` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '数据库驱动',
  `db_url` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '数据库地址',
  `db_user_name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '数据库账户',
  `db_password` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '连接密码',
  `db_type` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '数据库类型',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '数据库链接信息' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for sys_dept
-- ----------------------------
DROP TABLE IF EXISTS `sys_dept`;
CREATE TABLE `sys_dept`  (
  `dept_id` bigint(20) NOT NULL COMMENT '主键id',
  `pid` bigint(20) NULL DEFAULT 0 COMMENT '父部门id',
  `pids` varchar(512) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT '' COMMENT '父级ids',
  `simple_name` varchar(45) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '简称',
  `full_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '全称',
  `description` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '描述',
  `version` int(11) NULL DEFAULT NULL COMMENT '版本（乐观锁保留字段）',
  `sort` int(11) NULL DEFAULT NULL COMMENT '排序',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
  `create_user` bigint(20) NULL DEFAULT NULL COMMENT '创建人',
  `update_user` bigint(20) NULL DEFAULT NULL COMMENT '修改人',
  PRIMARY KEY (`dept_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '部门表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_dept
-- ----------------------------
INSERT INTO `sys_dept` VALUES (24, 0, '[0],', '总公司', '总公司', '', NULL, 1, NULL, NULL, NULL, NULL);
INSERT INTO `sys_dept` VALUES (25, 24, '[0],[24],', '开发部', '开发部', '', NULL, 2, NULL, NULL, NULL, NULL);
INSERT INTO `sys_dept` VALUES (26, 24, '[0],[24],', '运营部', '运营部', '', NULL, 3, NULL, NULL, NULL, NULL);
INSERT INTO `sys_dept` VALUES (27, 24, '[0],[24],', '战略部', '战略部', '', NULL, 4, NULL, NULL, NULL, NULL);

-- ----------------------------
-- Table structure for sys_dict
-- ----------------------------
DROP TABLE IF EXISTS `sys_dict`;
CREATE TABLE `sys_dict`  (
  `dict_id` bigint(20) NOT NULL COMMENT '字典id',
  `dict_type_id` bigint(20) NOT NULL COMMENT '所属字典类型的id',
  `code` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '字典编码',
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '字典名称',
  `parent_id` bigint(20) NOT NULL COMMENT '上级代码id',
  `parent_ids` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '所有上级id',
  `status` varchar(10) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL DEFAULT 'ENABLE' COMMENT '状态（字典）',
  `sort` int(11) NULL DEFAULT NULL COMMENT '排序',
  `description` varchar(1000) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '字典的描述',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `create_user` bigint(20) NULL DEFAULT NULL COMMENT '创建人',
  `update_user` bigint(20) NULL DEFAULT NULL COMMENT '修改人',
  PRIMARY KEY (`dict_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '基础字典' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_dict
-- ----------------------------
INSERT INTO `sys_dict` VALUES (1106120532442595330, 1106120208097067009, 'M', '男', 0, '[0]', 'ENABLE', NULL, '', '2019-03-14 17:11:00', NULL, 1, NULL);
INSERT INTO `sys_dict` VALUES (1106120574163337218, 1106120208097067009, 'F', '女', 0, '[0]', 'ENABLE', NULL, '', '2019-03-14 17:11:10', NULL, 1, NULL);
INSERT INTO `sys_dict` VALUES (1106120645697191938, 1106120265689055233, 'ENABLE', '启用', 0, '[0]', 'ENABLE', NULL, '', '2019-03-14 17:11:27', NULL, 1, NULL);
INSERT INTO `sys_dict` VALUES (1106120699468169217, 1106120265689055233, 'DISABLE', '禁用', 0, '[0]', 'ENABLE', NULL, '', '2019-03-14 17:11:40', NULL, 1, NULL);
INSERT INTO `sys_dict` VALUES (1106120784318939137, 1106120322450571266, 'ENABLE', '启用', 0, '[0]', 'ENABLE', NULL, '', '2019-03-14 17:12:00', NULL, 1, NULL);
INSERT INTO `sys_dict` VALUES (1106120825993543682, 1106120322450571266, 'FREEZE', '冻结', 0, '[0]', 'ENABLE', 1, '', '2019-03-14 17:12:10', '2019-03-16 10:56:36', 1, 1);
INSERT INTO `sys_dict` VALUES (1106120875872206849, 1106120322450571266, 'DELETED', '已删除', 0, '[0]', 'ENABLE', -1221, '', '2019-03-14 17:12:22', '2019-03-16 10:56:53', 1, 1);
INSERT INTO `sys_dict` VALUES (1106120935070613505, 1106120388036902914, 'Y', '删除', 0, '[0]', 'ENABLE', 23333, '', '2019-03-14 17:12:36', '2019-03-16 10:58:53', 1, 1);
INSERT INTO `sys_dict` VALUES (1106120968910258177, 1106120388036902914, 'N', '未删除', 0, '[0]', 'ENABLE', 1212211221, '', '2019-03-14 17:12:44', '2019-03-16 10:59:03', 1, 1);
INSERT INTO `sys_dict` VALUES (1106751061042974722, 1106120322450571266, '11212', '122', 0, '[0]', 'ENABLE', 1212, '122112', '2019-03-16 10:56:30', NULL, 1, NULL);
INSERT INTO `sys_dict` VALUES (1106751747772166145, 1106120388036902914, '12312', '3123123', 1106120968910258177, '[0],[1106120968910258177]', 'ENABLE', 123, '', '2019-03-16 10:59:14', NULL, 1, NULL);

-- ----------------------------
-- Table structure for sys_dict_type
-- ----------------------------
DROP TABLE IF EXISTS `sys_dict_type`;
CREATE TABLE `sys_dict_type`  (
  `dict_type_id` bigint(20) NOT NULL COMMENT '字典类型id',
  `code` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '字典类型编码',
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '字典类型名称',
  `description` varchar(1000) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '字典描述',
  `system_flag` char(1) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '是否是系统字典，Y-是，N-否',
  `status` varchar(10) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL DEFAULT 'ENABLE' COMMENT '状态(字典)',
  `sort` int(11) NULL DEFAULT NULL COMMENT '排序',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '添加时间',
  `create_user` bigint(20) NULL DEFAULT NULL COMMENT '创建人',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
  `update_user` bigint(20) NULL DEFAULT NULL COMMENT '修改人',
  PRIMARY KEY (`dict_type_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '字典类型表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_dict_type
-- ----------------------------
INSERT INTO `sys_dict_type` VALUES (1106120208097067009, 'SEX', '性别', '', 'Y', 'ENABLE', 4, '2019-03-14 17:09:43', 1, NULL, NULL);
INSERT INTO `sys_dict_type` VALUES (1106120265689055233, 'STATUS', '状态', '', 'Y', 'ENABLE', 3, '2019-03-14 17:09:57', 1, NULL, NULL);
INSERT INTO `sys_dict_type` VALUES (1106120322450571266, 'ACCOUNT_STATUS', '账号状态', '', 'Y', 'ENABLE', 21112, '2019-03-14 17:10:10', 1, '2019-03-16 10:56:15', 1);
INSERT INTO `sys_dict_type` VALUES (1106120388036902914, 'DEL_FLAG', '是否删除', '', 'Y', 'ENABLE', 2, '2019-03-14 17:10:26', 1, '2019-03-27 16:26:31', 1);

-- ----------------------------
-- Table structure for sys_file_info
-- ----------------------------
DROP TABLE IF EXISTS `sys_file_info`;
CREATE TABLE `sys_file_info`  (
  `file_id` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '主键id',
  `file_data` text CHARACTER SET utf8 COLLATE utf8_bin NULL COMMENT 'base64编码的文件',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
  `create_user` bigint(20) NULL DEFAULT NULL COMMENT '创建用户',
  `update_user` bigint(20) NULL DEFAULT NULL COMMENT '修改用户',
  PRIMARY KEY (`file_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '文件信息表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_file_info
-- ----------------------------
INSERT INTO `sys_file_info` VALUES ('1', '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', NULL, NULL, NULL, NULL);

-- ----------------------------
-- Table structure for sys_login_log
-- ----------------------------
DROP TABLE IF EXISTS `sys_login_log`;
CREATE TABLE `sys_login_log`  (
  `login_log_id` bigint(20) NOT NULL COMMENT '主键',
  `log_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '日志名称',
  `user_id` bigint(20) NULL DEFAULT NULL COMMENT '管理员id',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `succeed` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '是否执行成功',
  `message` text CHARACTER SET utf8 COLLATE utf8_bin NULL COMMENT '具体消息',
  `ip_address` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '登录ip',
  PRIMARY KEY (`login_log_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '登录记录' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for sys_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_menu`;
CREATE TABLE `sys_menu`  (
  `menu_id` bigint(20) NOT NULL COMMENT '主键id',
  `code` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '菜单编号',
  `pcode` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '菜单父编号',
  `pcodes` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '当前菜单的所有父菜单编号',
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '菜单名称',
  `icon` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '菜单图标',
  `url` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT 'url地址',
  `sort` int(65) NULL DEFAULT NULL COMMENT '菜单排序号',
  `levels` int(65) NULL DEFAULT NULL COMMENT '菜单层级',
  `menu_flag` varchar(32) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '是否是菜单(字典)',
  `description` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '备注',
  `status` varchar(32) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT 'ENABLE' COMMENT '菜单状态(字典)',
  `new_page_flag` varchar(32) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '是否打开新页面的标识(字典)',
  `open_flag` varchar(32) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '是否打开(字典)',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
  `create_user` bigint(20) NULL DEFAULT NULL COMMENT '创建人',
  `update_user` bigint(20) NULL DEFAULT NULL COMMENT '修改人',
  PRIMARY KEY (`menu_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '菜单表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_menu
-- ----------------------------
INSERT INTO `sys_menu` VALUES (105, 'system', '0', '[0],', '系统管理', 'layui-icon layui-icon-set', '#', 20, 1, 'Y', NULL, 'ENABLE', NULL, '1', NULL, '2019-03-29 16:32:27', NULL, 1);
INSERT INTO `sys_menu` VALUES (106, 'mgr', 'system', '[0],[system],', '用户管理', '', '/mgr', 1, 2, 'Y', NULL, 'ENABLE', NULL, '0', NULL, '2019-03-29 16:32:27', NULL, 1);
INSERT INTO `sys_menu` VALUES (107, 'mgr_add', 'mgr', '[0],[system],[mgr],', '添加用户', NULL, '/mgr/add', 1, 3, 'N', NULL, 'ENABLE', NULL, '0', NULL, '2019-03-29 16:32:27', NULL, 1);
INSERT INTO `sys_menu` VALUES (108, 'mgr_edit', 'mgr', '[0],[system],[mgr],', '修改用户', NULL, '/mgr/edit', 2, 3, 'N', NULL, 'ENABLE', NULL, '0', NULL, '2019-03-29 16:32:27', NULL, 1);
INSERT INTO `sys_menu` VALUES (109, 'mgr_delete', 'mgr', '[0],[system],[mgr],', '删除用户', NULL, '/mgr/delete', 3, 3, 'N', NULL, 'ENABLE', NULL, '0', NULL, '2019-03-29 16:32:27', NULL, 1);
INSERT INTO `sys_menu` VALUES (110, 'mgr_reset', 'mgr', '[0],[system],[mgr],', '重置密码', NULL, '/mgr/reset', 4, 3, 'N', NULL, 'ENABLE', NULL, '0', NULL, '2019-03-29 16:32:27', NULL, 1);
INSERT INTO `sys_menu` VALUES (111, 'mgr_freeze', 'mgr', '[0],[system],[mgr],', '冻结用户', NULL, '/mgr/freeze', 5, 3, 'N', NULL, 'ENABLE', NULL, '0', NULL, '2019-03-29 16:32:27', NULL, 1);
INSERT INTO `sys_menu` VALUES (112, 'mgr_unfreeze', 'mgr', '[0],[system],[mgr],', '解除冻结用户', NULL, '/mgr/unfreeze', 6, 3, 'N', NULL, 'ENABLE', NULL, '0', NULL, '2019-03-29 16:32:27', NULL, 1);
INSERT INTO `sys_menu` VALUES (113, 'mgr_setRole', 'mgr', '[0],[system],[mgr],', '分配角色', NULL, '/mgr/setRole', 7, 3, 'N', NULL, 'ENABLE', NULL, '0', NULL, '2019-03-29 16:32:27', NULL, 1);
INSERT INTO `sys_menu` VALUES (114, 'role', 'system', '[0],[system],', '角色管理', NULL, '/role', 2, 2, 'Y', NULL, 'ENABLE', NULL, '0', NULL, '2019-03-29 16:32:27', NULL, 1);
INSERT INTO `sys_menu` VALUES (115, 'role_add', 'role', '[0],[system],[role],', '添加角色', NULL, '/role/add', 1, 3, 'N', NULL, 'ENABLE', NULL, '0', NULL, '2019-03-29 16:32:27', NULL, 1);
INSERT INTO `sys_menu` VALUES (116, 'role_edit', 'role', '[0],[system],[role],', '修改角色', NULL, '/role/edit', 2, 3, 'N', NULL, 'ENABLE', NULL, '0', NULL, '2019-03-29 16:32:27', NULL, 1);
INSERT INTO `sys_menu` VALUES (117, 'role_remove', 'role', '[0],[system],[role],', '删除角色', NULL, '/role/remove', 3, 3, 'N', NULL, 'ENABLE', NULL, '0', NULL, '2019-03-29 16:32:27', NULL, 1);
INSERT INTO `sys_menu` VALUES (118, 'role_setAuthority', 'role', '[0],[system],[role],', '配置权限', NULL, '/role/setAuthority', 4, 3, 'N', NULL, 'ENABLE', NULL, '0', NULL, '2019-03-29 16:32:27', NULL, 1);
INSERT INTO `sys_menu` VALUES (119, 'menu', 'system', '[0],[system],', '菜单管理', NULL, '/menu', 4, 2, 'Y', NULL, 'ENABLE', NULL, '0', NULL, '2019-03-29 16:32:27', NULL, 1);
INSERT INTO `sys_menu` VALUES (120, 'menu_add', 'menu', '[0],[system],[menu],', '添加菜单', NULL, '/menu/add', 1, 3, 'N', NULL, 'ENABLE', NULL, '0', NULL, '2019-03-29 16:32:27', NULL, 1);
INSERT INTO `sys_menu` VALUES (121, 'menu_edit', 'menu', '[0],[system],[menu],', '修改菜单', NULL, '/menu/edit', 2, 3, 'N', NULL, 'ENABLE', NULL, '0', NULL, '2019-03-29 16:32:27', NULL, 1);
INSERT INTO `sys_menu` VALUES (122, 'menu_remove', 'menu', '[0],[system],[menu],', '删除菜单', NULL, '/menu/remove', 3, 3, 'N', NULL, 'ENABLE', NULL, '0', NULL, '2019-03-29 16:32:27', NULL, 1);
INSERT INTO `sys_menu` VALUES (128, 'log', 'system', '[0],[system],', '业务日志', NULL, '/log', 6, 2, 'Y', NULL, 'ENABLE', NULL, '0', NULL, '2019-03-29 16:32:27', NULL, 1);
INSERT INTO `sys_menu` VALUES (130, 'druid', 'system', '[0],[system],', '监控管理', NULL, '/druid', 7, 2, 'Y', NULL, 'ENABLE', NULL, NULL, NULL, '2019-03-29 16:32:27', NULL, 1);
INSERT INTO `sys_menu` VALUES (131, 'dept', 'system', '[0],[system],', '部门管理', NULL, '/dept', 3, 2, 'Y', NULL, 'ENABLE', NULL, NULL, NULL, '2019-03-29 16:32:27', NULL, 1);
INSERT INTO `sys_menu` VALUES (132, 'dict', 'system', '[0],[system],', '字典管理', '', '/dictType', 4, 2, 'Y', NULL, 'ENABLE', NULL, NULL, NULL, '2019-03-29 16:32:27', NULL, 1);
INSERT INTO `sys_menu` VALUES (133, 'loginLog', 'system', '[0],[system],', '登录日志', NULL, '/loginLog', 6, 2, 'Y', NULL, 'ENABLE', NULL, NULL, NULL, '2019-03-29 16:32:27', NULL, 1);
INSERT INTO `sys_menu` VALUES (134, 'log_clean', 'log', '[0],[system],[log],', '清空日志', NULL, '/log/delLog', 3, 3, 'N', NULL, 'ENABLE', NULL, NULL, NULL, '2019-03-29 16:32:27', NULL, 1);
INSERT INTO `sys_menu` VALUES (135, 'dept_add', 'dept', '[0],[system],[dept],', '添加部门', NULL, '/dept/add', 1, 3, 'N', NULL, 'ENABLE', NULL, NULL, NULL, '2019-03-29 16:32:27', NULL, 1);
INSERT INTO `sys_menu` VALUES (136, 'dept_update', 'dept', '[0],[system],[dept],', '修改部门', NULL, '/dept/update', 1, 3, 'N', NULL, 'ENABLE', NULL, NULL, NULL, '2019-03-29 16:32:27', NULL, 1);
INSERT INTO `sys_menu` VALUES (137, 'dept_delete', 'dept', '[0],[system],[dept],', '删除部门', NULL, '/dept/delete', 1, 3, 'N', NULL, 'ENABLE', NULL, NULL, NULL, '2019-03-29 16:32:27', NULL, 1);
INSERT INTO `sys_menu` VALUES (138, 'dict_add', 'dict', '[0],[system],[dict],', '添加字典', NULL, '/dictType/addItem', 1, 3, 'N', NULL, 'ENABLE', NULL, NULL, NULL, '2019-03-29 16:32:27', NULL, 1);
INSERT INTO `sys_menu` VALUES (139, 'dict_update', 'dict', '[0],[system],[dict],', '修改字典', NULL, '/dictType/editItem', 1, 3, 'N', NULL, 'ENABLE', NULL, NULL, NULL, '2019-03-29 16:32:27', NULL, 1);
INSERT INTO `sys_menu` VALUES (140, 'dict_delete', 'dict', '[0],[system],[dict],', '删除字典', NULL, '/dictType/delete', 1, 3, 'N', NULL, 'ENABLE', NULL, NULL, NULL, '2019-03-29 16:32:27', NULL, 1);
INSERT INTO `sys_menu` VALUES (141, 'notice', 'system', '[0],[system],', '通知管理', NULL, '/notice', 9, 2, 'Y', NULL, 'ENABLE', NULL, NULL, NULL, '2019-03-29 16:32:27', NULL, 1);
INSERT INTO `sys_menu` VALUES (142, 'notice_add', 'notice', '[0],[system],[notice],', '添加通知', NULL, '/notice/add', 1, 3, 'N', NULL, 'ENABLE', NULL, NULL, NULL, '2019-03-29 16:32:27', NULL, 1);
INSERT INTO `sys_menu` VALUES (143, 'notice_update', 'notice', '[0],[system],[notice],', '修改通知', NULL, '/notice/update', 2, 3, 'N', NULL, 'ENABLE', NULL, NULL, NULL, '2019-03-29 16:32:27', NULL, 1);
INSERT INTO `sys_menu` VALUES (144, 'notice_delete', 'notice', '[0],[system],[notice],', '删除通知', NULL, '/notice/delete', 3, 3, 'N', NULL, 'ENABLE', NULL, NULL, NULL, '2019-03-29 16:32:27', NULL, 1);
INSERT INTO `sys_menu` VALUES (145, 'sys_message', 'system', '[0],[system],', '消息通知', 'layui-icon layui-icon-tips', '/system/notice', 30, 2, 'Y', NULL, 'ENABLE', NULL, NULL, NULL, '2019-09-17 15:17:32', NULL, 1);
INSERT INTO `sys_menu` VALUES (149, 'api_mgr', 'dev_tools', '[0],[dev_tools],', '接口文档', 'fa-leaf', '/swagger-ui.html', 2, 2, 'Y', NULL, 'ENABLE', NULL, NULL, NULL, '2019-04-08 22:55:06', NULL, 1);
INSERT INTO `sys_menu` VALUES (150, 'to_menu_edit', 'menu', '[0],[system],[menu],', '菜单编辑跳转', '', '/menu/menu_edit', 4, 3, 'N', NULL, 'ENABLE', NULL, NULL, NULL, '2019-03-29 16:32:27', NULL, 1);
INSERT INTO `sys_menu` VALUES (151, 'menu_list', 'menu', '[0],[system],[menu],', '菜单列表', '', '/menu/list', 5, 3, 'N', NULL, 'ENABLE', NULL, NULL, NULL, '2019-03-29 16:32:27', NULL, 1);
INSERT INTO `sys_menu` VALUES (152, 'to_dept_update', 'dept', '[0],[system],[dept],', '修改部门跳转', '', '/dept/dept_update', 4, 3, 'N', NULL, 'ENABLE', NULL, NULL, NULL, '2019-03-29 16:32:27', NULL, 1);
INSERT INTO `sys_menu` VALUES (153, 'dept_list', 'dept', '[0],[system],[dept],', '部门列表', '', '/dept/list', 5, 3, 'N', NULL, 'ENABLE', NULL, NULL, NULL, '2019-03-29 16:32:27', NULL, 1);
INSERT INTO `sys_menu` VALUES (154, 'dept_detail', 'dept', '[0],[system],[dept],', '部门详情', '', '/dept/detail', 6, 3, 'N', NULL, 'ENABLE', NULL, NULL, NULL, '2019-03-29 16:32:27', NULL, 1);
INSERT INTO `sys_menu` VALUES (155, 'to_dict_edit', 'dict', '[0],[system],[dict],', '修改菜单跳转', '', '/dict/dict_edit', 4, 3, 'N', NULL, 'ENABLE', NULL, NULL, NULL, '2019-03-29 16:32:27', NULL, 1);
INSERT INTO `sys_menu` VALUES (156, 'dict_list', 'dict', '[0],[system],[dict],', '字典列表', '', '/dict/list', 5, 3, 'N', NULL, 'ENABLE', NULL, NULL, NULL, '2019-03-29 16:32:27', NULL, 1);
INSERT INTO `sys_menu` VALUES (157, 'dict_detail', 'dict', '[0],[system],[dict],', '字典详情', '', '/dict/detail', 6, 3, 'N', NULL, 'ENABLE', NULL, NULL, NULL, '2019-03-29 16:32:27', NULL, 1);
INSERT INTO `sys_menu` VALUES (158, 'log_list', 'log', '[0],[system],[log],', '日志列表', '', '/log/list', 2, 3, 'N', NULL, 'ENABLE', NULL, NULL, NULL, '2019-03-29 16:32:27', NULL, 1);
INSERT INTO `sys_menu` VALUES (159, 'log_detail', 'log', '[0],[system],[log],', '日志详情', '', '/log/detail', 3, 3, 'N', NULL, 'ENABLE', NULL, NULL, NULL, '2019-03-29 16:32:27', NULL, 1);
INSERT INTO `sys_menu` VALUES (160, 'del_login_log', 'loginLog', '[0],[system],[loginLog],', '清空登录日志', '', '/loginLog/delLoginLog', 1, 3, 'N', NULL, 'ENABLE', NULL, NULL, NULL, '2019-03-29 16:32:27', NULL, 1);
INSERT INTO `sys_menu` VALUES (161, 'login_log_list', 'loginLog', '[0],[system],[loginLog],', '登录日志列表', '', '/loginLog/list', 2, 3, 'N', NULL, 'ENABLE', NULL, NULL, NULL, '2019-03-29 16:32:27', NULL, 1);
INSERT INTO `sys_menu` VALUES (162, 'to_role_edit', 'role', '[0],[system],[role],', '修改角色跳转', '', '/role/role_edit', 5, 3, 'N', NULL, 'ENABLE', NULL, NULL, NULL, '2019-03-29 16:32:27', NULL, 1);
INSERT INTO `sys_menu` VALUES (163, 'to_role_assign', 'role', '[0],[system],[role],', '角色分配跳转', '', '/role/role_assign', 6, 3, 'N', NULL, 'ENABLE', NULL, NULL, NULL, '2019-03-29 16:32:27', NULL, 1);
INSERT INTO `sys_menu` VALUES (164, 'role_list', 'role', '[0],[system],[role],', '角色列表', '', '/role/list', 7, 3, 'N', NULL, 'ENABLE', NULL, NULL, NULL, '2019-03-29 16:32:27', NULL, 1);
INSERT INTO `sys_menu` VALUES (165, 'to_assign_role', 'mgr', '[0],[system],[mgr],', '分配角色跳转', '', '/mgr/role_assign', 8, 3, 'N', NULL, 'ENABLE', NULL, NULL, NULL, '2019-03-29 16:32:27', NULL, 1);
INSERT INTO `sys_menu` VALUES (166, 'to_user_edit', 'mgr', '[0],[system],[mgr],', '编辑用户跳转', '', '/mgr/user_edit', 9, 3, 'N', NULL, 'ENABLE', NULL, NULL, NULL, '2019-03-29 16:32:27', NULL, 1);
INSERT INTO `sys_menu` VALUES (167, 'mgr_list', 'mgr', '[0],[system],[mgr],', '用户列表', '', '/mgr/list', 10, 3, 'N', NULL, 'ENABLE', NULL, NULL, NULL, '2019-03-29 16:32:27', NULL, 1);
INSERT INTO `sys_menu` VALUES (171, 'dev_tools', '0', '[0],', 'API管理', 'layui-icon layui-icon-code-circle', '#', 30, 1, 'Y', NULL, 'ENABLE', NULL, NULL, NULL, '2019-04-08 22:55:06', NULL, 1);
INSERT INTO `sys_menu` VALUES (172, 'exceptionLog', '0', '[0],', '异常管理', 'layui-icon layui-icon-app', '/exceptionLog', 10, 1, 'Y', NULL, 'ENABLE', NULL, NULL, NULL, '2019-09-17 10:48:24', NULL, 1);
INSERT INTO `sys_menu` VALUES (1173775159297609730, 'exceptionLog_list', 'exceptionLog', '[0],[exceptionLog],', '异常列表', '', '/exceptionLog', 1, 2, 'Y', NULL, 'ENABLE', NULL, NULL, '2019-09-17 09:46:20', '2019-09-17 10:48:24', 1, 1);
INSERT INTO `sys_menu` VALUES (1173786015901192193, 'home', '0', '[0],', '首页', 'layui-icon layui-icon-home', '/system/console2', 1, 1, 'Y', NULL, 'ENABLE', NULL, NULL, '2019-09-17 10:29:29', NULL, 1, NULL);
INSERT INTO `sys_menu` VALUES (1173794812417839105, 'exceptionLog_delete', 'exceptionLog_list', '[0],[exceptionLog],[exceptionLog_list],', '异常删除', '', '/exceptionLog/delete', 1, 3, 'N', NULL, 'ENABLE', NULL, NULL, '2019-09-17 11:04:26', NULL, 1, NULL);

-- ----------------------------
-- Table structure for sys_notice
-- ----------------------------
DROP TABLE IF EXISTS `sys_notice`;
CREATE TABLE `sys_notice`  (
  `notice_id` bigint(20) NOT NULL COMMENT '主键',
  `title` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '标题',
  `content` text CHARACTER SET utf8 COLLATE utf8_bin NULL COMMENT '内容',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `create_user` bigint(20) NULL DEFAULT NULL COMMENT '创建人',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
  `update_user` bigint(20) NULL DEFAULT NULL COMMENT '修改人',
  PRIMARY KEY (`notice_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '通知表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_notice
-- ----------------------------
INSERT INTO `sys_notice` VALUES (6, '欢迎', 'hi，Guns旗舰版发布了！', '2017-01-11 08:53:20', 1, '2018-12-28 23:24:48', 1);
INSERT INTO `sys_notice` VALUES (8, '你好', '你好，世界！', '2017-05-10 19:28:57', 1, '2018-12-28 23:28:26', 1);

-- ----------------------------
-- Table structure for sys_operation_log
-- ----------------------------
DROP TABLE IF EXISTS `sys_operation_log`;
CREATE TABLE `sys_operation_log`  (
  `operation_log_id` bigint(20) NOT NULL COMMENT '主键',
  `log_type` varchar(32) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '日志类型(字典)',
  `log_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '日志名称',
  `user_id` bigint(65) NULL DEFAULT NULL COMMENT '用户id',
  `class_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '类名称',
  `method` text CHARACTER SET utf8 COLLATE utf8_bin NULL COMMENT '方法名称',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `succeed` varchar(32) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '是否成功(字典)',
  `message` text CHARACTER SET utf8 COLLATE utf8_bin NULL COMMENT '备注',
  PRIMARY KEY (`operation_log_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '操作日志' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_operation_log
-- ----------------------------

-- ----------------------------
-- Table structure for sys_relation
-- ----------------------------
DROP TABLE IF EXISTS `sys_relation`;
CREATE TABLE `sys_relation`  (
  `relation_id` bigint(20) NOT NULL COMMENT '主键',
  `menu_id` bigint(20) NULL DEFAULT NULL COMMENT '菜单id',
  `role_id` bigint(20) NULL DEFAULT NULL COMMENT '角色id',
  PRIMARY KEY (`relation_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '角色和菜单关联表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_relation
-- ----------------------------
INSERT INTO `sys_relation` VALUES (1071348922291826689, 105, 5);
INSERT INTO `sys_relation` VALUES (1071348922308603906, 106, 5);
INSERT INTO `sys_relation` VALUES (1071348922316992514, 107, 5);
INSERT INTO `sys_relation` VALUES (1071348922321186818, 108, 5);
INSERT INTO `sys_relation` VALUES (1071348922329575426, 109, 5);
INSERT INTO `sys_relation` VALUES (1071348922337964034, 110, 5);
INSERT INTO `sys_relation` VALUES (1071348922342158337, 111, 5);
INSERT INTO `sys_relation` VALUES (1071348922350546946, 112, 5);
INSERT INTO `sys_relation` VALUES (1071348922354741249, 113, 5);
INSERT INTO `sys_relation` VALUES (1071348922363129858, 165, 5);
INSERT INTO `sys_relation` VALUES (1071348922371518465, 166, 5);
INSERT INTO `sys_relation` VALUES (1071348922375712770, 167, 5);
INSERT INTO `sys_relation` VALUES (1071348922384101377, 114, 5);
INSERT INTO `sys_relation` VALUES (1071348922388295681, 115, 5);
INSERT INTO `sys_relation` VALUES (1071348922396684289, 116, 5);
INSERT INTO `sys_relation` VALUES (1071348922405072897, 117, 5);
INSERT INTO `sys_relation` VALUES (1071348922413461505, 118, 5);
INSERT INTO `sys_relation` VALUES (1071348922417655810, 162, 5);
INSERT INTO `sys_relation` VALUES (1071348922426044418, 163, 5);
INSERT INTO `sys_relation` VALUES (1071348922430238722, 164, 5);
INSERT INTO `sys_relation` VALUES (1071348922430238723, 119, 5);
INSERT INTO `sys_relation` VALUES (1071348922447015937, 120, 5);
INSERT INTO `sys_relation` VALUES (1071348922451210242, 121, 5);
INSERT INTO `sys_relation` VALUES (1071348922459598850, 122, 5);
INSERT INTO `sys_relation` VALUES (1071348922463793154, 150, 5);
INSERT INTO `sys_relation` VALUES (1071348922472181762, 151, 5);
INSERT INTO `sys_relation` VALUES (1071348922476376065, 128, 5);
INSERT INTO `sys_relation` VALUES (1071348922480570369, 134, 5);
INSERT INTO `sys_relation` VALUES (1071348922488958977, 158, 5);
INSERT INTO `sys_relation` VALUES (1071348922497347586, 159, 5);
INSERT INTO `sys_relation` VALUES (1071348922501541890, 130, 5);
INSERT INTO `sys_relation` VALUES (1071348922501541891, 131, 5);
INSERT INTO `sys_relation` VALUES (1071348922518319106, 135, 5);
INSERT INTO `sys_relation` VALUES (1071348922526707713, 136, 5);
INSERT INTO `sys_relation` VALUES (1071348922530902017, 137, 5);
INSERT INTO `sys_relation` VALUES (1071348922535096321, 152, 5);
INSERT INTO `sys_relation` VALUES (1071348922543484930, 153, 5);
INSERT INTO `sys_relation` VALUES (1071348922547679233, 154, 5);
INSERT INTO `sys_relation` VALUES (1071348922556067841, 132, 5);
INSERT INTO `sys_relation` VALUES (1071348922560262146, 138, 5);
INSERT INTO `sys_relation` VALUES (1071348922564456450, 139, 5);
INSERT INTO `sys_relation` VALUES (1071348922568650754, 140, 5);
INSERT INTO `sys_relation` VALUES (1071348922577039361, 155, 5);
INSERT INTO `sys_relation` VALUES (1071348922577039362, 156, 5);
INSERT INTO `sys_relation` VALUES (1071348922577039363, 157, 5);
INSERT INTO `sys_relation` VALUES (1071348922602205185, 133, 5);
INSERT INTO `sys_relation` VALUES (1071348922610593794, 160, 5);
INSERT INTO `sys_relation` VALUES (1071348922610593795, 161, 5);
INSERT INTO `sys_relation` VALUES (1071348922618982402, 141, 5);
INSERT INTO `sys_relation` VALUES (1071348922627371009, 142, 5);
INSERT INTO `sys_relation` VALUES (1071348922631565313, 143, 5);
INSERT INTO `sys_relation` VALUES (1071348922639953922, 144, 5);
INSERT INTO `sys_relation` VALUES (1173794897620930562, 105, 1);
INSERT INTO `sys_relation` VALUES (1173794897629319169, 106, 1);
INSERT INTO `sys_relation` VALUES (1173794897637707778, 107, 1);
INSERT INTO `sys_relation` VALUES (1173794897646096385, 108, 1);
INSERT INTO `sys_relation` VALUES (1173794897654484993, 109, 1);
INSERT INTO `sys_relation` VALUES (1173794897658679297, 110, 1);
INSERT INTO `sys_relation` VALUES (1173794897667067906, 111, 1);
INSERT INTO `sys_relation` VALUES (1173794897667067907, 112, 1);
INSERT INTO `sys_relation` VALUES (1173794897667067908, 113, 1);
INSERT INTO `sys_relation` VALUES (1173794897675456514, 165, 1);
INSERT INTO `sys_relation` VALUES (1173794897683845122, 166, 1);
INSERT INTO `sys_relation` VALUES (1173794897688039425, 167, 1);
INSERT INTO `sys_relation` VALUES (1173794897692233730, 114, 1);
INSERT INTO `sys_relation` VALUES (1173794897692233731, 115, 1);
INSERT INTO `sys_relation` VALUES (1173794897700622337, 116, 1);
INSERT INTO `sys_relation` VALUES (1173794897700622338, 117, 1);
INSERT INTO `sys_relation` VALUES (1173794897713205250, 118, 1);
INSERT INTO `sys_relation` VALUES (1173794897713205251, 162, 1);
INSERT INTO `sys_relation` VALUES (1173794897746759681, 163, 1);
INSERT INTO `sys_relation` VALUES (1173794897755148290, 164, 1);
INSERT INTO `sys_relation` VALUES (1173794897759342594, 119, 1);
INSERT INTO `sys_relation` VALUES (1173794897767731201, 120, 1);
INSERT INTO `sys_relation` VALUES (1173794897767731202, 121, 1);
INSERT INTO `sys_relation` VALUES (1173794897771925505, 122, 1);
INSERT INTO `sys_relation` VALUES (1173794897771925506, 150, 1);
INSERT INTO `sys_relation` VALUES (1173794897780314113, 151, 1);
INSERT INTO `sys_relation` VALUES (1173794897788702722, 128, 1);
INSERT INTO `sys_relation` VALUES (1173794897792897026, 134, 1);
INSERT INTO `sys_relation` VALUES (1173794897792897027, 158, 1);
INSERT INTO `sys_relation` VALUES (1173794897801285633, 159, 1);
INSERT INTO `sys_relation` VALUES (1173794897801285634, 130, 1);
INSERT INTO `sys_relation` VALUES (1173794897809674242, 131, 1);
INSERT INTO `sys_relation` VALUES (1173794897809674243, 135, 1);
INSERT INTO `sys_relation` VALUES (1173794897818062849, 136, 1);
INSERT INTO `sys_relation` VALUES (1173794897826451458, 137, 1);
INSERT INTO `sys_relation` VALUES (1173794897830645761, 152, 1);
INSERT INTO `sys_relation` VALUES (1173794897830645762, 153, 1);
INSERT INTO `sys_relation` VALUES (1173794897839034370, 154, 1);
INSERT INTO `sys_relation` VALUES (1173794897847422978, 132, 1);
INSERT INTO `sys_relation` VALUES (1173794897851617281, 138, 1);
INSERT INTO `sys_relation` VALUES (1173794897860005890, 139, 1);
INSERT INTO `sys_relation` VALUES (1173794897860005891, 140, 1);
INSERT INTO `sys_relation` VALUES (1173794897868394497, 155, 1);
INSERT INTO `sys_relation` VALUES (1173794897868394498, 156, 1);
INSERT INTO `sys_relation` VALUES (1173794897876783106, 157, 1);
INSERT INTO `sys_relation` VALUES (1173794897876783107, 133, 1);
INSERT INTO `sys_relation` VALUES (1173794897885171714, 160, 1);
INSERT INTO `sys_relation` VALUES (1173794897885171715, 161, 1);
INSERT INTO `sys_relation` VALUES (1173794897893560321, 141, 1);
INSERT INTO `sys_relation` VALUES (1173794897893560322, 142, 1);
INSERT INTO `sys_relation` VALUES (1173794897901948930, 143, 1);
INSERT INTO `sys_relation` VALUES (1173794897906143234, 144, 1);
INSERT INTO `sys_relation` VALUES (1173794897906143235, 171, 1);
INSERT INTO `sys_relation` VALUES (1173794897914531841, 149, 1);
INSERT INTO `sys_relation` VALUES (1173794897914531842, 172, 1);
INSERT INTO `sys_relation` VALUES (1173794897922920449, 145, 1);
INSERT INTO `sys_relation` VALUES (1173794897931309058, 1173775159297609730, 1);
INSERT INTO `sys_relation` VALUES (1173794897939697665, 1173794812417839105, 1);
INSERT INTO `sys_relation` VALUES (1173794897939697666, 1173786015901192193, 1);

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role`  (
  `role_id` bigint(20) NOT NULL COMMENT '主键id',
  `pid` bigint(20) NULL DEFAULT NULL COMMENT '父角色id',
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '角色名称',
  `description` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '提示',
  `sort` int(11) NULL DEFAULT NULL COMMENT '序号',
  `version` int(11) NULL DEFAULT NULL COMMENT '乐观锁',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
  `create_user` bigint(20) NULL DEFAULT NULL COMMENT '创建用户',
  `update_user` bigint(20) NULL DEFAULT NULL COMMENT '修改用户',
  PRIMARY KEY (`role_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '角色表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES (1, 0, '超级管理员', 'administrator', 1, 1, NULL, NULL, NULL, NULL);
INSERT INTO `sys_role` VALUES (5, 1, '临时', 'temp', 2, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_role` VALUES (1194796038773911554, 1, '开发', 'developer', 3, NULL, '2019-11-14 09:55:49', NULL, 1, NULL);

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user`  (
  `user_id` bigint(20) NOT NULL COMMENT '主键id',
  `avatar` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '头像',
  `account` varchar(45) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '账号',
  `password` varchar(45) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '密码',
  `salt` varchar(45) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT 'md5密码盐',
  `name` varchar(45) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '名字',
  `birthday` datetime(0) NULL DEFAULT NULL COMMENT '生日',
  `sex` varchar(32) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '性别(字典)',
  `email` varchar(45) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '电子邮件',
  `phone` varchar(45) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '电话',
  `role_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '角色id(多个逗号隔开)',
  `dept_id` bigint(20) NULL DEFAULT NULL COMMENT '部门id(多个逗号隔开)',
  `status` varchar(32) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '状态(字典)',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `create_user` bigint(20) NULL DEFAULT NULL COMMENT '创建人',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `update_user` bigint(20) NULL DEFAULT NULL COMMENT '更新人',
  `version` int(11) NULL DEFAULT NULL COMMENT '乐观锁',
  PRIMARY KEY (`user_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '管理员表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES (1, '1', 'admin', '1d6b1208c7d151d335790276a18e3d99', 'q6taw', 'admin', '2019-01-01 00:00:00', 'M', 'hhzhang@ai-kang.cn', '18538834689', '1', 25, 'ENABLE', '2016-01-29 08:49:53', NULL, '2019-09-17 09:56:56', 24, 25);
SET FOREIGN_KEY_CHECKS = 1;


###导出 znkf_admin 的数据库结构
CREATE DATABASE IF NOT EXISTS `znkf_admin` DEFAULT CHARSET utf8 COLLATE utf8_general_ci;
USE `znkf_admin`;
SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for num
-- ----------------------------
DROP TABLE IF EXISTS `num`;
CREATE TABLE `num`  (
  `i` int(11) NULL DEFAULT NULL
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_unicode_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO num (i) VALUES (0),(1),(2),(3),(4),(5),(6),(7),(8),(9);

-- ----------------------------
-- Table structure for sys_exception_key_word
-- ----------------------------
DROP TABLE IF EXISTS `sys_exception_key_word`;
CREATE TABLE `sys_exception_key_word`  (
  `id` int(11) UNSIGNED ZEROFILL NOT NULL AUTO_INCREMENT,
  `key_word` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '关键词',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 126 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '异常信息关键字表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for sys_exception_log
-- ----------------------------
DROP TABLE IF EXISTS `sys_exception_log`;
CREATE TABLE `sys_exception_log`  (
  `id` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '主键',
  `system` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '系统名称编号 1.znkf_pc_manage 2.kmc 3.rhbshop',
  `run_environment` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '运行环境',
  `host` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'ip地址',
  `port` int(10) NULL DEFAULT NULL COMMENT '端口号',
  `createtime` datetime(0) NULL DEFAULT NULL COMMENT '异常发生时间',
  `content` longtext CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '异常内容',
  `remark` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '备注',
  `view_num` int(11) NULL DEFAULT 0 COMMENT '浏览次数',
  `key_word` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '关键字id(,分隔)',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_unicode_ci COMMENT = '后台异常信息表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for sys_exception_operate
-- ----------------------------
DROP TABLE IF EXISTS `sys_exception_operate`;
CREATE TABLE `sys_exception_operate`  (
  `id` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `exception_id` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '异常id',
  `msg` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '操作',
  `user_name` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '操作人',
  `content` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '具体信息',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '操作时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '异常日志操作记录表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for sys_exception_solution
-- ----------------------------
DROP TABLE IF EXISTS `sys_exception_solution`;
CREATE TABLE `sys_exception_solution`  (
  `id` int(11) UNSIGNED ZEROFILL NOT NULL AUTO_INCREMENT,
  `solution` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '解决办法',
  `review` int(3) NULL DEFAULT 0 COMMENT '审核 0:通过  1:不通过',
  `remark` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '预留字段',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 83 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '异常解决办法' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for sys_exception_special_words
-- ----------------------------
DROP TABLE IF EXISTS `sys_exception_special_words`;
CREATE TABLE `sys_exception_special_words`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `special_words` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '特殊显示的字符',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 24 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_exception_special_words
-- ----------------------------
INSERT INTO `sys_exception_special_words` VALUES (1, 'Could not get JDBC Connection');
INSERT INTO `sys_exception_special_words` VALUES (2, 'org.springframework.jdbc.BadSqlGrammarException');
INSERT INTO `sys_exception_special_words` VALUES (3, 'org.mybatis.spring.MyBatisSystemException');
INSERT INTO `sys_exception_special_words` VALUES (4, 'Unknown column');
INSERT INTO `sys_exception_special_words` VALUES (5, 'A query was run and no Result Maps were found');
INSERT INTO `sys_exception_special_words` VALUES (6, 'You have an error in your SQL syntax');
INSERT INTO `sys_exception_special_words` VALUES (7, 'by zero');
INSERT INTO `sys_exception_special_words` VALUES (8, 'Parameter');
INSERT INTO `sys_exception_special_words` VALUES (9, 'not found');
INSERT INTO `sys_exception_special_words` VALUES (10, 'java.lang.NullPointerException');
INSERT INTO `sys_exception_special_words` VALUES (11, 'TooManyResultsException');
INSERT INTO `sys_exception_special_words` VALUES (12, 'Expected one result (or null) to be returned by selectOne(), but found');
INSERT INTO `sys_exception_special_words` VALUES (13, 'The error may involve defaultParameterMap');
INSERT INTO `sys_exception_special_words` VALUES (14, 'Available parameters are');
INSERT INTO `sys_exception_special_words` VALUES (15, 'Could not instantiate property type');
INSERT INTO `sys_exception_special_words` VALUES (16, 'Illegal attempt to get property');
INSERT INTO `sys_exception_special_words` VALUES (17, 'Table');
INSERT INTO `sys_exception_special_words` VALUES (18, "doesn't exist");
INSERT INTO `sys_exception_special_words` VALUES (19, 'com.zkhc.znkf.rest');
INSERT INTO `sys_exception_special_words` VALUES (20, 'The error occurred while setting parameters');
INSERT INTO `sys_exception_special_words` VALUES (21, 'java.lang.NumberFormatException');
INSERT INTO `sys_exception_special_words` VALUES (22, 'For input string');
INSERT INTO `sys_exception_special_words` VALUES (23, 'application/x-www-form-urlencoded');

-- ----------------------------
-- Table structure for sys_solution_relation
-- ----------------------------
DROP TABLE IF EXISTS `sys_solution_relation`;
CREATE TABLE `sys_solution_relation`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `key_word_id` int(11) NOT NULL COMMENT '关键词id',
  `solution_id` int(11) NOT NULL COMMENT '解决办法id',
  PRIMARY KEY (`id`, `key_word_id`, `solution_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 83 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '关键字与解决办法对照表' ROW_FORMAT = Dynamic;
SET FOREIGN_KEY_CHECKS = 1;


create user 'zkhc_exception_user'@'%' identified by 'Lyzk.ex,2019';
GRANT SELECT, INSERT, DELETE, CREATE, DROP, INDEX, CREATE VIEW, SHOW VIEW, CREATE ROUTINE, ALTER ROUTINE, TRIGGER ON `logging`.* TO 'zkhc_exception_user'@'%';
GRANT SELECT, INSERT, UPDATE, DELETE, REFERENCES, INDEX, LOCK TABLES, EXECUTE, SHOW VIEW, EVENT, TRIGGER ON `zkhc_exception_admin`.* TO 'zkhc_exception_user'@'%';
create user 'zkhc_admin'@'%' identified by 'Lykj.2018';
GRANT SELECT, INSERT, UPDATE, DELETE, REFERENCES, INDEX, LOCK TABLES, EXECUTE, SHOW VIEW, EVENT, TRIGGER ON `znkf_admin`.* TO 'zkhc_admin'@'%';
flush privileges;
