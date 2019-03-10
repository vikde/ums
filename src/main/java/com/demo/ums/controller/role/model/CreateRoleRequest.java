package com.demo.ums.controller.role.model;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

/**
 * Created on 2017/7/26.
 *
 * @author vikde
 */
public class CreateRoleRequest {
    @NotEmpty(message = "角色名称不能为空")
    @Size(min = 1, max = 10, message = "角色名称长度错误(1~10位)")
    private String roleName;
    @NotEmpty(message = "角色描述不能为空")
    @Size(min = 1, max = 200, message = "角色描述长度错误(1~200位)")
    private String description;

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
