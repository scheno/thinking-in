package com.xinghuan.thinking.in.spring.aop;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author shenchen
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
