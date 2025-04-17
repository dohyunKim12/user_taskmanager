package com.bos.usertaskmanager.service.auth;

import com.bos.usertaskmanager.model.CustomUserDetails;
import com.bos.usertaskmanager.model.User;
import com.bos.usertaskmanager.repository.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * CustomUserDetailsService is a service that implements UserDetailsService {@link UserDetailsService} to load user-specific data.
 *
 * It retrieves user information from the database using UserMapper and returns a CustomUserDetails object.
 * This service is used for authentication and authorization in the application when use Spring Security.
 * The `loadUserByUsername` method is called by Spring Security during the authentication process to load user details.
 *
 * @author dohyun.kim
 */

@Service
public class CustomUserDetailsService implements UserDetailsService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userMapper.getUserByUsername(username);
        if (user != null) {
            return new CustomUserDetails(user);
        }
        throw new UsernameNotFoundException("Cannot Find User");
    }
}
