package com.pxg.demo.adapter;

import com.pxg.demo.client.api.TermServiceI;
import com.pxg.demo.client.dto.common.PageReturnT;
import com.pxg.demo.client.dto.common.ReturnInfo;
import com.pxg.demo.client.dto.common.UpdateAction;
import com.pxg.demo.client.dto.request.TermPageQry;
import com.pxg.demo.client.dto.request.TermSaveCmd;
import com.pxg.demo.client.dto.response.TermDTO;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/term")
public class TermController {

    private final TermServiceI termServiceI;

    public TermController(TermServiceI termServiceI) {
        this.termServiceI = termServiceI;
    }

    @PostMapping("create")
    public ReturnInfo create(@RequestBody @Validated TermSaveCmd cmd) {
        return termServiceI.create(cmd);
    }

    @PostMapping("update")
    public ReturnInfo update(@RequestBody @Validated(value = {UpdateAction.class}) TermSaveCmd cmd) {
        return termServiceI.update(cmd);
    }

    @PostMapping("remove")
    public ReturnInfo remove(TermDTO dto) {
        return termServiceI.remove(dto);
    }

    @GetMapping("page")
    public PageReturnT<TermDTO> page(@Validated TermPageQry qry) {
        return termServiceI.page(qry);
    }

}
