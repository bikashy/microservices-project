package com.practice.ecommerce.order_service.client;

import com.practice.ecommerce.order_service.dtos.OrderDTO;
import com.practice.ecommerce.order_service.dtos.OrderRequestItemDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "inventory-service",path = "/products")
public interface InventaryOpenFiegnClien {

    @PutMapping(path = "/reduce-stocks")
    public Double reduceStock(@RequestBody OrderDTO orderDTO);

}
