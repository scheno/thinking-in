package com.xinghuan.thinking.in.spring.aop.features;

import com.xinghuan.thinking.in.spring.aop.features.aspect.AspectConfiguration;
import org.springframework.aop.AfterReturningAdvice;
import org.springframework.aop.MethodBeforeAdvice;
import org.springframework.aop.aspectj.annotation.AspectJProxyFactory;
import org.springframework.aop.framework.AopContext;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class AspectJAnnotationUsingApiDemo {

    public static void main(String[] args) {
        Map<String, Object> cache = new HashMap<>(16);

        AspectJProxyFactory proxyFactory = new AspectJProxyFactory(cache);

//        proxyFactory.addAspect(AspectConfiguration.class);

        proxyFactory.setExposeProxy(true);

        proxyFactory.addAdvice(new MethodBeforeAdvice() {
            @Override
            public void before(Method method, Object[] args, Object target) throws Throwable {
                if ("put".equals(method.getName()) && args.length == 2) {
                    Object proxy = AopContext.currentProxy();
                    System.out.printf("[MethodBeforeAdvice] 当前存放是 Key: %s , Value : %s ，" +
                            "代理对象：%s\n", args[0], args[1], proxy);
                }
            }
        });

        proxyFactory.addAdvice(new AfterReturningAdvice() {
            @Override
            public void afterReturning(Object returnValue, Method method, Object[] objects, Object o1) throws Throwable {
                if ("put".equals(method.getName()) && args.length == 2) {
                    System.out.printf("[AfterReturningAdvice] 当前存放是 Key: %s , 新存放的 Value : %s , 之前关联的 Value : %s\n ",
                            args[0],    // key
                            args[1],    // new value
                            returnValue // old value
                    );
                }
            }
        });

        Map<String, Object> proxy = proxyFactory.getProxy();
        proxy.put("1", "A");
        proxy.put("1", "B");
        System.out.println(cache.get("1"));
    }

}
