package com.pxg.demo.adapter;

import com.pxg.demo.client.api.UserServiceI;
import com.pxg.demo.client.dto.common.ReturnInfo;
import com.pxg.demo.client.dto.common.SingleReturnT;
import com.pxg.demo.client.dto.request.UserCreateCmd;
import com.pxg.demo.client.dto.request.UserUpdateCmd;
import com.pxg.demo.client.dto.response.UserDTO;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("user")
public class UserController {

    private final UserServiceI userServiceI;

    public UserController(UserServiceI userServiceI) {
        this.userServiceI = userServiceI;
    }

    @PostMapping("create")
    SingleReturnT<String> create(@RequestBody @Validated UserCreateCmd cmd) {
        return userServiceI.create(cmd);
    }

    @PostMapping("update")
    ReturnInfo update(@RequestBody @Validated UserUpdateCmd cmd) {
        return userServiceI.update(cmd);
    }

    @GetMapping("remove")
    ReturnInfo remove(@RequestParam String userId) {
        return userServiceI.remove(userId);
    }

    @GetMapping("get")
    SingleReturnT<UserDTO> getUser(@RequestParam String userId) {
        return userServiceI.getUser(userId);
    }

}
