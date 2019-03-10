package com.demo.ums.controller.user.model;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

/**
 * Created on 2017/7/26.
 *
 * @author vikde
 */
public class CreateUserRequest {
    @NotEmpty(message = "用户名不能为空")
    @Size(min = 3, max = 20, message = "用户名长度错误(3~20位)")
    private String username;
    @NotEmpty(message = "姓名不能为空")
    @Size(min = 1, max = 10, message = "姓名长度错误(1~10位)")
    private String name;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
