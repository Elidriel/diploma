package com.example.diploma.persistence.entity;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.io.Serializable;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "cart_product")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CartProductEntity {

    public CartProductEntity(CartProductId id, Long count) {
        this.id = id;
        this.count = count;
    }

    @EmbeddedId
    CartProductId id;

    @Column(name = "count")
    Long count;

    @OneToOne
    @JoinColumn(name = "product_id", insertable = false, updatable = false)
    ProductEntity product;

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @Embeddable
    @FieldDefaults(level = AccessLevel.PRIVATE)
    public static class CartProductId implements Serializable {

        @Column(name = "cart_id")
        Long cartId;

        @Column(name = "product_id")
        Long productId;

    }

}
