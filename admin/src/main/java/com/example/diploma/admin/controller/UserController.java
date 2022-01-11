package com.example.diploma.admin.controller;

import com.example.diploma.admin.service.UserService;
import com.example.diploma.persistence.dto.admin.UserDto;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/admin/user")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserController {

    UserService userService;

    @GetMapping("/get-all")
    public List<UserDto> getAllUsers(){
        return userService.getAllUsers();
    }

    @DeleteMapping("/delete")
    public List<UserDto> delete(@RequestParam("id") Long id) {
        userService.delete(id);
        return userService.getAllUsers();
    }

    @GetMapping("/block")
    public List<UserDto> delete(@RequestParam("id") Long id, @RequestParam("active") Boolean active) {
        userService.block(id, active);
        return userService.getAllUsers();
    }
}
