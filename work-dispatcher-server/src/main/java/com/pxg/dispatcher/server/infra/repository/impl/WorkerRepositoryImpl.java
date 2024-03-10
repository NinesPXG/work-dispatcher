package com.pxg.dispatcher.server.infra.repository.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.pxg.dispatcher.core.common.RemoteConst;
import com.pxg.dispatcher.core.entity.WorkNode;
import com.pxg.dispatcher.server.domain.worker.WorkerRepository;
import com.pxg.dispatcher.server.infra.repository.convertor.WorkerConvertor;
import com.pxg.dispatcher.server.infra.repository.mapper.WorkNodeMapper;
import com.pxg.dispatcher.server.infra.repository.po.WorkNodePO;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class WorkerRepositoryImpl extends ServiceImpl<WorkNodeMapper, WorkNodePO> implements WorkerRepository {


    @Override
    public boolean register(WorkNode workNode) {
        return saveOrUpdate(WorkerConvertor.INSTANCE.toPo(workNode));
    }

    @Override
    public boolean cancel(String workerId) {
        return removeById(workerId);
    }

    @Override
    public List<WorkNode> getRecentWorkers(String handlerCode) {
        LambdaQueryWrapper<WorkNodePO> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(WorkNodePO::getHandlerCode, handlerCode);
        wrapper.ge(WorkNodePO::getLastTime, System.currentTimeMillis() - RemoteConst.getDeadInterval() * 1000L);
        return WorkerConvertor.INSTANCE.toEntity(getBaseMapper().selectList(wrapper));
    }

}
