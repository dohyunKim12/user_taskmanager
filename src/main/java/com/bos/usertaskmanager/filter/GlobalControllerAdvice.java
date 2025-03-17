package com.bos.usertaskmanager.filter;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

@ControllerAdvice
public class GlobalControllerAdvice {
    @ModelAttribute("isLoggedIn")
    public boolean isLoggedIn() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authentication != null && authentication.isAuthenticated() && !(authentication instanceof AnonymousAuthenticationToken);
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
