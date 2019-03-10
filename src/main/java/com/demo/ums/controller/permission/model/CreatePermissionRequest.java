package com.demo.ums.controller.permission.model;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

/**
 * Created on 2017/7/26.
 *
 * @author vikde
 */
public class CreatePermissionRequest {
    @NotEmpty(message = "权限名不能为空")
    @Size(min = 1, max = 10, message = "权限名长度错误(1~10位)")
    private String permissionName;
    @Min(value = 1, message = "权限组id错误")
    private int permissionGroupId;
    @NotEmpty(message = "权限地址不能为空")
    private String path;
    @NotEmpty(message = "权限描述不能为空")
    @Size(min = 1, max = 200, message = "权限描述长度错误(1~200位)")
    private String description;

    public String getPermissionName() {
        return permissionName;
    }

    public void setPermissionName(String permissionName) {
        this.permissionName = permissionName;
    }

    public int getPermissionGroupId() {
        return permissionGroupId;
    }

    public void setPermissionGroupId(int permissionGroupId) {
        this.permissionGroupId = permissionGroupId;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
