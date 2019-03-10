package com.demo.ums.repository.mapper.ext;

import com.demo.ums.repository.model.Permission;
import com.demo.ums.repository.model.User;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

/**
 * @author vikde
 * @date 2019/03/10
 */
@Mapper
@Repository
public interface ExtUserMapper {

    /**
     * 创建用户
     *
     * @param username       用户名
     * @param name           姓名
     * @param password       密码
     * @param userStatusType 用户状态类型
     */
    @Insert("INSERT INTO user (username,name,password,userStatusType) VALUES (#{username},#{name},#{password},#{userStatusType})")
    void createUser(@Param("username") String username, @Param("name") String name, @Param("password") String password, @Param("userStatusType") int userStatusType);

    /**
     * 分配用户角色
     *
     * @param userId 用户id
     * @param roleId 角色id
     */
    @Insert("REPLACE INTO user_role (userId,roleId) VALUES (#{userId},#{roleId})")
    void assignUserRole(@Param("userId") int userId, @Param("roleId") int roleId);

    /**
     * 查询用户
     *
     * @param userId   用户id
     * @param username 用户名
     * @param name     姓名
     * @return 用户列表
     */
    @SelectProvider(type = ExtUserSqlProvider.class, method = "readUser")
    List<User> readUser(@Param("userId") Integer userId, @Param("username") String username, @Param("name") String name);

    /**
     * 通过用户名查询用户
     *
     * @param username 用户名
     * @return 用户
     */
    @Select("SELECT * FROM user WHERE username=#{username}")
    User readUserByUsername(@Param("username") String username);

    /**
     * 与用户登录
     *
     * @param userId    用户id
     * @param loginTime 登陆时间
     */
    @Update("UPDATE user SET preLoginTime=loginTime,loginCount=loginCount+1,loginTime=#{loginTime}  WHERE userId=#{userId}")
    void login(@Param("userId") int userId, @Param("loginTime") Date loginTime);

    /**
     * 获取用户权限
     *
     * @param userId 用户id
     * @return 权限列表
     */
    @Select("SELECT permission.*,role.roleName FROM user RIGHT JOIN user_role ON user.userId = user_role.userId " +
            "RIGHT JOIN role ON user_role.roleId = role.roleId RIGHT JOIN role_permission ON role.roleId = role_permission.roleId " +
            "RIGHT JOIN permission ON role_permission.permissionId = permission.permissionId " +
            "WHERE user.userId = #{userId}")
    List<Permission> readUserPermission(@Param("userId") int userId);

    /**
     * 修改用户密码
     *
     * @param username       用户名
     * @param password       密码
     * @param userStatusType 用户状态
     */
    @Update("UPDATE user SET password=#{password},userStatusType=#{userStatusType}  WHERE username=#{username}")
    void changePassword(@Param("username") String username, @Param("password") String password, @Param("userStatusType") int userStatusType);
}