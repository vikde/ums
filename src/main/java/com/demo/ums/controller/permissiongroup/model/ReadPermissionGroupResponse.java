package com.demo.ums.controller.permissiongroup.model;

import com.alibaba.fastjson.annotation.JSONField;
import com.demo.ums.repository.model.PermissionGroup;

import java.util.Date;

/**
 * @author vikde
 * @date 2018/01/16
 */
public class ReadPermissionGroupResponse {
    private int permissionGroupId;
    private String permissionGroupName;
    private String description;
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;

    public ReadPermissionGroupResponse(PermissionGroup permissionGroup) {
        this.permissionGroupId = permissionGroup.getPermissionGroupId();
        this.permissionGroupName = permissionGroup.getPermissionGroupName();
        this.description = permissionGroup.getDescription();
        this.createTime = permissionGroup.getCreateTime();
        this.updateTime = permissionGroup.getUpdateTime();
    }

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

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}
