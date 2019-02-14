CREATE TABLE `db_conf` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(32) NOT NULL COMMENT '配置名称',
  `db_name` varchar(32) NOT NULL COMMENT '数据名',
  `type` tinyint(4) NOT NULL COMMENT '数据类型 1-mysql  2-oracle',
  `host` varchar(32) NOT NULL COMMENT '数据库地址',
  `port` int(11) NOT NULL COMMENT '端口',
  `user_name` varchar(32) NOT NULL COMMENT '用户名',
  `password` varchar(32) NOT NULL COMMENT '密码',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `modify_time` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8 COMMENT='数据库配置表';
