package com.pxg.demo.infrastructure.repository.po;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@TableName("term")
public class TermPO {

    /**
     * 期次ID
     */
    @TableId
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
    @TableField(updateStrategy = FieldStrategy.NEVER)
    private Integer annual;

    /**
     * 备注
     */
    private String remark;

    /**
     * 状态
     */
    private Integer state;

    /**
     * 乐观锁
     */
    @Version
    private Integer revision;

    /**
     * 逻辑删除
     */
    @TableLogic
    private boolean deleted;


    @TableField(fill = FieldFill.INSERT)
    private String createdBy;

    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createdTime;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    private String updatedBy;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updatedTime;
}
