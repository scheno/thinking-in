package com.xinghuan.thinking.in.spring.aop.overview;

import java.lang.reflect.Method;
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import com.xinghuan.thinking.in.spring.aop.overview.service.DefaultEchoService;
import com.xinghuan.thinking.in.spring.aop.overview.service.EchoService;

/**
 * Cglib 动态代理 Demo
 *
 * @author xinghuan
 * @since 2023/6/6 17:53
 */
public class CglibProxyDemo {

    public static void main(String[] args) {
        Class<?> superClass = DefaultEchoService.class;
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(superClass);
        enhancer.setInterfaces(new Class[] {EchoService.class});
        enhancer.setCallback(new MethodInterceptor() {
            @Override
            public Object intercept(Object o, Method method, Object[] objects,
                    MethodProxy methodProxy) throws Throwable {
                long startTime = System.currentTimeMillis();
                Object result = methodProxy.invokeSuper(o, objects);
                long costTime = System.currentTimeMillis() - startTime;
                System.out.println("[CGLIB 字节码提升] echo 方法执行的实现：" + costTime + " ms.");
                return result;
            }
        });

        EchoService echoService = (EchoService) enhancer.create();
        echoService.echo("hello world");
    }

}
