package com.xinghuan.thinking.in.spring.aop.overview.interceptor;

import java.lang.reflect.Method;

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
