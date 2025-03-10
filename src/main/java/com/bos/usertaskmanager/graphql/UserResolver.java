package com.bos.usertaskmanager.graphql;

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
    public User createUser(@Argument String teamId,@Argument String username,@Argument String email) {
        User user = new User();
        user.setTeamId(teamId);
        user.setUsername(username);
        user.setEmail(email);
        return userService.createUser(user);
    }

    @MutationMapping
    public User updateUser(@Argument String userId,@Argument String teamId,@Argument String username,@Argument String email) {
        User user = new User();
        user.setUserId(userId);
        user.setTeamId(teamId);
        user.setUsername(username);
        user.setEmail(email);
        return userService.updateUser(user);
    }

    @MutationMapping
    public Boolean deleteUserById(@Argument String userId) {
        return userService.deleteUserById(userId);
    }

    @MutationMapping
    public Boolean deleteUserByUsername(@Argument String username) {
        return userService.deleteUserByUsername(username);
    }
}
