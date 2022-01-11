package com.example.diploma.front.controller;

import com.example.diploma.front.service.OrderHistoryService;
import com.example.diploma.front.service.UserService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/order-history")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class OrderHistoryController {

    OrderHistoryService orderHistoryService;

    @GetMapping
    public String getOrderHistoryPage(Model model) {

        if (!UserService.isAuthorized()) {
            return "404";
        }

        model.addAttribute("orders", orderHistoryService.getFramedOrders());

        return "orderHistory";

    }
}
