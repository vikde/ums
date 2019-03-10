package com.demo.ums.controller.role.model;

/**
 * @author vikde
 * @date 2019/03/10
 */
public class ReadUserRoleAssignResponse {
    private int roleId;
    private String roleName;
    private boolean isAssign;

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

    public boolean getIsAssign() {
        return isAssign;
    }

    public void setIsAssign(boolean isAssign) {
        this.isAssign = isAssign;
    }
}
