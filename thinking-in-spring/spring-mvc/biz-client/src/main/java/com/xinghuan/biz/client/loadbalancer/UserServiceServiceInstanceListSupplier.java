package com.xinghuan.biz.client.loadbalancer;

import org.springframework.cloud.client.DefaultServiceInstance;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.loadbalancer.core.ServiceInstanceListSupplier;
import reactor.core.publisher.Flux;

import java.util.Arrays;
import java.util.List;

/**
 * 为 "user-service" 实现 ServiceInstanceListSupplier
 *
 * @author <a href="mailto:mercyblitz@gmail.com">Mercy</a>
 * @since 1.0.0
 */
public class UserServiceServiceInstanceListSupplier implements ServiceInstanceListSupplier {

    @Override
    public String getServiceId() {
        return "user-service";
    }

    @Override
    public Flux<List<ServiceInstance>> get() {
        return Flux.just(Arrays.asList(getLocalServiceInstance()));
    }

    private ServiceInstance getLocalServiceInstance() {
        DefaultServiceInstance instance = new DefaultServiceInstance();
        instance.setInstanceId("user-service");
        instance.setHost("127.0.0.1");
        instance.setPort(8080);
        return instance;
    }
}
