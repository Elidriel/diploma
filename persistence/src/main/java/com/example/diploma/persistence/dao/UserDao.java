package com.example.diploma.persistence.dao;

import com.example.diploma.persistence.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UserDao extends JpaRepository<UserEntity, Long> {

    UserEntity findByUsername(String username);

}
