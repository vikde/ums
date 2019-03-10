package com.demo.ums.interceptor;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * @author vikde
 */
@Component
public class ApiPermissionInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Map<String, String[]> parameterMap = request.getParameterMap();
        List<String> parameterKeyList = new ArrayList<>(parameterMap.keySet());
        Collections.sort(parameterKeyList);

        HttpSession httpSession = request.getSession();

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
        //String username = request.getParameter("username");
        //if (username != null) {
        //    User user = userMapper.readUserByUsername(username);
        //    if (user != null) {
        //        List<Permission> permissions = permissionMapper.readPermissionByUserId(user.getUserId());
        //        if (permissions != null) {
        //            System.out.println(JSON.toJSON(permissions));
        //        }
        //    }
        //}
        //System.out.println(stringBuilder.toString());
        request.setAttribute("startTime", System.currentTimeMillis());
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        Long startTime = (Long) request.getAttribute("startTime");
        System.out.println("请求参数" + (System.currentTimeMillis() - startTime));
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
    }
}