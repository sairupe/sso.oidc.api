CREATE TABLE `account_info` (
  `account_id` bigint(20) NOT NULL COMMENT '账号ID',
  `password` varchar(32) NOT NULL COMMENT '账号密码',
  `user_name` varchar(64) NOT NULL COMMENT '用户名',
  `created_by` bigint(20) DEFAULT NULL COMMENT '创建人ID',
  `created_tm` bigint(20) DEFAULT NULL COMMENT '创建时间',
  `updated_by` bigint(20) DEFAULT NULL COMMENT '修改或删除人ID',
  `updated_tm` bigint(20) DEFAULT NULL COMMENT '修改或删除时间',
  `is_flag` tinyint(4) DEFAULT '1' COMMENT '逻辑删除is_flag tinyint,1:正常|0:删除，默认值为1',
  `last_update_tm` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '运维建设专用',
  PRIMARY KEY (account_id) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC COMMENT='账号表';

CREATE TABLE `oauth_client_details` (
  `oauth_client_details_id` bigint(20) NOT NULL COMMENT '主键ID',
  `client_id` varchar(255) NOT NULL COMMENT '客户端标识',
  `resource_ids` varchar(255) DEFAULT NULL COMMENT '接入资源列表',
  `client_secret` varchar(255) DEFAULT NULL COMMENT '客户端秘钥',
  `scope` varchar(255) DEFAULT NULL,
  `authorized_grant_types` varchar(255) DEFAULT NULL,
  `web_server_redirect_uri` varchar(255) DEFAULT NULL,
  `authorities` varchar(255) DEFAULT NULL,
  `access_token_validity` int(11) DEFAULT NULL,
  `refresh_token_validity` int(11) DEFAULT NULL,
  `additional_information` LONGTEXT DEFAULT NULL,
  `archived` tinyint(4) DEFAULT NULL,
  `trusted` tinyint(4) DEFAULT NULL,
  `autoapprove` varchar(255) DEFAULT NULL,
  `created_by` bigint(20) DEFAULT NULL COMMENT '创建人ID',
  `created_tm` bigint(20) DEFAULT NULL COMMENT '创建时间',
  `updated_by` bigint(20) DEFAULT NULL COMMENT '修改或删除人ID',
  `updated_tm` bigint(20) DEFAULT NULL COMMENT '修改或删除时间',
  `is_flag` tinyint(4) DEFAULT '1' COMMENT '逻辑删除is_flag tinyint,1:正常|0:删除，默认值为1',
  `last_update_tm` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '运维建设专用',
  PRIMARY KEY ( `oauth_client_details_id` ) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC COMMENT='接入客户信息';

CREATE TABLE `oauth_code` (
  `oauth_code_id` bigint(20) NOT NULL COMMENT '主键ID',
  `code` VARCHAR ( 255 ) DEFAULT NULL,
  `authentication` BLOB NULL,
  `created_by` BIGINT ( 20 ) DEFAULT NULL COMMENT '创建人ID',
  `created_tm` BIGINT ( 20 ) DEFAULT NULL COMMENT '创建时间',
  `updated_by` BIGINT ( 20 ) DEFAULT NULL COMMENT '修改或删除人ID',
  `updated_tm` BIGINT ( 20 ) DEFAULT NULL COMMENT '修改或删除时间',
  `is_flag` TINYINT ( 4 ) DEFAULT '1' COMMENT '逻辑删除is_flag tinyint,1:正常|0:删除，默认值为1',
  `last_update_tm` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '运维建设专用',
  PRIMARY KEY ( `oauth_code_id` ) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC COMMENT='授权码表';