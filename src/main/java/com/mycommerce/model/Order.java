package com.mycommerce.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.mycommerce.enums.StatusOrder;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "date_order")
    private LocalDateTime dateOrder;

    @Column(name = "delivery_adress")
    private String deliveryAdress;

    @Column(name = "number_order")
    private String numberOrder;

    @Column(name = "fee_delivery")
    private BigDecimal feeDelivery;

    @Column(name = "total_value")
    private BigDecimal totalValue;

    @Enumerated(EnumType.STRING)
    private StatusOrder status;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "restaurant_id")
    private Restaurant restaurant;

    @JsonIgnore
    @OneToMany(mappedBy = "order")
    private List<ItemOrder> itemOrders = new ArrayList<>();

}
