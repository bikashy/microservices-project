package com.practice.ecommerce.inventory_service.service;

import com.practice.ecommerce.inventory_service.dtos.OrderRequestDTO;
import com.practice.ecommerce.inventory_service.dtos.OrderRequestItemDTO;
import com.practice.ecommerce.inventory_service.dtos.ProductDTO;
import com.practice.ecommerce.inventory_service.entity.ProductEntity;
import com.practice.ecommerce.inventory_service.repository.ProductRepository;
import jakarta.transaction.Transactional;
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


@Transactional
    public Double reduceStocks(OrderRequestDTO orderRequestDTO) {
    log.info("reducing the stock");
    double totalPrice = 0.0;
    for (OrderRequestItemDTO orderRequestItemDTO : orderRequestDTO.getOrderItems()){
      Long productId = orderRequestItemDTO.getProductId();
      Integer quantity = orderRequestItemDTO.getQuantity();
      ProductEntity product = productRepository.findById(productId).orElseThrow(() ->new RuntimeException("product not found with id "+productId));
      if (product.getStocks() < quantity){
          throw new RuntimeException("Product can not be full filled with the given quantity ");
      }
      product.setStocks(product.getStocks()-quantity);
      productRepository.save(product);
      totalPrice = quantity * product.getPrice();
    }
     return totalPrice;
    }
}



