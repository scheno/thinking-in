package com.xinghuan.biz.api.openfeign;

import feign.codec.Decoder;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.boot.autoconfigure.http.HttpMessageConvertersAutoConfiguration;
import org.springframework.cloud.openfeign.support.ResponseEntityDecoder;
import org.springframework.cloud.openfeign.support.SpringDecoder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author shenchen
 * @since 2024/4/17 16:38
 */
@Configuration
public class UserServiceFeignClientConfiguration {

    /**
     * 对象创建于 Spring Boot
     * {@link HttpMessageConvertersAutoConfiguration#messageConverters(ObjectProvider)}
     */
    @Autowired
    private ObjectFactory<HttpMessageConverters> messageConverters;

    @Bean
    Decoder feignDecoder() {
        return new ApiResponseDecoder(new ResponseEntityDecoder(new SpringDecoder(messageConverters)));
    }

}
