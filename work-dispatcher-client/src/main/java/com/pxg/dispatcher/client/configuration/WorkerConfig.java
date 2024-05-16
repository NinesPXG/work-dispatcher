package com.pxg.dispatcher.client.configuration;

import com.pxg.dispatcher.core.model.Worker;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.Arrays;
import java.util.List;


@ConfigurationProperties(prefix = "worker-config")
public class WorkerConfig {

    private String centerUri;

    private String handlerCode;

    private int heartbeatTime;

    private int initDelay;

    private int port;

    public SecurityConfig security = new SecurityConfig();


    public String getCenterUri() {
        return centerUri;
    }

    public void setCenterUri(String centerUri) {
        this.centerUri = centerUri;
    }

    public String getHandlerCode() {
        return handlerCode;
    }

    public void setHandlerCode(String handlerCode) {
        this.handlerCode = handlerCode;
    }

    public int getHeartbeatTime() {
        return heartbeatTime;
    }

    public void setHeartbeatTime(int seconds) {
        this.heartbeatTime = seconds;
    }

    public int getInitDelay() {
        return initDelay;
    }

    public void setInitDelay(int seconds) {
        this.initDelay = seconds;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }


    public SecurityConfig getSecurity() {
        return security;
    }

    public void setSecurity(SecurityConfig security) {
        this.security = security;
    }


    public static class SecurityConfig {

        private boolean enable = true;

        private List<String> whiteList = List.of(Worker.class.getName());


        public boolean isEnable() {
            return enable;
        }

        public void setEnable(boolean enable) {
            this.enable = enable;
        }

        public List<String> getWhiteList() {
            return whiteList;
        }

        public void setWhiteList(String whiteList) {
            this.whiteList = Arrays.asList(whiteList.split(","));
        }

    }

}
