package com.pxg.demo.client.dto.request;

import com.pxg.demo.client.dto.common.BasePageQry;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class TermPageQry extends BasePageQry {

    @Schema(description = "年度")
    @NotNull(message = "年度不为空")
    private Integer annual;

    @Schema(description = "期次名称")
    private String termName;

    @Schema(description = "期次类型")
    private String termType;

    @Schema(description = "状态")
    private Integer state;

}
