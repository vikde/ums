package com.demo.ums.configuration.security;

import com.demo.ums.common.SessionConstant;
import com.demo.ums.common.util.PasswordUtil;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

import javax.annotation.Resource;

/**
 * @author vikde
 * @date 2019/05/02
 */
@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {
    @Resource
    private CustomUserDetailsServiceImpl customUserDetailsServiceImpl;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        // 获取用户输入的用户名和密码
        String inUsername = authentication.getName();
        String inPassword = authentication.getCredentials().toString();

        if (StringUtils.isEmpty(inUsername) || StringUtils.isEmpty(inPassword)) {
            throw new BadCredentialsException("用户名或密码不能为空");
        }

        CustomWebAuthenticationDetails details = (CustomWebAuthenticationDetails) authentication.getDetails();
        String inputVerificationCode = details.getVerificationCode();

        RequestAttributes requestAttributes = RequestContextHolder.currentRequestAttributes();
        Boolean verificationCodeRequire = (Boolean) requestAttributes.getAttribute(SessionConstant.LOGIN_VERIFICATION_CODE_REQUIRE, RequestAttributes.SCOPE_SESSION);
        String verificationCode = (String) requestAttributes.getAttribute(SessionConstant.LOGIN_VERIFICATION_CODE, RequestAttributes.SCOPE_SESSION);

        //需要验证
        if (verificationCodeRequire != null && verificationCodeRequire) {
            if (verificationCode == null || !verificationCode.equalsIgnoreCase(inputVerificationCode)) {
                throw new DisabledException("验证码输入错误");
            }
        }

        // userDetails为数据库中查询到的用户信息
        UserDetails userDetails = customUserDetailsServiceImpl.loadUserByUsername(inUsername);

        // 如果是自定义AuthenticationProvider，需要手动密码校验
        String password = PasswordUtil.encryptPassword(inPassword);
        if (!userDetails.getPassword().equals(password)) {
            throw new BadCredentialsException("密码错误");
        }
        if (!userDetails.isAccountNonExpired()) {
            throw new DisabledException("用户已被删除");
        }
        if (!userDetails.isCredentialsNonExpired()) {
            throw new DisabledException("密码已过期");
        }
        return new UsernamePasswordAuthenticationToken(inUsername, inPassword, userDetails.getAuthorities());
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}