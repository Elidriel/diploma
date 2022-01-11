package com.example.diploma.persistence.entity;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "catalog")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CatalogEntity {

    @Id
    @Column(name = "id")
    @GeneratedValue(generator = "catalog_id_seq")
    Long id;

    @Column(name ="name")
    String name;

    @Column(name = "url")
    String url;


}
