package com.demo.ums.controller.user.model;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

/**
 * Created on 2017/7/26.
 *
 * @author vikde
 */
public class LoginRequest {
    @NotEmpty(message = "用户名不能为空")
    private String username;
    @Size(min = 8, max = 30, message = "密码长度错误(8~30位)")
    @NotEmpty
    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
