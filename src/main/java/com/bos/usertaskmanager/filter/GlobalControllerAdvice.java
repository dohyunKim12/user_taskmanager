package com.bos.usertaskmanager.filter;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

/**
 * Global ControllerAdvice {@link ControllerAdvice} class for send attribute to client.
 *
 * {@link ModelAttribute} call when controller method is called.
 *
 * This class is used to add common attributes to the model for all controllers
 * (May cause performance issues if abused, specified model attributes are added to the model for specific controller methods)
 *
 * @author dohyunkim
 * @see org.springframework.web.bind.annotation.ModelAttribute
 */
@ControllerAdvice
public class GlobalControllerAdvice {
    @ModelAttribute("isLoggedIn")
    public boolean isLoggedIn() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authentication != null && authentication.isAuthenticated() && !(authentication instanceof AnonymousAuthenticationToken);
    }

    @ModelAttribute("isAdmin")
    public boolean isAdmin() {
        return hasRole("ADMIN");
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

    public static boolean hasRole(String role) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authentication != null &&
                authentication.getAuthorities().stream()
                        .anyMatch(authority -> authority.getAuthority().equals("ROLE_" + role));
    }
}
