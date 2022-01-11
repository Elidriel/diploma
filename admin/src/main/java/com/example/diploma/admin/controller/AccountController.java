package com.example.diploma.admin.controller;

import com.example.diploma.admin.security.SecurityUser;
import com.example.diploma.admin.security.SecurityUserService;
import com.example.diploma.persistence.dto.admin.AdminUserDto;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.stream.Collectors;

@RestController
@AllArgsConstructor
@RequestMapping("/admin/account")
public class AccountController {

    @GetMapping
    public AdminUserDto account() {
        final SecurityUser securityUser = SecurityUserService.getCurrentUser();

        AdminUserDto userDto = new AdminUserDto();
        userDto.setLogin(securityUser.getUsername());
        userDto.setFullName(securityUser.getUsername());
        userDto.setAuthorities(securityUser.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList()));
        return userDto;
    }

}
