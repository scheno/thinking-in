package com.xinghuan.thinking.in.spring.aop.overview;

import com.xinghuan.thinking.in.spring.aop.overview.interceptor.AfterReturnInterceptor;
import com.xinghuan.thinking.in.spring.aop.overview.interceptor.BeforeInterceptor;
import com.xinghuan.thinking.in.spring.aop.overview.interceptor.ExceptionInterceptor;
import com.xinghuan.thinking.in.spring.aop.overview.interceptor.FinallyInterceptor;
import com.xinghuan.thinking.in.spring.aop.overview.service.DefaultEchoService;
import com.xinghuan.thinking.in.spring.aop.overview.service.EchoService;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class AopInterceptorDemo {

    public static void main(String[] args) {
        // 前置模式 + 后置模式
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        Object proxy = Proxy.newProxyInstance(classLoader, new Class[]{EchoService.class}, new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                if (EchoService.class.isAssignableFrom(method.getDeclaringClass())) {
                    // 前置拦截器
                    BeforeInterceptor beforeInterceptor = new BeforeInterceptor() {
                        @Override
                        public Object before(Object proxy, Method method, Object[] args) {
                            return System.currentTimeMillis();
                        }
                    };
                    Long startTime = 0L;
                    Long endTime = 0L;
                    Object result = null;
                    try {
                        // 前置拦截
                        startTime = (Long) beforeInterceptor.before(proxy, method, args);
                        EchoService echoService = new DefaultEchoService();
                        result = echoService.echo((String) args[0]); // 目标对象执行
                        // 方法执行后置拦截器
                        AfterReturnInterceptor afterReturnInterceptor = new AfterReturnInterceptor() {
                            @Override
                            public Object after(Object proxy, Method method, Object[] args, Object returnResult) {
                                return System.currentTimeMillis();
                            }
                        };
                        // 执行 after
                        endTime = (Long) afterReturnInterceptor.after(proxy, method, args, result);
                        throw new RuntimeException("error!!!");
                    } catch (Exception e) {
                        // 异常拦截器（处理方法执行后）
                        ExceptionInterceptor interceptor = (proxy1, method1, args1, throwable) -> {
                            System.out.println(throwable.getMessage());
                        };
                        interceptor.intercept(proxy, method, args, e);
                    } finally {
                        // finally 后置拦截器
                        FinallyInterceptor interceptor = new TimeFinallyInterceptor(startTime, endTime);
                        Long costTime = (Long) interceptor.finalize(proxy, method, args, result);
                        System.out.println("echo 方法执行的实现：" + costTime + " ms.");
                    }

                }
                return null;
            }
        });

        EchoService echoService = (EchoService) proxy;
        echoService.echo("Hello,World");
    }
}

class TimeFinallyInterceptor implements FinallyInterceptor {

    private final Long startTime;

    private final Long endTime;

    TimeFinallyInterceptor(Long startTime, Long endTime) {
        this.startTime = startTime;
        this.endTime = endTime;
    }

    @Override
    public Object finalize(Object proxy, Method method, Object[] args, Object returnResult) {
        // 方法执行时间（毫秒）
        Long costTime = endTime - startTime;
        return costTime;
    }
}
