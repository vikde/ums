package com.demo.ums.controller.permissiongroup.model;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

/**
 * @author vikde
 * @date 2017/12/7
 */
public class UpdatePermissionGroupRequest {
    @Min(value = 1, message = "权限组id错误")
    private int permissionGroupId;
    @NotEmpty(message = "权限组名不能为空")
    @Size(min = 1, max = 10, message = "权限组名长度错误(1~10位)")
    private String permissionGroupName;
    @NotEmpty(message = "权限组描述不能为空")
    @Size(min = 1, max = 200, message = "权限组描述长度错误(1~200位)")
    private String description;

    public int getPermissionGroupId() {
        return permissionGroupId;
    }

    public void setPermissionGroupId(int permissionGroupId) {
        this.permissionGroupId = permissionGroupId;
    }

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
