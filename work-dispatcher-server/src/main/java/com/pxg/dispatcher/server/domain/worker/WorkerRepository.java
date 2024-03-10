package com.pxg.dispatcher.server.domain.worker;

import com.pxg.dispatcher.core.entity.WorkNode;

import java.util.List;

public interface WorkerRepository {

    boolean register(WorkNode workNode);

    boolean cancel(String workerId);

    List<WorkNode> getRecentWorkers(String handlerCode);

}
