package com.xinghuan.spring.cloud.sleuth.web.facade.impl;

import com.xinghuan.sping.cloud.sleuth.api.domain.User;
import com.xinghuan.sping.cloud.sleuth.api.facade.UserRegisterFacade;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;

@RestController
public class UserRegisterFacadeImpl implements UserRegisterFacade {

    Random random = new Random();

    @Override
    public User register(User user) {
        user.setId(Long.valueOf(random.nextInt()));
        return user;
    }
}
