package com.demo.ums.repository.mapper;

import com.demo.ums.repository.model.Role;
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

/**
 * @author vikde
 * @date 2019/03/10
 */
@Mapper
@Repository
public interface RoleMapper {
    @Delete({
            "delete from role",
            "where roleId = #{roleId,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer roleId);

    @Insert({
            "insert into role (roleName, description, ",
            "createTime, updateTime)",
            "values (#{roleName,jdbcType=VARCHAR}, #{description,jdbcType=VARCHAR}, ",
            "#{createTime,jdbcType=TIMESTAMP}, #{updateTime,jdbcType=TIMESTAMP})"
    })
    @SelectKey(statement = "SELECT LAST_INSERT_ID()", keyProperty = "roleId", before = false, resultType = Integer.class)
    int insert(Role record);

    @InsertProvider(type = RoleSqlProvider.class, method = "insertSelective")
    @SelectKey(statement = "SELECT LAST_INSERT_ID()", keyProperty = "roleId", before = false, resultType = Integer.class)
    int insertSelective(Role record);

    @Select({
            "select",
            "roleId, roleName, description, createTime, updateTime",
            "from role",
            "where roleId = #{roleId,jdbcType=INTEGER}"
    })
    @Results({
            @Result(column = "roleId", property = "roleId", jdbcType = JdbcType.INTEGER, id = true),
            @Result(column = "roleName", property = "roleName", jdbcType = JdbcType.VARCHAR),
            @Result(column = "description", property = "description", jdbcType = JdbcType.VARCHAR),
            @Result(column = "createTime", property = "createTime", jdbcType = JdbcType.TIMESTAMP),
            @Result(column = "updateTime", property = "updateTime", jdbcType = JdbcType.TIMESTAMP)
    })
    Role selectByPrimaryKey(Integer roleId);

    @UpdateProvider(type = RoleSqlProvider.class, method = "updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(Role record);

    @Update({
            "update role",
            "set roleName = #{roleName,jdbcType=VARCHAR},",
            "description = #{description,jdbcType=VARCHAR},",
            "createTime = #{createTime,jdbcType=TIMESTAMP},",
            "updateTime = #{updateTime,jdbcType=TIMESTAMP}",
            "where roleId = #{roleId,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(Role record);
}