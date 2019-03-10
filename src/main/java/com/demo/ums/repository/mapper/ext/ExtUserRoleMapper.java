package com.demo.ums.repository.mapper.ext;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * @author vikde
 * @date 2019/03/10
 */
@Mapper
@Repository
public interface ExtUserRoleMapper {

    /**
     * 通过userId删除全部的角色配置
     *
     * @param userId 用户id
     */
    @Delete({
            "delete from user_role",
            "where roleId = #{userId,jdbcType=INTEGER}"
    })
    void deleteByUserId(@Param("userId") Integer userId);

    /**
     * 分配用户角色
     *
     * @param userId 用户id
     * @param roleId 角色id
     */
    @Insert("REPLACE INTO user_role (userId,roleId) VALUES (#{userId},#{roleId})")
    void assignUserRole(@Param("userId") int userId, @Param("roleId") int roleId);
}