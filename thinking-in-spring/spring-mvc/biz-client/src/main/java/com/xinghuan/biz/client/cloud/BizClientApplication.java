package com.xinghuan.biz.client.cloud;

import com.xinghuan.biz.api.interfaces.UserRegistrationService;
import com.xinghuan.biz.client.cloud.loadbalancer.CpuUsageBalancerConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.loadbalancer.annotation.LoadBalancerClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @author shenchen
 * @since 2024/4/21 17:05
 */
@ComponentScan
@EnableAutoConfiguration
@EnableDiscoveryClient
@EnableFeignClients(clients = UserRegistrationService.class)
//@EnableFeignClients(clients = UserRegistrationService.class, defaultConfiguration = DefaultFeignClientsConfiguration.class)
@LoadBalancerClient(name = "user-service", configuration = CpuUsageBalancerConfiguration.class)
@EnableScheduling
public class BizClientApplication {

    public static void main(String[] args) {
        SpringApplication.run(BizClientApplication.class);
    }

}
