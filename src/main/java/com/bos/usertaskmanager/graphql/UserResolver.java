package com.bos.usertaskmanager.graphql;

import com.bos.usertaskmanager.dto.CreateUserInput;
import com.bos.usertaskmanager.dto.ResultDto;
import com.bos.usertaskmanager.dto.UpdateUserInput;
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
    public User createUser(@Argument CreateUserInput input) {
        User user = new User();
        user.setTeamId(input.getTeamId());
        user.setUsername(input.getUsername());
        user.setPassword(input.getPassword());
        user.setEmail(input.getEmail());
        user.setRole(input.getRole());
        return userService.createUser(user);
    }

    @MutationMapping
    public User updateUser(@Argument UpdateUserInput input) {
        User user = new User();
        user.setUserId(input.getUserId());
        user.setTeamId(input.getTeamId());
        user.setUsername(input.getUsername());
        user.setEmail(input.getEmail());
        user.setPassword(input.getPassword());
        user.setRole(input.getRole());
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
