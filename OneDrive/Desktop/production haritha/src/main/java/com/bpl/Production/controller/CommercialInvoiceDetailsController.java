package com.bpl.Production.controller;

import org.springframework.beans.factory.annotation.Autowired;

import com.bpl.Production.entity.CommercialInvoiceDetails;
import com.bpl.Production.service.CommercialInvoiceDetailsService;
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
@RequestMapping("/api/commercial-invoice-details")
public class CommercialInvoiceDetailsController {

	@Autowired
	private CommercialInvoiceDetailsService commercialInvoiceDetailsService;
	
	// Create CommercialInvoiceDetails
    @PostMapping
    public ResponseEntity<CommercialInvoiceDetails> createCommercialInvoiceDetails(@RequestBody CommercialInvoiceDetails commercialInvoiceDetails) {
        CommercialInvoiceDetails createdInvoiceDetails = commercialInvoiceDetailsService.createCommercialInvoiceDetails(commercialInvoiceDetails);
        return new ResponseEntity<>(createdInvoiceDetails, HttpStatus.CREATED);
    }

    // Get CommercialInvoiceDetails by ID
    @GetMapping("/{id}")
    public ResponseEntity<CommercialInvoiceDetails> getCommercialInvoiceDetailsById(@PathVariable Long id) {
        Optional<CommercialInvoiceDetails> commercialInvoiceDetails = commercialInvoiceDetailsService.getCommercialInvoiceDetailsById(id);
        if (commercialInvoiceDetails.isPresent()) {
            return new ResponseEntity<>(commercialInvoiceDetails.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Get all CommercialInvoiceDetails
    @GetMapping
    public ResponseEntity<List<CommercialInvoiceDetails>> getAllCommercialInvoiceDetails() {
        List<CommercialInvoiceDetails> commercialInvoiceDetails = commercialInvoiceDetailsService.getAllCommercialInvoiceDetails();
        return new ResponseEntity<>(commercialInvoiceDetails, HttpStatus.OK);
    }

    // Update CommercialInvoiceDetails
    @PutMapping("/{id}")
    public ResponseEntity<CommercialInvoiceDetails> updateCommercialInvoiceDetails(@PathVariable Long id,
                                                                                    @RequestBody CommercialInvoiceDetails commercialInvoiceDetails) {
        try {
            CommercialInvoiceDetails updatedInvoiceDetails = commercialInvoiceDetailsService.updateCommercialInvoiceDetails(id, commercialInvoiceDetails);
            return new ResponseEntity<>(updatedInvoiceDetails, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Delete CommercialInvoiceDetails
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCommercialInvoiceDetails(@PathVariable Long id) {
        commercialInvoiceDetailsService.deleteCommercialInvoiceDetails(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
