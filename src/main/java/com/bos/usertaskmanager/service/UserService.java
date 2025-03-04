package com.bos.usertaskmanager.service;

import com.bos.usertaskmanager.model.User;
import com.bos.usertaskmanager.repository.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;

    public User getUserById(String userId) {
        return userMapper.getUserById(userId);
    }

    public List<User> getAllUsers() {
        return userMapper.getAllUsers();
    }

    public User createUser(User user) {
        userMapper.insertUser(user);
        return user;  // Return the user with the ID populated after insertion
    }

    public User updateUser(User user) {
        userMapper.updateUser(user);
        return user;
    }

    public boolean deleteUserByUsername(String username) {
        userMapper.deleteUserByUsername(username);
        return true;
    }

    public boolean deleteUserById(String userId) {
        return userMapper.deleteUserById(userId) > 0;
    }
}
