package com.example.diploma.persistence.dao;

import com.example.diploma.persistence.entity.CartEntity;
import com.example.diploma.persistence.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartDao extends JpaRepository<CartEntity, Long> {

    CartEntity findByUserAndIsFramedIsFalse(UserEntity user);

}
