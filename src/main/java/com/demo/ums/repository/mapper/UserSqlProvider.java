package com.demo.ums.repository.mapper;

import com.demo.ums.repository.model.User;
import org.apache.ibatis.jdbc.SQL;

/**
 * @author vikde
 * @date 2019/03/10
 */
public class UserSqlProvider {

    public String insertSelective(User record) {
        SQL sql = new SQL();
        sql.INSERT_INTO("user");

        if (record.getUsername() != null) {
            sql.VALUES("username", "#{username,jdbcType=VARCHAR}");
        }

        if (record.getName() != null) {
            sql.VALUES("name", "#{name,jdbcType=VARCHAR}");
        }

        if (record.getPassword() != null) {
            sql.VALUES("password", "#{password,jdbcType=VARCHAR}");
        }

        if (record.getUserStatusType() != null) {
            sql.VALUES("userStatusType", "#{userStatusType,jdbcType=INTEGER}");
        }

        if (record.getLoginTime() != null) {
            sql.VALUES("loginTime", "#{loginTime,jdbcType=TIMESTAMP}");
        }

        if (record.getPreLoginTime() != null) {
            sql.VALUES("preLoginTime", "#{preLoginTime,jdbcType=TIMESTAMP}");
        }

        if (record.getLoginCount() != null) {
            sql.VALUES("loginCount", "#{loginCount,jdbcType=INTEGER}");
        }

        if (record.getCreateTime() != null) {
            sql.VALUES("createTime", "#{createTime,jdbcType=TIMESTAMP}");
        }

        if (record.getUpdateTime() != null) {
            sql.VALUES("updateTime", "#{updateTime,jdbcType=TIMESTAMP}");
        }

        return sql.toString();
    }

    public String updateByPrimaryKeySelective(User record) {
        SQL sql = new SQL();
        sql.UPDATE("user");

        if (record.getUsername() != null) {
            sql.SET("username = #{username,jdbcType=VARCHAR}");
        }

        if (record.getName() != null) {
            sql.SET("name = #{name,jdbcType=VARCHAR}");
        }

        if (record.getPassword() != null) {
            sql.SET("password = #{password,jdbcType=VARCHAR}");
        }

        if (record.getUserStatusType() != null) {
            sql.SET("userStatusType = #{userStatusType,jdbcType=INTEGER}");
        }

        if (record.getLoginTime() != null) {
            sql.SET("loginTime = #{loginTime,jdbcType=TIMESTAMP}");
        }

        if (record.getPreLoginTime() != null) {
            sql.SET("preLoginTime = #{preLoginTime,jdbcType=TIMESTAMP}");
        }

        if (record.getLoginCount() != null) {
            sql.SET("loginCount = #{loginCount,jdbcType=INTEGER}");
        }

        if (record.getCreateTime() != null) {
            sql.SET("createTime = #{createTime,jdbcType=TIMESTAMP}");
        }

        if (record.getUpdateTime() != null) {
            sql.SET("updateTime = #{updateTime,jdbcType=TIMESTAMP}");
        }

        sql.WHERE("userId = #{userId,jdbcType=INTEGER}");

        return sql.toString();
    }
}