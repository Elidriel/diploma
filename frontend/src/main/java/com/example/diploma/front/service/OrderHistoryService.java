package com.example.diploma.front.service;

import com.example.diploma.persistence.dao.OrderHeaderDao;
import com.example.diploma.persistence.dao.UserDao;
import com.example.diploma.persistence.dto.front.OrderHeaderDto;
import com.example.diploma.persistence.dto.front.OrderHistoryDto;
import com.example.diploma.persistence.entity.OrderHeaderEntity;
import com.example.diploma.persistence.entity.UserEntity;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class OrderHistoryService {

    UserDao userDao;
    CartService cartService;
    OrderHeaderDao orderHeaderDao;

    public List<OrderHistoryDto> getFramedOrders() {

        UserEntity user = userDao.findByUsername(UserService.getCurrentUser().getUsername());
        List<OrderHeaderEntity> framedOrders = orderHeaderDao.findByUser(user);

        return framedOrders.stream()
                .map(orderHeaderEntity -> {
                    OrderHistoryDto orderHistoryDto = new OrderHistoryDto();
                    orderHistoryDto.setDateCreate(orderHeaderEntity.getCreateDate());
                    orderHistoryDto.setId(orderHeaderEntity.getId());
                    orderHistoryDto.setDeliveryTo(orderHeaderEntity.getDeliveryTo());
                    orderHistoryDto.setCartDto(cartService.getCartDto(orderHeaderEntity.getCart()));
                    orderHistoryDto.setAddress(orderHeaderEntity.getAddress());
                    return orderHistoryDto;
                })
                .collect(Collectors.toList());


    }

}
