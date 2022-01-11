package com.example.diploma.persistence.dto.admin;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProductDto {

    Long id;
    String name;
    String brand;
    Double price;
    List<SizeDto> sizes;
    List<ColorDto> colors;
    String imageName;
    CategoryDto category;
}
