package com.pxg.dispatcher.core.service;

import com.pxg.dispatcher.core.entity.WorkNode;

import java.util.List;

public interface WorkerServiceI {

    String register(WorkNode workerInfo);

    boolean cancel(String workerId);

    List<WorkNode> getWorkers(String handlerCode);

}
