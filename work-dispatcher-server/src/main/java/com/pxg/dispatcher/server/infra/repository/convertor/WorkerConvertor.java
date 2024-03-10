package com.pxg.dispatcher.server.infra.repository.convertor;

import com.pxg.dispatcher.core.entity.WorkNode;
import com.pxg.dispatcher.server.infra.repository.po.WorkNodePO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface WorkerConvertor {

    WorkerConvertor INSTANCE = Mappers.getMapper(WorkerConvertor.class);

    List<WorkNode> toEntity(List<WorkNodePO> list);

    WorkNode toEntity(WorkNodePO po);

    WorkNodePO toPo(WorkNode workerNode);

}
