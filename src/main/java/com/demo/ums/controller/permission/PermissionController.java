package com.demo.ums.controller.permission;

import com.demo.ums.common.JsonResult;
import com.demo.ums.common.exception.ApiException;
import com.demo.ums.controller.permission.model.*;
import com.demo.ums.service.PermissionService;
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
@RequestMapping("/api/permission/")
public class PermissionController {
    @Resource
    private PermissionService permissionService;

    /**
     * 新建权限
     */
    @PreAuthorize("hasAuthority('createPermission')")
    @RequestMapping(value = "createPermission")
    public JsonResult createPermission(@Valid CreatePermissionRequest createPermissionRequest) {
        permissionService.createPermission(createPermissionRequest.getPermissionName(), createPermissionRequest.getPermissionGroupId(),
                                           createPermissionRequest.getPath(), createPermissionRequest.getDescription());
        return JsonResult.getSuccessInstance("权限创建成功");
    }

    /**
     * 删除权限
     */
    @PreAuthorize("hasAuthority('deletePermission')")
    @RequestMapping(value = "deletePermission")
    public JsonResult deletePermission(@Valid DeletePermissionRequest deletePermissionRequest) throws ApiException {
        permissionService.deletePermission(deletePermissionRequest.getPermissionId());
        return JsonResult.getSuccessInstance("权限删除成功");
    }

    /**
     * 更新权限
     */
    @PreAuthorize("hasAuthority('updatePermission')")
    @RequestMapping(value = "updatePermission")
    public JsonResult updatePermission(@Valid UpdatePermissionRequest updatePermissionRequest) {
        permissionService.updatePermission(updatePermissionRequest);
        return JsonResult.getSuccessInstance("权限更新成功");
    }

    /**
     * 查询权限组
     */
    @PreAuthorize("hasAuthority('readPermission')")
    @RequestMapping(value = "readPermission")
    public JsonResult readPermission(@Valid ReadPermissionRequest readPermissionRequest) {
        return permissionService.readPermission(readPermissionRequest);
    }

    /**
     * 查询权限组
     */
    @PreAuthorize("hasAuthority('readPermissionByGroup')")
    @RequestMapping(value = "readPermissionByGroup")
    public JsonResult readPermissionByGroup(@Valid ReadPermissionByGroupRequest readPermissionByGroupRequest) {
        return permissionService.readPermissionByGroup(readPermissionByGroupRequest);
    }
}
