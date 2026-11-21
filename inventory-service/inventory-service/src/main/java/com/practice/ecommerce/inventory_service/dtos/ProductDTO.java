package com.practice.ecommerce.inventory_service.dtos;

import lombok.Data;

@Data
public class ProductDTO {
    private Long id;

    private String title;
    private Integer price;

    private String stocks;
}
