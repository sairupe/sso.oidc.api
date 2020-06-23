CREATE TABLE `account_info` (
  `account_id` bigint(20) NOT NULL COMMENT '账号ID',
  `password` bigint(20) NOT NULL COMMENT '账号密码',
  `deleted_by` bigint(20) DEFAULT NULL COMMENT '删除人ID',
  `deleted_tm` bigint(20) DEFAULT NULL COMMENT '删除时间戳',
  `created_by` bigint(20) DEFAULT NULL COMMENT '创建人ID',
  `created_tm` bigint(20) DEFAULT NULL COMMENT '创建时间',
  `updated_by` bigint(20) DEFAULT NULL COMMENT '修改或删除人ID',
  `updated_tm` bigint(20) DEFAULT NULL COMMENT '修改或删除时间',
  `is_flag` tinyint(4) DEFAULT '1' COMMENT '逻辑删除is_flag tinyint,1:正常|0:删除，默认值为1',
  `last_update_tm` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '运维建设专用',
  PRIMARY KEY (account_id) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC COMMENT='账号表';