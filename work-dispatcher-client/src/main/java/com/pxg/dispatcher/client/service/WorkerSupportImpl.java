package com.pxg.dispatcher.client.service;

import com.pxg.dispatcher.client.configuration.WorkerConfig;
import com.pxg.dispatcher.core.entity.WorkerNode;
import com.pxg.dispatcher.core.service.WorkerServiceI;

import java.util.List;
import java.util.stream.Collectors;

public class WorkerSupportImpl implements WorkerSupport {

    private final WorkerServiceI workerServiceI;
    private final WorkerConfig workerConfig;

    public WorkerSupportImpl(WorkerServiceI workerServiceI, WorkerConfig workerConfig) {
        this.workerServiceI = workerServiceI;
        this.workerConfig = workerConfig;
    }

    @Override
    public String register(WorkerNode workerNode) {
        return workerServiceI.register(workerNode);
    }

    @Override
    public boolean cancel(String workerId) {
        return workerServiceI.cancel(workerId);
    }

    @Override
    public List<WorkerNode> getWorkers(String handlerCode) {
        List<WorkerNode> workerNodes = workerServiceI.getWorkers(handlerCode);
        return workerNodes.parallelStream().filter(WorkerNode::isAvailable).collect(Collectors.toList());
    }

    @Override
    public List<WorkerNode> getWorkers() {
        return getWorkers(workerConfig.getHandlerCode());
    }

}
