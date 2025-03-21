package com.bos.usertaskmanager.repository;

import com.bos.usertaskmanager.dto.UserDto;
import com.bos.usertaskmanager.model.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface UserMapper {
    @Select("SELECT * FROM users")
    List<User> getAllUsers();

    @Select("SELECT * FROM users LEFT JOIN team ON users.team_id = team.team_id " +
            "ORDER BY team.team_id, users.user_id")
    List<UserDto> getAllUsersInfo();

    @Select("SELECT * FROM users where user_id = #{id}")
    User getUserById(String id);

    @Select("SELECT username FROM users where user_id = #{id}")
    String getUsernameById(String id);

    @Select("SELECT * FROM users where username = #{username}")
    User getUserByUsername(String username);

    @Insert("INSERT INTO users (username, password, email, team_id, role) values (#{username}, #{password}, #{email}, #{teamId}, #{role})")
    void insertUser(User user);

    @Update("UPDATE users SET username = #{username} WHERE user_id = #{userId}")
    void updateUsername(User user);

    @Update("UPDATE users SET username = #{username}, team_id = #{teamId}, email = #{email}, role = #{role} WHERE user_id = #{userId}")
    void updateUser(User user);

    @Delete("DELETE FROM users where username = #{username}")
    void deleteUserByUsername(String username);

    @Delete("DELETE FROM users where user_id = #{id}")
    int deleteUserById(String id);

    @Delete("DELETE FROM users")
    void deleteAllUsers();
}
