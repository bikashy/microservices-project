package com.practice.ecommerce.order_service.dtos;

import lombok.Data;

@Data
public class ProductDTO {
    private Long id;

    private String name;

    private Double price;

    private Integer stock;
}
