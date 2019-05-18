package com.demo.ums.repository.mapper.ext;

import com.demo.ums.repository.model.PermissionGroupPO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.SelectProvider;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author vikde
 * @date 2017/12/05
 */
@Mapper
@Repository
public interface ExtPermissionGroupMapper {
    /**
     * 查询权限组
     *
     * @param permissionGroupId   权限组id
     * @param permissionGroupName 权限组名
     * @return 权限组列表
     */
    @SelectProvider(type = ExtPermissionGroupSqlProvider.class, method = "readPermissionGroup")
    List<PermissionGroupPO> readPermissionGroup(@Param("permissionGroupId") Integer permissionGroupId, @Param("permissionGroupName") String permissionGroupName);
}