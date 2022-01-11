package com.example.diploma.admin.service;

import com.example.diploma.persistence.dao.CatalogDao;
import com.example.diploma.persistence.dto.admin.CatalogDto;
import com.example.diploma.persistence.dto.admin.CategoryDto;
import com.example.diploma.persistence.entity.CatalogEntity;
import com.example.diploma.persistence.entity.CategoryEntity;
import com.example.diploma.persistence.exeption.CatalogUrlExistsException;
import com.example.diploma.persistence.exeption.CategoryUrlExistsException;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class CatalogService {

    CatalogDao catalogDao;

    public List<CatalogDto> getAllCatalogs() {
        return catalogDao.findAll().stream().map(CatalogService::convertToDto).collect(Collectors.toList());
    }

    public CatalogDto getById(Long id) {
        return convertToDto(catalogDao.getOne(id));
    }

    public void delete(Long id) {
        catalogDao.deleteById(id);
    }

    public CatalogEntity save(CatalogDto catalogDto) throws CatalogUrlExistsException {

        if (catalogDao.existsByUrlExcludeCurrentCatalog(catalogDto.getUrl(), catalogDto.getId())) {
            throw new CatalogUrlExistsException(String.format("Каталог с url %s уже существует", catalogDto.getUrl()));
        }

       CatalogEntity catalogEntity = catalogDao.getOne(catalogDto.getId());
       catalogEntity.setName(catalogDto.getName());
       catalogEntity.setUrl(catalogDto.getUrl());
       return catalogDao.save(catalogEntity);

    }

    public CatalogEntity create(CatalogDto catalogDto) throws CatalogUrlExistsException {

        if (catalogDao.existsByUrl(catalogDto.getUrl())) {
            throw new CatalogUrlExistsException(String.format("Каталог с url %s уже существует", catalogDto.getUrl()));
        }

        CatalogEntity catalogEntity = new CatalogEntity();
        catalogEntity.setName(catalogDto.getName());
        catalogEntity.setUrl(catalogDto.getUrl());
        return catalogDao.save(catalogEntity);

    }

    public static CatalogDto convertToDto(CatalogEntity catalogEntity) {

        CatalogDto catalogDto = new CatalogDto();
        catalogDto.setId(catalogEntity.getId());
        catalogDto.setName(catalogEntity.getName());
        catalogDto.setUrl(catalogEntity.getUrl());
        return catalogDto;

    }

}
