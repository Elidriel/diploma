package com.example.diploma.persistence.dao;

import com.example.diploma.persistence.entity.OrderHeaderEntity;
import com.example.diploma.persistence.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderHeaderDao extends JpaRepository<OrderHeaderEntity, Long> {

    List<OrderHeaderEntity> findByUser(UserEntity userEntity);

}
