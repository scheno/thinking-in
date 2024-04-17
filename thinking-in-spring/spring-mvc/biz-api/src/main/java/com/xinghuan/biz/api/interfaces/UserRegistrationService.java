package com.xinghuan.biz.api.interfaces;

import com.xinghuan.biz.api.exception.UserException;
import com.xinghuan.biz.api.model.User;
import com.xinghuan.biz.api.openfeign.UserServiceFeignClientConfiguration;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;

/**
 * 用户注册服务接口（Open Feign、Dubbo 等公用）
 * <p>
 * JAX-RS
 * Dubbo
 * OpenFeign
 * RMI -> 动态代理（基于接口编程）
 *
 * @author <a href="mailto:mercyblitz@gmail.com">Mercy</a>
 * @since
 */
@FeignClient(name = "user-service", configuration = UserServiceFeignClientConfiguration.class)
@DubboService
public interface UserRegistrationService {

    @PostMapping(value = "/user/register", produces = "application/json;v=3") // V3
    Boolean registerUser(@RequestBody @Validated @Valid User user) throws UserException;

    @PostMapping(value = "/user/register", produces = "application/json;v=3.1") // V3.1
    default Boolean registerUserV31(@RequestBody @Validated @Valid User user) throws UserException {
        // default 方法确保源码兼容（代码兼容）
        // v3.1 的实现有个别 v3
        return false;
    }

}
