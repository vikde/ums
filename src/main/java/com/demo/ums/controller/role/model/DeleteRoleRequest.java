package com.demo.ums.controller.role.model;

import javax.validation.constraints.Min;

/**
 * Created on 2017/7/26.
 *
 * @author vikde
 */
public class DeleteRoleRequest {
    @Min(value = 1, message = "角色id错误")
    private int roleId;

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }
}
