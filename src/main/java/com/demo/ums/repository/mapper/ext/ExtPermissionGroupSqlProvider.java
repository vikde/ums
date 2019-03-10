package com.demo.ums.repository.mapper.ext;

import org.apache.ibatis.jdbc.SQL;
import org.springframework.util.StringUtils;

import java.util.Map;

/**
 * @author vikde
 * @date 2017/12/8
 */
public class ExtPermissionGroupSqlProvider {
    /**
     * 生成权限组查询sql
     */
    public static String readPermissionGroup(Map<String, Object> params) {
        Integer permissionGroupId = (Integer) params.get("permissionGroupId");
        String permissionGroupName = (String) params.get("permissionGroupName");

        SQL sql = new SQL();
        sql.SELECT("*").FROM("permission_group");
        if (permissionGroupId != null && permissionGroupId > 0) {
            sql.WHERE("permissionGroupId=#{permissionGroupId}");
        }
        if (!StringUtils.isEmpty(permissionGroupName)) {
            sql.WHERE("permissionGroupName LIKE CONCAT('%',#{permissionGroupName},'%')");
        }
        sql.ORDER_BY("updateTime desc");
        return sql.toString();
    }

}
