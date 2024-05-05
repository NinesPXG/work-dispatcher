package com.pxg.demo.client.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class UserDTO {

    @Schema(description = "用户编号")
    private String accountId;

    @Schema(description = "用户昵称")
    private String nickName;

    @Schema(description = "电话号码")
    private String phoneNumber;

    @Schema(description = "联系地址")
    private String contactAddress;

    @Schema(description = "状态")
    private Integer state;

}
