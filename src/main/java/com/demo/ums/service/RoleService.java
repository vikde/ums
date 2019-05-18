package com.demo.ums.service;

import com.demo.ums.common.JsonResult;
import com.demo.ums.common.exception.ApiException;
import com.demo.ums.controller.permission.model.ReadPermissionResponse;
import com.demo.ums.controller.role.model.*;
import com.demo.ums.repository.mapper.RoleMapper;
import com.demo.ums.repository.mapper.ext.ExtPermissionMapper;
import com.demo.ums.repository.mapper.ext.ExtRoleMapper;
import com.demo.ums.repository.mapper.ext.ExtRolePermissionMapper;
import com.demo.ums.repository.model.RolePO;
import com.demo.ums.repository.model.RolePermissionPO;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.google.common.base.Splitter;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author vikde
 * @date 2017/12/5
 */
@Service
public class RoleService {
    @Resource
    private ExtRoleMapper extRoleMapper;
    @Resource
    private RoleMapper roleMapper;
    @Resource
    private ExtRolePermissionMapper extRolePermissionMapper;
    @Resource
    private ExtPermissionMapper extPermissionMapper;

    /**
     * 创建角色
     */
    public void createRole(String roleName, String description) {
        extRoleMapper.createRole(roleName, description);
    }

    /**
     * 删除角色
     */
    public void deleteRole(int roleId) throws ApiException {
        PageHelper.startPage(1, 1);
        List<RolePermissionPO> rolePermissions = extRolePermissionMapper.readRolePermission(roleId, null);
        if (!CollectionUtils.isEmpty(rolePermissions)) {
            throw new ApiException("角色还在使用");
        }
        roleMapper.deleteByPrimaryKey(roleId);
    }

    /**
     * 更新角色
     */
    public void updateRole(UpdateRoleRequest updateRoleRequest) {
        RolePO role = new RolePO();
        role.setRoleId(updateRoleRequest.getRoleId());
        role.setRoleName(updateRoleRequest.getRoleName());
        role.setDescription(updateRoleRequest.getDescription());
        roleMapper.updateByPrimaryKeySelective(role);
    }

    /**
     * 分配角色权限
     */
    @Transactional(rollbackFor = Exception.class)
    public void assignRolePermission(int roleId, String permissionIds) {
        extRolePermissionMapper.deleteByRoleId(roleId);
        Splitter.on(",").omitEmptyStrings().trimResults()
                .split(permissionIds)
                .forEach(permissionId -> {
                    extRoleMapper.assignRolePermission(roleId, Integer.parseInt(permissionId));
                });
    }

    public void readAllRolePermission() {
        List<ReadPermissionResponse> readPermissionResponses = extPermissionMapper.readPermission(null, null);
        Map<Integer, List<ReadPermissionResponse>> map = new HashMap<>();
        for (ReadPermissionResponse readPermissionResponse : readPermissionResponses) {
            List<ReadPermissionResponse> list = map.computeIfAbsent(readPermissionResponse.getPermissionGroupId(), k -> new ArrayList<>());
            list.add(readPermissionResponse);
        }
    }

    /**
     * 查询角色
     */
    public JsonResult readRole(ReadRoleRequest readRoleRequest) {
        if (readRoleRequest.getPageNumber() != null && readRoleRequest.getPageSize() != null) {
            PageHelper.startPage(readRoleRequest.getPageNumber(), readRoleRequest.getPageSize());
        }
        List<RolePO> roleList = extRoleMapper.readRole(readRoleRequest.getRoleId(), readRoleRequest.getRoleName());

        JsonResult jsonResult = JsonResult.getSuccessInstance();
        jsonResult.setTotal(roleList instanceof Page ? ((Page) roleList).getTotal() : roleList.size());
        List<ReadRoleVO> readRoleRespons = new ArrayList<>(roleList.size());
        for (RolePO role : roleList) {
            readRoleRespons.add(new ReadRoleVO(role));
        }
        jsonResult.setData(readRoleRespons);
        return jsonResult;
    }

    /**
     * 查询用户角色分配列表
     */
    public JsonResult readUserRoleAssign(ReadUserRoleAssignRequest readUserRoleAssignRequest) {
        List<ReadUserRoleAssignResponse> list = extRoleMapper.readUserRoleAssign(readUserRoleAssignRequest.getUserId());
        JsonResult jsonResult = JsonResult.getSuccessInstance();
        jsonResult.setData(list);
        return jsonResult;
    }
}
