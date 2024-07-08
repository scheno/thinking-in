package com.xinghuan.common.nacos.registry;

import org.springframework.boot.web.context.WebServerInitializedEvent;
import org.springframework.context.ApplicationListener;

/**
 * @author shenchen
 * @since 2024/4/24 22:56
 */
public class MultiNacosServiceRegistration implements ApplicationListener<WebServerInitializedEvent> {

    @Override
    public void onApplicationEvent(WebServerInitializedEvent event) {

    }

}
