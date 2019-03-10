package com.demo.ums.repository.mapper;

import com.demo.ums.repository.model.Role;
import org.apache.ibatis.jdbc.SQL;

/**
 * @author vikde
 * @date 2019/03/10
 */
public class RoleSqlProvider {

    public String insertSelective(Role record) {
        SQL sql = new SQL();
        sql.INSERT_INTO("role");

        if (record.getRoleName() != null) {
            sql.VALUES("roleName", "#{roleName,jdbcType=VARCHAR}");
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

    public String updateByPrimaryKeySelective(Role record) {
        SQL sql = new SQL();
        sql.UPDATE("role");

        if (record.getRoleName() != null) {
            sql.SET("roleName = #{roleName,jdbcType=VARCHAR}");
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

        sql.WHERE("roleId = #{roleId,jdbcType=INTEGER}");

        return sql.toString();
    }
}