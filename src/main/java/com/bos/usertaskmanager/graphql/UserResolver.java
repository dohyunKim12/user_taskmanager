package com.bos.usertaskmanager.graphql;

import com.bos.usertaskmanager.dto.ResultDto;
import com.bos.usertaskmanager.model.User;
import com.bos.usertaskmanager.service.UserService;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class UserResolver {
    private final UserService userService;
    public UserResolver(UserService userService) {
        this.userService = userService;
    }

    @QueryMapping
    public User getUser(@Argument String userId) {
        return userService.getUserById(userId);
    }

    @QueryMapping
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @QueryMapping
    public User getUserByUsername(@Argument String username) {
        return userService.getUserByUsername(username);
    }

    @MutationMapping
    public User createUser(@Argument String teamId,@Argument String username,@Argument String password, @Argument String email, @Argument String role) {
        User user = new User();
        user.setTeamId(teamId);
        user.setUsername(username);
        user.setPassword(password);
        user.setEmail(email);
        user.setRole(role);
        return userService.createUser(user);
    }

    @MutationMapping
    public User updateUser(@Argument String userId,@Argument String teamId,@Argument String username,@Argument String email, @Argument String role) {
        User user = new User();
        user.setUserId(userId);
        user.setTeamId(teamId);
        user.setUsername(username);
        user.setEmail(email);
        user.setRole(role);
        return userService.updateUser(user);
    }

    @MutationMapping
    public ResultDto deleteUserById(@Argument String userId) {
        if(userService.deleteUserById(userId)){;
            return new ResultDto(true, "User deleted successfully");
        } else {
            return new ResultDto(false, "User not found");
        }
    }

    @MutationMapping
    public ResultDto deleteUserByUsername(@Argument String username) {
        if(userService.deleteUserByUsername(username)) {;
            return new ResultDto(true, "User deleted successfully");
        } else {
            return new ResultDto(false, "User not found");
        }
    }
}
