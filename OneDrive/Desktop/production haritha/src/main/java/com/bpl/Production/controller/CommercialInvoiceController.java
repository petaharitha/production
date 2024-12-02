package com.bpl.Production.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.bpl.Production.entity.CommercialInvoice;
import com.bpl.Production.service.CommercialInvoiceService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/commercial-invoices")
public class CommercialInvoiceController {

    @Autowired
    private CommercialInvoiceService commercialInvoiceService;

    // Create a new CommercialInvoice
    @PostMapping
    public ResponseEntity<CommercialInvoice> createCommercialInvoice(@RequestBody CommercialInvoice commercialInvoice) {
        CommercialInvoice createdInvoice = commercialInvoiceService.createCommercialInvoice(commercialInvoice);
        return new ResponseEntity<>(createdInvoice, HttpStatus.CREATED);
    }

    // Get CommercialInvoice by ID
    @GetMapping("/{id}")
    public ResponseEntity<CommercialInvoice> getCommercialInvoiceById(@PathVariable("id") Long id) {
        Optional<CommercialInvoice> invoice = commercialInvoiceService.getCommercialInvoiceById(id);
        return invoice.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    // Get all CommercialInvoices
    @GetMapping
    public List<CommercialInvoice> getAllCommercialInvoices() {
        return commercialInvoiceService.getAllCommercialInvoices();
    }

    // Update an existing CommercialInvoice
    @PutMapping("/{id}")
    public ResponseEntity<CommercialInvoice> updateCommercialInvoice(@PathVariable("id") Long id, @RequestBody CommercialInvoice commercialInvoice) {
        try {
            CommercialInvoice updatedInvoice = commercialInvoiceService.updateCommercialInvoice(id, commercialInvoice);
            return ResponseEntity.ok(updatedInvoice);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    // Delete a CommercialInvoice by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCommercialInvoice(@PathVariable("id") Long id) {
        try {
            commercialInvoiceService.deleteCommercialInvoice(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}

