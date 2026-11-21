package com.practice.ecommerce.order_service.repository;

import com.practice.ecommerce.order_service.dtos.OrderDTO;
import com.practice.ecommerce.order_service.enitity.OrdersEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<OrdersEntity,Long> {
}
