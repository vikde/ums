package com.demo.ums.service;

import com.demo.ums.common.Config;
import com.demo.ums.common.JsonResult;
import com.demo.ums.common.exception.ApiException;
import com.demo.ums.common.type.UserStatusType;
import com.demo.ums.common.util.PasswordUtil;
import com.demo.ums.controller.user.model.*;
import com.demo.ums.repository.mapper.UserMapper;
import com.demo.ums.repository.mapper.ext.ExtUserMapper;
import com.demo.ums.repository.mapper.ext.ExtUserRoleMapper;
import com.demo.ums.repository.model.PermissionPO;
import com.demo.ums.repository.model.UserPO;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.google.common.base.Splitter;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
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
        UserPO oldUser = extUserMapper.readUserByUsername(username);
        if (oldUser != null) {
            throw new ApiException("用户已存在");
        }
        UserPO user = new UserPO();
        user.setUsername(username);
        user.setName(name);
        user.setPassword(PasswordUtil.encryptPassword(Config.INIT_PASSWORD));
        user.setUserStatusType(UserStatusType.INACTIVE.getIndex());
        userMapper.insertSelective(user);
    }

    /**
     * 删除用户
     */
    public void deleteUser(String currentUsername, DeleteUserRequest deleteUserRequest) throws ApiException {
        UserPO user = userMapper.selectByPrimaryKey(deleteUserRequest.getUserId());
        if (user == null) {
            throw new ApiException("用户不存在");
        }
        if (user.getUserStatusType() == UserStatusType.DELETED.getIndex()) {
            throw new ApiException("用户已被删除");
        }
        if (currentUsername.equalsIgnoreCase(user.getUsername())) {
            throw new ApiException("不能删除自己");
        }
        UserPO updateUser = new UserPO();
        updateUser.setUserId(deleteUserRequest.getUserId());
        updateUser.setUserStatusType(UserStatusType.DELETED.getIndex());
        userMapper.updateByPrimaryKeySelective(updateUser);
    }

    /**
     * 更新用户
     */
    public void updateUser(UpdateUserRequest updateUserRequest) {
        UserPO user = new UserPO();
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
        List<UserPO> userList = extUserMapper.readUser(readUserRequest.getUserId(), readUserRequest.getUsername(), readUserRequest.getName());

        JsonResult jsonResult = JsonResult.getSuccessInstance();
        jsonResult.setTotal(userList instanceof Page ? ((Page) userList).getTotal() : userList.size());
        List<ReadUserVO> readUserRespons = new ArrayList<>(userList.size());
        for (UserPO user : userList) {
            readUserRespons.add(new ReadUserVO(user));
        }
        jsonResult.setData(readUserRespons);
        return jsonResult;
    }

    /**
     * 登录
     */
    public void login(String username) {
        //记录登陆数据
        extUserMapper.login(username, new Date());
    }

    /**
     * 获取当前用户信息与权限列表
     */
    public ReadOwnVO readOwn(String username) {
        UserPO user = extUserMapper.readUserByUsername(username);
        ReadOwnVO readOwnVO = new ReadOwnVO(user);
        return readOwnVO;
    }

    /**
     * 获取用户权限
     */
    public List<PermissionPO> readUserPermission(int userId) {
        return extUserMapper.readUserPermission(userId);
    }

    /**
     * 修改密码
     */
    public void changePassword(String username, String password, String firstPassword, String secondPassword) throws ApiException {
        if (!firstPassword.equalsIgnoreCase(secondPassword)) {
            throw new ApiException("两次输入的密码不一致密码");
        }
        UserPO user = extUserMapper.readUserByUsername(username);
        if (user == null || !password.equals(PasswordUtil.decryptPassword(user.getPassword()))) {
            throw new ApiException("用户名或密码错误");
        }
        String encryptPassword = PasswordUtil.encryptPassword(firstPassword);
        extUserMapper.changePassword(username, encryptPassword, UserStatusType.ACTIVE.getIndex());
    }
}
