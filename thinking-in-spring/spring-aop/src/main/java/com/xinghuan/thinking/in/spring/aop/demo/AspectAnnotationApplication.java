package com.xinghuan.thinking.in.spring.aop.demo;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author shenchen
 * @since 2024/3/7 17:19
 */
public class AspectAnnotationApplication {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.register(AspectConfiguration.class);
        applicationContext.register(AspectAnnotationApplication.class);
        applicationContext.refresh();

        AspectAnnotationApplication application = applicationContext.getBean(AspectAnnotationApplication.class);
        application.execute("下单");
    }

    public void execute(String message) {
        System.out.println("方法执行了，消息内容：" + message);
    }

}
