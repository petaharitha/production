package com.supplier.controller;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import com.supplier.entity.PurchasingRFQ;
import com.supplier.repository.PurchasingRFQRepository;
import com.supplier.service.PurchasingRFQExportService;
import com.supplier.service.PurchasingRFQPdfService;
import com.supplier.service.PurchasingRFQService;

import jakarta.servlet.ServletOutputStream;

@RestController
@RequestMapping("/api/vendors/purchasing-rfq")
public class PurchasingRFQController {

    @Autowired
    private PurchasingRFQService purchasingRFQService;
    
    @Autowired
    private PurchasingRFQRepository purchasingRFQRepository;
    
    @Autowired
    private PurchasingRFQPdfService purchasingRFQPdfService;
    
    @Autowired
    private PurchasingRFQExportService purchasingRFQExportService;

 // Create a new PurchasingRFQ
    @PostMapping
    public ResponseEntity<PurchasingRFQ> createPurchasingRFQ(
            @RequestHeader("Authorization") String jwt, 
            @RequestBody PurchasingRFQ purchasingRFQ) {
        PurchasingRFQ createdPurchasingRFQ = purchasingRFQService.createPurchasingRFQ(purchasingRFQ);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdPurchasingRFQ);
    }

    // Get all PurchasingRFQs
    @GetMapping
    public ResponseEntity<List<PurchasingRFQ>> getAllPurchasingRFQs(
            @RequestHeader("Authorization") String jwt) {
        List<PurchasingRFQ> purchasingRFQs = purchasingRFQService.getAllPurchasingRFQ();
        return ResponseEntity.ok(purchasingRFQs);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getPurchasingRFQById(@PathVariable Long id) {
        Optional<PurchasingRFQ> purchasingRFQ = purchasingRFQService.getPurchasingRFQById(id);

        if (purchasingRFQ.isPresent()) {
            return ResponseEntity.ok(purchasingRFQ.get());
        } else {
            return ResponseEntity.status(404).body("PurchasingRFQ with ID " + id + " not found.");
        }
    }

    // Update a PurchasingRFQ by its ID
    @PutMapping("/{id}")
    public ResponseEntity<PurchasingRFQ> updatePurchasingRFQ(
            @RequestHeader("Authorization") String jwt, 
            @PathVariable Long id, 
            @RequestBody PurchasingRFQ purchasingRFQDetails) {
        PurchasingRFQ updatedPurchasingRFQ = purchasingRFQService.updatePurchasingRFQ(purchasingRFQDetails);
        return ResponseEntity.ok(updatedPurchasingRFQ);
    }

    // Delete a PurchasingRFQ by its ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePurchasingRFQ(
            @RequestHeader("Authorization") String jwt, 
            @PathVariable Long id) {
        purchasingRFQService.deletePurchasingRFQ(id);
        return ResponseEntity.noContent().build();
    }
//
//    @PutMapping("/{id}")
//    public ResponseEntity<PurchasingRFQ> updateRFQ(@RequestHeader ("Authorization") String jwt, @PathVariable Long id, @RequestBody PurchasingRFQ rfqDetails) {
//        return ResponseEntity.ok(rfqService.updateRFQ(id, rfqDetails));
//    }
//
//    @DeleteMapping("/{id}")
//    public ResponseEntity<Void> deleteRFQ(@RequestHeader ("Authorization") String jwt, @PathVariable Long id) {
//        rfqService.deleteRFQ(id);
//        return ResponseEntity.noContent().build();
//    }

    @GetMapping("/export")
    public ResponseEntity<byte[]> exportRFQsToExcel(@RequestHeader ("Authorization") String jwt) {
        try {
            byte[] excelFile = purchasingRFQExportService.exportRFQsToExcel();
            return ResponseEntity.ok()
                    .contentType(MediaType.APPLICATION_OCTET_STREAM)
                    .header("Content-Disposition", "attachment; filename=sales_rfqs.xlsx")
                    .body(excelFile);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }
    
    @GetMapping("/pdf")
    public void exportToPdf(@RequestHeader ("Authorization") String jwt, ServletOutputStream servletOutputStream) throws Exception{
    	List<PurchasingRFQ> salesRFQs = purchasingRFQRepository.findAll();
    	purchasingRFQPdfService.generatePDF(salesRFQs, servletOutputStream );
    }
}

