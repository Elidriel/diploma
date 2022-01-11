package com.example.diploma.persistence.dto.admin;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserDto {

        Long id;
        String fullname;
        String phoneNumber;
        Boolean isActive;
        String city;
        String country;
        String username;
        List<String> authorities;

}
