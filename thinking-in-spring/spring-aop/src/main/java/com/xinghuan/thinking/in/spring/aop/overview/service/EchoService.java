package com.xinghuan.thinking.in.spring.aop.overview.service;

/**
 * @author xinghuan
 * @since 2023/6/6 17:25
 */
public interface EchoService {


    /**
     * 打印接口
     *
     * @param message 消息
     * @return 消息
     * @throws NullPointerException
     */
    String echo(String message) throws NullPointerException;

}
