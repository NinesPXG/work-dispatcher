package com.pxg.demo.domain.term;

import com.pxg.demo.client.dto.common.PageReturnT;
import com.pxg.demo.client.dto.request.TermPageQry;

public interface TermRepository {

    boolean create(Term term);

    boolean update(Term term);

    boolean remove(String termId, Integer annual);

    PageReturnT<Term> page(TermPageQry qry);
}
