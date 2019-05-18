package com.demo.ums.controller.role.model;

import com.alibaba.fastjson.annotation.JSONField;
import com.demo.ums.repository.model.RolePO;

import java.io.Serializable;
import java.util.Date;

/**
 * @author mybatis-generator
 */
public class ReadRoleVO implements Serializable {
    private int roleId;
    private String roleName;
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;
    private String description;

    public ReadRoleVO(RolePO role) {
        this.roleId = role.getRoleId();
        this.roleName = role.getRoleName();
        this.createTime = role.getCreateTime();
        this.updateTime = role.getUpdateTime();
        this.description = role.getDescription();
    }

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "ReadRoleVO{" +
                "roleId=" + roleId +
                ", roleName='" + roleName + '\'' +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", description='" + description + '\'' +
                '}';
    }
}