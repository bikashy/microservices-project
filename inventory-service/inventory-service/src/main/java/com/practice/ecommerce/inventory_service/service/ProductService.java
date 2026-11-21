package com.practice.ecommerce.inventory_service.service;

import com.practice.ecommerce.inventory_service.dtos.ProductDTO;
import com.practice.ecommerce.inventory_service.entity.ProductEntity;
import com.practice.ecommerce.inventory_service.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;
    private final ModelMapper modelMapper;


public List<ProductDTO> getAllInventory(){
    log.info("fetching all inventory");
    List<ProductEntity> productEntities = productRepository.findAll();
    return productEntities.stream().map(productEntity -> modelMapper.map(productEntity,ProductDTO.class))
            .collect(Collectors.toList());
}

public ProductDTO getProductById(Long productId){
    log.info("fetching details about the product id : {}",productId);
    Optional<ProductEntity> inventory = productRepository.findById(productId);
    return inventory.map(productEntity -> modelMapper.map(productEntity,ProductDTO.class))
            .orElseThrow(()->new RuntimeException("Inventory service not found"));

}


}



