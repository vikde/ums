package com.demo.ums.controller.user.model;

import com.demo.ums.repository.model.UserPO;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

/**
 * @author vikde
 * @date 2017/12/30
 */
public class ReadUserVO {
    private int userId;
    private String username;
    private String name;
    private int userStatusType;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date loginTime;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date preLoginTime;
    private int loginCount;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;

    public ReadUserVO(UserPO user) {
        userId = user.getUserId();
        username = user.getUsername();
        name = user.getName();
        userStatusType = user.getUserStatusType();
        createTime = user.getCreateTime();
        loginTime = user.getLoginTime();
        preLoginTime = user.getPreLoginTime();
        loginCount = user.getLoginCount();
        updateTime = user.getUpdateTime();
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

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    public String toString() {
        return "ReadUserVO{" +
                "userId=" + userId +
                ", username='" + username + '\'' +
                ", name='" + name + '\'' +
                ", userStatusType=" + userStatusType +
                ", createTime=" + createTime +
                ", loginTime=" + loginTime +
                ", preLoginTime=" + preLoginTime +
                ", loginCount=" + loginCount +
                ", updateTime=" + updateTime +
                '}';
    }
}
