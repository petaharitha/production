package com.bpl.Production.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bpl.Production.entity.OrderInfoRevision;
import com.bpl.Production.service.OrderInfoRevisionService;

@RestController
@RequestMapping("/api/order-info-revision")
public class OrderInfoRevisionController {

	@Autowired
	private OrderInfoRevisionService orderInfoRevisionService;
	
	// Create or Update OrderInfoRevision
    @PostMapping
    public ResponseEntity<OrderInfoRevision> createOrderInfoRevision(@RequestBody OrderInfoRevision orderInfoRevision) {
        OrderInfoRevision savedOrderInfoRevision = orderInfoRevisionService.createOrderInfoRevision(orderInfoRevision);
        return ResponseEntity.ok(savedOrderInfoRevision);
    }

    // Get all OrderInfoRevisions
    @GetMapping
    public List<OrderInfoRevision> getAllOrderInfoRevisions() {
        return orderInfoRevisionService.getAllOrderInfoRevisions();
    }

    // Get OrderInfoRevision by ID
    @GetMapping("/{id}")
    public ResponseEntity<OrderInfoRevision> getOrderInfoRevisionById(@PathVariable Integer id) {
        Optional<OrderInfoRevision> orderInfoRevision = orderInfoRevisionService.getOrderInfoRevisionById(id);
        return orderInfoRevision.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Delete OrderInfoRevision by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOrderInfoRevision(@PathVariable Integer id) {
        orderInfoRevisionService.deleteOrderInfoRevision(id);
        return ResponseEntity.noContent().build();
    }

    // Update OrderInfoRevision by ID
    @PutMapping("/{id}")
    public ResponseEntity<OrderInfoRevision> updateOrderInfoRevision(
        @PathVariable Integer id, 
        @RequestBody OrderInfoRevision orderInfoRevisionDetails
    ) {
        Optional<OrderInfoRevision> updatedOrderInfoRevision = orderInfoRevisionService.updateOrderInfoRevision(id, orderInfoRevisionDetails);
        return updatedOrderInfoRevision.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }
}
