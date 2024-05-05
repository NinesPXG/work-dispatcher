package com.pxg.demo.infrastructure.repository.po;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * <p>
 * 用户信息
 * </p>
 *
 * @author generator
 * @since 2022-03-12
 */
@Data
@TableName("user_info")
public class UserInfoPO {

    /**
     * 用户编号
     */
    @TableId
    private String accountId;

    /**
     * 用户昵称
     */
    private String nickName;

    /**
     * 性别
     */
    private Integer sex;

    /**
     * 用户姓名
     */
    private String userName;

    /**
     * 身份证号
     */
    private String idNumber;

    /**
     * 电话号码
     */
    private String phoneNumber;

    /**
     * 联系地址
     */
    private String contactAddress;

    /**
     * 拓展字段
     */
    @TableField(exist = false)
    private String extJson;

    /**
     * 状态
     */
    private Integer state;

    /**
     * 最近时间
     */
    private LocalDateTime latestTime;

    /**
     * 乐观锁
     */
    @Version
    private Integer revision;

    /**
     * 逻辑删除
     */
    @TableLogic
    private String deleted;

}
