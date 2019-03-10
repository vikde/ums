package com.demo.ums.controller.user.model;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

/**
 * Created on 2017/7/26.
 *
 * @author vikde
 */
public class ChangePasswordRequest {
    @NotEmpty(message = "用户名不能为空")
    @Size(min = 3, max = 20, message = "用户名长度错误(3~20位)")
    private String username;
    @NotEmpty(message = "原密码不能为空")
    @Size(min = 8, max = 30, message = "原密码长度错误(8~30位)")
    private String password;
    @NotEmpty(message = "新密码不能为空")
    @Size(min = 8, max = 30, message = "新密码长度错误(8~30位)")
    private String firstPassword;
    @NotEmpty(message = "确认新密码不能为空")
    @Size(min = 8, max = 30, message = "确认新密码长度错误(8~30位)")
    private String secondPassword;

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

    public String getFirstPassword() {
        return firstPassword;
    }

    public void setFirstPassword(String firstPassword) {
        this.firstPassword = firstPassword;
    }

    public String getSecondPassword() {
        return secondPassword;
    }

    public void setSecondPassword(String secondPassword) {
        this.secondPassword = secondPassword;
    }
}
