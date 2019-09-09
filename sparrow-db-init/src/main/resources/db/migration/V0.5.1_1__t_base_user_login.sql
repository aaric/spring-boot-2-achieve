DROP TABLE IF EXISTS `t_base_user_login`;

CREATE TABLE IF NOT EXISTS `t_base_user_login` (
  `id` BIGINT UNSIGNED NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `user_name` VARCHAR(50) COMMENT '用户名',
  `password` VARCHAR(32) NOT NULL DEFAULT '' COMMENT '密码',
  `password_salt` VARCHAR(10) NOT NULL DEFAULT '' COMMENT '密码盐',
  `real_name` VARCHAR(50) NOT NULL DEFAULT '' COMMENT '真实姓名',
  `avatar_path` VARCHAR(200) NOT NULL DEFAULT '' COMMENT '用户头像路径',
  `email` VARCHAR(100) NOT NULL DEFAULT '' COMMENT '电子邮箱',
  `phone_number` VARCHAR(15) NOT NULL DEFAULT '' COMMENT '手机号',
  `identity_number` VARCHAR(18) NOT NULL DEFAULT '' COMMENT '身份证号',
  `status` TINYINT(4) UNSIGNED NOT NULL DEFAULT '1' COMMENT '状态: 1-可用, 2-禁用',
  `remark` VARCHAR(500) NOT NULL DEFAULT '' COMMENT '备注',
  `create_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '记录创建时间',
  PRIMARY KEY `idx_primary_id` (`id`),
  UNIQUE KEY `idx_unique_user_name` (`user_name`),
  UNIQUE KEY `idx_unique_email` (`email`),
  UNIQUE KEY `idx_unique_phone_number` (`phone_number`),
  UNIQUE KEY `idx_unique_identity_number` (`identity_number`)
) ENGINE=InnoDB COMMENT='基础用户登录表';