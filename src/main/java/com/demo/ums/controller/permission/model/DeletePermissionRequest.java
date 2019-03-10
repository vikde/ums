package com.demo.ums.controller.permission.model;

import javax.validation.constraints.Min;

/**
 * Created on 2017/7/26.
 *
 * @author vikde
 */
public class DeletePermissionRequest {
    @Min(value = 1, message = "权限id错误")
    private int permissionId;

    public int getPermissionId() {
        return permissionId;
    }

    public void setPermissionId(int permissionId) {
        this.permissionId = permissionId;
    }
}
