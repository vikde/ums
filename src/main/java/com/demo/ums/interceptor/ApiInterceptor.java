package com.demo.ums.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * @author vikde
 */
public class ApiInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        System.out.println(request.getRequestURI());

        Map<String, String[]> parameterMap = request.getParameterMap();
        List<String> parameterKeyList = new ArrayList<>(parameterMap.keySet());
        Collections.sort(parameterKeyList);

        StringBuilder stringBuilder = new StringBuilder();
        for (String parameterKey : parameterKeyList) {
            if (stringBuilder.length() > 0) {
                stringBuilder.append("&");
            }
            stringBuilder.append(parameterKey);
            stringBuilder.append("=");
            String[] parameterValue = parameterMap.get(parameterKey);
            if (parameterValue != null && parameterValue.length == 1) {
                stringBuilder.append(parameterValue[0]);
            }
        }
        System.out.println(stringBuilder.toString());
        request.setAttribute("startTime", System.currentTimeMillis());
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        Long startTime = (Long) request.getAttribute("startTime");
        System.out.println("请求耗时:" + (System.currentTimeMillis() - startTime));
    }
}