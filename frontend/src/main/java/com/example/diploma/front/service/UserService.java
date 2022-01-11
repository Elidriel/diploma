package com.example.diploma.front.service;

import com.example.diploma.front.security.SecurityUser;
import com.example.diploma.persistence.dao.UserDao;
import com.example.diploma.persistence.entity.UserEntity;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserService {

    UserDao userDao;

    public UserEntity findByUsername(String username) {
        return userDao.findByUsername(username);
    }

    public static User getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null || !authentication.isAuthenticated() || "anonymousUser"
                .equals(authentication.getPrincipal()) || "anonymous".equals(authentication.getPrincipal())) {
            return null;
        }

        return (User) authentication.getPrincipal();
    }

    public static boolean isAuthorized() {
        return SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getPrincipal() instanceof User;
    }


}
