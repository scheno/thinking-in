package com.xinghuan.thinking.in.spring.bean;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author shenchen
 * @since 2023/7/4 16:42
 */
@Configuration
public class BeanAnnotationDemo {

    @Bean
    User user() {
        return new User();
    }

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.register(BeanAnnotationDemo.class);
        context.refresh();
        User user = context.getBean(User.class);
        BeanAnnotationDemo demo = context.getBean(BeanAnnotationDemo.class);
        for (int i = 0; i < 10; i++) {
            user = demo.user();
            System.out.println(user);
        }
        System.out.println(user);
        context.close();
    }

    class User {

        String name = "John";

    }
}
