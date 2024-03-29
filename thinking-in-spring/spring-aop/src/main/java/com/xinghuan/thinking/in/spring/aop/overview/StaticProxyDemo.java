package com.xinghuan.thinking.in.spring.aop.overview;

import com.xinghuan.thinking.in.spring.aop.overview.service.DefaultEchoService;
import com.xinghuan.thinking.in.spring.aop.overview.service.EchoService;
import com.xinghuan.thinking.in.spring.aop.overview.service.ProxyEchoService;

/**
 * 静态代理实现 Demo
 *
 * @author xinghuan
 * @since 2023/6/6 17:31
 */
public class StaticProxyDemo {

    public static void main(String[] args) {
        EchoService echoService = new DefaultEchoService();
        ProxyEchoService proxy = new ProxyEchoService(echoService);
        String result = proxy.echo("你好");
        System.out.println(result);
    }

}
