package com.example.diploma.front.controller;

import com.example.diploma.front.service.CartService;
import com.example.diploma.front.service.RegistrationService;
import com.example.diploma.front.service.UserService;
import com.example.diploma.persistence.dto.front.CartDto;
import com.example.diploma.persistence.exeption.UserAlreadyExistsException;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;

@Controller
@RequiredArgsConstructor
@RequestMapping("/cart")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class CartController {

    CartService cartService;
    RegistrationService registrationService;

    @GetMapping
    public String getCartPage(Model model) {

        if (!UserService.isAuthorized()) {
            model.addAttribute("cart", new CartDto(0D, new ArrayList<>()));
        } else {
            model.addAttribute("cart", cartService.getCartDto());
        }

        return "cart";

    }

    @DeleteMapping("/remove")
    @ResponseBody
    public ResponseEntity<HttpStatus> removeProductFromCart(@RequestParam("productId") Long productId) {
        cartService.removeProductFromCart(productId);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @PutMapping("/add")
    @ResponseBody
    public ResponseEntity addProductToCart(@RequestParam("productId") Long productId, HttpServletRequest request) throws UserAlreadyExistsException {

        if (!UserService.isAuthorized()) {
            registrationService.createAndAuthorizeTempUser(request);
        }

        cartService.addProductToCart(productId);
        return ResponseEntity.status(HttpStatus.OK).build();

    }

    @PutMapping("/change")
    @ResponseBody
    public ResponseEntity addProductToCart(@RequestParam("productId") Long productId,
                                           @RequestParam("count") Long count,
                                           HttpServletRequest request) throws UserAlreadyExistsException {

        cartService.changeProductInCart(productId, count);
        return ResponseEntity.status(HttpStatus.OK).build();

    }

}
