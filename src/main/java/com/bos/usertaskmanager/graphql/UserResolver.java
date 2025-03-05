package com.bos.usertaskmanager.graphql;

import com.bos.usertaskmanager.model.User;
import com.bos.usertaskmanager.service.UserService;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class UserResolver {

    @Autowired
    private UserService userService;
//    private final UserService userService;
//    public UserResolver(UserService userService) {
//        this.userService = userService;
//    }

    // Query to fetch a single user by ID
    @QueryMapping
    public User getUser(@Argument String userId) {
        return userService.getUserById(userId);
    }

    // Query to fetch all users
    @QueryMapping
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    // Mutation to create a new user
    public User createUser(String teamId, String username, String email) {
        User user = new User();
        user.setTeamId(teamId);
        user.setUsername(username);
        user.setEmail(email);
        return userService.createUser(user);
    }

    // Mutation to update an existing user
    public User updateUser(String userId, String teamId, String username, String email) {
        User user = new User();
        user.setUserId(userId);
        user.setTeamId(teamId);
        user.setUsername(username);
        user.setEmail(email);
        return userService.updateUser(user);
    }

    // Mutation to delete a user
    public Boolean deleteUserById(String userId) {
        return userService.deleteUserById(userId);
    }
    public Boolean deleteUserByUsername(String username) {
        return userService.deleteUserByUsername(username);
    }
}
