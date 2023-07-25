package com.xinghuan.thinking.in.spring.aop.features;

import com.xinghuan.thinking.in.spring.aop.features.aspect.AspectConfiguration;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author shenchen
 * @since 2023/6/8 11:18
 */
public class AspectJAnnotatedPointcutDemo {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.register(AspectJAnnotatedPointcutDemo.class, AspectConfiguration.class);
        applicationContext.refresh();
        AspectConfiguration configuration = applicationContext.getBean(AspectConfiguration.class);
        AspectJAnnotatedPointcutDemo aspectJAnnotatedPointcutDemo = applicationContext.getBean(AspectJAnnotatedPointcutDemo.class);
        System.out.println(configuration);
        aspectJAnnotatedPointcutDemo.execute();
        applicationContext.close();
    }

    public void execute() {
        System.out.println("方法执行了！！！");
    }

}
