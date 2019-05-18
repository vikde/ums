package com.demo.ums.repository.mapper;

import com.demo.ums.repository.model.UserPO;
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
public interface UserMapper {
    @Delete({
        "delete from user",
        "where userId = #{userId,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer userId);

    @Insert({
        "insert into user (username, name, ",
        "password, userStatusType, ",
        "loginTime, preLoginTime, ",
        "loginCount, createTime, ",
        "updateTime)",
        "values (#{username,jdbcType=VARCHAR}, #{name,jdbcType=VARCHAR}, ",
        "#{password,jdbcType=VARCHAR}, #{userStatusType,jdbcType=INTEGER}, ",
        "#{loginTime,jdbcType=TIMESTAMP}, #{preLoginTime,jdbcType=TIMESTAMP}, ",
        "#{loginCount,jdbcType=INTEGER}, #{createTime,jdbcType=TIMESTAMP}, ",
        "#{updateTime,jdbcType=TIMESTAMP})"
    })
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="userId", before=false, resultType=Integer.class)
    int insert(UserPO record);

    @InsertProvider(type=UserSqlProvider.class, method="insertSelective")
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="userId", before=false, resultType=Integer.class)
    int insertSelective(UserPO record);

    @Select({
        "select",
        "userId, username, name, password, userStatusType, loginTime, preLoginTime, loginCount, ",
        "createTime, updateTime",
        "from user",
        "where userId = #{userId,jdbcType=INTEGER}"
    })
    @Results({
        @Result(column="userId", property="userId", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="username", property="username", jdbcType=JdbcType.VARCHAR),
        @Result(column="name", property="name", jdbcType=JdbcType.VARCHAR),
        @Result(column="password", property="password", jdbcType=JdbcType.VARCHAR),
        @Result(column="userStatusType", property="userStatusType", jdbcType=JdbcType.INTEGER),
        @Result(column="loginTime", property="loginTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="preLoginTime", property="preLoginTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="loginCount", property="loginCount", jdbcType=JdbcType.INTEGER),
        @Result(column="createTime", property="createTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="updateTime", property="updateTime", jdbcType=JdbcType.TIMESTAMP)
    })
    UserPO selectByPrimaryKey(Integer userId);

    @UpdateProvider(type=UserSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(UserPO record);

    @Update({
        "update user",
        "set username = #{username,jdbcType=VARCHAR},",
          "name = #{name,jdbcType=VARCHAR},",
          "password = #{password,jdbcType=VARCHAR},",
          "userStatusType = #{userStatusType,jdbcType=INTEGER},",
          "loginTime = #{loginTime,jdbcType=TIMESTAMP},",
          "preLoginTime = #{preLoginTime,jdbcType=TIMESTAMP},",
          "loginCount = #{loginCount,jdbcType=INTEGER},",
          "createTime = #{createTime,jdbcType=TIMESTAMP},",
          "updateTime = #{updateTime,jdbcType=TIMESTAMP}",
        "where userId = #{userId,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(UserPO record);
}