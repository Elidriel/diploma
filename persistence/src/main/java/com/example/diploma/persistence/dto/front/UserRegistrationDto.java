package com.example.diploma.persistence.dto.front;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserRegistrationDto {

    String city;
    String country;
    String username;
    String fullname;
    String password;
    Boolean isTempUser;
    String phoneNumber;

}
