package com.pxg.dispatcher.client.service;

import com.pxg.dispatcher.client.configuration.WorkerConfig;
import com.pxg.dispatcher.core.entity.WorkNode;
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
    public String register(WorkNode workerInfo) {
        return workerServiceI.register(workerInfo);
    }

    @Override
    public boolean cancel(String workerId) {
        return workerServiceI.cancel(workerId);
    }

    @Override
    public List<WorkNode> getWorkers(String handlerCode) {
        List<WorkNode> workNodes = workerServiceI.getWorkers(handlerCode);
        return workNodes.parallelStream().filter(WorkNode::isAvailable).collect(Collectors.toList());
    }

    @Override
    public List<WorkNode> getWorkers() {
        return getWorkers(workerConfig.getHandlerCode());
    }

}
