package com.example.diploma.admin.controller;

import com.example.diploma.admin.service.CatalogService;
import com.example.diploma.persistence.dto.admin.CatalogDto;
import com.example.diploma.persistence.exeption.CatalogUrlExistsException;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/admin/catalog")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class CatalogController {

    CatalogService catalogService;

    @GetMapping("/get-all")
    public List<CatalogDto> getAllCatalogs() {
        return catalogService.getAllCatalogs();
    }

    @GetMapping("/get/{id}")
    public CatalogDto getById(@PathVariable Long id) {
        return catalogService.getById(id);
    }

    @PostMapping("/save")
    public CatalogDto save(@RequestBody CatalogDto catalogDto) {

        try {
            if (catalogDto.getId() != null) {
                return CatalogService.convertToDto(catalogService.save(catalogDto));
            }
            return CatalogService.convertToDto(catalogService.create(catalogDto));
        } catch (CatalogUrlExistsException e) {
            log.warn(e.getMessage());
            return new CatalogDto();
        }

    }

    @DeleteMapping("/delete")
    public List<CatalogDto> delete(@RequestParam("id") Long id) {
        catalogService.delete(id);
        return catalogService.getAllCatalogs();
    }

}
