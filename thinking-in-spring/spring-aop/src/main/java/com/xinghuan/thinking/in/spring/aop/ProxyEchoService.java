package com.xinghuan.thinking.in.spring.aop;

/**
 * @author shenchen
 * @since 2023/6/6 17:28
 */
public class ProxyEchoService implements EchoService {

    private EchoService echoService;

    public ProxyEchoService(EchoService echoService) {
        this.echoService = echoService;
    }

    @Override
    public String echo(String message) throws NullPointerException {
        long startTime = System.currentTimeMillis();
        String result = echoService.echo(message);
        long costTime = System.currentTimeMillis() - startTime;
        System.out.println("echo 方法执行的实现：" + costTime + " ms.");
        return result;
    }
}
