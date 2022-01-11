package com.example.diploma.front.service;

import com.example.diploma.persistence.dao.CartDao;
import com.example.diploma.persistence.dao.OrderHeaderDao;
import com.example.diploma.persistence.dao.UserDao;
import com.example.diploma.persistence.dto.front.OrderHeaderDto;
import com.example.diploma.persistence.entity.CartEntity;
import com.example.diploma.persistence.entity.OrderHeaderEntity;
import com.example.diploma.persistence.entity.UserEntity;
import com.example.diploma.persistence.enums.PaymentType;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;

@Service
@Transactional
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class MakingAnOrderService {

    UserDao userDao;
    CartDao cartDao;
    OrderHeaderDao orderHeaderDao;

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy.MM.dd");

    public OrderHeaderDto getOrderHeaderDto() {

        OrderHeaderDto orderHeaderDto = new OrderHeaderDto();
        UserEntity user = userDao.findByUsername(UserService.getCurrentUser().getUsername());

        orderHeaderDto.setCustomerFullname(user.getFullName());
        orderHeaderDto.setCity(user.getCity());
        orderHeaderDto.setCustomerPhone(user.getPhoneNumber());

        return orderHeaderDto;

    }

    public void saveOrder(OrderHeaderDto orderHeaderDto) {

        UserEntity user = userDao.findByUsername(UserService.getCurrentUser().getUsername());
        CartEntity cart = cartDao.findByUserAndIsFramedIsFalse(user);

        OrderHeaderEntity orderHeaderEntity = new OrderHeaderEntity();
        orderHeaderEntity.setCart(cart);
        orderHeaderEntity.setUser(user);
        orderHeaderEntity.setComment(orderHeaderDto.getComment());
        orderHeaderEntity.setCustomerFullname(orderHeaderDto.getCustomerFullname());
        orderHeaderEntity.setCustomerPhone(orderHeaderDto.getCustomerPhone());
        orderHeaderEntity.setCreateDate(OffsetDateTime.now());

        orderHeaderEntity.setPaymentType(PaymentType.findByDescription(orderHeaderDto.getPaymentType()));

        String address = "г. " + orderHeaderDto.getCity() +
                " ул. " + orderHeaderDto.getStreet() +
                " д. " + orderHeaderDto.getHouse() +
                " кв. " + orderHeaderDto.getApartment();
        orderHeaderEntity.setAddress(address);

        OffsetDateTime deliveryTo = LocalDate.parse(orderHeaderDto.getDeliveryTo(), formatter).atStartOfDay().atOffset(ZoneOffset.UTC);

        orderHeaderEntity.setDeliveryTo(deliveryTo);

        orderHeaderDao.save(orderHeaderEntity);

        cart.setIsFramed(true);
        cartDao.save(cart);

    }


}
