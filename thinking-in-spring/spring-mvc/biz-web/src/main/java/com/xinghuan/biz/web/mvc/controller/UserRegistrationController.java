package com.xinghuan.biz.web.mvc.controller;

import com.xinghuan.biz.api.interfaces.UserRegistrationService;
import com.xinghuan.biz.api.model.User;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author <a href="mailto:mercyblitz@gmail.com">Mercy</a>
 * @since
 */
@RestController
public class UserRegistrationController implements UserRegistrationService {

//    @Autowired
//    private UserRegistrationService userRegistrationService;

    // REST -> { body : {}}

    @Override
    @ResponseBody
    public Boolean registerUser(User user) {
//        return userRegistrationService.registerUser(user);
        return Boolean.TRUE;
    }
}

