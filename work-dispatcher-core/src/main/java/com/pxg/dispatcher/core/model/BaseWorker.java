package com.pxg.dispatcher.core.model;

import com.pxg.dispatcher.core.entity.WorkNode;
import org.springframework.util.StringUtils;

public abstract class BaseWorker implements Worker {

    private String workerType;

    private String host;

    private int port;


    public void bindWorkNode(WorkNode workerNode) {
        this.host = workerNode.getHost();
        this.port = workerNode.getPort();
    }

    public void setWorkerType(String workerType) {
        this.workerType = workerType;
    }

    @Override
    public String getWorkerType() {
        return StringUtils.hasText(workerType) ? workerType : getWorkerJar();
    }

    @Override
    public String getHost() {
        return host;
    }

    @Override
    public int getPort() {
        return port;
    }

}
