package com.demo.ums.controller.role;

import com.demo.ums.common.JsonResult;
import com.demo.ums.common.exception.ApiException;
import com.demo.ums.controller.role.model.*;
import com.demo.ums.service.RoleService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.Valid;

/**
 * @author vikde
 * @date 2017/12/5
 */
@RestController
@RequestMapping("/api/role/")
public class RoleController {
    @Resource
    private RoleService roleService;

    /**
     * 新建角色
     */
    @PreAuthorize("hasAuthority('createRole')")
    @RequestMapping(value = "createRole")
    public JsonResult createPermissionGroup(@Valid CreateRoleRequest createRoleRequest) {
        roleService.createRole(createRoleRequest.getRoleName(), createRoleRequest.getDescription());
        return JsonResult.getSuccessInstance("角色创建成功");
    }

    /**
     * 删除角色
     */
    @PreAuthorize("hasAuthority('deleteRole')")
    @RequestMapping(value = "deleteRole")
    public JsonResult deleteRole(@Valid DeleteRoleRequest deleteRoleRequest) throws ApiException {
        roleService.deleteRole(deleteRoleRequest.getRoleId());
        return JsonResult.getSuccessInstance("角色删除成功");
    }

    /**
     * 更新权限组
     */
    @PreAuthorize("hasAuthority('updateRole')")
    @RequestMapping(value = "updateRole")
    public JsonResult updateRole(@Valid UpdateRoleRequest updateRoleRequest) {
        roleService.updateRole(updateRoleRequest);
        return JsonResult.getSuccessInstance("角色更新成功");
    }

    /**
     * 分配角色权限
     */
    @PreAuthorize("hasAuthority('assignRolePermission')")
    @RequestMapping(value = "assignRolePermission")
    public JsonResult assignRolePermission(@Valid AssignRolePermissionRequest assignRolePermissionRequest) {
        roleService.assignRolePermission(assignRolePermissionRequest.getRoleId(), assignRolePermissionRequest.getPermissionIds());
        return JsonResult.getSuccessInstance("分配角色权限成功");
    }

    /**
     * 查询角色
     */
    @PreAuthorize("hasAuthority('readRole')")
    @RequestMapping(value = "readRole")
    public JsonResult readRole(@Valid ReadRoleRequest readRoleRequest) {
        return roleService.readRole(readRoleRequest);
    }

    /**
     * 查询用户角色分配列表
     */
    @PreAuthorize("hasAuthority('readUserRoleAssign')")
    @RequestMapping(value = "readUserRoleAssign")
    public JsonResult readUserRoleAssign(@Valid ReadUserRoleAssignRequest readUserRoleAssignRequest) {
        return roleService.readUserRoleAssign(readUserRoleAssignRequest);
    }
}
