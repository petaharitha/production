package com.bpl.Production.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
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

import com.bpl.Production.entity.FgStock;
import com.bpl.Production.service.FgStockService;

@RestController
@RequestMapping("/api/fg-stock")
public class FgStockController {

	@Autowired
	private FgStockService fgStockService;
	
	// Create \FgStock
    @PostMapping
    public ResponseEntity<FgStock> createFgStock(@RequestBody FgStock fgStock) {
        FgStock savedFgStock = fgStockService.createFgStock(fgStock);
        return new ResponseEntity<>(savedFgStock, HttpStatus.CREATED);
    }

    // Get FgStock by ID
    @GetMapping("/{id}")
    public ResponseEntity<FgStock> getFgStockById(@PathVariable Integer id) {
        Optional<FgStock> fgStockOptional = fgStockService.getFgStockById(id);
        return fgStockOptional.map(fgStock -> new ResponseEntity<>(fgStock, HttpStatus.OK))
                              .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // Get All FgStocks
    @GetMapping
    public ResponseEntity<List<FgStock>> getAllFgStocks() {
        List<FgStock> fgStockList = fgStockService.getAllFgStocks();
        return new ResponseEntity<>(fgStockList, HttpStatus.OK);
    }

    // Delete FgStock by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFgStock(@PathVariable Integer id) {
        fgStockService.deleteFgStock(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    // Update FgStock by ID
    @PutMapping("/{id}")
    public ResponseEntity<FgStock> updateFgStock(@PathVariable Integer id, @RequestBody FgStock updatedFgStock) {
        FgStock updated = fgStockService.updateFgStock(id, updatedFgStock);
        return updated != null ? new ResponseEntity<>(updated, HttpStatus.OK)
                              : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
