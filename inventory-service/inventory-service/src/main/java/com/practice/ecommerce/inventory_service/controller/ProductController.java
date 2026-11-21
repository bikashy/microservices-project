package com.practice.ecommerce.inventory_service.controller;

import com.practice.ecommerce.inventory_service.dtos.ProductDTO;
import com.practice.ecommerce.inventory_service.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestClient;

import java.util.List;


@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping(path = "/products")
public class ProductController {
    private final ProductService productService;
    private final DiscoveryClient discoveryClient;
    private final RestClient restClient;

    @GetMapping(path = "/fetchOrder")
    public String fetchOrder(){
        ServiceInstance serviceInstance = discoveryClient.getInstances("order-service").getFirst();
     return restClient.get().uri(serviceInstance.getUri()+"/api/v1/orders/hello").retrieve().body(String.class);
    }

    @GetMapping
    public ResponseEntity<List<ProductDTO>> getAllInventory(){
        return ResponseEntity.ok(productService.getAllInventory());
    }

    @GetMapping(path = "/id")
    public ResponseEntity<ProductDTO> getInventoryById(@PathVariable Long id){
        return ResponseEntity.ok(productService.getProductById(id));
    }

}
