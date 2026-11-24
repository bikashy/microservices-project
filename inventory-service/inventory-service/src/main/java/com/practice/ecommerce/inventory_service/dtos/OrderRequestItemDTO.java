package com.practice.ecommerce.inventory_service.dtos;

import lombok.Data;

@Data
public class OrderRequestItemDTO {
    private Long productId;
    private Integer quantity;
}
