package com.xinghuan.biz.client.cloud.controller;

import com.xinghuan.biz.api.interfaces.UserRegistrationService;
import com.xinghuan.biz.api.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author shenchen
 * @since 2024/4/21 17:06
 */
@RestController
public class BizClientFeignController {

    @Autowired
    private UserRegistrationService userRegistrationService;

    @GetMapping("/user/register")
    public Object registerUser() {
        User user = new User();
        user.setId(1L);
        user.setName("ABC");
        return userRegistrationService.registerUser(user);
    }

}
