package com.pxg.dispatcher.core.service;

import com.pxg.dispatcher.core.entity.WorkerNode;

import java.util.List;

public interface WorkerServiceI {

    String register(WorkerNode workerNode);

    boolean cancel(String workerId);

    List<WorkerNode> getWorkers(String handlerCode);

}
