package com.practice.ecommerce.order_service.service;

import com.practice.ecommerce.order_service.dtos.OrderDTO;
import com.practice.ecommerce.order_service.dtos.OrderRequestItemDto;
import com.practice.ecommerce.order_service.enitity.OrdersEntity;
import com.practice.ecommerce.order_service.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrdersService {
    private final OrderRepository orderRepository;
    private final ModelMapper modelMapper;

    public List<OrderDTO> getAllOrder(){
        log.info("fetching all order");
        List<OrdersEntity> ordersEntities = orderRepository.findAll();
        return ordersEntities.stream().map(ordersEntity -> modelMapper.map(ordersEntity, OrderDTO.class))
                .collect(Collectors.toList());
    }

    public OrderDTO getOrderById(Long  id){
        log.info("fetching order of id :{}",id);
        OrdersEntity ordersEntity  = orderRepository.findById(id).orElseThrow(()->new RuntimeException("Order not found"));
        return modelMapper.map(ordersEntity, OrderDTO.class);

    }
}
