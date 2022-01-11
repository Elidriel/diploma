package com.example.diploma.persistence.dao;

import com.example.diploma.persistence.entity.CartProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartProductDao extends JpaRepository<CartProductEntity, CartProductEntity.CartProductId> {

}
