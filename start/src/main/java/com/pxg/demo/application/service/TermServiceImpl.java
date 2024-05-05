package com.pxg.demo.application.service;

import cn.hutool.core.util.IdUtil;
import com.pxg.demo.client.api.TermServiceI;
import com.pxg.demo.client.dto.common.PageReturnT;
import com.pxg.demo.client.dto.common.ReturnInfo;
import com.pxg.demo.client.dto.request.TermPageQry;
import com.pxg.demo.client.dto.request.TermSaveCmd;
import com.pxg.demo.client.dto.response.TermDTO;
import com.pxg.demo.domain.term.Term;
import com.pxg.demo.domain.term.TermRepository;
import com.pxg.demo.infrastructure.repository.convertor.TermConvertor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TermServiceImpl implements TermServiceI {

    private final TermRepository termRepository;

    @Override
    public ReturnInfo create(TermSaveCmd cmd) {
        Term term = TermConvertor.INSTANCE.cmd2Do(cmd);
        term.setTermId(IdUtil.getSnowflakeNextIdStr());
        termRepository.create(term);
        return ReturnInfo.buildSuccess();
    }

    @Override
    public ReturnInfo update(TermSaveCmd cmd) {
        Term term = TermConvertor.INSTANCE.cmd2Do(cmd);
        termRepository.update(term);
        return ReturnInfo.buildSuccess();
    }

    @Override
    public ReturnInfo remove(TermDTO dto) {
        termRepository.remove(dto.getTermId(), dto.getAnnual());
        return ReturnInfo.buildSuccess();
    }

    @Override
    public PageReturnT<TermDTO> page(TermPageQry qry) {
        PageReturnT<Term> result = termRepository.page(qry);
        return PageReturnT.of(result.getData(), TermConvertor.INSTANCE::do2Dto,
                result.getTotalCount(), result.getPageSize(), result.getPageIndex());
    }
}
