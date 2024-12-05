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

import com.supplier.entity.PurchasingInvoice;
import com.supplier.repository.PurchasingInvoiceRepository;
import com.supplier.service.PurchasingInvoiceExportService;
import com.supplier.service.PurchasingInvoicePdfService;
import com.supplier.service.PurchasingInvoiceService;

import jakarta.servlet.ServletOutputStream;

@RestController
@RequestMapping("/api/vendors/purchasing-invoice")
public class PurchasingInvoiceController {

    @Autowired
    private PurchasingInvoiceService invoiceService;
    
    @Autowired
    private PurchasingInvoiceRepository invoiceRepository;
    
    @Autowired
    private PurchasingInvoicePdfService invoicePdfService;
    
    @Autowired
    private PurchasingInvoiceExportService exportService;

    @PostMapping
    public ResponseEntity<PurchasingInvoice> createInvoice(@RequestHeader ("Authorization") String jwt,@RequestBody PurchasingInvoice invoice) {
        return ResponseEntity.status(HttpStatus.CREATED).body(invoiceService.createInvoice(invoice));
    }

    @GetMapping
    public ResponseEntity<List<PurchasingInvoice>> getAllInvoices(@RequestHeader ("Authorization") String jwt) {
        return ResponseEntity.ok(invoiceService.getAllInvoices());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PurchasingInvoice> getInvoiceById(@RequestHeader ("Authorization") String jwt,@PathVariable Long id) {
        return ResponseEntity.ok(invoiceService.getInvoiceById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<PurchasingInvoice> updateInvoice(@RequestHeader ("Authorization") String jwt,@PathVariable Long id, @RequestBody PurchasingInvoice invoiceDetails) {
        return ResponseEntity.ok(invoiceService.updateInvoice(id, invoiceDetails));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteInvoice(@RequestHeader ("Authorization") String jwt,@PathVariable Long id) {
        invoiceService.deleteInvoice(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/export")
    public ResponseEntity<byte[]> exportInvoiceToExcel(@RequestHeader ("Authorization") String jwt) {
        try {
            byte[] excelFile = exportService.exportInvoiceToExcel();
            return ResponseEntity.ok()
                    .contentType(MediaType.APPLICATION_OCTET_STREAM)
                    .header("Content-Disposition", "attachment; filename=purchasing_PO.xlsx")
                    .body(excelFile);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping("/pdf")
    public void exportToPdf(@RequestHeader ("Authorization") String jwt,ServletOutputStream servletOutputStream) throws Exception{
    	List<PurchasingInvoice> salesRFQs = invoiceRepository.findAll();
    	invoicePdfService.generatePDF(salesRFQs, servletOutputStream );
    }

}


