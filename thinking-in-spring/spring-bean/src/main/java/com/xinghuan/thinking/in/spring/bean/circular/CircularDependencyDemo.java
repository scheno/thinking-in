package com.xinghuan.thinking.in.spring.bean.circular;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.scheduling.annotation.EnableAsync;

@Configuration
@EnableAsync
@EnableAspectJAutoProxy
public class CircularDependencyDemo {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.register(CircularDependencyDemo.class, TestService1.class, TestService2.class);
        applicationContext.refresh();
        TestService1 testService1 = applicationContext.getBean(TestService1.class);
        testService1.test2();
        applicationContext.close();
    }

}
