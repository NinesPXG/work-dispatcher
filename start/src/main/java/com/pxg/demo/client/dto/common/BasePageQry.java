package com.pxg.demo.client.dto.common;

import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Data
public abstract class BasePageQry {

    @NotNull
    @Min(1)
    private Integer pageIndex;

    @NotNull
    @Min(1)
    private Integer pageSize;

}
