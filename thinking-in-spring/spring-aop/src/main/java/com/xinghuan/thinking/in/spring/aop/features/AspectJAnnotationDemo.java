package com.xinghuan.thinking.in.spring.aop.features;

import com.xinghuan.thinking.in.spring.aop.features.aspect.AspectConfiguration;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class AspectJAnnotationDemo {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.register(AspectConfiguration.class);
        applicationContext.refresh();
        AspectConfiguration configuration = applicationContext.getBean(AspectConfiguration.class);
        System.out.println(configuration);
        configuration.toString();
        applicationContext.close();
    }
}
