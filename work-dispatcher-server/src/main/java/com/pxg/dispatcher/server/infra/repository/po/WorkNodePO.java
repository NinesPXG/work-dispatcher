package com.pxg.dispatcher.server.infra.repository.po;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@TableName("dp_worker")
public class WorkNodePO {

    @TableId
    private String workerId;

    private String handlerCode;

    private String cluster;

    private String host;

    private Integer port;

    private long lastTime;

    private LocalDateTime createdTime;

}
