CREATE TABLE `dp_worker`
(
    `worker_id`    varchar(32)  NOT NULL COMMENT '工作节点id',
    `handler_code` varchar(128) NOT NULL COMMENT '执行器类型',
    `cluster`      varchar(128) NOT NULL COMMENT '集群',
    `host`         varchar(128) NOT NULL COMMENT '域名',
    `port`         int          NOT NULL COMMENT '端口',
    `last_time`    bigint       NOT NULL COMMENT '最近时间',
    `created_time` datetime     NOT NULL COMMENT '创建时间',
    PRIMARY KEY (`worker_id`),
    KEY `worker_handler_code_idx` (`handler_code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='工作节点实例';
