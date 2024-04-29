package com.xinghuan.sping.cloud.sleuth.client.contoller;

import com.xinghuan.sping.cloud.sleuth.api.domain.User;
import com.xinghuan.sping.cloud.sleuth.api.facade.UserRegisterFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    private UserRegisterFacade userRegisterFacade;

    @PostMapping("/user/register")
    public User register(@RequestBody User user) {
        return userRegisterFacade.register(user);
    }

}
