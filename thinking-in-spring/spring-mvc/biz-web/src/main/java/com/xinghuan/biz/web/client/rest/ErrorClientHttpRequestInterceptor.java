package com.xinghuan.biz.web.client.rest;

import org.springframework.core.Ordered;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;

import java.io.IOException;

/**
 * @author shenchen
 * @since 2024/4/17 11:15
 */
public class ErrorClientHttpRequestInterceptor implements ClientHttpRequestInterceptor, Ordered {

    @Override
    public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution) throws IOException {
        HttpHeaders headers = request.getHeaders();
        boolean valid = "true".equals(headers.getFirst("validation-result"));
        if (!valid) {
            // TODO
        }

        // 执行请求
        return execution.execute(request, body);
    }

    @Override
    public int getOrder() {
        return Ordered.LOWEST_PRECEDENCE;
    }

}
