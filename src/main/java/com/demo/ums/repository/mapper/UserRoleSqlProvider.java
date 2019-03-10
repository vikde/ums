package com.demo.ums.repository.mapper;

import com.demo.ums.repository.model.UserRole;
import org.apache.ibatis.jdbc.SQL;

/**
 * @author vikde
 * @date 2019/03/10
 */
public class UserRoleSqlProvider {

    public String insertSelective(UserRole record) {
        SQL sql = new SQL();
        sql.INSERT_INTO("user_role");

        if (record.getUserId() != null) {
            sql.VALUES("userId", "#{userId,jdbcType=INTEGER}");
        }

        if (record.getRoleId() != null) {
            sql.VALUES("roleId", "#{roleId,jdbcType=INTEGER}");
        }

        if (record.getCreateTime() != null) {
            sql.VALUES("createTime", "#{createTime,jdbcType=TIMESTAMP}");
        }

        if (record.getUpdateTime() != null) {
            sql.VALUES("updateTime", "#{updateTime,jdbcType=TIMESTAMP}");
        }

        return sql.toString();
    }

    public String updateByPrimaryKeySelective(UserRole record) {
        SQL sql = new SQL();
        sql.UPDATE("user_role");

        if (record.getCreateTime() != null) {
            sql.SET("createTime = #{createTime,jdbcType=TIMESTAMP}");
        }

        if (record.getUpdateTime() != null) {
            sql.SET("updateTime = #{updateTime,jdbcType=TIMESTAMP}");
        }

        sql.WHERE("userId = #{userId,jdbcType=INTEGER}");
        sql.WHERE("roleId = #{roleId,jdbcType=INTEGER}");

        return sql.toString();
    }
}