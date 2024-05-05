package com.pxg.demo.infrastructure.repository.convertor;

import com.pxg.demo.client.dto.request.UserCreateCmd;
import com.pxg.demo.client.dto.request.UserUpdateCmd;
import com.pxg.demo.client.dto.response.UserDTO;
import com.pxg.demo.domain.user.UserInfo;
import com.pxg.demo.infrastructure.repository.po.UserInfoPO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserInfoConvertor {

    UserInfoConvertor INSTANCE = Mappers.getMapper(UserInfoConvertor.class);

    UserDTO do2Dto(UserInfo info);

    UserInfo cmd2Do(UserUpdateCmd cmd);

    UserInfo cmd2Do(UserCreateCmd cmd);

    UserInfo po2Do(UserInfoPO dao);

    UserInfoPO do2Po(UserInfo info);
}
