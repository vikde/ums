package com.demo.ums.repository.mapper.ext;

import org.apache.ibatis.jdbc.SQL;

import java.util.Map;

/**
 * @author vikde
 * @date 2017/12/8
 */
public class ExtRolePermissionSqlProvider {
    /**
     * 生成角色权限关联查询sql
     */
    public static String readRolePermission(Map<String, Object> params) {
        Integer roleId = (Integer) params.get("roleId");
        Integer permissionId = (Integer) params.get("permissionId");

        SQL sql = new SQL();
        sql.SELECT("*").FROM("role_permission");
        if (roleId != null && roleId > 0) {
            sql.WHERE("roleId=#{roleId} ");
        }
        if (permissionId != null && permissionId > 0) {
            sql.WHERE("permissionId=#{permissionId} ");
        }
        return sql.toString();
    }

}
