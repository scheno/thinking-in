package com.xinghuan.biz.web.servlet.mvc.interceptor;

import io.github.resilience4j.bulkhead.Bulkhead;
import io.github.resilience4j.bulkhead.BulkheadConfig;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.core.annotation.AnnotatedElementUtils;
import org.springframework.core.annotation.AnnotationAttributes;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author shenchen
 * @since 2024/4/20 16:02
 */
public class ResourceBulkheadHandlerInterceptor implements HandlerInterceptor, InitializingBean, DisposableBean, ApplicationListener<ContextRefreshedEvent> {

    private BulkheadConfig config;

    @Deprecated
    private Map<String, Bulkhead> bulkheadsCache;

    private Map<Method, Bulkhead> methodBulkheadsMapping;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 判断是否需要限流
        // 正常执行 postHandle 方法
        // 异常执行 afterCompletion 方法

        if (handler instanceof HandlerMethod) {
            HandlerMethod handlerMethod = (HandlerMethod) handler;
            Bulkhead bulkhead = doGetBulkhead(handlerMethod);
            if (bulkhead != null) {
                bulkhead.acquirePermission();
            }
        }

        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        // 记录
        HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        // 清除状态
        if (handler instanceof HandlerMethod) {
            HandlerMethod handlerMethod = (HandlerMethod) handler;
            Bulkhead bulkhead = doGetBulkhead(handlerMethod);
            if (bulkhead != null) {
                bulkhead.releasePermission();
            }
        }
    }

    @Deprecated
    private Bulkhead getBulkhead(HandlerMethod handlerMethod) {
        String resourceName = getResourceName(handlerMethod);
        return bulkheadsCache.computeIfAbsent(resourceName, n -> Bulkhead.of(resourceName, config));
    }

    private String getResourceName(HandlerMethod handlerMethod) {
        Method method = handlerMethod.getMethod();
        AnnotationAttributes annotationAttributes = AnnotatedElementUtils.getMergedAnnotationAttributes(method, RequestMapping.class);
        String[] path = annotationAttributes.getStringArray("path");
        return path[0];
    }


    @Override
    public void afterPropertiesSet() throws Exception {
        this.config = BulkheadConfig.custom().build();
        this.bulkheadsCache = new ConcurrentHashMap<>();
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        this.methodBulkheadsMapping = new HashMap<>();
        ApplicationContext context = event.getApplicationContext();
        Map<String, RequestMappingHandlerMapping> requestMappingHandlerMappingMap =
                context.getBeansOfType(RequestMappingHandlerMapping.class);
        for (RequestMappingHandlerMapping requestMappingHandlerMapping : requestMappingHandlerMappingMap.values()) {
            Map<RequestMappingInfo, HandlerMethod> handlerMethods = requestMappingHandlerMapping.getHandlerMethods();
            for (Map.Entry<RequestMappingInfo, HandlerMethod> entry : handlerMethods.entrySet()) {
                RequestMappingInfo requestMappingInfo = entry.getKey();
                HandlerMethod handlerMethod = entry.getValue();
                Method method = handlerMethod.getMethod();
                String resourceName = requestMappingInfo.toString();
                methodBulkheadsMapping.put(method, Bulkhead.of(resourceName, config));
            }
        }
    }

    @Override
    public void destroy() throws Exception {

    }

    private Bulkhead doGetBulkhead(HandlerMethod handlerMethod) {
        Method method = handlerMethod.getMethod();
        return methodBulkheadsMapping.get(method);
    }

}
