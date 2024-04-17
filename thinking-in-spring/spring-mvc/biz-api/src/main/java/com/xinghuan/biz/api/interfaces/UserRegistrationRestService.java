package com.xinghuan.biz.api.interfaces;

import com.xinghuan.biz.api.ApiRequest;
import com.xinghuan.biz.api.ApiResponse;
import com.xinghuan.biz.api.model.User;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * 用户注册服务 REST 接口（Open Feign、 Spring WebMVC）
 *
 * @author <a href="mailto:mercyblitz@gmail.com">Mercy</a>
 * @since
 */
@FeignClient("${user-registration.rest-service.name}")
@DubboService
@Deprecated
public interface UserRegistrationRestService {

    @PostMapping(path = "/user/register", produces = "application/json;v=1") // V1
    ApiResponse<Boolean> registerUser(@RequestBody @Validated ApiRequest<User> userRequest);

    @PostMapping(path = "/user/register", produces = "application/json;v=2") // V2
    ApiResponse<Boolean> registerUser(@RequestBody @Validated User user);


}