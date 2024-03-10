package com.pxg.dispatcher.client.configuration;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties(prefix = "worker-config")
public class WorkerConfig {

    private String centerUri;

    private String handlerCode;

    private int heartbeatTime;

    private int initDelay;

    private int port;

}
