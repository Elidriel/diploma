package com.example.diploma.admin.service;


import com.example.diploma.persistence.dao.CatalogDao;
import com.example.diploma.persistence.dao.CategoryDao;
import com.example.diploma.persistence.dto.admin.CategoryDto;
import com.example.diploma.persistence.entity.CategoryEntity;
import com.example.diploma.persistence.exeption.CategoryUrlExistsException;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class CategoryService {

    CatalogDao catalogDao;
    CategoryDao categoryDao;

    public List<CategoryDto> getAllCategories() {

        List<CategoryEntity> categoryEntities = categoryDao.findAll();
        List<CategoryDto> categoryDtos = new ArrayList<>();
        for (CategoryEntity categoryEntity : categoryEntities) {
            categoryDtos.add(convertToDto(categoryEntity));
        }
        return categoryDtos;
    }

    public void delete(Long id) {
        categoryDao.deleteById(id);
    }

    public CategoryEntity save(CategoryDto categoryDto) throws CategoryUrlExistsException {

        if (categoryDao.existsByUrlAndCatalogIdExcludeCurrentCategory(categoryDto.getUrl(), categoryDto.getCatalogDto().getId(), categoryDto.getId())) {
            throw new CategoryUrlExistsException(String.format("Категория с url %s уже существует", categoryDto.getUrl()));
        }

        CategoryEntity categoryEntity = categoryDao.findById(categoryDto.getId()).get();
        categoryEntity.setName(categoryDto.getName());
        categoryEntity.setUrl(categoryDto.getUrl());
        categoryEntity.setCatalogEntity(catalogDao.getOne(categoryDto.getCatalogDto().getId()));
        return categoryDao.save(categoryEntity);

    }

    public CategoryEntity create(CategoryDto categoryDto) throws CategoryUrlExistsException {

        if (categoryDao.existsByUrlAndCatalogUrl(categoryDto.getUrl(), categoryDto.getCatalogDto().getUrl())) {
            throw new CategoryUrlExistsException(String.format("Категория с url %s уже существует", categoryDto.getUrl()));
        }

        CategoryEntity categoryEntity = new CategoryEntity();
        categoryEntity.setName(categoryDto.getName());
        categoryEntity.setUrl(categoryDto.getUrl());
        categoryEntity.setCatalogEntity(catalogDao.getOne(categoryDto.getCatalogDto().getId()));
        return categoryDao.save(categoryEntity);
    }


    public static CategoryDto convertToDto(CategoryEntity categoryEntity) {

        CategoryDto categoryDto = new CategoryDto();
        categoryDto.setId(categoryEntity.getId());
        categoryDto.setName(categoryEntity.getName());
        categoryDto.setUrl(categoryEntity.getUrl());
        categoryDto.setCatalogDto(CatalogService.convertToDto(categoryEntity.getCatalogEntity()));
        return categoryDto;

    }


}
