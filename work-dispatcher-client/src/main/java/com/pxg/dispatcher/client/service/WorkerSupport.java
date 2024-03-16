package com.pxg.dispatcher.client.service;


import com.pxg.dispatcher.core.entity.WorkerNode;

import java.util.List;

public interface WorkerSupport {

    String register(WorkerNode workerInfo);

    boolean cancel(String workerId);

    List<WorkerNode> getWorkers(String handlerCode);

    default List<WorkerNode> getWorkers() {
        return getWorkers("");
    }

}
