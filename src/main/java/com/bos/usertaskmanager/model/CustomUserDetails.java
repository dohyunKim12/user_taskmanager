package com.bos.usertaskmanager.model;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

/**
 * Custom implementation of {@link UserDetails} containing essential user information
 * required during authentication and authorization.
 *
 * On the initial login, retrieves user authority information from the database and stores
 * it within the JWT claims for stateless authentication.
 *
 * @author dohyunkim
 * @see org.springframework.security.core.userdetails.UserDetails
 */
public class CustomUserDetails implements UserDetails {
    private final User userInfo;

    public CustomUserDetails(User userInfo) {
        this.userInfo = userInfo;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if (userInfo.getRole().equals("ADMIN")) {
            return List.of(new SimpleGrantedAuthority("ROLE_ADMIN"));
        } else if (userInfo.getRole().equals("Administrator")) {
            return List.of(new SimpleGrantedAuthority("ROLE_Administrator"));
        } else if (userInfo.getRole().equals("Regular")) {
            return List.of(new SimpleGrantedAuthority("ROLE_User"));
        }
        return List.of();
    }

    @Override
    public String getPassword() {
        return this.userInfo.getPassword();
    }

    @Override
    public String getUsername() {
        return this.userInfo.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() { return true; }

    @Override
    public boolean isAccountNonLocked() { return true; }

    @Override
    public boolean isCredentialsNonExpired() { return true; }

    @Override
    public boolean isEnabled() { return true; }

}
