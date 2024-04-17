package com.xinghuan.biz.client.web.openfeign;

import com.xinghuan.biz.api.interfaces.UserRegistrationService;
import com.xinghuan.biz.api.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.cloud.openfeign.FeignClientSpecification;
import org.springframework.cloud.openfeign.FeignContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;
import java.util.List;

/**
 * @author shenchen
 * @since 2024/4/17 15:32
 */
@EnableFeignClients(clients = UserRegistrationService.class)
public class FeignClientBootstrap {

    @Autowired(required = false)
    private List<FeignClientSpecification> configurations = new ArrayList<>();

    @Bean
    public FeignContext feignContext() {
        FeignContext context = new FeignContext();
        context.setConfigurations(this.configurations);
        return context;
    }

    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();

        // 注册当前引导类
        applicationContext.register(FeignClientBootstrap.class);

        // 启动应用上下文
        applicationContext.refresh();

        UserRegistrationService userRegistrationService = applicationContext.getBean(UserRegistrationService.class);

        User user = new User();
        user.setId(1L);
        user.setName("ABC");
        userRegistrationService.registerUser(user);

        applicationContext.close();
    }
}
