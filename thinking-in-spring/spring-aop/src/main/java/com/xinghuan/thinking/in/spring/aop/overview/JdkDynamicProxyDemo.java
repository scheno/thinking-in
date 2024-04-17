package com.xinghuan.thinking.in.spring.aop.overview;

import com.xinghuan.thinking.in.spring.aop.overview.service.DefaultEchoService;
import com.xinghuan.thinking.in.spring.aop.overview.service.EchoService;
import com.xinghuan.thinking.in.spring.aop.overview.service.ProxyEchoService;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * Jdk 动态代理 Demo
 *
 * @author xinghuan
 * @since 2023/6/6 17:43
 */
public class JdkDynamicProxyDemo {

    public static void main(String[] args) {
        Object proxy = Proxy.newProxyInstance(Thread.currentThread().getContextClassLoader(),
                DefaultEchoService.class.getInterfaces(), new InvocationHandler() {
                    @Override
                    public Object invoke(Object proxy, Method method, Object[] args)
                            throws Throwable {
                        ProxyEchoService echoService = new ProxyEchoService(
                                new DefaultEchoService());
                        return echoService.echo((String) args[0]);
                    }
                });
        EchoService echoService = (EchoService) proxy;
        echoService.echo("hello world");
    }

}
