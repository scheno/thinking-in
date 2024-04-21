package com.xinghuan.biz.web.servlet.filter;

import io.github.resilience4j.circuitbreaker.CircuitBreaker;
import io.github.resilience4j.circuitbreaker.CircuitBreakerConfig;
import io.github.resilience4j.circuitbreaker.CircuitBreakerRegistry;
import org.apache.catalina.core.ApplicationFilterChain;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.util.ClassUtils;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 资源 {@link CircuitBreaker} Filter（细粒度）
 *
 * @author <a href="mailto:mercyblitz@gmail.com">Mercy</a>
 * @since 1.0.0
 */
@WebFilter(filterName = "resourceCircuitBreakerFilter", urlPatterns = "/*",
        dispatcherTypes = {
                DispatcherType.REQUEST
        })
@Order(Ordered.HIGHEST_PRECEDENCE + 1)
public class ResourceCircuitBreakerFilter implements Filter {

    /**
     * org.apache.catalina.core.ApplicationFilterFactory#createFilterChain(javax.servlet.ServletRequest, org.apache.catalina.Wrapper, javax.servlet.Servlet)
     */
    private final static String FILTER_CHAIN_IMPL_CLASS_NAME = "org.apache.catalina.core.ApplicationFilterChain";

    private final static Class<?> FILTER_CHAIN_IMPL_CLASS = ClassUtils.resolveClassName(FILTER_CHAIN_IMPL_CLASS_NAME, null);

    private CircuitBreakerRegistry circuitBreakerRegistry;

    private Map<String, CircuitBreaker> circuitBreakersCache;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        CircuitBreakerConfig circuitBreakerConfig = CircuitBreakerConfig
                .custom()
                .build();

        this.circuitBreakerRegistry = CircuitBreakerRegistry.of(circuitBreakerConfig);
        this.circuitBreakersCache = new ConcurrentHashMap<>();
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpServlet = (HttpServletRequest) request;

        String servletName = getServletName(httpServlet, chain);
        if (servletName == null) {
            chain.doFilter(request, response);
            return;
        }

        CircuitBreaker circuitBreaker = circuitBreakersCache.computeIfAbsent(servletName, circuitBreakerRegistry::circuitBreaker);

        try {
            circuitBreaker.decorateCheckedRunnable(() -> chain.doFilter(request, response)).run();
        } catch (Throwable e) {
            throw new ServletException(e);
        }
    }

    private String getServletName(HttpServletRequest httpServlet, FilterChain chain) throws ServletException {
        String servletName = null;
        // /abc , /a/b/c
        // // Servlet 未提供 API 来解决 HTTP 请求匹配映射到了哪个 Servlet
        if (FILTER_CHAIN_IMPL_CLASS != null) {
            ApplicationFilterChain filterChain = (ApplicationFilterChain) chain;
            try {
                Field field = FILTER_CHAIN_IMPL_CLASS.getDeclaredField("servlet");
                field.setAccessible(true);
                Servlet servlet = (Servlet) field.get(filterChain);
                if (servlet != null) {
                    servletName = servlet.getServletConfig().getServletName();
                }
            } catch (Exception e) {
                throw new ServletException(e);
            }

        }
        return servletName;
    }

    @Override
    public void destroy() {
    }

}
