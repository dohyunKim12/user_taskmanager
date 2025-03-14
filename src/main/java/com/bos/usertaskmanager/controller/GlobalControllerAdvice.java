package com.bos.usertaskmanager.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

@ControllerAdvice
public class GlobalControllerAdvice {
    @ModelAttribute("isLoggedIn")
    public boolean isLoggedIn(Authentication authentication) {
        return authentication != null && authentication.isAuthenticated();
    }

    @ModelAttribute("username")
    public String username(Authentication authentication) {
        if(authentication != null && authentication.getPrincipal() instanceof UserDetails) {
            return ((UserDetails)authentication.getPrincipal()).getUsername();
        }

        if(authentication != null && authentication.isAuthenticated()) {
            return authentication.getName();
        }
        return null;
    }
}
