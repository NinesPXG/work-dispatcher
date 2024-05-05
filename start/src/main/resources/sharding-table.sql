
CREATE TABLE `user_info`
(
    `account_id`      varchar(32)  NOT NULL COMMENT '用户编号',
    `nick_name`       varchar(128) NOT NULL COMMENT '用户昵称',
    `sex`             int                   DEFAULT NULL COMMENT '性别',
    `user_name`       varchar(128) NOT NULL COMMENT '用户姓名',
    `id_number`       varchar(32)  NOT NULL COMMENT '身份证号',
    `phone_number`    varchar(32)           DEFAULT NULL COMMENT '电话号码',
    `contact_address` varchar(128)          DEFAULT NULL COMMENT '联系地址',
    `state`           int          NOT NULL DEFAULT '0' COMMENT '状态',
    `revision`        int          NOT NULL DEFAULT '0' COMMENT '乐观锁',
    `deleted`         varchar(1)   NOT NULL DEFAULT '0' COMMENT '逻辑删除',
    `latest_time`     datetime     NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '最近时间',
    PRIMARY KEY (`account_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='用户信息 ';


CREATE TABLE `term`
(
    `term_id`      varchar(32)  NOT NULL COMMENT '期次ID',
    `term_name`    varchar(128) NOT NULL COMMENT '期次名称',
    `term_type`    varchar(32)  NOT NULL COMMENT '期次类型',
    `annual`       int          NOT NULL COMMENT '年度',
    `remark`       varchar(512) DEFAULT NULL COMMENT '备注',
    `state`        int          NOT NULL COMMENT '状态',
    `revision`     int          NOT NULL COMMENT '乐观锁',
    `deleted`      varchar(1)   NOT NULL COMMENT '逻辑删除',
    `created_by`   varchar(32)  NOT NULL COMMENT '创建人',
    `created_time` datetime     NOT NULL COMMENT '创建时间',
    `updated_by`   varchar(32)  NOT NULL COMMENT '更新人',
    `updated_time` datetime     NOT NULL COMMENT '更新时间',
    PRIMARY KEY (`term_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='期次 ';
