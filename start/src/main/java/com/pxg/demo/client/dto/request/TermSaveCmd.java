package com.pxg.demo.client.dto.request;

import com.pxg.demo.client.dto.common.UpdateAction;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class TermSaveCmd {

    @Schema(description = "期次ID")
    @NotBlank(message = "期次Id不能为空", groups = {UpdateAction.class})
    private String termId;

    @Schema(description = "期次名称")
    @NotBlank(message = "期次名称不能为空")
    @Size(max = 128)
    private String termName;

    @Schema(description = "期次类型")
    @NotBlank(message = "期次类型不能为空")
    private String termType;

    @Schema(description = "年度")
    @NotNull(message = "年度不能为空")
    private Integer annual;

    @Schema(description = "备注")
    @Size(max = 512)
    private String remark;

    @Schema(description = "状态")
    private Integer state;
}
