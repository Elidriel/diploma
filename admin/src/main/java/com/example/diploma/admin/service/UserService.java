package com.example.diploma.admin.service;

import com.example.diploma.persistence.dao.UserDao;
import com.example.diploma.persistence.dto.admin.UserDto;
import com.example.diploma.persistence.entity.UserEntity;
import com.example.diploma.persistence.enums.Status;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserService {

    UserDao userDao;

    public List<UserDto> getAllUsers() {
        List<UserEntity> userEntities = userDao.findAll();
        List<UserDto> userDtos = new ArrayList<>();
        for (UserEntity userEntity : userEntities) {
            userDtos.add(convertToDto(userEntity));
        }
        return userDtos;
    }

    public UserEntity findByUsername(String username) {
        return userDao.findByUsername(username);
    }

    public UserEntity findById(Long id) {
        return userDao.findById(id).orElseThrow(() -> new UsernameNotFoundException("Пользователь с id=" + id + " не найден"));
    }

    public void delete(Long id){
        userDao.deleteById(id);
    }

    public void block(Long id, Boolean active){
        UserEntity userEntity = userDao.findById(id).get();
        userEntity.setStatus(active ? Status.ACTIVE : Status.NOT_ACTIVE);
        userDao.save(userEntity);
    }

    public static UserDto convertToDto(UserEntity userEntity) {
        UserDto userDto = new UserDto();
        userDto.setId(userEntity.getId());
        userDto.setFullname(userEntity.getFullName());
        userDto.setPhoneNumber(userEntity.getPhoneNumber());
        userDto.setIsActive(Status.ACTIVE.equals(userEntity.getStatus()));
        userDto.setCity(userEntity.getCity());
        userDto.setCountry(userEntity.getCountry());
        userDto.setUsername(userEntity.getUsername());
        return userDto;
    }

}
