package com.demo.ums.repository.mapper.ext;

import org.apache.ibatis.jdbc.SQL;

import java.util.Map;

/**
 * @author vikde
 * @date 2017/12/8
 */
public class ExtRoleSqlProvider {
    public static String readRole(Map<String, Object> params) {
        Integer roleId = (Integer) params.get("roleId");
        String roleName = (String) params.get("roleName");

        SQL sql = new SQL();
        sql.SELECT("*").FROM("role");
        if (roleId != null && roleId > 0) {
            sql.WHERE("roleId=#{roleId} ");
        }
        if (roleName != null && !roleName.isEmpty()) {
            sql.WHERE("roleName LIKE CONCAT('%',#{roleName},'%') ");
        }
        sql.ORDER_BY("updateTime desc");
        return sql.toString();
    }

}
