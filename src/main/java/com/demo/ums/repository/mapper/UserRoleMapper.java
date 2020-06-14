package com.demo.ums.repository.mapper;

import com.demo.ums.repository.model.UserRoleDO;
import com.demo.ums.repository.model.UserRoleDOKey;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.annotations.UpdateProvider;
import org.apache.ibatis.type.JdbcType;

@Mapper
public interface UserRoleMapper {
    @Delete({
        "delete from user_role",
        "where userId = #{userId,jdbcType=INTEGER}",
          "and roleId = #{roleId,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(UserRoleDOKey key);

    @Insert({
        "insert into user_role (userId, roleId, ",
        "createTime, updateTime)",
        "values (#{userId,jdbcType=INTEGER}, #{roleId,jdbcType=INTEGER}, ",
        "#{createTime,jdbcType=TIMESTAMP}, #{updateTime,jdbcType=TIMESTAMP})"
    })
    int insert(UserRoleDO record);

    @InsertProvider(type=UserRoleSqlProvider.class, method="insertSelective")
    int insertSelective(UserRoleDO record);

    @Select({
        "select",
        "userId, roleId, createTime, updateTime",
        "from user_role",
        "where userId = #{userId,jdbcType=INTEGER}",
          "and roleId = #{roleId,jdbcType=INTEGER}"
    })
    @Results({
        @Result(column="userId", property="userId", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="roleId", property="roleId", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="createTime", property="createTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="updateTime", property="updateTime", jdbcType=JdbcType.TIMESTAMP)
    })
    UserRoleDO selectByPrimaryKey(UserRoleDOKey key);

    @UpdateProvider(type=UserRoleSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(UserRoleDO record);

    @Update({
        "update user_role",
        "set createTime = #{createTime,jdbcType=TIMESTAMP},",
          "updateTime = #{updateTime,jdbcType=TIMESTAMP}",
        "where userId = #{userId,jdbcType=INTEGER}",
          "and roleId = #{roleId,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(UserRoleDO record);
}