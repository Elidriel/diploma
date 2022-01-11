package com.example.diploma.persistence.dto.front;

import com.example.diploma.persistence.enums.PaymentType;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class OrderHeaderDto {

    String city;
    String street;
    String house;
    String apartment;
    String comment;
    String paymentType;
    String customerFullname;
    String customerPhone;
    String deliveryTo;

}
