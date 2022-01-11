package com.example.diploma.persistence.dto.front;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.time.OffsetDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class OrderHistoryDto {

    Long id;
    OffsetDateTime dateCreate;
    OffsetDateTime deliveryTo;
    String address;
    CartDto cartDto;

}
