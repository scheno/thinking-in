package com.xinghuan.biz.api.interfaces;

import com.xinghuan.biz.api.model.User;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

/**
 * 用户登录服务接口（Open Feign、Dubbo 等公用）
 * @author <a href="mailto:mercyblitz@gmail.com">Mercy</a>
 * @since
 */
@FeignClient("${user-login.service.name}") // user // user-login user-registration
@RequestMapping("/user")
@DubboService
public interface UserLoginService {

    @PostMapping("/login")
    @Deprecated
    User login(Map<String,Object> context);
}
