package com.example.diploma.front.controller;

import com.example.diploma.front.service.AccountService;
import com.example.diploma.front.service.UserService;
import com.example.diploma.persistence.dto.front.UserAccountDto;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequiredArgsConstructor
@RequestMapping("/account")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AccountController {

    AccountService accountService;

    @GetMapping
    public String getAccountPage(Model model) {

        if (!UserService.isAuthorized()) {
            return "404";
        }

        model.addAttribute("userAccount", accountService.getUserAccount());
        return "account";

    }

    @PostMapping("/save")
    public String saveAccountChanges(UserAccountDto userAccountDto, Model model) {

        accountService.saveAccountChanges(userAccountDto);

        model.addAttribute("userAccount", accountService.getUserAccount());
        return "account";

    }
}
