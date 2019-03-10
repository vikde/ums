package com.demo.ums.controller.permissiongroup;

import com.demo.ums.common.JsonResult;
import com.demo.ums.common.exception.ApiException;
import com.demo.ums.controller.ApiController;
import com.demo.ums.controller.permissiongroup.model.CreatePermissionGroupRequest;
import com.demo.ums.controller.permissiongroup.model.DeletePermissionGroupRequest;
import com.demo.ums.controller.permissiongroup.model.ReadPermissionGroupRequest;
import com.demo.ums.controller.permissiongroup.model.UpdatePermissionGroupRequest;
import com.demo.ums.service.PermissionGroupService;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.validation.Valid;

/**
 * @author vikde
 * @date 2017/12/5
 */
@ApiController
@RequestMapping("/api/permissionGroup/")
public class PermissionGroupController {
    @Resource
    private PermissionGroupService permissionGroupService;

    /**
     * 创建权限组
     */
    @RequestMapping(value = "createPermissionGroup")
    public JsonResult createPermissionGroup(@Valid CreatePermissionGroupRequest createPermissionGroupRequest) {
        permissionGroupService.createPermissionGroup(createPermissionGroupRequest.getPermissionGroupName(), createPermissionGroupRequest.getDescription());
        return JsonResult.getSuccessInstance("创建权限组成功");
    }

    /**
     * 删除权限组
     */
    @RequestMapping(value = "deletePermissionGroup")
    public JsonResult deletePermissionGroup(@Valid DeletePermissionGroupRequest deletePermissionGroupRequest) throws ApiException {
        permissionGroupService.deletePermissionGroup(deletePermissionGroupRequest.getPermissionGroupId());
        return JsonResult.getSuccessInstance("删除权限组成功");
    }

    /**
     * 更新权限组
     */
    @RequestMapping(value = "updatePermissionGroup")
    public JsonResult updatePermissionGroup(@Valid UpdatePermissionGroupRequest updatePermissionGroupRequest) {
        permissionGroupService.updatePermissionGroup(updatePermissionGroupRequest.getPermissionGroupId(), updatePermissionGroupRequest.getPermissionGroupName(),
                                                     updatePermissionGroupRequest.getDescription());
        return JsonResult.getSuccessInstance("更新权限组成功");
    }

    /**
     * 查询权限组
     */
    @RequestMapping(value = "readPermissionGroup")
    public JsonResult readPermissionGroup(@Valid ReadPermissionGroupRequest readPermissionGroupRequest) {
        return permissionGroupService.readPermissionGroup(readPermissionGroupRequest);
    }
}
