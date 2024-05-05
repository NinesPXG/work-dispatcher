package com.pxg.demo.client.api;

import com.pxg.demo.client.dto.common.ReturnInfo;
import com.pxg.demo.client.dto.common.SingleReturnT;
import com.pxg.demo.client.dto.request.UserCreateCmd;
import com.pxg.demo.client.dto.request.UserUpdateCmd;
import com.pxg.demo.client.dto.response.UserDTO;

public interface UserServiceI {

    SingleReturnT<String> create(UserCreateCmd cmd);

    ReturnInfo update(UserUpdateCmd cmd);

    ReturnInfo remove(String userId);

    SingleReturnT<UserDTO> getUser(String userId);

}
