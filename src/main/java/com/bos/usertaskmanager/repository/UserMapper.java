package com.bos.usertaskmanager.repository;

import com.bos.usertaskmanager.model.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface UserMapper {
    @Select("SELECT * FROM users")
    List<User> getAllUsers();

    @Select("SELECT * FROM users where user_id = #{id}")
    User getUserById(String id);

    @Select("SELECT * FROM users where username = #{username}")
    User getUserByUsername(String username);

    @Insert("INSERT INTO users (username, team_id, email) values (#{username}, #{teamId}, #{email})")
    void insertUser(User user);

    @Update("UPDATE users SET username = #{username} WHERE user_id = #{id}")
    void updateUsername(User user);

    @Update("UPDATE users SET username = #{username}, email = #{email} WHERE user_id = #{id}")
    void updateUser(User user);

    @Delete("DELETE FROM users where username = #{username}")
    void deleteUserByUsername(String username);

    @Delete("DELETE FROM users where user_id = #{id}")
    int deleteUserById(String id);

    @Delete("DELETE FROM users")
    void deleteAllUsers();
}
