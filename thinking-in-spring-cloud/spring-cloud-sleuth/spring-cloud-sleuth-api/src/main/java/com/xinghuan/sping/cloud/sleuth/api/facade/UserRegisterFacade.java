package com.xinghuan.sping.cloud.sleuth.api.facade;

import com.xinghuan.sping.cloud.sleuth.api.domain.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(value = "sleuth-web")
public interface UserRegisterFacade {

    @PostMapping("/user/register")
    User register(@RequestBody User user);
}
