package com.bos.usertaskmanager.repository;

import com.bos.usertaskmanager.model.User;
import org.apache.ibatis.annotations.*;

@Mapper
public interface UserMapper {
    @Select("SELECT * FROM user where user_id = #{id}")
    User findUserById(int id);

    @Select("SELECT * FROM user where username = #{username}")
    User findUserByUsername(String username);

    @Insert("INSERT INTO user (username) values (#{username})")
    void insertUser(String username);

    @Delete("DELETE FROM user where username = #{username}")
    void deleteUserByUsername(String username);
}
