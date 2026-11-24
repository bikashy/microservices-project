package com.practice.ecommerce.order_service.controller;

import com.practice.ecommerce.order_service.client.InventaryOpenFiegnClien;
import com.practice.ecommerce.order_service.dtos.OrderDTO;
import com.practice.ecommerce.order_service.dtos.OrderRequestItemDto;
import com.practice.ecommerce.order_service.service.OrdersService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping(path = "/orders")
@Slf4j
@RequiredArgsConstructor
public class OrdersController {
private final OrdersService ordersService;



@GetMapping(path = "hello")
public String orderServiceCalled(){
    return "From Order service controller";
}

@GetMapping
    public ResponseEntity<List<OrderDTO>> getAllOrder(HttpServletRequest httpServletRequest){
    log.info("fetching all order via controller");
    List<OrderDTO> orderDTOS = ordersService.getAllOrder();
    return ResponseEntity.ok(orderDTOS);
}

@GetMapping(path = "/{id}")
    public ResponseEntity<OrderDTO> getOrderById(@PathVariable Long id){
    return ResponseEntity.ok(ordersService.getOrderById(id));
}



@PostMapping(path = "/create-order")
    public OrderDTO createOrder(@RequestBody OrderDTO orderDTO){
    log.info("---------------------------------------------------------------:{}",orderDTO);
 OrderDTO orderDTO1 =  ordersService.createOrder(orderDTO);
   return orderDTO1;
}



}


