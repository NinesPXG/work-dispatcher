package com.pxg.demo.client.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

@Data
public class UserUpdateCmd {

    @Schema(description = "用户编号")
    @NotBlank(message = "用户编号不能为空")
    private String accountId;

    @Schema(description = "用户昵称")
    @NotBlank(message = "用户昵称不能为空")
    private String nickName;

    @Schema(description = "性别")
    @Min(0)
    @Max(1)
    private Integer sex;

    @Schema(description = "电话号码")
    private String phoneNumber;

    @Schema(description = "联系地址")
    private String contactAddress;

    @Schema(description = "状态")
    private Integer state;

}
