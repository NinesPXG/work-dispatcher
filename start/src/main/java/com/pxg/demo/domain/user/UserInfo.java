package com.pxg.demo.domain.user;

import com.pxg.demo.domain.BaseEntity;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class UserInfo extends BaseEntity {

    /**
     * 用户编号
     */
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
     * 状态
     */
    private Integer state;

    private LocalDateTime latestTime;

}
