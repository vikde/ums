package com.demo.ums.repository.mapper;

import com.demo.ums.repository.model.Permission;
import org.apache.ibatis.jdbc.SQL;

/**
 * @author vikde
 * @date 2019/03/10
 */
public class PermissionSqlProvider {

    public String insertSelective(Permission record) {
        SQL sql = new SQL();
        sql.INSERT_INTO("permission");

        if (record.getPermissionName() != null) {
            sql.VALUES("permissionName", "#{permissionName,jdbcType=VARCHAR}");
        }

        if (record.getPermissionGroupId() != null) {
            sql.VALUES("permissionGroupId", "#{permissionGroupId,jdbcType=INTEGER}");
        }

        if (record.getPath() != null) {
            sql.VALUES("path", "#{path,jdbcType=VARCHAR}");
        }

        if (record.getDescription() != null) {
            sql.VALUES("description", "#{description,jdbcType=VARCHAR}");
        }

        if (record.getCreateTime() != null) {
            sql.VALUES("createTime", "#{createTime,jdbcType=TIMESTAMP}");
        }

        if (record.getUpdateTime() != null) {
            sql.VALUES("updateTime", "#{updateTime,jdbcType=TIMESTAMP}");
        }

        return sql.toString();
    }

    public String updateByPrimaryKeySelective(Permission record) {
        SQL sql = new SQL();
        sql.UPDATE("permission");

        if (record.getPermissionName() != null) {
            sql.SET("permissionName = #{permissionName,jdbcType=VARCHAR}");
        }

        if (record.getPermissionGroupId() != null) {
            sql.SET("permissionGroupId = #{permissionGroupId,jdbcType=INTEGER}");
        }

        if (record.getPath() != null) {
            sql.SET("path = #{path,jdbcType=VARCHAR}");
        }

        if (record.getDescription() != null) {
            sql.SET("description = #{description,jdbcType=VARCHAR}");
        }

        if (record.getCreateTime() != null) {
            sql.SET("createTime = #{createTime,jdbcType=TIMESTAMP}");
        }

        if (record.getUpdateTime() != null) {
            sql.SET("updateTime = #{updateTime,jdbcType=TIMESTAMP}");
        }

        sql.WHERE("permissionId = #{permissionId,jdbcType=INTEGER}");

        return sql.toString();
    }
}