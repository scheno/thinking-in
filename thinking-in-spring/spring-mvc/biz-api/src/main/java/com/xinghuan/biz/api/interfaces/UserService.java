package com.xinghuan.biz.api.interfaces;

import com.xinghuan.biz.api.model.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

/**
 * 用户服务接口（Open Feign、Dubbo 等公用）
 *
 * @author <a href="mailto:mercyblitz@gmail.com">Mercy</a>
 * @see UserLoginService
 * @see UserRegistrationService
 * @since
 * @deprecated 该接口不再推荐使用，请使用 {@link UserLoginService} 或 {@link UserRegistrationService}
 */
@FeignClient("${user.service.name}") // user // user-login user-registration
@RequestMapping("/user")
@Deprecated
public interface UserService {

    @PostMapping("/register")
    Boolean registerUser(User user);

    @PostMapping("/login")
    @Deprecated
    User login(Map<String, Object> context);
}
