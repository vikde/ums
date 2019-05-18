package com.demo.ums.repository.mapper;

import com.demo.ums.repository.model.PermissionGroupPO;
import org.apache.ibatis.jdbc.SQL;

public class PermissionGroupSqlProvider {

    public String insertSelective(PermissionGroupPO record) {
        SQL sql = new SQL();
        sql.INSERT_INTO("permission_group");
        
        if (record.getPermissionGroupName() != null) {
            sql.VALUES("permissionGroupName", "#{permissionGroupName,jdbcType=VARCHAR}");
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

    public String updateByPrimaryKeySelective(PermissionGroupPO record) {
        SQL sql = new SQL();
        sql.UPDATE("permission_group");
        
        if (record.getPermissionGroupName() != null) {
            sql.SET("permissionGroupName = #{permissionGroupName,jdbcType=VARCHAR}");
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
        
        sql.WHERE("permissionGroupId = #{permissionGroupId,jdbcType=INTEGER}");
        
        return sql.toString();
    }
}