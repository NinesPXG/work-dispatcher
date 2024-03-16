package com.pxg.dispatcher.server.service.impl;

import cn.hutool.core.util.IdUtil;
import com.pxg.dispatcher.core.entity.WorkerNode;
import com.pxg.dispatcher.core.service.WorkerServiceI;
import com.pxg.dispatcher.server.domain.worker.WorkerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

@Service
@RequiredArgsConstructor
public class WorkerServiceImpl implements WorkerServiceI {

    private final WorkerRepository workerRepository;

    @Override
    public String register(WorkerNode workerNode) {
        if (!StringUtils.hasText(workerNode.getWorkerId())) {
            workerNode.setWorkerId(IdUtil.getSnowflakeNextIdStr());
        }
        workerRepository.register(workerNode);
        return workerNode.getWorkerId();
    }

    @Override
    public boolean cancel(String workerId) {
        return workerRepository.cancel(workerId);
    }

    @Override
    public List<WorkerNode> getWorkers(String handlerCode) {
        return workerRepository.getRecentWorkers(handlerCode);
    }
}
