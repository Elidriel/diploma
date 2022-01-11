package com.example.diploma.admin.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;

public class SecurityUserService {

    public static SecurityUser getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null || !authentication.isAuthenticated())
            return null;

        User user = (User) authentication.getPrincipal();
        return new SecurityUser(user.getUsername(), user.getPassword(), user.getAuthorities(), user.isEnabled());
    }

}
