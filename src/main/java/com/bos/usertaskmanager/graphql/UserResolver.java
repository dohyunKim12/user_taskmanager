package com.bos.usertaskmanager.graphql;

import com.bos.usertaskmanager.model.User;
import com.bos.usertaskmanager.service.UserService;
import graphql.kickstart.tools.GraphQLQueryResolver;
import graphql.kickstart.tools.GraphQLMutationResolver;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Component
public class UserResolver implements GraphQLQueryResolver, GraphQLMutationResolver {

    @Autowired
    private UserService userService;

    // Query to fetch a single user by ID
    public User getUser(String userId) {
        return userService.getUserById(userId);
    }

    // Query to fetch all users
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
