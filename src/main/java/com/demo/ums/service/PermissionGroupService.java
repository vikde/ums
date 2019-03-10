package com.demo.ums.service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.demo.ums.common.JsonResult;
import com.demo.ums.common.exception.ApiException;
import com.demo.ums.common.type.JsonResultType;
import com.demo.ums.controller.permission.model.ReadPermissionResponse;
import com.demo.ums.controller.permissiongroup.model.ReadPermissionGroupRequest;
import com.demo.ums.controller.permissiongroup.model.ReadPermissionGroupResponse;
import com.demo.ums.repository.mapper.PermissionGroupMapper;
import com.demo.ums.repository.mapper.ext.ExtPermissionGroupMapper;
import com.demo.ums.repository.mapper.ext.ExtPermissionMapper;
import com.demo.ums.repository.model.PermissionGroup;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @author vikde
 * @date 2017/12/5
 */
@Service
public class PermissionGroupService {
    @Resource
    private PermissionGroupMapper permissionGroupMapper;
    @Resource
    private ExtPermissionGroupMapper extPermissionGroupMapper;
    @Resource
    private ExtPermissionMapper extPermissionMapper;

    /**
     * 创建权限组
     */
    public void createPermissionGroup(String permissionGroupName, String description) {
        PermissionGroup permissionGroup = new PermissionGroup();
        permissionGroup.setPermissionGroupName(permissionGroupName);
        permissionGroup.setDescription(description);
        permissionGroupMapper.insertSelective(permissionGroup);
    }

    /**
     * 删除权限组
     */
    public void deletePermissionGroup(int permissionGroupId) throws ApiException {
        PageHelper.startPage(1, 1);
        List<ReadPermissionResponse> permissions = extPermissionMapper.readPermission(null, permissionGroupId);
        if (!CollectionUtils.isEmpty(permissions)) {
            throw new ApiException(JsonResultType.PERMISSION_GROUP_USED);
        }
        permissionGroupMapper.deleteByPrimaryKey(permissionGroupId);
    }

    /**
     * 更新权限组
     */
    public void updatePermissionGroup(int permissionGroupId, String permissionGroupName, String description) {
        PermissionGroup permissionGroup = new PermissionGroup();
        permissionGroup.setPermissionGroupId(permissionGroupId);
        permissionGroup.setPermissionGroupName(permissionGroupName);
        permissionGroup.setDescription(description);
        permissionGroupMapper.updateByPrimaryKeySelective(permissionGroup);
    }

    /**
     * 查询权限组
     */
    public JsonResult readPermissionGroup(ReadPermissionGroupRequest readPermissionGroupRequest) {
        if (readPermissionGroupRequest.getPageNumber() != null && readPermissionGroupRequest.getPageSize() != null) {
            PageHelper.startPage(readPermissionGroupRequest.getPageNumber(), readPermissionGroupRequest.getPageSize());
        }
        List<PermissionGroup> permissionGroupList = extPermissionGroupMapper.readPermissionGroup(readPermissionGroupRequest.getPermissionGroupId(), readPermissionGroupRequest.getPermissionGroupName());

        JsonResult jsonResult = JsonResult.getSuccessInstance();
        jsonResult.setTotal(permissionGroupList instanceof Page ? ((Page) permissionGroupList).getTotal() : permissionGroupList.size());
        List<ReadPermissionGroupResponse> readPermissionGroupResponses = new ArrayList<>(permissionGroupList.size());
        for (PermissionGroup permissionGroup : permissionGroupList) {
            readPermissionGroupResponses.add(new ReadPermissionGroupResponse(permissionGroup));
        }
        jsonResult.setData(readPermissionGroupResponses);
        return jsonResult;
    }
}
