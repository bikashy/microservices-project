package com.practice.ecommerce.order_service.service;


import com.practice.ecommerce.order_service.client.InventaryOpenFiegnClien;
import com.practice.ecommerce.order_service.dtos.OrderDTO;
import com.practice.ecommerce.order_service.dtos.OrderRequestItemDto;
import com.practice.ecommerce.order_service.enitity.OrderItems;
import com.practice.ecommerce.order_service.enitity.OrderStatus;
import com.practice.ecommerce.order_service.enitity.OrdersEntity;
import com.practice.ecommerce.order_service.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.resilience.annotation.Retryable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrdersService {
    private final OrderRepository orderRepository;
    private final ModelMapper modelMapper;
    private final InventaryOpenFiegnClien inventaryOpenFiegnClien;

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

//    public OrderDTO createOrder(OrderDTO orderDTO) {
//        Double totalPrice = inventaryOpenFiegnClien.reduceStock(orderDTO);
//        OrdersEntity orders = modelMapper.map(orderDTO,OrdersEntity.class);
//        for (OrderItems orderItems:orders.getOrderItems()){
//            orderItems.setOrders(orders);
//        }
//        orders.setPrice(totalPrice);
//        orders.setOrderStatus(OrderStatus.CONFORMED);
//
//        OrdersEntity orders1 = orderRepository.save(orders);
//        return modelMapper.map(orders1,OrderDTO.class);
//    }



    @Retry
    public OrderDTO createOrder(OrderDTO orderDTO) {

        Double totalPrice = inventaryOpenFiegnClien.reduceStock(orderDTO);

        OrdersEntity orders = new OrdersEntity();

        orders.setPrice(totalPrice);
        orders.setOrderStatus(OrderStatus.CONFORMED);

        List<OrderItems> orderItems = orderDTO.getOrderItems()
                .stream()
                .map(itemDto -> {
                    OrderItems item = new OrderItems();

                    item.setProductId(itemDto.getProductId());
                    item.setQuantity(itemDto.getQuantity());

                    item.setOrders(orders);

                    return item;
                })
                .collect(Collectors.toList());

        orders.setOrderItems(orderItems);

        OrdersEntity savedOrder = orderRepository.save(orders);

        return modelMapper.map(savedOrder, OrderDTO.class);
    }


}
