package com.bpl.Production.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bpl.Production.entity.SalesUnit;
import com.bpl.Production.service.SalesUnitService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/sales-units")
@Validated
public class SalesUnitController {

    @Autowired
    private SalesUnitService salesUnitService;

    // Create SalesUnit
    @PostMapping
    public ResponseEntity<SalesUnit> createSalesUnit(@Valid @RequestBody SalesUnit salesUnit) {
        SalesUnit createdSalesUnit = salesUnitService.createSalesUnit(salesUnit);
        return new ResponseEntity<>(createdSalesUnit, HttpStatus.CREATED);
    }

    // Update SalesUnit
    @PutMapping("/{id}")
    public ResponseEntity<SalesUnit> updateSalesUnit(@PathVariable Integer id, @Valid @RequestBody SalesUnit updatedSalesUnit) {
        SalesUnit updated = salesUnitService.updateSalesUnit(id, updatedSalesUnit);
        return new ResponseEntity<>(updated, HttpStatus.OK);
    }

    // Edit SalesUnit (same as update, but semantically can be used for partial updates if needed)
    @PatchMapping("/{id}")
    public ResponseEntity<SalesUnit> editSalesUnit(@PathVariable Integer id, @Valid @RequestBody SalesUnit editedSalesUnit) {
        SalesUnit updated = salesUnitService.editSalesUnit(id, editedSalesUnit);
        return new ResponseEntity<>(updated, HttpStatus.OK);
    }

    // Delete SalesUnit
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSalesUnit(@PathVariable Integer id) {
        salesUnitService.deleteSalesUnit(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    // Get SalesUnit by ID
    @GetMapping("/{id}")
    public ResponseEntity<SalesUnit> getSalesUnitById(@PathVariable Integer id) {
        Optional<SalesUnit> salesUnit = salesUnitService.getSalesUnitById(id);
        return salesUnit.map(ResponseEntity::ok).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // Get all SalesUnits with pagination (no filters)
    @GetMapping
    public ResponseEntity<Page<SalesUnit>> getAllSalesUnitsWithPagination(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "true") boolean ascending) {
        Page<SalesUnit> salesUnits = salesUnitService.getAllSalesUnitsWithPagination(page, size, sortBy, ascending);
        return new ResponseEntity<>(salesUnits, HttpStatus.OK);
    }

    // Get SalesUnits with pagination and optional filters for VE Code and VE Description
    @GetMapping("/filter")
    public ResponseEntity<Page<SalesUnit>> getSalesUnitsWithPaginationAndFilter(
            @RequestParam(required = false) String veCode,
            @RequestParam(required = false) String veDescription,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "true") boolean ascending) {
        Page<SalesUnit> salesUnits = salesUnitService.getSalesUnitsWithPaginationAndFilter(veCode, veDescription, page, size, sortBy, ascending);
        return new ResponseEntity<>(salesUnits, HttpStatus.OK);
    }
}
