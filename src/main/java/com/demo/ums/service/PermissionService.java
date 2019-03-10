package com.demo.ums.service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.demo.ums.common.JsonResult;
import com.demo.ums.common.exception.ApiException;
import com.demo.ums.common.type.JsonResultType;
import com.demo.ums.controller.permission.model.*;
import com.demo.ums.repository.mapper.PermissionMapper;
import com.demo.ums.repository.mapper.ext.ExtPermissionMapper;
import com.demo.ums.repository.mapper.ext.ExtRolePermissionMapper;
import com.demo.ums.repository.model.Permission;
import com.demo.ums.repository.model.RolePermission;
import com.demo.ums.repository.model.RolePermissionKey;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author vikde
 * @date 2017/12/5
 */
@Service
public class PermissionService {
    @Resource
    private ExtPermissionMapper extPermissionMapper;
    @Resource
    private PermissionMapper permissionMapper;
    @Resource
    private ExtRolePermissionMapper extRolePermissionMapper;

    /**
     * 新建权限
     */
    public void createPermission(String permissionName, int permissionGroupId, String path, String description) {
        extPermissionMapper.createPermission(permissionName, permissionGroupId, path, description);
    }

    /**
     * 删除权限
     */
    public void deletePermission(int permissionId) throws ApiException {
        PageHelper.startPage(1, 1);
        List<RolePermission> rolePermissions = extRolePermissionMapper.readRolePermission(null, permissionId);
        if (!CollectionUtils.isEmpty(rolePermissions)) {
            throw new ApiException(JsonResultType.PERMISSION_USED);
        }
        permissionMapper.deleteByPrimaryKey(permissionId);
    }

    /**
     * 更新权限
     */
    public void updatePermission(UpdatePermissionRequest updatePermissionRequest) {
        Permission permission = new Permission();
        permission.setPermissionId(updatePermissionRequest.getPermissionId());
        permission.setPermissionGroupId(updatePermissionRequest.getPermissionGroupId());
        permission.setPermissionName(updatePermissionRequest.getPermissionName());
        permission.setPath(updatePermissionRequest.getPath());
        permission.setDescription(updatePermissionRequest.getDescription());
        permissionMapper.updateByPrimaryKeySelective(permission);
    }

    /**
     * 查询权限
     */
    public JsonResult readPermission(ReadPermissionRequest readPermissionRequest) {
        if (readPermissionRequest.getPageNumber() != null && readPermissionRequest.getPageSize() != null) {
            PageHelper.startPage(readPermissionRequest.getPageNumber(), readPermissionRequest.getPageSize());
        }
        List<ReadPermissionResponse> permissionList = extPermissionMapper.readPermission(readPermissionRequest.getPermissionId(), readPermissionRequest.getPermissionGroupId());

        JsonResult jsonResult = JsonResult.getSuccessInstance();
        jsonResult.setTotal(permissionList instanceof Page ? ((Page) permissionList).getTotal() : permissionList.size());
        jsonResult.setData(permissionList);
        return jsonResult;
    }

    /**
     * 查询权限
     */
    public JsonResult readPermissionByGroup(ReadPermissionByGroupRequest readPermissionByGroupRequest) {
        int roleId = readPermissionByGroupRequest.getRoleId();
        //全部权限
        List<ReadPermissionResponse> permissionList = extPermissionMapper.readPermission(null, null);
        //角色权限列表
        List<RolePermission> rolePermissionList = extRolePermissionMapper.readRolePermission(roleId, null);
        //已经存在的角色
        Set<Integer> permissionSet = rolePermissionList.stream().map(RolePermissionKey::getPermissionId).collect(Collectors.toSet());

        Map<Integer, ReadPermissionByGroupResponse> resultMap = new HashMap<>();
        for (ReadPermissionResponse permission : permissionList) {
            ReadPermissionByGroupResponse readPermissionByGroupResponse = resultMap.get(permission.getPermissionGroupId());
            if (readPermissionByGroupResponse == null) {
                readPermissionByGroupResponse = new ReadPermissionByGroupResponse();
                readPermissionByGroupResponse.setPermissionGroupId(permission.getPermissionGroupId());
                readPermissionByGroupResponse.setPermissionGroupName(permission.getPermissionGroupName());
                resultMap.put(permission.getPermissionGroupId(), readPermissionByGroupResponse);
            }
            ReadPermissionByGroupResponse.Permission innerPermission = new ReadPermissionByGroupResponse.Permission();
            innerPermission.setPermissionId(permission.getPermissionId());
            innerPermission.setPermissionName(permission.getPermissionName());
            innerPermission.setPath(permission.getPath());
            innerPermission.setIsAssign(permissionSet.contains(permission.getPermissionId()));
            readPermissionByGroupResponse.getPermissionList().add(innerPermission);
        }

        JsonResult jsonResult = JsonResult.getSuccessInstance();
        jsonResult.setTotal(0L);
        jsonResult.setData(resultMap.values());
        return jsonResult;
    }

}
