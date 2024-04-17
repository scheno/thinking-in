package com.xinghuan.biz.client.web.openfeign;

import com.xinghuan.biz.api.interfaces.UserRegistrationService;
import com.xinghuan.biz.api.model.User;
import com.xinghuan.biz.client.loadbalancer.UserServiceLoadBalancerConfiguration;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.loadbalancer.annotation.LoadBalancerClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * @author shenchen
 * @since 2024/4/17 15:45
 */
@EnableAutoConfiguration
@EnableFeignClients(clients = UserRegistrationService.class)
@LoadBalancerClient(name = "user-service", configuration = UserServiceLoadBalancerConfiguration.class)
public class FeignClientSpringBootBootstrap {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = new SpringApplicationBuilder(FeignClientSpringBootBootstrap.class)
                .web(WebApplicationType.NONE)
                .build()
                .run(args);


        UserRegistrationService userRegistrationService = context.getBean(UserRegistrationService.class);

        User user = new User();
        user.setId(1L);
        user.setName("ABC");

        System.out.println("userRegistrationService.registerUser : " + userRegistrationService.registerUser(user));

        context.close();
    }

}
