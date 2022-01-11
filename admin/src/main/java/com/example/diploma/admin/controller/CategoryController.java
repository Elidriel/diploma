package com.example.diploma.admin.controller;

import com.example.diploma.admin.service.CategoryService;
import com.example.diploma.persistence.dto.admin.CategoryDto;
import com.example.diploma.persistence.exeption.CategoryUrlExistsException;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/admin/category")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class CategoryController {

    CategoryService categoryService;

    @GetMapping("/get-all")
    public List<CategoryDto> getAllCategories() {
        return categoryService.getAllCategories();
    }

    @PostMapping("/save")
    public CategoryDto save(@RequestBody CategoryDto categoryDto) {

        try {
            if (categoryDto.getId() != null) {
                return CategoryService.convertToDto(categoryService.save(categoryDto));
            }
            return CategoryService.convertToDto(categoryService.create(categoryDto));
        } catch (CategoryUrlExistsException e) {
            log.warn(e.getMessage());
            return new CategoryDto();
        }

    }

    @DeleteMapping("/delete")
    public List<CategoryDto> delete(@RequestParam("id") Long id) {
        categoryService.delete(id);
        return categoryService.getAllCategories();
    }
}
