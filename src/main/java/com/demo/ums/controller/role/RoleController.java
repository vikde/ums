package com.demo.ums.controller.role;

import com.demo.ums.common.JsonResult;
import com.demo.ums.common.exception.ApiException;
import com.demo.ums.controller.ApiController;
import com.demo.ums.controller.role.model.*;
import com.demo.ums.service.RoleService;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.validation.Valid;

/**
 * @author vikde
 * @date 2017/12/5
 */
@ApiController
@RequestMapping("/api/role/")
public class RoleController {
    @Resource
    private RoleService roleService;

    /**
     * 新建角色
     */
    @RequestMapping(value = "createRole")
    public JsonResult createPermissionGroup(@Valid CreateRoleRequest createRoleRequest) {
        roleService.createRole(createRoleRequest.getRoleName(), createRoleRequest.getDescription());
        return JsonResult.getSuccessInstance("新建角色成功");
    }

    /**
     * 删除角色
     */
    @RequestMapping(value = "deleteRole")
    public JsonResult deleteRole(@Valid DeleteRoleRequest deleteRoleRequest) throws ApiException {
        roleService.deleteRole(deleteRoleRequest.getRoleId());
        return JsonResult.getSuccessInstance("删除角色成功");
    }

    /**
     * 更新权限组
     */
    @RequestMapping(value = "updateRole")
    public JsonResult updateRole(@Valid UpdateRoleRequest updateRoleRequest) {
        roleService.updateRole(updateRoleRequest);
        return JsonResult.getSuccessInstance("更新角色成功");
    }

    /**
     * 分配角色权限
     */
    @RequestMapping(value = "assignRolePermission")
    public JsonResult assignRolePermission(@Valid AssignRolePermissionRequest assignRolePermissionRequest) {
        roleService.assignRolePermission(assignRolePermissionRequest.getRoleId(), assignRolePermissionRequest.getPermissionIds());
        return JsonResult.getSuccessInstance();
    }

    /**
     * 查询角色
     */
    @RequestMapping(value = "readRole")
    public JsonResult readRole(@Valid ReadRoleRequest readRoleRequest) {
        return roleService.readRole(readRoleRequest);
    }

    /**
     * 查询用户角色分配列表
     */
    @RequestMapping(value = "readUserRoleAssign")
    public JsonResult readUserRoleAssign(@Valid ReadUserRoleAssignRequest readUserRoleAssignRequest) {
        return roleService.readUserRoleAssign(readUserRoleAssignRequest);
    }
}
