package com.xinghuan.thinking.in.spring.aop.features;

import com.xinghuan.thinking.in.spring.aop.overview.service.EchoService;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ProxyFactoryBeanDemo {

    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath:/META-INF/spring-aop-context.xml");

        EchoService echoService = context.getBean("echoServiceProxyFactoryBean", EchoService.class);

        echoService.echo("hello world");

        context.close();
    }

}
