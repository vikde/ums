package com.demo.ums.repository.mapper.ext;

import com.demo.ums.controller.permission.model.ReadPermissionResponse;
import org.apache.ibatis.annotations.Insert;
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
public interface ExtPermissionMapper {
    /**
     * 新建权限
     *
     * @param permissionName    权限名称
     * @param permissionGroupId 权限组id
     * @param path              权限地址
     * @param description       权限描述
     */
    @Insert("INSERT INTO permission (permissionName,permissionGroupId,path,description) VALUES (#{permissionName},#{permissionGroupId},#{path},#{description})")
    void createPermission(@Param("permissionName") String permissionName, @Param("permissionGroupId") int permissionGroupId,
                          @Param("path") String path, @Param("description") String description);

    /**
     * 查询权限
     *
     * @param permissionId      权限id
     * @param permissionGroupId 权限组id
     * @return 权限组列表
     */
    @SelectProvider(type = ExtPermissionSqlProvider.class, method = "readPermission")
    List<ReadPermissionResponse> readPermission(@Param("permissionId") Integer permissionId, @Param("permissionGroupId") Integer permissionGroupId);
}