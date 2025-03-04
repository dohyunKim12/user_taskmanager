package com.bos.usertaskmanager.repository;

import com.bos.usertaskmanager.model.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface UserMapper {
    @Select("SELECT * FROM user")
    List<User> getAllUsers();

    @Select("SELECT * FROM user where user_id = #{id}")
    User getUserById(String id);

    @Select("SELECT * FROM user where username = #{username}")
    User getUserByUsername(String username);

    @Insert("INSERT INTO user (username) values (#{username})")
    void insertUser(User user);

    @Update("UPDATE user SET username = #{username} WHERE user_id = #{id}")
    void updateUsername(User user);

    @Update("UPDATE user SET username = #{username}, email = #{email} WHERE user_id = #{id}")
    void updateUser(User user);

    @Delete("DELETE FROM user where username = #{username}")
    void deleteUserByUsername(String username);

    @Delete("DELETE FROM user where user_id = #{id}")
    int deleteUserById(String id);

    @Delete("DELETE FROM user")
    void deleteAllUsers();
}
