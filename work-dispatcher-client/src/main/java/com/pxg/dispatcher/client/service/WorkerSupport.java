package com.pxg.dispatcher.client.service;


import com.pxg.dispatcher.core.entity.WorkNode;

import java.util.List;

public interface WorkerSupport {

    String register(WorkNode workerInfo);

    boolean cancel(String workerId);

    List<WorkNode> getWorkers(String handlerCode);

    default List<WorkNode> getWorkers() {
        return getWorkers("");
    }

}
