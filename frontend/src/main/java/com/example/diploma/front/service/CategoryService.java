package com.example.diploma.front.service;

import com.example.diploma.persistence.dao.CatalogDao;
import com.example.diploma.persistence.dao.CategoryDao;
import com.example.diploma.persistence.dao.ProductDao;
import com.example.diploma.persistence.dto.front.CategoryDto;
import com.example.diploma.persistence.dto.front.ProductDto;
import com.example.diploma.persistence.entity.CatalogEntity;
import com.example.diploma.persistence.entity.CategoryEntity;
import com.example.diploma.persistence.entity.ProductEntity;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class CategoryService {

    CategoryDao categoryDao;

    public CategoryDto getCategoryByUrlAndCatalogUrl(String catalogUrl, String categoryUrl) {

        CategoryEntity selectedCategory = categoryDao.getByUrlAndCatalogUrl(categoryUrl, catalogUrl);

        if (Objects.isNull(selectedCategory)) {
            return null;
        }

        return convertToCategoryDto(selectedCategory);

    }

    public List<CategoryDto> getCategoriesByCatalogUrl(String catalogUrl) {
        return categoryDao.findByCatalogEntityUrl(catalogUrl).stream().map(this::convertToCategoryDto).collect(Collectors.toList());
    }

    public Boolean existsByUrlAndCatalogUrl(String catalogUrl, String categoryUrl) {
        return categoryDao.existsByUrlAndCatalogUrl(categoryUrl, catalogUrl);
    }

    private CategoryDto convertToCategoryDto(CategoryEntity categoryEntity) {

        CategoryDto categoryDto = new CategoryDto();
        categoryDto.setName(categoryEntity.getName());
        categoryDto.setUrl(categoryEntity.getUrl());
        categoryDto.setCatalogUrl(categoryEntity.getCatalogEntity().getUrl());
        return categoryDto;

    }
}
