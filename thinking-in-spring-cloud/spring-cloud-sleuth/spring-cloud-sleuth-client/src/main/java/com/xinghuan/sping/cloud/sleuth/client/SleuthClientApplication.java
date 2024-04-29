package com.xinghuan.sping.cloud.sleuth.client;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients(basePackages = "com.xinghuan.sping.cloud.sleuth.api.facade")
@SpringBootApplication
public class SleuthClientApplication {

    public static void main(String[] args) {
        SpringApplication.run(SleuthClientApplication.class);
    }

}
