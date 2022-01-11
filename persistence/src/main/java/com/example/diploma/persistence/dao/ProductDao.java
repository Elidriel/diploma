package com.example.diploma.persistence.dao;

import com.example.diploma.persistence.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductDao extends JpaRepository<ProductEntity, Long> {

    @Query("select e from ProductEntity e join CatalogEntity c on e.category.catalogEntity = c " +
            "where c.url = :catalogUrl and e.category.url = :categoryUrl")
    List<ProductEntity> findProductsByCatalogUrlAndCategoryUrl(String catalogUrl, String categoryUrl);

    @Query("select e from ProductEntity e join CatalogEntity c on e.category.catalogEntity = c " +
            "where c.url = :catalogUrl")
    List<ProductEntity> findProductsByCatalogUrl(String catalogUrl);

    @Query("select distinct e.brand from ProductEntity e")
    List<String> findAllBrands();

}
