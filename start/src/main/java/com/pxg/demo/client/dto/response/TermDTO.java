package com.pxg.demo.client.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class TermDTO {

    @Schema(description = "期次ID")
    private String termId;

    @Schema(description = "期次名称")
    private String termName;

    @Schema(description = "期次类型")
    private String termType;

    @Schema(description = "年度")
    private Integer annual;

    @Schema(description = "备注")
    private String remark;

    @Schema(description = "状态")
    private Integer state;
}
