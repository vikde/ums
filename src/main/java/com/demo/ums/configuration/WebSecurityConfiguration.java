package com.demo.ums.configuration;

import com.demo.ums.configuration.security.CustomAuthenticationDetailsSource;
import com.demo.ums.configuration.security.CustomAuthenticationProvider;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import javax.annotation.Resource;

/**
 * @author vikde
 * @date 2019/05/01
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {
    @Resource
    private CustomAuthenticationProvider customAuthenticationProvider;
    @Resource
    private CustomAuthenticationDetailsSource customAuthenticationDetailsSource;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
            .antMatchers("/assets/**", "/favicon.ico").permitAll()
            .anyRequest().authenticated()
            .and()
            .formLogin()
            .loginPage("/login").permitAll()
            .loginProcessingUrl("/api/user/login").permitAll()
            .usernameParameter("username")
            .passwordParameter("password")
            .authenticationDetailsSource(customAuthenticationDetailsSource)
            .failureForwardUrl("/api/user/login/failure").permitAll()
            .successForwardUrl("/api/user/login/success")
            .and()
            .logout()
            .logoutUrl("/logout")
            .logoutSuccessUrl("/api/user/logout").permitAll()
            .and().headers().frameOptions().disable()
            .and().csrf().disable();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(customAuthenticationProvider);
    }
}