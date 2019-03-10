package com.demo.ums.controller.role.model;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * Created on 2017/7/26.
 *
 * @author vikde
 */
public class AssignRolePermissionRequest {
    @Min(value = 1, message = "角色id错误")
    private int roleId;
    @NotNull(message = "权限id错误")
    private String permissionIds;

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    public String getPermissionIds() {
        return permissionIds;
    }

    public void setPermissionIds(String permissionIds) {
        this.permissionIds = permissionIds;
    }
}
