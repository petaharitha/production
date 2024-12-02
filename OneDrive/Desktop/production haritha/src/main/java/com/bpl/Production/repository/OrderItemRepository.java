package com.bpl.Production.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bpl.Production.entity.OrderItem;

public interface OrderItemRepository extends JpaRepository<OrderItem, Integer>{

}
