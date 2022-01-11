package com.example.diploma.persistence.dao;

import com.example.diploma.persistence.entity.ColorEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ColorDao extends JpaRepository<ColorEntity, Long> {

    @Query(value = "select * from color c where c.id not in (select pc.color_id from product_color pc where pc.product_id = :productId)",
            nativeQuery = true)
    List<ColorEntity> getAvailableColorsByProduct(Long productId);
}
