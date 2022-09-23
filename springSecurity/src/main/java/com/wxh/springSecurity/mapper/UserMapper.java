package com.wxh.springSecurity.mapper;

import com.wxh.springSecurity.entity.AuthUser;
import org.apache.ibatis.annotations.*;
import org.thymeleaf.expression.Strings;

/**
 * @Auther: WXH
 * @Date: 2022/9/7 - 09 - 07 - 15:55
 */
@Mapper
public interface UserMapper {
    @Select("select * from user where name=#{name}")
    AuthUser getUserPasswordByUsername(String name);
    @Options(useGeneratedKeys = true,keyColumn = "id",keyProperty = "id")//回传id
    @Insert("insert into user(name,password,role) values(#{name},#{password},#{role})")
    int register(AuthUser authUser);
    @Insert("insert into student(uid,name,sex,grade) values(#{uid},#{name},#{sex},#{grade})")
    int addStudent(@Param("uid")int uid,@Param("name")String name,@Param("sex") String sex,@Param("grade") String grade);
    @Select("select * from student where uid=#{uid}")
    int getSidByUid(@Param("uid") int uid);
    @Select("select count(*) from student")
    int getStudentCount();

}
