package com.example.diploma.persistence.entity;


import com.example.diploma.persistence.enums.Role;
import com.example.diploma.persistence.enums.Status;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Table;
import java.util.List;
import java.util.Set;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserEntity {

    @Id
    @Column(name = "id")
    @GeneratedValue(generator = "users_id_seq")
    Long id;

    @Column(name = "fullname")
    String fullName;

    @Column(name = "password")
    String password;

    @Column(name = "city")
    String city;

    @Column(name = "country")
    String country;

    @Column(name = "phone_number")
    String phoneNumber;

    @Column(name = "username")
    String username;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    Status status;

    @ElementCollection(targetClass = Role.class, fetch = FetchType.EAGER)
    @CollectionTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"))
    @Enumerated(EnumType.STRING)
    @Column(name = "role")
    List<Role> roles;

}
