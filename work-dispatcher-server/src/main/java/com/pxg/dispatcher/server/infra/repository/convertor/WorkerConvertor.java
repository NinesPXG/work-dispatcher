package com.pxg.dispatcher.server.infra.repository.convertor;

import com.pxg.dispatcher.core.entity.WorkerNode;
import com.pxg.dispatcher.server.infra.repository.po.WorkerNodePO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface WorkerConvertor {

    WorkerConvertor INSTANCE = Mappers.getMapper(WorkerConvertor.class);

    List<WorkerNode> toEntity(List<WorkerNodePO> list);

    WorkerNode toEntity(WorkerNodePO po);

    WorkerNodePO toPo(WorkerNode workerNode);

}
