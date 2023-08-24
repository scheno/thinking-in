package com.xinghuan.thinking.in.redisson;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author shenchen
 * @since 2023/8/21 16:03
 */
@MapperScan("com.xinghuan.thinking.in.redisson.repo")
@SpringBootApplication
public class RedissonApplication {

    public static void main(String[] args) {
        SpringApplication.run(RedissonApplication.class);
    }

}
