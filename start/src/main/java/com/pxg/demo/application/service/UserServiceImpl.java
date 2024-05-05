package com.pxg.demo.application.service;

import cn.hutool.core.util.IdUtil;
import com.pxg.demo.client.api.UserServiceI;
import com.pxg.demo.client.dto.common.ReturnInfo;
import com.pxg.demo.client.dto.common.SingleReturnT;
import com.pxg.demo.client.dto.request.UserCreateCmd;
import com.pxg.demo.client.dto.request.UserUpdateCmd;
import com.pxg.demo.client.dto.response.UserDTO;
import com.pxg.demo.config.Const;
import com.pxg.demo.domain.user.UserInfo;
import com.pxg.demo.domain.user.UserRepository;
import com.pxg.demo.infrastructure.repository.convertor.UserInfoConvertor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserServiceI {

    private final UserRepository userRepository;

    @Override
    public SingleReturnT<String> create(UserCreateCmd cmd) {
        UserInfo userInfo = UserInfoConvertor.INSTANCE.cmd2Do(cmd);
        if (!StringUtils.hasText(userInfo.getAccountId())){
            userInfo.setAccountId(IdUtil.getSnowflakeNextIdStr());
        }
        try {
            userRepository.create(userInfo);
        }catch (Exception e) {
            return SingleReturnT.buildFailure(Const.BAD_REQUEST,"注册失败，请尝试更换编号");
        }

        return SingleReturnT.of(userInfo.getAccountId());
    }

    @Override
    public ReturnInfo update(UserUpdateCmd cmd) {
        UserInfo userInfo = UserInfoConvertor.INSTANCE.cmd2Do(cmd);
        userRepository.update(userInfo);
        return ReturnInfo.buildSuccess();
    }

    @Override
    public ReturnInfo remove(String userId) {
        userRepository.remove(userId);
        return ReturnInfo.buildSuccess();
    }

    @Override
    public SingleReturnT<UserDTO> getUser(String userId) {
        UserInfo userInfo = userRepository.findById(userId);
        return SingleReturnT.of(UserInfoConvertor.INSTANCE.do2Dto(userInfo));
    }

}
