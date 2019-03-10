package com.demo.ums.controller.permission;

import com.demo.ums.common.JsonResult;
import com.demo.ums.common.exception.ApiException;
import com.demo.ums.controller.ApiController;
import com.demo.ums.controller.permission.model.*;
import com.demo.ums.service.PermissionService;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.validation.Valid;

/**
 * @author vikde
 * @date 2017/12/5
 */
@ApiController
@RequestMapping("/api/permission/")
public class PermissionController {
    @Resource
    private PermissionService permissionService;

    /**
     * 新建权限
     */
    @RequestMapping(value = "createPermission")
    public JsonResult createPermission(@Valid CreatePermissionRequest createPermissionRequest) {
        permissionService.createPermission(createPermissionRequest.getPermissionName(), createPermissionRequest.getPermissionGroupId(),
                                           createPermissionRequest.getPath(), createPermissionRequest.getDescription());
        return JsonResult.getSuccessInstance("新建权限成功");
    }

    /**
     * 删除权限
     */
    @RequestMapping(value = "deletePermission")
    public JsonResult deletePermission(@Valid DeletePermissionRequest deletePermissionRequest) throws ApiException {
        permissionService.deletePermission(deletePermissionRequest.getPermissionId());
        return JsonResult.getSuccessInstance("删除权限成功");
    }

    /**
     * 更新权限
     */
    @RequestMapping(value = "updatePermission")
    public JsonResult updatePermission(@Valid UpdatePermissionRequest updatePermissionRequest) {
        permissionService.updatePermission(updatePermissionRequest);
        return JsonResult.getSuccessInstance("更新权限组成功");
    }

    /**
     * 查询权限组
     */
    @RequestMapping(value = "readPermission")
    public JsonResult readPermission(@Valid ReadPermissionRequest readPermissionRequest) {
        return permissionService.readPermission(readPermissionRequest);
    }

    /**
     * 查询权限组
     */
    @RequestMapping(value = "readPermissionByGroup")
    public JsonResult readPermissionByGroup(@Valid ReadPermissionByGroupRequest readPermissionByGroupRequest) {
        return permissionService.readPermissionByGroup(readPermissionByGroupRequest);
    }
}
