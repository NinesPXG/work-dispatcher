package com.pxg.dispatcher.server.domain.worker;

import com.pxg.dispatcher.core.entity.WorkerNode;

import java.util.List;

public interface WorkerRepository {

    boolean register(WorkerNode workerNode);

    boolean cancel(String workerId);

    List<WorkerNode> getRecentWorkers(String handlerCode);

}
