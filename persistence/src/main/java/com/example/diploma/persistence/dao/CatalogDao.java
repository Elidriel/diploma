package com.example.diploma.persistence.dao;

import com.example.diploma.persistence.entity.CatalogEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CatalogDao extends JpaRepository<CatalogEntity, Long> {

    CatalogEntity findByUrl(String url);

    @Query("select case when count(e) > 0 then TRUE else FALSE end from CatalogEntity e where e.url = :url and e.id <> :id")
    Boolean existsByUrlExcludeCurrentCatalog(String url, Long id);

    Boolean existsByUrl(String url);

}
