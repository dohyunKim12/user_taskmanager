package com.bos.usertaskmanager.config;

import com.bos.usertaskmanager.model.User;
import com.bos.usertaskmanager.repository.UserMapper;
import com.bos.usertaskmanager.service.UserService;
import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class InitialAdminLoader {
    private final UserMapper userMapper;
    private final AdminProperties adminProperties;
    Logger logger = LoggerFactory.getLogger(UserService.class);

    public InitialAdminLoader(UserMapper userMapper, AdminProperties adminProperties) {
        this.userMapper = userMapper;
        this.adminProperties = adminProperties;
    }

    @PostConstruct
    public void initAdminUser() {
        boolean exists = userMapper.existsByUsername("admin");
        if (!exists) {
            User admin = new User();
            admin.setUsername(adminProperties.getUsername());
            admin.setEmail("admin@bos-semi.com");
            admin.setPassword(adminProperties.getPassword());
            admin.setRole("ADMIN");
            userMapper.insertUser(admin);
            logger.info("Admin user created: " + admin);
        }
    }
}
