package com.demo.ums.controller.user.model;

import javax.validation.constraints.Min;

/**
 * Created on 2017/7/26.
 *
 * @author vikde
 */
public class DeleteUserRequest {
    @Min(value = 1, message = "用户id错误")
    private int userId;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}
