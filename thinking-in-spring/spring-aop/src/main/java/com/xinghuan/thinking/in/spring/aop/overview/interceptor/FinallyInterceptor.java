package com.xinghuan.thinking.in.spring.aop.overview.interceptor;

import java.lang.reflect.Method;

/**
 * 最终执行后置拦截器
 *
 * @author xinghuan
 * @since 2024/1/31 14:35
 */
public interface FinallyInterceptor {

    /**
     * 最终执行
     *
     * @param proxy
     * @param method
     * @param args
     * @param returnResult 执行方法返回结果
     * @return
     */
    Object finalize(Object proxy, Method method, Object[] args, Object returnResult);

}
