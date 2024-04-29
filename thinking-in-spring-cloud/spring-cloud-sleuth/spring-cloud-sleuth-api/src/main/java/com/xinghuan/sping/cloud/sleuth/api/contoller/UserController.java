package com.xinghuan.sping.cloud.sleuth.api.contoller;

import com.xinghuan.sping.cloud.sleuth.api.domain.User;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @PostMapping("/user/register")
    public String register(User user) {
        return user.getName();
    }

}
