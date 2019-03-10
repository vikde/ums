package com.demo.ums.controller.user.model;

import com.alibaba.fastjson.annotation.JSONField;
import com.demo.ums.repository.model.Permission;
import com.demo.ums.repository.model.User;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created on 2017/07/02.
 *
 * @author vikde
 */
public class ReadOwnResponse implements Serializable {
    private int userId;
    private String username;
    private String name;
    private int userStatusType;
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date loginTime;
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date preLoginTime;
    private int loginCount;
    private List<Permission> permissionList = new ArrayList<>();

    public ReadOwnResponse() {
    }

    public ReadOwnResponse(User user) {
        userId = user.getUserId();
        username = user.getUsername();
        name = user.getName();
        userStatusType = user.getUserStatusType();
        createTime = user.getCreateTime();
        loginTime = user.getLoginTime();
        preLoginTime = user.getPreLoginTime();
        loginCount = user.getLoginCount();
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getUserStatusType() {
        return userStatusType;
    }

    public void setUserStatusType(int userStatusType) {
        this.userStatusType = userStatusType;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getLoginTime() {
        return loginTime;
    }

    public void setLoginTime(Date loginTime) {
        this.loginTime = loginTime;
    }

    public Date getPreLoginTime() {
        return preLoginTime;
    }

    public void setPreLoginTime(Date preLoginTime) {
        this.preLoginTime = preLoginTime;
    }

    public int getLoginCount() {
        return loginCount;
    }

    public void setLoginCount(int loginCount) {
        this.loginCount = loginCount;
    }

    public List<Permission> getPermissionList() {
        return permissionList;
    }

    public void setPermissionList(List<Permission> permissionList) {
        this.permissionList = permissionList;
    }
}
