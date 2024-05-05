package com.pxg.demo.infrastructure.repository.convertor;

import com.pxg.demo.client.dto.request.TermSaveCmd;
import com.pxg.demo.client.dto.response.TermDTO;
import com.pxg.demo.domain.term.Term;
import com.pxg.demo.infrastructure.repository.po.TermPO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface TermConvertor {

    TermConvertor INSTANCE = Mappers.getMapper(TermConvertor.class);

    TermDTO do2Dto(Term term);

    Term cmd2Do(TermSaveCmd cmd);

    Term po2Do(TermPO po);

    TermPO do2Po(Term term);

}
