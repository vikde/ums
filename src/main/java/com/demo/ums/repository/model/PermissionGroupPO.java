package com.demo.ums.repository.model;

import java.io.Serializable;
import java.util.Date;

/**
 * @author mybatis-generator
 * @date 2019/05/01
 */
public class PermissionGroupPO implements Serializable {
    /**
     *权限组id
     */
    private Integer permissionGroupId;

    /**
     *权限组名称
     */
    private String permissionGroupName;

    /**
     *权限组描述
     */
    private String description;

    /**
     *创建时间
     */
    private Date createTime;

    /**
     *更新时间
     */
    private Date updateTime;

    private static final long serialVersionUID = 1L;

    public Integer getPermissionGroupId() {
        return permissionGroupId;
    }

    public void setPermissionGroupId(Integer permissionGroupId) {
        this.permissionGroupId = permissionGroupId;
    }

    public String getPermissionGroupName() {
        return permissionGroupName;
    }

    public void setPermissionGroupName(String permissionGroupName) {
        this.permissionGroupName = permissionGroupName == null ? null : permissionGroupName.trim();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
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
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        PermissionGroupPO other = (PermissionGroupPO) that;
        return (this.getPermissionGroupId() == null ? other.getPermissionGroupId() == null : this.getPermissionGroupId().equals(other.getPermissionGroupId()))
            && (this.getPermissionGroupName() == null ? other.getPermissionGroupName() == null : this.getPermissionGroupName().equals(other.getPermissionGroupName()))
            && (this.getDescription() == null ? other.getDescription() == null : this.getDescription().equals(other.getDescription()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()))
            && (this.getUpdateTime() == null ? other.getUpdateTime() == null : this.getUpdateTime().equals(other.getUpdateTime()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getPermissionGroupId() == null) ? 0 : getPermissionGroupId().hashCode());
        result = prime * result + ((getPermissionGroupName() == null) ? 0 : getPermissionGroupName().hashCode());
        result = prime * result + ((getDescription() == null) ? 0 : getDescription().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        result = prime * result + ((getUpdateTime() == null) ? 0 : getUpdateTime().hashCode());
        return result;
    }
}