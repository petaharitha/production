package com.bpl.Production.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bpl.Production.entity.FreeStock;
import com.bpl.Production.service.FreeStockService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/free-stock")
public class FreeStockController {

	 @Autowired
	    private FreeStockService freeStockService;

	    // Create FreeStock
	    @PostMapping
	    public ResponseEntity<FreeStock> createFreeStock(@Valid @RequestBody FreeStock freeStock) {
	        FreeStock createdFreeStock = freeStockService.createFreeStock(freeStock);
	        return new ResponseEntity<>(createdFreeStock, HttpStatus.CREATED);
	    }

	    // Get FreeStock by ID
	    @GetMapping("/{id}")
	    public ResponseEntity<FreeStock> getFreeStockById(@PathVariable Integer id) {
	        Optional<FreeStock> freeStock = freeStockService.getFreeStockById(id);
	        if (freeStock.isPresent()) {
	            return new ResponseEntity<>(freeStock.get(), HttpStatus.OK);
	        } else {
	            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	        }
	    }

	    // Get all FreeStocks
	    @GetMapping
	    public ResponseEntity<List<FreeStock>> getAllFreeStocks() {
	        List<FreeStock> freeStocks = freeStockService.getAllFreeStocks();
	        return new ResponseEntity<>(freeStocks, HttpStatus.OK);
	    }

	    // Delete FreeStock by ID
	    @DeleteMapping("/{id}")
	    public ResponseEntity<Void> deleteFreeStock(@PathVariable Integer id) {
	        Optional<FreeStock> freeStock = freeStockService.getFreeStockById(id);
	        if (freeStock.isPresent()) {
	            freeStockService.deleteFreeStock(id);
	            return new ResponseEntity<>(HttpStatus.NO_CONTENT); // Successful deletion
	        } else {
	            return new ResponseEntity<>(HttpStatus.NOT_FOUND); // Not found
	        }
	    }

	    // Update FreeStock by ID
	    @PutMapping("/{id}")
	    public ResponseEntity<FreeStock> updateFreeStock(@PathVariable Integer id, @Valid @RequestBody FreeStock freeStock) {
	        FreeStock updatedFreeStock = freeStockService.updateFreeStock(id, freeStock);
	        if (updatedFreeStock != null) {
	            return new ResponseEntity<>(updatedFreeStock, HttpStatus.OK);
	        } else {
	            return new ResponseEntity<>(HttpStatus.NOT_FOUND);  // If FreeStock not found
	        }
	    }
	    @GetMapping("/free-stock")
	    public String getFreeStock(@RequestParam(required = false) String orderNo, Model model) {
	        List<FreeStock> freeStockList = freeStockService.getFreeStocks(orderNo);
	       

	        model.addAttribute("freeStockList", freeStockList);
	       
	        return "free-stock"; // Thymeleaf template name
	    }
}
