package com.pxg.demo.domain.term;

import com.pxg.demo.domain.BaseEntity;
import lombok.Data;

@Data
public class Term extends BaseEntity {

    /**
     * 期次ID
     */
    private String termId;

    /**
     * 期次名称
     */
    private String termName;

    /**
     * 期次类型
     */
    private String termType;

    /**
     * 年度
     */
    private Integer annual;

    /**
     * 备注
     */
    private String remark;

    /**
     * 状态
     */
    private Integer state;

}
