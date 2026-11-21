package com.practice.ecommerce.order_service.dtos;

import lombok.*;

import java.util.List;


@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderDTO {
    private Long id;
    private List<OrderRequestItemDto> orderItems;

    private Integer price;
}
