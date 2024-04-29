package com.xinghuan.sping.cloud.sleuth.client.contoller;

import com.xinghuan.sping.cloud.sleuth.client.domain.User;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @PostMapping("/user/register")
    public String register(@RequestBody User user) {
        return user.getName();
    }

}
