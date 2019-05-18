package com.demo.ums.repository.mapper.ext;

import org.apache.ibatis.jdbc.SQL;

import java.util.Map;

/**
 * @author vikde
 * @date 2017/12/8
 */
public class ExtPermissionSqlProvider {
    /**
     * 生成权限查询sql
     */
    public static String readPermission(Map<String, Object> params) {
        Integer permissionId = (Integer) params.get("permissionId");
        Integer permissionGroupId = (Integer) params.get("permissionGroupId");

        SQL sql = new SQL();
        sql.SELECT("p.*,pg.permissionGroupName");
        sql.FROM("permission p").RIGHT_OUTER_JOIN("permission_group pg ON p.permissionGroupId = pg.permissionGroupId");
        if (permissionId != null && permissionId > 0) {
            sql.WHERE("p.permissionId=#{permissionId}");
        }
        if (permissionGroupId != null && permissionGroupId > 0) {
            sql.WHERE("p.permissionGroupId=#{permissionGroupId}");
        }
        sql.ORDER_BY("updateTime desc,p.permissionId desc");
        return sql.toString();
    }

}
