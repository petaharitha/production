package com.bpl.Production.controller;

import org.springframework.beans.factory.annotation.Autowired;

import com.bpl.Production.entity.OrderInfo;
import com.bpl.Production.service.OrderInfoService;

import java.util.List;
import java.util.Optional;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/order-info")
public class OrderInfoController {

	@Autowired
	private OrderInfoService orderInfoService;
	
	// Create a new OrderInfo
    @PostMapping
    public ResponseEntity<OrderInfo> createOrderInfo(@RequestBody OrderInfo orderInfo) {
        OrderInfo createdOrderInfo = orderInfoService.createOrderInfo(orderInfo);
        return ResponseEntity.ok(createdOrderInfo);
    }

    // Get all OrderInfo records
    @GetMapping
    public ResponseEntity<List<OrderInfo>> getAllOrderInfo() {
        List<OrderInfo> orderInfoList = orderInfoService.getAllOrderInfo();
        return ResponseEntity.ok(orderInfoList);
    }

    // Get OrderInfo by ID
    @GetMapping("/{id}")
    public ResponseEntity<OrderInfo> getOrderInfoById(@PathVariable Integer id) {
        Optional<OrderInfo> orderInfoOptional = orderInfoService.getOrderInfoById(id);
        if (orderInfoOptional.isPresent()) {
            return ResponseEntity.ok(orderInfoOptional.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Update an OrderInfo
    @PutMapping("/{id}")
    public ResponseEntity<OrderInfo> updateOrderInfo(@PathVariable Integer id, @RequestBody OrderInfo orderInfoDetails) {
        OrderInfo updatedOrderInfo = orderInfoService.updateOrderInfo(id, orderInfoDetails);
        if (updatedOrderInfo != null) {
            return ResponseEntity.ok(updatedOrderInfo);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Delete an OrderInfo
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOrderInfo(@PathVariable Integer id) {
        orderInfoService.deleteOrderInfo(id);
        return ResponseEntity.noContent().build();
    }
}
