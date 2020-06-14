package com.demo.ums.service;

import com.demo.ums.common.JsonResult;
import com.demo.ums.common.exception.ApiException;
import com.demo.ums.controller.permission.model.ReadPermissionResponse;
import com.demo.ums.controller.permissiongroup.model.ReadPermissionGroupRequest;
import com.demo.ums.controller.permissiongroup.model.ReadPermissionGroupVO;
import com.demo.ums.repository.mapper.PermissionGroupMapper;
import com.demo.ums.repository.mapper.ext.ExtPermissionGroupMapper;
import com.demo.ums.repository.mapper.ext.ExtPermissionMapper;
import com.demo.ums.repository.model.PermissionGroupDO;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
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
        PermissionGroupDO permissionGroup = new PermissionGroupDO();
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
            throw new ApiException("权限组还在使用");
        }
        permissionGroupMapper.deleteByPrimaryKey(permissionGroupId);
    }

    /**
     * 更新权限组
     */
    public void updatePermissionGroup(int permissionGroupId, String permissionGroupName, String description) {
        PermissionGroupDO permissionGroup = new PermissionGroupDO();
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
        List<PermissionGroupDO> permissionGroupList = extPermissionGroupMapper.readPermissionGroup(readPermissionGroupRequest.getPermissionGroupId(), readPermissionGroupRequest.getPermissionGroupName());

        JsonResult jsonResult = JsonResult.getSuccessInstance();
        jsonResult.setTotal(permissionGroupList instanceof Page ? ((Page<PermissionGroupDO>) permissionGroupList).getTotal() : permissionGroupList.size());
        List<ReadPermissionGroupVO> readPermissionGroupRespons = new ArrayList<>(permissionGroupList.size());
        for (PermissionGroupDO permissionGroup : permissionGroupList) {
            readPermissionGroupRespons.add(new ReadPermissionGroupVO(permissionGroup));
        }
        jsonResult.setData(readPermissionGroupRespons);
        return jsonResult;
    }
}
