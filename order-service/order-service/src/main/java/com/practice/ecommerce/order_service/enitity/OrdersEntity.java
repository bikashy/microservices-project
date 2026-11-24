package com.practice.ecommerce.order_service.enitity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
@Table(name = "orders")
@Entity
@Getter
@Setter
public class OrdersEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;

    private Double price;

    @OneToMany(mappedBy = "orders",cascade = CascadeType.ALL,orphanRemoval = true)
    private List<OrderItems> orderItems;
}
