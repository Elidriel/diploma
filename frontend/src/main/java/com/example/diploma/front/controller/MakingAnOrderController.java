package com.example.diploma.front.controller;

import com.example.diploma.front.service.CartService;
import com.example.diploma.front.service.MakingAnOrderService;
import com.example.diploma.front.service.OrderHistoryService;
import com.example.diploma.front.service.UserService;
import com.example.diploma.persistence.dto.front.OrderHeaderDto;
import com.example.diploma.persistence.entity.UserEntity;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/making-an-order")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class MakingAnOrderController {

    CartService cartService;
    OrderHistoryService orderHistoryService;
    MakingAnOrderService makingAnOrderService;

    @GetMapping
    public String getMakingAnOrderPage(Model model) {

        if (!UserService.isAuthorized()) {
            return "404";
        }

        model.addAttribute("cart", cartService.getCartDto());
        model.addAttribute("order", makingAnOrderService.getOrderHeaderDto());

        return "makingAnOrder";

    }

    @PostMapping("/save")
    public String saveOrder(OrderHeaderDto orderHeaderDto, Model model) {


        makingAnOrderService.saveOrder(orderHeaderDto);

        model.addAttribute("orders", orderHistoryService.getFramedOrders());

        return "orderHistory";
    }
}
