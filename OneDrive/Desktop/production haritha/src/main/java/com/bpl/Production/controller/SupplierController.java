package com.bpl.Production.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bpl.Production.entity.Supplier;
import com.bpl.Production.service.SupplierService;

@RestController
@RequestMapping("/api/suppliers")
public class SupplierController {

    @Autowired
    private SupplierService supplierService;

    // Create new supplier
    @PostMapping
    public ResponseEntity<Supplier> createSupplier(@RequestBody Supplier supplier) {
        Supplier savedSupplier = supplierService.createSupplier(supplier);
        return new ResponseEntity<>(savedSupplier, HttpStatus.CREATED);
    }

    // Update an existing supplier
    @PutMapping("/{id}")
    public ResponseEntity<Supplier> updateSupplier(@PathVariable Integer id, @RequestBody Supplier supplier) {
        Supplier updatedSupplier = supplierService.updateSupplier(id, supplier);
        if (updatedSupplier != null) {
            return new ResponseEntity<>(updatedSupplier, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND); // Supplier not found
    }

    // Delete a supplier
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSupplier(@PathVariable Integer id) {
        if (supplierService.deleteSupplier(id)) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT); // Successfully deleted
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND); // Supplier not found
    }

    // Get supplier by id
    @GetMapping("/{id}")
    public ResponseEntity<Supplier> getSupplierById(@PathVariable Integer id) {
        Optional<Supplier> supplier = supplierService.getSupplierById(id);
        if (supplier.isPresent()) {
            return new ResponseEntity<>(supplier.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND); // Supplier not found
    }

    // Get all suppliers with pagination and sorting
    @GetMapping
    public ResponseEntity<Page<Supplier>> getSuppliers(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "name") String sortBy) {
        Page<Supplier> suppliers = supplierService.getSuppliers(page, size, sortBy);
        return new ResponseEntity<>(suppliers, HttpStatus.OK);
    }

    // Get suppliers with filtering (e.g., name or contact)
    @GetMapping("/filter")
    public ResponseEntity<Page<Supplier>> getSuppliersWithFilter(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String contact,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "name") String sortBy) {
        Page<Supplier> suppliers = supplierService.getSuppliersWithFilter(name, contact, page, size, sortBy);
        return new ResponseEntity<>(suppliers, HttpStatus.OK);
    }
}
