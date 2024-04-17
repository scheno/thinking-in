package com.xinghuan.biz.web.mvc.controller;

import com.xinghuan.biz.api.ApiRequest;
import com.xinghuan.biz.api.ApiResponse;
import com.xinghuan.biz.api.interfaces.UserRegistrationRestService;
import com.xinghuan.biz.api.model.User;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author <a href="mailto:mercyblitz@gmail.com">Mercy</a>
 * @since
 */
@RestController
public class UserRegistrationRestController implements UserRegistrationRestService {

    @Override
    public ApiResponse<Boolean> registerUser(@RequestBody @Validated User user) {
        return ApiResponse.ok(Boolean.TRUE);
    }

    @Override
    public ApiResponse<Boolean> registerUser(@RequestBody @Validated ApiRequest<User> userRequest) {
        return ApiResponse.ok(Boolean.TRUE);
    }


}
