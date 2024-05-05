package com.pxg.demo.infrastructure.repository.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.pxg.demo.client.dto.common.PageReturnT;
import com.pxg.demo.client.dto.request.TermPageQry;
import com.pxg.demo.domain.term.Term;
import com.pxg.demo.domain.term.TermRepository;
import com.pxg.demo.infrastructure.repository.convertor.TermConvertor;
import com.pxg.demo.infrastructure.repository.mapper.TermMapper;
import com.pxg.demo.infrastructure.repository.po.TermPO;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import java.util.Objects;

@Repository
public class TermRepositoryImpl extends ServiceImpl<TermMapper, TermPO> implements TermRepository {

    @Override
    public boolean create(Term term) {
        TermPO po = TermConvertor.INSTANCE.do2Po(term);
        return save(po);
    }

    @Override
    public boolean update(Term term) {
        TermPO po = TermConvertor.INSTANCE.do2Po(term);
        return updateById(po);
    }

    @Override
    public boolean remove(String termId, Integer annual) {
        return remove(lambdaQuery()
                .eq(TermPO::getTermId, termId)
                .eq(TermPO::getAnnual, annual));
    }

    @Override
    public PageReturnT<Term> page(TermPageQry qry) {
        LambdaQueryWrapper<TermPO> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(TermPO::getAnnual, qry.getAnnual())
                .eq(StringUtils.hasText(qry.getTermType()), TermPO::getTermType, qry.getTermType())
                .like(StringUtils.hasText(qry.getTermName()), TermPO::getTermName, qry.getTermName())
                .eq(Objects.nonNull(qry.getState()), TermPO::getState, qry.getState());

        IPage<TermPO> page = new Page<>(qry.getPageIndex(), qry.getPageSize());
        page(page, wrapper);
        return PageReturnT.of(page.getRecords(), TermConvertor.INSTANCE::po2Do, page.getTotal(), page.getSize(), page.getCurrent());
    }

}
