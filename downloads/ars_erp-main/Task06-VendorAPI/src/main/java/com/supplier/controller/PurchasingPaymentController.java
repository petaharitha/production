package com.supplier.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

import com.supplier.entity.PurchasingPaymentMade;
import com.supplier.repository.PurchasingPaymentRepository;
import com.supplier.service.PurchasingPaymentExportService;
import com.supplier.service.PurchasingPaymentPdfService;
import com.supplier.service.PurchasingPaymentService;

import jakarta.servlet.ServletOutputStream;


@RestController
@RequestMapping("/api/vendors/payments")
public class PurchasingPaymentController {

    // Logger for this class
    private static final Logger logger = LoggerFactory.getLogger(PurchasingPaymentController.class);

    @Autowired
    private PurchasingPaymentService paymentService;
    
    @Autowired
    private PurchasingPaymentRepository paymentRepository;
    
    @Autowired
    private PurchasingPaymentExportService exportService;
    
    @Autowired
    private PurchasingPaymentPdfService paymentPdfService;

    /**
     * Create a new payment.
     */
    @PostMapping
    public ResponseEntity<PurchasingPaymentMade> createPayment(@RequestHeader ("Authorization") String jwt,
            @RequestBody PurchasingPaymentMade payment) {
        logger.info("Request to create a new payment.");
        PurchasingPaymentMade createdPayment = paymentService.createPayment(payment);
        return new ResponseEntity<>(createdPayment, HttpStatus.CREATED);
    }

    /**
     * Get all payments.
     */
    @GetMapping
    public ResponseEntity<List<PurchasingPaymentMade>> getAllPayments(@RequestHeader ("Authorization") String jwt) {
        logger.info("Request to fetch all payments.");
        List<PurchasingPaymentMade> payments = paymentService.getAllPayments();
        return new ResponseEntity<>(payments, HttpStatus.OK);
    }

    /**
     * Get payment by ID.
     */
    @GetMapping("/{id}")
    public ResponseEntity<PurchasingPaymentMade> getPaymentById(@RequestHeader ("Authorization") String jwt,@PathVariable Long id) {
        logger.info("Request to fetch payment with ID: {}", id);
        PurchasingPaymentMade payment = paymentService.getPaymentById(id);
        return new ResponseEntity<>(payment, HttpStatus.OK);
    }

    /**
     * Update payment by ID.
     */
    @PutMapping("/{id}")
    public ResponseEntity<PurchasingPaymentMade> updatePayment(@RequestHeader ("Authorization") String jwt,
            @PathVariable Long id, @RequestBody PurchasingPaymentMade paymentDetails) {
        logger.info("Request to update payment with ID: {}", id);
        PurchasingPaymentMade updatedPayment = paymentService.updatePayment(id, paymentDetails);
        return new ResponseEntity<>(updatedPayment, HttpStatus.OK);
    }

    /**
     * Delete payment by ID.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePayment(@RequestHeader ("Authorization") String jwt,@PathVariable Long id) {
        logger.info("Request to delete payment with ID: {}", id);
        paymentService.deletePayment(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    
    @GetMapping("/export")
    public ResponseEntity<byte[]> exportRFQsToExcel(@RequestHeader ("Authorization") String jwt) {
        try {
            byte[] excelFile = exportService.exportPaymentToExcel();
            return ResponseEntity.ok()
                    .contentType(MediaType.APPLICATION_OCTET_STREAM)
                    .header("Content-Disposition", "attachment; filename=purchasing_Quote.xlsx")
                    .body(excelFile);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }
    
    @GetMapping("/pdf")
    public void exportToPdf(@RequestHeader ("Authorization") String jwt,ServletOutputStream servletOutputStream) throws Exception{
    	List<PurchasingPaymentMade> salesRFQs = paymentRepository.findAll();
    	paymentPdfService.generatePDF(salesRFQs, servletOutputStream );
    }

}
