package com.example.diploma.admin.service;

import com.example.diploma.persistence.dao.CategoryDao;
import com.example.diploma.persistence.dao.ColorDao;
import com.example.diploma.persistence.dao.ProductDao;
import com.example.diploma.persistence.dao.SizeDao;
import com.example.diploma.persistence.dto.admin.ColorDto;
import com.example.diploma.persistence.dto.admin.ProductDto;
import com.example.diploma.persistence.dto.admin.ProductShortDto;
import com.example.diploma.persistence.dto.admin.SizeDto;
import com.example.diploma.persistence.entity.ColorEntity;
import com.example.diploma.persistence.entity.ProductEntity;
import com.example.diploma.persistence.entity.SizeEntity;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@Transactional
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ProductService {

    CategoryDao categoryDao;
    ProductDao productDao;
    ColorDao colorDao;
    SizeDao sizeDao;

    public List<ProductShortDto> getAllProducts() {

        return StreamSupport.stream(productDao.findAll().spliterator(), false).collect(Collectors.toList())
                .stream()
                .map(ProductService::convertToShortDto)
                .collect(Collectors.toList());

    }

    public List<ColorDto> getAvailableColorsByProduct(Long productId) {

        return colorDao.getAvailableColorsByProduct(productId)
                .stream()
                .map(colorEntity -> new ColorDto(colorEntity.getId(), colorEntity.getValue()))
                .collect(Collectors.toList());

    }

    public List<ColorDto> getAllColors() {

        return StreamSupport.stream(colorDao.findAll().spliterator(), false).collect(Collectors.toList())
                .stream()
                .map(colorEntity -> new ColorDto(colorEntity.getId(), colorEntity.getValue()))
                .collect(Collectors.toList());

    }

    public List<SizeDto> getAvailableSizesByProduct(Long productId) {

        return sizeDao.getAvailableSizesByProduct(productId)
                .stream()
                .map(sizeEntity -> new SizeDto(sizeEntity.getId(), sizeEntity.getValue()))
                .collect(Collectors.toList());

    }

    public List<SizeDto> getAllSizes() {

        return StreamSupport.stream(sizeDao.findAll().spliterator(), false).collect(Collectors.toList())
                .stream()
                .map(sizeEntity -> new SizeDto(sizeEntity.getId(), sizeEntity.getValue()))
                .collect(Collectors.toList());

    }

    public ProductDto getProduct(Long id) {

        ProductEntity productEntity = productDao
                .findById(id)
                .orElseThrow(() -> new RuntimeException("Продукт не найден"));
        return convertToDto(productEntity);

    }

    public ProductEntity save(ProductDto productDto) {

        ProductEntity productEntity = productDao
                .findById(productDto.getId())
                .orElseThrow(() -> new RuntimeException("Продукт не найден"));
        productEntity.setBrand(productDto.getBrand());
        productEntity.setName(productDto.getName());
        productEntity.setPrice(productDto.getPrice());
        productEntity.setImageName(productDto.getImageName());

        productEntity.setColors(productDto.getColors()
                .stream()
                .map(colorDto -> colorDao.findById(colorDto.getId()).get())
                .collect(Collectors.toList()));

        productEntity.setSizes(productDto.getSizes()
                .stream()
                .map(sizeDto -> sizeDao.findById(sizeDto.getId()).get())
                .collect(Collectors.toList())
        );

        productEntity.setCategory(categoryDao.findById(productDto.getCategory().getId()).get());

        return productDao.save(productEntity);

    }

    public ProductEntity create(ProductDto productDto) {
        ProductEntity productEntity = new ProductEntity();
        productEntity.setName(productDto.getName());
        productEntity.setBrand(productDto.getBrand());
        productEntity.setPrice(productDto.getPrice());
        productEntity.setImageName(productDto.getImageName());

        List<ColorEntity> colorEntities = new ArrayList<>();
        for (ColorDto colorDto : productDto.getColors()) {
            ColorEntity colorEntity = colorDao.findById(colorDto.getId()).get();
            colorEntities.add(colorEntity);
        }
        productEntity.setColors(colorEntities);

        List<SizeEntity> sizeEntities = new ArrayList<>();
        for (SizeDto sizeDto : productDto.getSizes()) {
            SizeEntity sizeEntity = sizeDao.findById(sizeDto.getId()).get();
            sizeEntities.add(sizeEntity);
        }
        productEntity.setSizes(sizeEntities);

        productEntity.setCategory(categoryDao.findById(productDto.getCategory().getId()).get());

        return productDao.save(productEntity);
    }

    public void delete(Long id) {
        productDao.deleteById(id);
    }

    public static ProductDto convertToDto(ProductEntity productEntity) {

        ProductDto productDto = new ProductDto();
        productDto.setId(productEntity.getId());
        productDto.setBrand(productEntity.getBrand());
        productDto.setCategory(CategoryService.convertToDto(productEntity.getCategory()));
        productDto.setImageName(productEntity.getImageName());
        productDto.setColors(
                productEntity.getColors()
                        .stream()
                        .map(colorEntity -> new ColorDto(colorEntity.getId(), colorEntity.getValue()))
                        .collect(Collectors.toList())
        );
        productDto.setName(productEntity.getName());
        productDto.setSizes(
                productEntity.getSizes()
                        .stream()
                        .map(sizeEntity -> new SizeDto(sizeEntity.getId(), sizeEntity.getValue()))
                        .collect(Collectors.toList())
        );
        productDto.setPrice(productEntity.getPrice());
        return productDto;

    }

    public static ProductShortDto convertToShortDto(ProductEntity productEntity) {

        ProductShortDto productShortDto = new ProductShortDto();
        productShortDto.setId(productEntity.getId());
        productShortDto.setBrand(productEntity.getBrand());
        productShortDto.setCategoryName(productEntity.getCategory().getName());
        productShortDto.setName(productEntity.getName());
        return productShortDto;

    }
}
