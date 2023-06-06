package com.xinghuan.thinking.in.spring.aop;

/**
 * @author shenchen
 * @since 2023/6/6 17:27
 */
public class DefaultEchoService implements EchoService {

    @Override
    public String echo(String message) throws NullPointerException {
        return "[ECHO] " + message;
    }

}
