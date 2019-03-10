package com.demo.ums.controller.permission.model;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * Created on 2017/7/26.
 *
 * @author vikde
 */
public class ReadPermissionByGroupRequest {
    @Min(value = 1, message = "角色id错误")
    @NotNull(message = "角色id不能为空")
    private Integer roleId;

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }
}
