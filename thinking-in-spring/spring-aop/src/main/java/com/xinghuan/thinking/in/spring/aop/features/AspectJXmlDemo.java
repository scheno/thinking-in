package com.xinghuan.thinking.in.spring.aop.features;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class AspectJXmlDemo {

    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath:/META-INF/spring-aop-context.xml");

        AspectJXmlDemo aspectJAnnotationDemo = context.getBean(AspectJXmlDemo.class);

        context.close();
    }

}
