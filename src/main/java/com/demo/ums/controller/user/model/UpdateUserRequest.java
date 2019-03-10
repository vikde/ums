package com.demo.ums.controller.user.model;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

/**
 * @author vikde
 * @date 2017/12/7
 */
public class UpdateUserRequest {
    @Min(value = 1, message = "用户id错误")
    private int userId;
    @NotEmpty(message = "用户姓名不能为空")
    @Size(min = 1, max = 10, message = "用户姓名角色名长度错误(1~10位)")
    private String name;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
