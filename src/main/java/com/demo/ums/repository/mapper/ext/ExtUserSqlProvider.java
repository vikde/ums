package com.demo.ums.repository.mapper.ext;

import org.apache.ibatis.jdbc.SQL;

import java.util.Map;

/**
 * @author vikde
 * @date 2017/12/8
 */
public class ExtUserSqlProvider {
    /**
     * 生成用户查询sql
     */
    public static String readUser(Map<String, Object> params) {
        Integer userId = (Integer) params.get("userId");
        String username = (String) params.get("username");
        String name = (String) params.get("name");

        SQL sql = new SQL();
        sql.SELECT("*").FROM("user");
        if (userId != null && userId > 0) {
            sql.WHERE("userId=#{userId}");
        }
        if (username != null && !username.isEmpty()) {
            sql.WHERE("username LIKE CONCAT('%',#{username},'%')");
        }
        if (name != null && !name.isEmpty()) {
            sql.WHERE("name LIKE CONCAT('%',#{name},'%')");
        }
        sql.ORDER_BY("updateTime desc");
        return sql.toString();
    }

}
