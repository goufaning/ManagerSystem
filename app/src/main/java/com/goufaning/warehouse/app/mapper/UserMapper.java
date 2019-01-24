package com.goufaning.warehouse.app.mapper;

import com.goufaning.warehouse.app.entity.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository // 只用于去除错误提示
public interface UserMapper {

    @Select("select * from user_332 where name= #{name} and password = #{password}")
    User findUserByNameAndPassword(@Param("name") String name, @Param("password") String password);

    @Select("select * from user_332")
    List<User> getAll();

    @Insert("insert into user_332(name,password,permissions,description) values(#{name},#{passwor},#{permissions},#{description})")
    int insert(@Param("name") String name, @Param("password") String password, @Param("permissions") int permissions, @Param("description") String description);
}
