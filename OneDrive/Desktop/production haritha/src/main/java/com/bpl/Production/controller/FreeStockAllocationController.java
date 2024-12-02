package com.bpl.Production.controller;

import java.util.List;
import java.util.Optional;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bpl.Production.entity.FreeStockAllocation;
import com.bpl.Production.service.FreeStockAllocationService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/free-stock-allocation")
public class FreeStockAllocationController {

	@Autowired
	private FreeStockAllocationService freeStockAllocationService;
	
	// Create operation
    @PostMapping("/create")
    public ResponseEntity<String> createFreeStockAllocation(@Valid @RequestBody FreeStockAllocation freeStockAllocation,
                                                             BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return new ResponseEntity<>("Validation Failed: " + bindingResult.getAllErrors(), HttpStatus.BAD_REQUEST);
        }
        freeStockAllocationService.createFreeStockAllocation(freeStockAllocation);
        return new ResponseEntity<>("Free Stock Allocation created successfully", HttpStatus.CREATED);
    }

    // GetById operation
    @GetMapping("/get/{id}")
    public ResponseEntity<FreeStockAllocation> getFreeStockAllocationById(@PathVariable Integer id) {
        Optional<FreeStockAllocation> freeStockAllocation = freeStockAllocationService.getFreeStockAllocationById(id);
        if (freeStockAllocation.isPresent()) {
            return new ResponseEntity<>(freeStockAllocation.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    // GetAll operation
    @GetMapping("/getAll")
    public ResponseEntity<List<FreeStockAllocation>> getAllFreeStockAllocations() {
        List<FreeStockAllocation> freeStockAllocations = freeStockAllocationService.getAllFreeStockAllocations();
        return new ResponseEntity<>(freeStockAllocations, HttpStatus.OK);
    }

    // Delete operation
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteFreeStockAllocation(@PathVariable Integer id) {
        boolean isDeleted = freeStockAllocationService.deleteFreeStockAllocation(id);
        if (isDeleted) {
            return new ResponseEntity<>("Free Stock Allocation deleted successfully", HttpStatus.OK);
        }
        return new ResponseEntity<>("Free Stock Allocation not found", HttpStatus.NOT_FOUND);
    }

    // Update operation
    @PutMapping("/update/{id}")
    public ResponseEntity<FreeStockAllocation> updateFreeStockAllocation(
            @PathVariable Integer id, @Valid @RequestBody FreeStockAllocation updatedFreeStockAllocation, 
            BindingResult bindingResult) {

//        if (bindingResult.hasErrors()) {
//            return new ResponseEntity<>("Validation Failed: " + bindingResult.getAllErrors(), HttpStatus.BAD_REQUEST);
//        }

        FreeStockAllocation updated = freeStockAllocationService.updateFreeStockAllocation(id, updatedFreeStockAllocation);

        if (updated != null) {
            return new ResponseEntity<>(updated, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    
    @GetMapping("getAllocationsByOrderNo")
    public ResponseEntity<List<FreeStockAllocation>> getAllocationsByOrderNo(@RequestParam String orderNo) {
        List<FreeStockAllocation> allocations = freeStockAllocationService.getAllocationsByOrderNo(orderNo);
        return ResponseEntity.ok(allocations);
    }
}
