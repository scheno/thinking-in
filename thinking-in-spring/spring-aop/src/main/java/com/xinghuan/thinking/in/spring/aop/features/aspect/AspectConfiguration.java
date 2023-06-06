package com.xinghuan.thinking.in.spring.aop.features.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Aspect
@Configuration
@EnableAspectJAutoProxy(exposeProxy = true)
public class AspectConfiguration {
    
}
