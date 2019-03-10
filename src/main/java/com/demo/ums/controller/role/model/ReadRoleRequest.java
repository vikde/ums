package com.demo.ums.controller.role.model;

import java.io.Serializable;

/**
 * Created on 2017/7/26.
 *
 * @author vikde
 */
public class ReadRoleRequest implements Serializable {
    private Integer roleId;
    private String roleName;
    private Integer pageNumber;
    private Integer pageSize;

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public Integer getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(Integer pageNumber) {
        this.pageNumber = pageNumber;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }
}
