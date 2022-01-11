package com.example.diploma.front.service;

import com.example.diploma.persistence.exeption.UserAlreadyExistsException;
import com.example.diploma.persistence.dao.UserDao;
import com.example.diploma.persistence.dto.front.UserRegistrationDto;
import com.example.diploma.persistence.entity.UserEntity;
import com.example.diploma.persistence.enums.Role;
import com.example.diploma.persistence.enums.Status;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

@Service
@Transactional
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class RegistrationService {

    UserDao userDao;
    PasswordEncoder passwordEncoder;
    AuthenticationManager authenticationManager;

    public void registerNewUser(UserRegistrationDto user, Boolean isTempUser) throws UserAlreadyExistsException{

        UserEntity userEntity = userDao.findByUsername(user.getUsername());

        if(userEntity != null) {
            throw new UserAlreadyExistsException("Пользователь с email " + user.getUsername() + " уже зарегистрирован");
        } else {
            user.setIsTempUser(isTempUser);
            UserEntity newUser = convertToEntity(user);
            userDao.save(newUser);
        }

    }

    public void createAndAuthorizeTempUser(HttpServletRequest request) throws UserAlreadyExistsException {

        UserRegistrationDto tempUser = new UserRegistrationDto();
        tempUser.setUsername(RandomStringUtils.random(20, true, true));
        tempUser.setPassword(RandomStringUtils.random(20, true, true));
        registerNewUser(tempUser, true);
        authenticateUserAndSetSession(tempUser, request);

    }

    private UserEntity convertToEntity(UserRegistrationDto userRegistrationDto){
        UserEntity userEntity = new UserEntity();
        userEntity.setFullName(userRegistrationDto.getFullname());
        userEntity.setUsername(userRegistrationDto.getUsername());
        userEntity.setPassword(passwordEncoder.encode(userRegistrationDto.getPassword()));
        userEntity.setCity(userRegistrationDto.getCity());
        userEntity.setCountry(userRegistrationDto.getCountry());
        userEntity.setPhoneNumber(userRegistrationDto.getPhoneNumber());
        userEntity.setStatus(Status.ACTIVE);
        userEntity.setRoles(Collections.singletonList(userRegistrationDto.getIsTempUser() ? Role.ROLE_TEMP_USER : Role.ROLE_USER));

        return userEntity;
    }

    private void authenticateUserAndSetSession(UserRegistrationDto user, HttpServletRequest request) {
        String username = user.getUsername();
        String password = user.getPassword();
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(username, password);

        request.getSession();

        token.setDetails(new WebAuthenticationDetails(request));
        Authentication authenticatedUser = authenticationManager.authenticate(token);

        SecurityContextHolder.getContext().setAuthentication(authenticatedUser);
    }
}
