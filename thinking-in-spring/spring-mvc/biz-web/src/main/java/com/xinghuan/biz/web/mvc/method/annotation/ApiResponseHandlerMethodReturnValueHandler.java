package com.xinghuan.biz.web.mvc.method.annotation;

import com.xinghuan.biz.api.ApiResponse;
import org.springframework.core.MethodParameter;
import org.springframework.core.annotation.AnnotatedElementUtils;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.http.server.ServletServerHttpResponse;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodReturnValueHandler;
import org.springframework.web.method.support.ModelAndViewContainer;

import javax.servlet.http.HttpServletResponse;

/**
 * @author <a href="mailto:mercyblitz@gmail.com">Mercy</a>
 * @see org.springframework.web.servlet.mvc.method.annotation.RequestResponseBodyMethodProcessor
 * @since
 */
public class ApiResponseHandlerMethodReturnValueHandler implements HandlerMethodReturnValueHandler {

    private MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();

    @Override
    public boolean supportsReturnType(MethodParameter returnType) {
        return (AnnotatedElementUtils.hasAnnotation(returnType.getContainingClass(), ResponseBody.class) ||
                returnType.hasMethodAnnotation(ResponseBody.class))
                && !ApiResponse.class.equals(returnType.getParameterType());
    }

    @Override
    public void handleReturnValue(Object returnValue, MethodParameter returnType, ModelAndViewContainer mavContainer,
                                  NativeWebRequest webRequest) throws Exception {
        // TODO 可通过客户端的传递的请求头来切换不同的响应体的内容
        mavContainer.setRequestHandled(true);
        // returnValue =  POJO
        ApiResponse apiResponse = ApiResponse.ok(returnValue);
        HttpServletResponse response = (HttpServletResponse) webRequest.getNativeResponse();
        response.addHeader("v", "3");
        ServletServerHttpResponse httpOutMessage = createOutputMessage(webRequest);
        converter.write(apiResponse, MediaType.APPLICATION_JSON, httpOutMessage);
    }

    protected ServletServerHttpResponse createOutputMessage(NativeWebRequest webRequest) {
        HttpServletResponse response = webRequest.getNativeResponse(HttpServletResponse.class);
        Assert.state(response != null, "No HttpServletResponse");
        return new ServletServerHttpResponse(response);
    }
}
