package com.demo.ums.service;

import com.demo.ums.repository.mapper.ext.ExtUserRoleMapper;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.demo.ums.common.Config;
import com.demo.ums.common.JsonResult;
import com.demo.ums.common.exception.ApiException;
import com.demo.ums.common.type.JsonResultType;
import com.demo.ums.common.type.UserStatusType;
import com.demo.ums.common.util.PasswordUtil;
import com.demo.ums.controller.user.model.*;
import com.demo.ums.repository.mapper.UserMapper;
import com.demo.ums.repository.mapper.ext.ExtUserMapper;
import com.demo.ums.repository.model.Permission;
import com.demo.ums.repository.model.User;
import com.google.common.base.Splitter;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created on 2017/7/3.
 *
 * @author vikde
 */
@Service
public class UserService {
    @Resource
    private UserMapper userMapper;
    @Resource
    private ExtUserMapper extUserMapper;
    @Resource
    private ExtUserRoleMapper extUserRoleMapper;

    /**
     * 创建用户
     */
    public void createUser(String username, String name) throws ApiException {
        User oldUser = extUserMapper.readUserByUsername(username);
        if (oldUser != null) {
            throw new ApiException(JsonResultType.USER_ALREADY_EXIST);
        }
        User user = new User();
        user.setUsername(username);
        user.setName(name);
        user.setPassword(PasswordUtil.encryptPassword(Config.INIT_PASSWORD));
        user.setUserStatusType(UserStatusType.INACTIVE.getIndex());
        userMapper.insertSelective(user);
    }

    /**
     * 删除用户
     */
    public void deleteUser(HttpSession httpSession, DeleteUserRequest deleteUserRequest) throws ApiException {
        User user = userMapper.selectByPrimaryKey(deleteUserRequest.getUserId());
        if (user == null) {
            throw new ApiException(JsonResultType.USER_NOT_EXIST);
        }
        if (user.getUserStatusType() == UserStatusType.DELETED.getIndex()) {
            throw new ApiException(JsonResultType.USER_IS_DELETED);
        }
        Object object = httpSession.getAttribute("user");
        if (!(object instanceof ReadOwnResponse)) {
            throw new ApiException(JsonResultType.USER_NOT_LOGIN);
        }
        ReadOwnResponse readOwnResponse = (ReadOwnResponse) object;
        if (readOwnResponse.getUserId() == user.getUserId()) {
            throw new ApiException(JsonResultType.USER_EXCEPTION, "不能删除自己");
        }
        User updateUser = new User();
        updateUser.setUserId(deleteUserRequest.getUserId());
        updateUser.setUserStatusType(UserStatusType.DELETED.getIndex());
        userMapper.updateByPrimaryKeySelective(updateUser);
    }

    /**
     * 更新用户
     */
    public void updateUser(UpdateUserRequest updateUserRequest) {
        User user = new User();
        user.setUserId(updateUserRequest.getUserId());
        user.setName(updateUserRequest.getName());
        userMapper.updateByPrimaryKeySelective(user);
    }

    /**
     * 分配用户角色
     */
    public void assignUserRole(AssignUserRoleRequest assignUserRoleRequest) {
        int userId = assignUserRoleRequest.getUserId();
        String roleIds = assignUserRoleRequest.getRoleIds();
        extUserRoleMapper.deleteByUserId(userId);
        Splitter.on(",").omitEmptyStrings().trimResults()
                .split(roleIds)
                .forEach(roleId -> {
                    extUserRoleMapper.assignUserRole(userId, Integer.parseInt(roleId));
                });
    }

    /**
     * 查询用户
     */
    public JsonResult readUser(ReadUserRequest readUserRequest) {
        PageHelper.startPage(readUserRequest.getPageNumber(), readUserRequest.getPageSize());
        List<User> userList = extUserMapper.readUser(readUserRequest.getUserId(), readUserRequest.getUsername(), readUserRequest.getName());

        JsonResult jsonResult = JsonResult.getSuccessInstance();
        jsonResult.setTotal(userList instanceof Page ? ((Page) userList).getTotal() : userList.size());
        List<ReadUserResponse> readUserResponses = new ArrayList<>(userList.size());
        for (User user : userList) {
            readUserResponses.add(new ReadUserResponse(user));
        }
        jsonResult.setData(readUserResponses);
        return jsonResult;
    }

    /**
     * 登录
     */
    public void login(HttpSession httpSession, String username, String password) throws ApiException {
        User user = extUserMapper.readUserByUsername(username);
        if (user == null || !password.equals(PasswordUtil.decryptPassword(user.getPassword()))) {
            throw new ApiException(JsonResultType.USER_NAME_OR_PASSWORD_ERROR);
        }
        if (user.getUserStatusType() == UserStatusType.DELETED.getIndex()) {
            throw new ApiException(JsonResultType.USER_IS_DELETED);
        } else if (user.getUserStatusType() == UserStatusType.INACTIVE.getIndex()) {
            throw new ApiException(JsonResultType.USER_NOT_ACTIVE);
        }
        //记录登陆数据
        extUserMapper.login(user.getUserId(), new Date());
        user.setLoginTime(new Date());

        ReadOwnResponse readOwnResponse = new ReadOwnResponse(user);
        List<Permission> permissions = extUserMapper.readUserPermission(readOwnResponse.getUserId());
        for (Permission permission : permissions) {
            readOwnResponse.getPermissionList().add(permission);
        }
        httpSession.setAttribute("user", readOwnResponse);
    }

    /**
     * 登录
     */
    public void logout(HttpSession httpSession) {
        httpSession.invalidate();
    }

    /**
     * 获取当前用户信息与权限列表
     */
    public ReadOwnResponse readOwn(HttpSession httpSession) throws ApiException {
        Object object = httpSession.getAttribute("user");
        if (object == null || !(object instanceof ReadOwnResponse)) {
            throw new ApiException(JsonResultType.USER_NOT_LOGIN);
        }
        return (ReadOwnResponse) object;
    }

    /**
     * 获取用户权限
     */
    public List<Permission> readUserPermission(int userId) {
        return extUserMapper.readUserPermission(userId);
    }

    /**
     * 修改密码
     */
    public void changePassword(HttpSession httpSession, String username, String password, String firstPassword, String secondPassword) throws ApiException {
        if (!firstPassword.equalsIgnoreCase(secondPassword)) {
            throw new ApiException(JsonResultType.USER_PASSWORD_DIFFERENT);
        }
        User user = extUserMapper.readUserByUsername(username);
        if (user == null || !password.equals(PasswordUtil.decryptPassword(user.getPassword()))) {
            throw new ApiException(JsonResultType.USER_NAME_OR_PASSWORD_ERROR);
        }
        String encryptPassword = PasswordUtil.encryptPassword(firstPassword);
        if (encryptPassword == null) {
            throw new ApiException(JsonResultType.SYSTEM_EXCEPTION);
        }
        extUserMapper.changePassword(username, encryptPassword, UserStatusType.ACTIVE.getIndex());
        httpSession.invalidate();
    }
}
