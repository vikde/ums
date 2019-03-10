package com.demo.ums.repository.mapper;

import com.demo.ums.repository.model.RolePermission;
import org.apache.ibatis.jdbc.SQL;

/**
 * @author vikde
 * @date 2019/03/10
 */
public class RolePermissionSqlProvider {

    public String insertSelective(RolePermission record) {
        SQL sql = new SQL();
        sql.INSERT_INTO("role_permission");

        if (record.getRoleId() != null) {
            sql.VALUES("roleId", "#{roleId,jdbcType=INTEGER}");
        }

        if (record.getPermissionId() != null) {
            sql.VALUES("permissionId", "#{permissionId,jdbcType=INTEGER}");
        }

        if (record.getCreateTime() != null) {
            sql.VALUES("createTime", "#{createTime,jdbcType=TIMESTAMP}");
        }

        if (record.getUpdateTime() != null) {
            sql.VALUES("updateTime", "#{updateTime,jdbcType=TIMESTAMP}");
        }

        return sql.toString();
    }

    public String updateByPrimaryKeySelective(RolePermission record) {
        SQL sql = new SQL();
        sql.UPDATE("role_permission");

        if (record.getCreateTime() != null) {
            sql.SET("createTime = #{createTime,jdbcType=TIMESTAMP}");
        }

        if (record.getUpdateTime() != null) {
            sql.SET("updateTime = #{updateTime,jdbcType=TIMESTAMP}");
        }

        sql.WHERE("roleId = #{roleId,jdbcType=INTEGER}");
        sql.WHERE("permissionId = #{permissionId,jdbcType=INTEGER}");

        return sql.toString();
    }
}