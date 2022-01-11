package com.example.diploma.persistence.dao;

import com.example.diploma.persistence.entity.SizeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SizeDao extends JpaRepository<SizeEntity, Long> {

    @Query(value = "select * from size s where s.id not in (select ps.size_id from product_size ps where ps.product_id = :productId)",
            nativeQuery = true)
    List<SizeEntity> getAvailableSizesByProduct(Long productId);
}
