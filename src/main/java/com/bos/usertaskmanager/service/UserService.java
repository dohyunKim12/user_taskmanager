package com.bos.usertaskmanager.service;

import com.bos.usertaskmanager.dto.UserDto;
import com.bos.usertaskmanager.model.User;
import com.bos.usertaskmanager.repository.UserMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(rollbackFor = Exception.class)
public class UserService {
    Logger logger = LoggerFactory.getLogger(UserService.class);
    @Autowired
    private UserMapper userMapper;

    public User getUserById(String userId) {
        return userMapper.getUserById(userId);
    }

    public List<User> getAllUsers() {
        return userMapper.getAllUsers();
    }

    public Object getAllUsersInfoExceptAdmin() {
        return userMapper.getAllUsersInfoExceptAdmin();
    }

    public List<UserDto> getAllUsersInfo() {
        return userMapper.getAllUsersInfo();
    }

//    public User createUser(User user) {
//        logger.info("Creating user: " + user);
//        userMapper.insertUser(user);
//        logger.info("User created: " + user);
//        return user;  // Return the user with the ID populated after insertion
//    }
//
    public User createUser(User user) {
        userMapper.insertUser(user);
        return userMapper.getUserByUsername(user.getUsername());
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

    public User getUserByUsername(String username) {
        return userMapper.getUserByUsername(username);
    }


}
