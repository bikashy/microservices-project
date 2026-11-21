package com.practice.ecommerce.order_service.dtos;


import lombok.*;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderRequestItemDto {
    private Long id;
    private Long productId;
    private Integer quantity;
}
