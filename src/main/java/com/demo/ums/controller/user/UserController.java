package com.demo.ums.controller.user;

import com.demo.ums.common.JsonResult;
import com.demo.ums.common.exception.ApiException;
import com.demo.ums.controller.user.model.*;
import com.demo.ums.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.WebAttributes;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

/**
 * Created on 2017/07/02.
 *
 * @author vikde
 */
@RestController
@RequestMapping("/api/user/")
public class UserController {
    @Resource
    private UserService userService;

    /**
     * 创建用户
     */
    @PreAuthorize("hasAuthority('createUser')")
    @RequestMapping(value = "createUser")
    public JsonResult createUser(@Valid CreateUserRequest createUserRequest) throws ApiException {
        userService.createUser(createUserRequest.getUsername(), createUserRequest.getName());
        return JsonResult.getSuccessInstance();
    }

    /**
     * 删除用户
     */
    @PreAuthorize("hasAuthority('deleteUser')")
    @RequestMapping(value = "deleteUser")
    public JsonResult deleteUser(@Valid DeleteUserRequest deleteUserRequest) throws ApiException {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        userService.deleteUser(username, deleteUserRequest);
        return JsonResult.getSuccessInstance("删除用户成功");
    }

    /**
     * 更新用户
     */
    @PreAuthorize("hasAuthority('updateUser')")
    @RequestMapping(value = "updateUser")
    public JsonResult updateUser(@Valid UpdateUserRequest updateRoleRequest) {
        userService.updateUser(updateRoleRequest);
        return JsonResult.getSuccessInstance("更新用户成功");
    }

    /**
     * 分配用户角色
     */
    @PreAuthorize("hasAuthority('assignUserRole')")
    @RequestMapping(value = "assignUserRole")
    public JsonResult assignUserRole(@Valid AssignUserRoleRequest assignUserRoleRequest) {
        userService.assignUserRole(assignUserRoleRequest);
        return JsonResult.getSuccessInstance();
    }

    /**
     * 查询用户
     */
    @PreAuthorize("hasAuthority('readUser')")
    @RequestMapping(value = "readUser")
    public JsonResult readUser(@Valid ReadUserRequest readUserRequest) {
        return userService.readUser(readUserRequest);
    }

    /**
     * 登录
     */
    @PreAuthorize("authentication.authenticated")
    @RequestMapping(value = "login/success")
    public JsonResult loginSuccess() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        userService.login(username);
        return JsonResult.getSuccessInstance("登录成功");
    }

    /**
     * 登录失败
     */
    @RequestMapping(value = "login/failure")
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public JsonResult loginFailure(HttpServletRequest request) {
        Exception exception = (Exception) request.getAttribute(WebAttributes.AUTHENTICATION_EXCEPTION);
        return JsonResult.getInstance(HttpStatus.BAD_REQUEST, exception == null ? "error" : exception.getMessage());
    }

    /**
     * 退出登录成功
     */
    @RequestMapping(value = "logout")
    public JsonResult logout(HttpSession httpSession) {
        httpSession.invalidate();
        return JsonResult.getSuccessInstance("退出成功");
    }

    /**
     * 修改密码
     */
    @RequestMapping(value = "changePassword")
    public JsonResult changePassword(@Valid ChangePasswordRequest changePasswordRequest) throws ApiException {
        userService.changePassword(changePasswordRequest.getUsername(), changePasswordRequest.getPassword(),
                                   changePasswordRequest.getFirstPassword(), changePasswordRequest.getSecondPassword());
        SecurityContextHolder.clearContext();
        return JsonResult.getSuccessInstance();
    }

}
