package com.pxg.demo.client.api;

import com.pxg.demo.client.dto.common.PageReturnT;
import com.pxg.demo.client.dto.common.ReturnInfo;
import com.pxg.demo.client.dto.request.TermPageQry;
import com.pxg.demo.client.dto.request.TermSaveCmd;
import com.pxg.demo.client.dto.response.TermDTO;

public interface TermServiceI {

    ReturnInfo create(TermSaveCmd cmd);

    ReturnInfo update(TermSaveCmd cmd);

    ReturnInfo remove(TermDTO dto);

    PageReturnT<TermDTO> page(TermPageQry qry);
}
