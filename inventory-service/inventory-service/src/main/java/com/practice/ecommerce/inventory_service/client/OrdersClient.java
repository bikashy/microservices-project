package com.practice.ecommerce.inventory_service.client;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.data.domain.jaxb.SpringDataJaxb;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@FeignClient(name = "order-service",path = "/orders")
public interface OrdersClient {
    @GetMapping("/hello")
    public String hello();

    @GetMapping
    public ResponseEntity<List<SpringDataJaxb.OrderDto>> getAllOrder(HttpServletRequest httpServletRequest);
}
