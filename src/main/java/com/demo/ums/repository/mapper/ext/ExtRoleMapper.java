package com.demo.ums.repository.mapper.ext;

import com.demo.ums.controller.role.model.ReadUserRoleAssignResponse;
import com.demo.ums.repository.model.RoleDO;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author vikde
 * @date 2019/03/10
 */
@Mapper
@Repository
public interface ExtRoleMapper {
    /**
     * 创建角色
     *
     * @param roleName    角色名称
     * @param description 角色描述
     */
    @Insert("INSERT INTO role (roleName,description) VALUES (#{roleName},#{description})")
    void createRole(@Param("roleName") String roleName, @Param("description") String description);

    /**
     * 分配角色权限
     *
     * @param roleId       角色id
     * @param permissionId 权限id
     */
    @Insert("REPLACE INTO role_permission (roleId,permissionId) VALUES (#{roleId},#{permissionId})")
    void assignRolePermission(@Param("roleId") int roleId, @Param("permissionId") int permissionId);

    /**
     * 查询角色
     *
     * @param roleId   角色id
     * @param roleName 角色名
     * @return 角色列表
     */
    @SelectProvider(type = ExtRoleSqlProvider.class, method = "readRole")
    List<RoleDO> readRole(@Param("roleId") Integer roleId, @Param("roleName") String roleName);

    /**
     * 查询用户角色分配列表
     *
     * @param userId 用户id
     * @return 所有角色列表
     */
    @Select({"select r.roleId roleId, roleName, !isnull(userId) isAssign ",
            "from role r left join ",
            "(select roleId, userId from user_role where userId = #{userId}) ur ",
            "on r.roleId = ur.roleId"})
    List<ReadUserRoleAssignResponse> readUserRoleAssign(@Param("userId") Integer userId);
}