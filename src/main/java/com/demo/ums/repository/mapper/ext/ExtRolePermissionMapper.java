package com.demo.ums.repository.mapper.ext;

import com.demo.ums.repository.model.RolePermission;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.SelectProvider;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author vikde
 * @date 2019/03/10
 */
@Mapper
@Repository
public interface ExtRolePermissionMapper {

    /**
     * 查询角色
     *
     * @param roleId 角色id
     * @param permissionId 权限id
     * @return 角色列表
     */
    @SelectProvider(type = ExtRolePermissionSqlProvider.class, method = "readRolePermission")
    List<RolePermission> readRolePermission(@Param("roleId") Integer roleId, @Param("permissionId") Integer permissionId);

    /**
     * 通过roleId删除全部的权限配置
     *
     * @param roleId 角色id
     * @return 删除数量
     */
    @Delete({
            "delete from role_permission",
            "where roleId = #{roleId,jdbcType=INTEGER}"
    })
    int deleteByRoleId(@Param("roleId") Integer roleId);
}