package com.demo.ums.configuration.security;

import org.springframework.security.web.authentication.WebAuthenticationDetails;

import javax.servlet.http.HttpServletRequest;

/**
 * @author vikde
 * @date 2019/05/01
 */
public class CustomWebAuthenticationDetails extends WebAuthenticationDetails {
    private final String verificationCode;

    public CustomWebAuthenticationDetails(HttpServletRequest request) {
        super(request);
        verificationCode = request.getParameter("verificationCode");
    }

    public String getVerificationCode() {
        return verificationCode;
    }
}