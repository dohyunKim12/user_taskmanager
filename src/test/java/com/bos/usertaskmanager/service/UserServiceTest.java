package com.bos.usertaskmanager.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.bos.usertaskmanager.dto.CreateUserInput;
import com.bos.usertaskmanager.model.User;
import com.bos.usertaskmanager.repository.UserMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {
    Logger logger = LoggerFactory.getLogger(UserServiceTest.class);

    @Mock
    private UserMapper userMapper;

    @InjectMocks
    private UserService userService;

    @Test
    public void testGetUserById() {
        // [Arrange]
        // Prepare a mock user ID and a dummy User object (dummy data)
        String userId = "USR-000001";
        User dummyUser = new User();
        dummyUser.setUserId(userId);
        dummyUser.setUsername("john.doe");
        dummyUser.setEmail("john@example.com");

        // when - thenReturn : Set behavior that when userMapper.getUserById(userId) is called, return dummyUser
        when(userMapper.getUserById(userId)).thenReturn(dummyUser);

        // [Act]
        User user = userService.getUserById(userId);

        // [Verify]
        assertNotNull(user, "User should not be null");
        assertEquals(userId, user.getUserId(), "User ID should match");
        assertEquals("john.doe", user.getUsername(), "Username should match");
        assertEquals("john@example.com", user.getEmail(), "Email should match");

        verify(userMapper, times(1)).getUserById(userId);

        logger.info("User ID: {}", user.getUserId());
    }

    @Test
    public void testCreateUser() {
        User dummyUser = new User();
        dummyUser.setUserId("USR-000001");
        dummyUser.setUsername("john.doe");
        dummyUser.setEmail("john@example.com");
        dummyUser.setRole("Regular");
        dummyUser.setPassword("password");
        dummyUser.setTeamId("TEM-000001");

        doNothing().when(userMapper).insertUser(dummyUser);
        when(userMapper.getUserByUsername("john.doe")).thenReturn(dummyUser);

        User newUser = userService.createUser(dummyUser);

        assertNotNull(newUser, "New user should not be null");
        assertEquals("USR-000001", newUser.getUserId(), "User ID) should match");
    }
}
