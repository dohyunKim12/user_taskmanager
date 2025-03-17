package com.bos.usertaskmanager.model;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

public class CustomUserDetails implements UserDetails {
    private final User userInfo;

    public CustomUserDetails(User userInfo) {
        this.userInfo = userInfo;
    }

    // 최초 로그인 시 JWT를 생성하는 과정에서 한번 호출되어 DB에서 권한정보를 얻음.
    // 이후 이 권한정보를 JWT 토큰의 Claims 에 저장.
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
//        return List.of(new SimpleGrantedAuthority(userInfo.getRole())); // userInfo에서 실제 권한 리턴
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
