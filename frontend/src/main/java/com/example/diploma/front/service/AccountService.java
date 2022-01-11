package com.example.diploma.front.service;

import com.example.diploma.persistence.dao.UserDao;
import com.example.diploma.persistence.dto.front.UserAccountDto;
import com.example.diploma.persistence.entity.UserEntity;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AccountService {

    UserService userService;
    UserDao userDao;

    public UserAccountDto getUserAccount() {

        UserEntity currentUser = userService.findByUsername(UserService.getCurrentUser().getUsername());
        return convertToDto(currentUser);

    }

    public void saveAccountChanges(UserAccountDto userAccountDto) {

        UserEntity currentUser = userService.findByUsername(UserService.getCurrentUser().getUsername());
        currentUser.setPhoneNumber(userAccountDto.getPhone());
        currentUser.setCountry(userAccountDto.getCountry());
        currentUser.setCity(userAccountDto.getCity());
        currentUser.setFullName(userAccountDto.getFullname());
        userDao.save(currentUser);

    }


    private UserAccountDto convertToDto(UserEntity userEntity) {

        UserAccountDto userAccountDto = new UserAccountDto();
        userAccountDto.setFullname(userEntity.getFullName());
        userAccountDto.setCity(userEntity.getCity());
        userAccountDto.setCountry(userEntity.getCountry());
        userAccountDto.setPhone(userEntity.getPhoneNumber());
        return userAccountDto;

    }

}
