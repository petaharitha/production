package com.bpl.Production.controller;

import org.springframework.beans.factory.annotation.Autowired;

import com.bpl.Production.entity.OrderDetailsRevision;
import com.bpl.Production.service.OrderDetailsRevisionService;

import java.util.List;
import java.util.Optional;
import org.springframework.http.HttpStatus;
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
@RequestMapping("/api/order-details-revision")
public class OrderDetailsRevisionController {

	@Autowired
	private OrderDetailsRevisionService orderDetailsRevisionService;

    // Create a new OrderDetailsRevision
    @PostMapping
    public ResponseEntity<OrderDetailsRevision> createOrderDetailsRevision(@RequestBody OrderDetailsRevision orderDetailsRevision) {
        OrderDetailsRevision createdOrderDetailsRevision = orderDetailsRevisionService.createOrderDetailsRevision(orderDetailsRevision);
        return new ResponseEntity<>(createdOrderDetailsRevision, HttpStatus.CREATED);
    }

    // Get an OrderDetailsRevision by ID
    @GetMapping("/{id}")
    public ResponseEntity<OrderDetailsRevision> getOrderDetailsRevisionById(@PathVariable Integer id) {
        Optional<OrderDetailsRevision> orderDetailsRevision = orderDetailsRevisionService.getOrderDetailsRevisionById(id);
        if (orderDetailsRevision.isPresent()) {
            return new ResponseEntity<>(orderDetailsRevision.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Get all OrderDetailsRevisions
    @GetMapping
    public ResponseEntity<List<OrderDetailsRevision>> getAllOrderDetailsRevisions() {
        List<OrderDetailsRevision> orderDetailsRevisions = orderDetailsRevisionService.getAllOrderDetailsRevisions();
        return new ResponseEntity<>(orderDetailsRevisions, HttpStatus.OK);
    }

    // Update an existing OrderDetailsRevision
    @PutMapping("/{id}")
    public ResponseEntity<OrderDetailsRevision> updateOrderDetailsRevision(
            @PathVariable Integer id,
            @RequestBody OrderDetailsRevision orderDetailsRevision) {
        try {
            OrderDetailsRevision updatedOrderDetailsRevision = orderDetailsRevisionService.updateOrderDetailsRevision(id, orderDetailsRevision);
            return new ResponseEntity<>(updatedOrderDetailsRevision, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Delete an OrderDetailsRevision
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOrderDetailsRevision(@PathVariable Integer id) {
        try {
            orderDetailsRevisionService.deleteOrderDetailsRevision(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
