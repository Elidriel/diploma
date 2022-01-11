package com.example.diploma.front.controller;

import com.example.diploma.front.service.CatalogService;
import com.example.diploma.persistence.dao.ProductDao;
import com.example.diploma.persistence.entity.ProductEntity;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class MainController {

    @GetMapping("/")
    public String main(Model model) {
        return "main";
    }
}
