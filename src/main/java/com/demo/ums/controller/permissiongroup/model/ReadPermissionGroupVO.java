package com.demo.ums.controller.permissiongroup.model;

import com.demo.ums.repository.model.PermissionGroupPO;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

/**
 * @author vikde
 * @date 2018/01/16
 */
public class ReadPermissionGroupVO {
    private int permissionGroupId;
    private String permissionGroupName;
    private String description;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;

    public ReadPermissionGroupVO(PermissionGroupPO permissionGroup) {
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

    @Override
    public String toString() {
        return "ReadPermissionGroupVO{" +
                "permissionGroupId=" + permissionGroupId +
                ", permissionGroupName='" + permissionGroupName + '\'' +
                ", description='" + description + '\'' +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                '}';
    }
}
