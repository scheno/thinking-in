package com.xinghuan.thinking.in.spring.aop.features.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Aspect
@Configuration
@EnableAspectJAutoProxy()
public class AspectConfiguration {

    @Pointcut("execution(* *.*())")
    public void pointcut() {

    }

    @Before("pointcut()")
    public void before() {
        System.out.println("前置通知");
    }

    @Around("pointcut()")
    public Object around(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        System.out.println("环绕通知-前");
        Object result = proceedingJoinPoint.proceed();
        System.out.println("环绕通知-后");
        return result;
    }

    @After("pointcut()")
    public void after() {
        System.out.println("后置通知");
    }
}
