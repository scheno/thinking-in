package com.xinghuan.thinking.in.spring.aop.overview.service;

/**
 * EchoService 默认实现类
 *
 * @author xinghuan
 * @since 2023/6/6 17:27
 */
public class DefaultEchoService implements EchoService {

    @Override
    public String echo(String message) throws NullPointerException {
        if (1 == 1)  {
            throw new RuntimeException("报错啦");
        }
        return "[ECHO] " + message;
    }

}
