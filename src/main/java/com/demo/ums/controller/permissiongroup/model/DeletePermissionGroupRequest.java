package com.demo.ums.controller.permissiongroup.model;

import javax.validation.constraints.Min;

/**
 * Created on 2017/7/26.
 *
 * @author vikde
 */
public class DeletePermissionGroupRequest {
    @Min(value = 1, message = "权限组id错误")
    private int permissionGroupId;

    public int getPermissionGroupId() {
        return permissionGroupId;
    }

    public void setPermissionGroupId(int permissionGroupId) {
        this.permissionGroupId = permissionGroupId;
    }
}
