package com.xinghuan.biz.client.cloud.loadbalancer;

import org.springframework.beans.factory.ObjectProvider;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.DefaultResponse;
import org.springframework.cloud.client.loadbalancer.Request;
import org.springframework.cloud.client.loadbalancer.Response;
import org.springframework.cloud.loadbalancer.core.ReactorServiceInstanceLoadBalancer;
import org.springframework.cloud.loadbalancer.core.ServiceInstanceListSupplier;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Map;

/**
 * @author shenchen
 * @since 2024/4/21 17:07
 */
public class CpuUsageLoadBalancer implements ReactorServiceInstanceLoadBalancer {

    private final ObjectProvider<ServiceInstanceListSupplier> serviceInstanceListSupplierProvider;

    public CpuUsageLoadBalancer(ObjectProvider<ServiceInstanceListSupplier> serviceInstanceListSupplierProvider) {
        this.serviceInstanceListSupplierProvider = serviceInstanceListSupplierProvider;
    }

    public Mono<Response<ServiceInstance>> choose(Request request) {
        ServiceInstanceListSupplier serviceInstanceListSupplier = serviceInstanceListSupplierProvider.getIfAvailable();
        Flux<List<ServiceInstance>> flux = serviceInstanceListSupplier.get();
        List<ServiceInstance> serviceInstances = flux.blockFirst();
        for (ServiceInstance serviceInstance : serviceInstances) {
            Map<String, String> metadata = serviceInstance.getMetadata();
            String cpuUsage = metadata.get("cpu-usage");
            Integer usage = Integer.valueOf(cpuUsage);
            // TODO 完成 CPU 利用率的算法实现
        }
        return Mono.justOrEmpty(new DefaultResponse(serviceInstances.get(0)));
    }

}
