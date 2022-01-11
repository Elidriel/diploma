package com.example.diploma.front.service;

import com.example.diploma.persistence.dao.CatalogDao;
import com.example.diploma.persistence.dto.front.CatalogDto;
import com.example.diploma.persistence.entity.CatalogEntity;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class CatalogService {

    CatalogDao catalogDao;

    public List<CatalogDto> getAlLCatalogs() {
        return catalogDao.findAll().stream().map(this::convertToDto).collect(Collectors.toList());
    }

    public CatalogDto getCatalogByUrl(String url) {
        return convertToDto(catalogDao.findByUrl(url));
    }

    public Boolean existsByUrl(String catalogUrl) {
        return catalogDao.existsByUrl(catalogUrl);
    }

    private CatalogDto convertToDto(CatalogEntity catalogEntity) {
        CatalogDto catalogDto = new CatalogDto();
        catalogDto.setName(catalogEntity.getName());
        catalogDto.setUrl(catalogEntity.getUrl());
        return catalogDto;
    }
}
