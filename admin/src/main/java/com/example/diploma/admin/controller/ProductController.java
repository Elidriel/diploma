package com.example.diploma.admin.controller;

import com.example.diploma.admin.service.ProductService;
import com.example.diploma.persistence.dto.admin.ColorDto;
import com.example.diploma.persistence.dto.admin.ProductDto;
import com.example.diploma.persistence.dto.admin.ProductShortDto;
import com.example.diploma.persistence.dto.admin.SizeDto;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/admin/product")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ProductController {

    ProductService productService;

    @GetMapping("/get-all")
    public List<ProductShortDto> getAllProducts() {
        return productService.getAllProducts();
    }

    @GetMapping("/get")
    public ProductDto getProduct(@RequestParam("id") Long id) {
        return productService.getProduct(id);
    }

    @GetMapping("/get-available-colors")
    public List<ColorDto> getAvailableColors(@RequestParam(value = "product-id", required = false) Long productId) {
        if (productId != null) {
            return productService.getAvailableColorsByProduct(productId);
        }
        return productService.getAllColors();
    }

    @GetMapping("/get-available-sizes")
    public List<SizeDto> getAvailableSizes(@RequestParam(value = "product-id", required = false) Long productId) {
        if (productId != null) {
            return productService.getAvailableSizesByProduct(productId);
        }
        return productService.getAllSizes();
    }

    @PostMapping("/save")
    public ProductDto save(@RequestBody ProductDto productDto) {

        if(productDto.getId() != null) {
            return ProductService.convertToDto(productService.save(productDto));
        }
        return ProductService.convertToDto(productService.create(productDto));
    }

    @DeleteMapping("/delete")
    public List<ProductShortDto> delete(@RequestParam("id") Long id) {
        productService.delete(id);
        return productService.getAllProducts();
    }

}
