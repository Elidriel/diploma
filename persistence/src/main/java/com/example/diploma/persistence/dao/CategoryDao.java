package com.example.diploma.persistence.dao;

import com.example.diploma.persistence.entity.CatalogEntity;
import com.example.diploma.persistence.entity.CategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryDao extends JpaRepository<CategoryEntity, Long> {

    @Query("select case when count(e) > 0 then TRUE else FALSE end from CategoryEntity e where e.url = :url and e.catalogEntity.id <> :catalogId and e.id <> :id")
    Boolean existsByUrlAndCatalogIdExcludeCurrentCategory(String url, Long catalogId, Long id);

    @Query("select case when count(e) > 0 then TRUE else FALSE end from CategoryEntity e where e.url = :url and e.catalogEntity.url = :catalogUrl")
    Boolean existsByUrlAndCatalogUrl(String url, String catalogUrl);

    @Query("select e from CategoryEntity e where e.url = :url and e.catalogEntity.url = :catalogUrl")
    CategoryEntity getByUrlAndCatalogUrl(String url, String catalogUrl);

    List<CategoryEntity> findByCatalogEntityUrl(String catalogUrl);

}
