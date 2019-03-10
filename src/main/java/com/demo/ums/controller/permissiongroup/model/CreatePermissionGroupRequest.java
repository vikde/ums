package com.demo.ums.controller.permissiongroup.model;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

/**
 * Created on 2017/7/26.
 *
 * @author vikde
 */
public class CreatePermissionGroupRequest {
    @NotEmpty(message = "权限组名称不能为空")
    @Size(min = 1, max = 10, message = "权限组名称长度错误(1~10位)")
    private String permissionGroupName;
    @NotEmpty(message = "权限组描述不能为空")
    @Size(min = 1, max = 200, message = "权限组描述长度错误(1~200位)")
    private String description;

    public String getPermissionGroupName() {
        return permissionGroupName;
    }

    public void setPermissionGroupName(String permissionGroupName) {
        this.permissionGroupName = permissionGroupName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
