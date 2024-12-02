package com.bpl.Production.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bpl.Production.entity.OrderDetails;

public interface OrderDetailsRepository extends JpaRepository<OrderDetails, Integer>{

}
