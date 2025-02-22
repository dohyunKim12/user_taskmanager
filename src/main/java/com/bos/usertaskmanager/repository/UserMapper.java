package com.bos.usertaskmanager.repository;

import com.bos.usertaskmanager.model.User;
import org.apache.ibatis.annotations.*;

@Mapper
public interface UserMapper {
    @Select("SELECT * FROM user")
    User[] getAllUsers();

    @Select("SELECT * FROM user where user_id = #{id}")
    User getUserById(int id);

    @Select("SELECT * FROM user where username = #{username}")
    User getUserByUsername(String username);

    @Insert("INSERT INTO user (username) values (#{username})")
    void insertUser(String username);

    @Delete("DELETE FROM user where username = #{username}")
    void deleteUserByUsername(String username);

    @Update("UPDATE user SET username = #{username} WHERE user_id = #{id}")
    void updateUsername(User user);
}
