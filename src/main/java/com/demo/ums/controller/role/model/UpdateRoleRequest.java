package com.demo.ums.controller.role.model;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

/**
 * @author vikde
 * @date 2017/12/7
 */
public class UpdateRoleRequest {
    @Min(value = 1, message = "角色id错误")
    private int roleId;
    @NotEmpty(message = "角色名不能为空")
    @Size(min = 1, max = 10, message = "角色名长度错误(1~10位)")
    private String roleName;
    @NotEmpty(message = "角色描述不能为空")
    @Size(min = 1, max = 200, message = "角色描述长度错误(1~200位)")
    private String description;

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
