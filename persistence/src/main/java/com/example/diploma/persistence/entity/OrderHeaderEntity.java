package com.example.diploma.persistence.entity;

import com.example.diploma.persistence.enums.PaymentType;
import com.example.diploma.persistence.enums.Role;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.time.OffsetDateTime;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "order_header")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class OrderHeaderEntity {

    @Id
    @Column(name = "id")
    @GeneratedValue(generator = "order_header_id_seq")
    Long id;

    @Column(name = "create_date")
    OffsetDateTime createDate;

    @Column(name = "delivery_to")
    OffsetDateTime deliveryTo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    UserEntity user;

    @Enumerated(EnumType.STRING)
    @Column(name = "payment_type")
    PaymentType paymentType;

    @Column(name = "address")
    String address;

    @Column(name = "customer_fullname")
    String customerFullname;

    @Column(name = "customer_phone")
    String customerPhone;

    @Column(name = "comment")
    String comment;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cart_id")
    CartEntity cart;

}
