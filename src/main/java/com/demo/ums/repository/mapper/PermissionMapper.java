package com.demo.ums.repository.mapper;

import com.demo.ums.repository.model.PermissionPO;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.annotations.UpdateProvider;
import org.apache.ibatis.type.JdbcType;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface PermissionMapper {
    @Delete({
        "delete from permission",
        "where permissionId = #{permissionId,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer permissionId);

    @Insert({
        "insert into permission (permissionName, permissionGroupId, ",
        "path, description, ",
        "createTime, updateTime)",
        "values (#{permissionName,jdbcType=VARCHAR}, #{permissionGroupId,jdbcType=INTEGER}, ",
        "#{path,jdbcType=VARCHAR}, #{description,jdbcType=VARCHAR}, ",
        "#{createTime,jdbcType=TIMESTAMP}, #{updateTime,jdbcType=TIMESTAMP})"
    })
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="permissionId", before=false, resultType=Integer.class)
    int insert(PermissionPO record);

    @InsertProvider(type=PermissionSqlProvider.class, method="insertSelective")
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="permissionId", before=false, resultType=Integer.class)
    int insertSelective(PermissionPO record);

    @Select({
        "select",
        "permissionId, permissionName, permissionGroupId, path, description, createTime, ",
        "updateTime",
        "from permission",
        "where permissionId = #{permissionId,jdbcType=INTEGER}"
    })
    @Results({
        @Result(column="permissionId", property="permissionId", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="permissionName", property="permissionName", jdbcType=JdbcType.VARCHAR),
        @Result(column="permissionGroupId", property="permissionGroupId", jdbcType=JdbcType.INTEGER),
        @Result(column="path", property="path", jdbcType=JdbcType.VARCHAR),
        @Result(column="description", property="description", jdbcType=JdbcType.VARCHAR),
        @Result(column="createTime", property="createTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="updateTime", property="updateTime", jdbcType=JdbcType.TIMESTAMP)
    })
    PermissionPO selectByPrimaryKey(Integer permissionId);

    @UpdateProvider(type=PermissionSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(PermissionPO record);

    @Update({
        "update permission",
        "set permissionName = #{permissionName,jdbcType=VARCHAR},",
          "permissionGroupId = #{permissionGroupId,jdbcType=INTEGER},",
          "path = #{path,jdbcType=VARCHAR},",
          "description = #{description,jdbcType=VARCHAR},",
          "createTime = #{createTime,jdbcType=TIMESTAMP},",
          "updateTime = #{updateTime,jdbcType=TIMESTAMP}",
        "where permissionId = #{permissionId,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(PermissionPO record);
}