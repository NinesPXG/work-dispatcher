package com.pxg.demo.client.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.*;

@Data
public class UserCreateCmd {

    @Schema(description = "用户编号")
    @Pattern(regexp = "[0-9]{6,10}",message = "用户编号为6-10位数字")
    private String accountId;

    @Schema(description = "用户昵称")
    @NotBlank(message = "用户昵称不能为空")
    @Size(max = 128, message = "昵称长度上限128")
    private String nickName;

    @Schema(description = "性别")
    @Min(0)
    @Max(1)
    private Integer sex;

    @Schema(description = "用户姓名")
    @NotBlank(message = "身份信息未绑定")
    @Size(max = 128)
    private String userName;

    @Schema(description = "身份证号")
    @NotBlank(message = "身份信息未绑定")
    @Size(max = 32)
    private String idNumber;

    @Schema(description = "电话号码")
    private String phoneNumber;

    @Schema(description = "联系地址")
    private String contactAddress;

}
