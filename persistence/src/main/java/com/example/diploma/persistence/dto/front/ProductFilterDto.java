package com.example.diploma.persistence.dto.front;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProductFilterDto {

    Double startingPrice;
    Double finalPrice;
    List<Integer> sizes;
    List<String> colors;
    List<String> brands;

}
