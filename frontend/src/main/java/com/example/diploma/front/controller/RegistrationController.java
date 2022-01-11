package com.example.diploma.front.controller;

import com.example.diploma.front.service.CatalogService;
import com.example.diploma.persistence.exeption.UserAlreadyExistsException;
import com.example.diploma.front.service.RegistrationService;
import com.example.diploma.persistence.dto.front.UserRegistrationDto;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/registration")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class RegistrationController {

    RegistrationService registrationService;

    @GetMapping
    public String registrationPage(Model model) {
        model.addAttribute("user", new UserRegistrationDto());
        return "registration";
    }

    @PostMapping
    public String registerNewUser(UserRegistrationDto user, Model model) {

        try {
            registrationService.registerNewUser(user, false);
            model.addAttribute("registerSuccess", "Поздравляем с успешной регистрацией");
            return "login";
        } catch (UserAlreadyExistsException e) {
            model.addAttribute("user", new UserRegistrationDto());
            model.addAttribute("error", e.getMessage());
            return "registration";
        }

    }
}
