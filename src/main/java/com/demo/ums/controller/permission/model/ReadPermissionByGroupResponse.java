package com.demo.ums.controller.permission.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created on 2017/7/26.
 *
 * @author vikde
 */
public class ReadPermissionByGroupResponse implements Serializable {
    private Integer permissionGroupId;
    private String permissionGroupName;
    private List<Permission> permissionList = new ArrayList<>();

    public static class Permission {
        private Integer permissionId;
        private String permissionName;
        private String path;
        private boolean isAssign;

        public Integer getPermissionId() {
            return permissionId;
        }

        public void setPermissionId(Integer permissionId) {
            this.permissionId = permissionId;
        }

        public String getPermissionName() {
            return permissionName;
        }

        public void setPermissionName(String permissionName) {
            this.permissionName = permissionName;
        }

        public String getPath() {
            return path;
        }

        public void setPath(String path) {
            this.path = path;
        }

        public boolean getIsAssign() {
            return isAssign;
        }

        public void setIsAssign(boolean isAssign) {
            this.isAssign = isAssign;
        }
    }

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
        this.permissionGroupName = permissionGroupName;
    }

    public List<Permission> getPermissionList() {
        return permissionList;
    }

    public void setPermissionList(List<Permission> permissionList) {
        this.permissionList = permissionList;
    }
}