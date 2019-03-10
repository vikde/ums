package com.demo.ums.controller.role.model;

import javax.validation.constraints.Min;

/**
 * @author vikde
 * @date 2019/03/10
 */
public class ReadUserRoleAssignRequest {
    @Min(value = 1, message = "用户id错误")
    private Integer userId;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
}
