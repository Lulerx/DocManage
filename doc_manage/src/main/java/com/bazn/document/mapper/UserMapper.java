package com.bazn.document.mapper;

import com.bazn.document.bean.User;
import org.apache.ibatis.annotations.*;

import java.sql.SQLException;

@Mapper
public interface UserMapper {

    @Insert("insert into user(username,nickname,password,email,userface,regTime)" +
            "values(#{username}, #{nickname}, #{password}, #{email}, #{userface}, #{regTime})")
    int insertUser(User user) throws SQLException;

    @Update({"<script>",
            "update user",
            "<set>",
            "<if test='nickname != null'>",
            "nickname = #{nickname},",
            "</if>",
            "<if test='password != null'>",
            "password = #{password},",
            "</if>",
            "<if test='email != null'>",
            "email = #{email},",
            "</if>",
            "<if test='userface != null'>",
            "userface = #{userface},",
            "</if>",
            "<if test='enabled != null'>",
            "enabled = #{enabled},",
            "</if>",
            "</set>",
            "where id=#{id}",
            "</script>"})
    int updateUser(User user) throws SQLException;

    @Select("select * from user where id=#{id}")
    User findUserById(@Param("id") Long id) throws SQLException;


    @Select("select * from user where username=#{username}")
    User findUserByUserName(@Param("username") String username);
}
