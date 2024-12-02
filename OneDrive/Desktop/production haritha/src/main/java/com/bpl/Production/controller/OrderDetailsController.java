package com.bpl.Production.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bpl.Production.entity.OrderDetails;
import com.bpl.Production.service.OrderDetailsService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/order-details")
public class OrderDetailsController {

	@Autowired
	private OrderDetailsService orderDetailsService;
	
	// Create OrderDetails
    @PostMapping
    public ResponseEntity<OrderDetails> createOrderDetails(@Valid @RequestBody OrderDetails orderDetails, BindingResult result) {
        if (result.hasErrors()) {
            // You can customize the error response if needed
            return ResponseEntity.badRequest().body(null); // Return bad request if validation fails
        }
        OrderDetails savedOrderDetails = orderDetailsService.saveOrderDetails(orderDetails);
        return new ResponseEntity<>(savedOrderDetails, HttpStatus.CREATED);
    }

    // Get OrderDetails by ID
    @GetMapping("/{id}")
    public ResponseEntity<OrderDetails> getOrderDetailsById(@PathVariable Integer id) {
        OrderDetails orderDetails = orderDetailsService.getOrderDetailsById(id);
        if (orderDetails == null) {
            return ResponseEntity.notFound().build(); // Return 404 if not found
        }
        return ResponseEntity.ok(orderDetails);
    }

    // Get All OrderDetails
    @GetMapping
    public ResponseEntity<List<OrderDetails>> getAllOrderDetails() {
        List<OrderDetails> orderDetailsList = orderDetailsService.getAllOrderDetails();
        return ResponseEntity.ok(orderDetailsList);
    }

    // Update OrderDetails
    @PutMapping("/{id}")
    public ResponseEntity<OrderDetails> updateOrderDetails(@PathVariable Integer id, @Valid @RequestBody OrderDetails orderDetails, BindingResult result) {
        if (result.hasErrors()) {
            return ResponseEntity.badRequest().body(null); // Return bad request if validation fails
        }
        OrderDetails updatedOrderDetails = orderDetailsService.updateOrderDetails(id, orderDetails);
        if (updatedOrderDetails == null) {
            return ResponseEntity.notFound().build(); // Return 404 if not found for update
        }
        return ResponseEntity.ok(updatedOrderDetails);
    }

    // Delete OrderDetails
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOrderDetails(@PathVariable Integer id) {
        boolean deleted = orderDetailsService.deleteOrderDetails(id);
        if (!deleted) {
            return ResponseEntity.notFound().build(); // Return 404 if not found to delete
        }
        return ResponseEntity.noContent().build(); // Return 204 for successful deletion
    }
}
