package com.xinghuan.thinking.in.spring.aop.features;

import com.xinghuan.thinking.in.spring.aop.features.interceptor.EchoServiceMethodInterceptor;
import com.xinghuan.thinking.in.spring.aop.overview.service.DefaultEchoService;
import com.xinghuan.thinking.in.spring.aop.overview.service.EchoService;
import org.springframework.aop.framework.ProxyFactory;

public class ProxyFactoryDemo {

    public static void main(String[] args) {
        DefaultEchoService defaultEchoService = new DefaultEchoService();
        // 注入目标对象（被代理）
        ProxyFactory proxyFactory = new ProxyFactory();
        proxyFactory.setTargetClass(EchoService.class);
        proxyFactory.setTarget(defaultEchoService);
        // 添加 Advice 实现 MethodInterceptor < Interceptor < Advice
        proxyFactory.addAdvice(new EchoServiceMethodInterceptor());
        // 获取代理对象
        EchoService echoService = (EchoService) proxyFactory.getProxy();
        System.out.println(echoService.echo("Hello,World"));
    }

}
