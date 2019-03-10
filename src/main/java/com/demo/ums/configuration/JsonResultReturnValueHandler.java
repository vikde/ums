package com.demo.ums.configuration;

import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import com.demo.ums.common.JsonResult;
import com.demo.ums.controller.ApiResponseBody;
import org.springframework.core.MethodParameter;
import org.springframework.core.annotation.AnnotatedElementUtils;
import org.springframework.http.MediaType;
import org.springframework.http.server.ServletServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodReturnValueHandler;
import org.springframework.web.method.support.ModelAndViewContainer;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author vikde
 * @date 2019/03/10
 */
@Component
public class JsonResultReturnValueHandler implements HandlerMethodReturnValueHandler {
    @Resource
    private FastJsonHttpMessageConverter fastJsonHttpMessageConverter;

    @Override
    public boolean supportsReturnType(MethodParameter returnType) {
        return (AnnotatedElementUtils.hasAnnotation(returnType.getContainingClass(), ApiResponseBody.class) ||
                returnType.hasMethodAnnotation(ApiResponseBody.class));
    }

    @Override
    public void handleReturnValue(Object returnValue, MethodParameter returnType, ModelAndViewContainer mavContainer, NativeWebRequest webRequest) throws Exception {
        mavContainer.setRequestHandled(true);
        HttpServletRequest request = webRequest.getNativeRequest(HttpServletRequest.class);
        HttpServletResponse response = webRequest.getNativeResponse(HttpServletResponse.class);
        ServletServerHttpResponse servletServerHttpResponse = new ServletServerHttpResponse(response);
        JsonResult jsonResult;
        Long startTime = (Long) request.getAttribute("startTime");
        int costTime = 0;
        if (startTime != null) {
            costTime = (int) (System.currentTimeMillis() - startTime);
        }

        if (returnValue instanceof JsonResult) {
            jsonResult = (JsonResult) returnValue;
        } else {
            jsonResult = JsonResult.getSuccessInstance();
            jsonResult.setData(returnValue);
        }
        jsonResult.setCostTime(costTime);
        fastJsonHttpMessageConverter.write(jsonResult, MediaType.APPLICATION_JSON_UTF8, servletServerHttpResponse);
    }
}