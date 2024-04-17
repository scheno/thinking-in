package com.xinghuan.thinking.in.spring.aop.demo;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * @author shenchen
 * @since 2024/3/7 17:14
 */
@Aspect
@EnableAspectJAutoProxy
public class AspectConfiguration {

    @Pointcut("execution(public void com.xinghuan.thinking.in.spring.aop.demo.AspectAnnotationApplication.execute(..))")
    public void pointcut() {

    }

    @Before("pointcut()")
    public void before() {
        System.out.println("before...");
    }

    @Around("pointcut()")
    public void around(ProceedingJoinPoint pdj) throws Throwable {
        System.out.println("before around...");
        pdj.proceed();
        System.out.println("after around...");
    }

}
