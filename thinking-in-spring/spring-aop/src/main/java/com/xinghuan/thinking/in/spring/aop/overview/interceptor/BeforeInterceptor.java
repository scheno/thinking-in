package com.xinghuan.thinking.in.spring.aop.overview.interceptor;

import java.lang.reflect.Method;

/**
 * 前置拦截器
 *
 * @author shenchen
 * @since 2024/1/31 14:34
 */
public interface BeforeInterceptor {

    /**
     * 前置执行
     *
     * @param proxy
     * @param method
     * @param args
     * @return
     */
    Object before(Object proxy, Method method, Object[] args);

}
