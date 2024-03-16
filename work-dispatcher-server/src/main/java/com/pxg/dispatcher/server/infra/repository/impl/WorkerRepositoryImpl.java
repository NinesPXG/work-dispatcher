package com.pxg.dispatcher.server.infra.repository.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.pxg.dispatcher.core.common.RemoteConst;
import com.pxg.dispatcher.core.entity.WorkerNode;
import com.pxg.dispatcher.server.domain.worker.WorkerRepository;
import com.pxg.dispatcher.server.infra.repository.convertor.WorkerConvertor;
import com.pxg.dispatcher.server.infra.repository.mapper.WorkNodeMapper;
import com.pxg.dispatcher.server.infra.repository.po.WorkerNodePO;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class WorkerRepositoryImpl extends ServiceImpl<WorkNodeMapper, WorkerNodePO> implements WorkerRepository {


    @Override
    public boolean register(WorkerNode workerNode) {
        return saveOrUpdate(WorkerConvertor.INSTANCE.toPo(workerNode));
    }

    @Override
    public boolean cancel(String workerId) {
        return removeById(workerId);
    }

    @Override
    public List<WorkerNode> getRecentWorkers(String handlerCode) {
        LambdaQueryWrapper<WorkerNodePO> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(WorkerNodePO::getHandlerCode, handlerCode);
        wrapper.ge(WorkerNodePO::getLastTime, System.currentTimeMillis() - RemoteConst.getDeadInterval() * 1000L);
        return WorkerConvertor.INSTANCE.toEntity(getBaseMapper().selectList(wrapper));
    }

}
