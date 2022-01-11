package com.example.diploma.admin.security;

import com.example.diploma.admin.service.UserService;
import com.example.diploma.persistence.entity.UserEntity;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Objects;


@Service("userDetailsServiceImpl")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@AllArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    UserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity user = userService.findByUsername(username);
        if (Objects.isNull(user)) {
            throw new UsernameNotFoundException("Пользователь не найден");
        }
        return SecurityUser.fromUser(user);
    }

}
