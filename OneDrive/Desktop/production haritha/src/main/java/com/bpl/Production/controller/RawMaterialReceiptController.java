package com.bpl.Production.controller;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
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
import org.springframework.web.multipart.MultipartFile;

import com.bpl.Production.entity.RawMaterialReceipt;
import com.bpl.Production.repository.RawMaterialReceiptRepository;
import com.bpl.Production.service.RawMaterialReceiptService;
import com.bpl.Production.xml.RawMaterialReciptImportExport;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/raw-material-receipts")
public class RawMaterialReceiptController {

    @Autowired
    private RawMaterialReceiptService rawMaterialReceiptService;
    
    @Autowired
    private RawMaterialReciptImportExport rawMaterialReciptImportExport;
    
    @Autowired
    private RawMaterialReceiptRepository rawMaterialReceiptRepository;

    // Create a new RawMaterialReceipt
    @PostMapping
    public ResponseEntity<RawMaterialReceipt> createRawMaterialReceipt(@Valid @RequestBody RawMaterialReceipt rawMaterialReceipt) {
        RawMaterialReceipt createdReceipt = rawMaterialReceiptService.createRawMaterialReceipt(rawMaterialReceipt);
        return ResponseEntity.ok(createdReceipt);
    }

    // Update an existing RawMaterialReceipt
    @PutMapping("/{id}")
    public ResponseEntity<RawMaterialReceipt> updateRawMaterialReceipt(@PathVariable Integer id, @Valid @RequestBody RawMaterialReceipt rawMaterialReceipt) {
        RawMaterialReceipt updatedReceipt = rawMaterialReceiptService.updateRawMaterialReceipt(id, rawMaterialReceipt);
        return ResponseEntity.ok(updatedReceipt);
    }

    // Delete a RawMaterialReceipt
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRawMaterialReceipt(@PathVariable Integer id) {
        rawMaterialReceiptService.deleteRawMaterialReceipt(id);
        return ResponseEntity.noContent().build();
    }

    // Get a list of all RawMaterialReceipts
    @GetMapping
    public List<RawMaterialReceipt> getAllRawMaterialReceipts() {
        return rawMaterialReceiptService.getAllRawMaterialReceipts();
    }

    // Get a specific RawMaterialReceipt by ID
    @GetMapping("/{id}")
    public ResponseEntity<RawMaterialReceipt> getRawMaterialReceiptById(@PathVariable Integer id) {
        RawMaterialReceipt receipt = rawMaterialReceiptService.getRawMaterialReceiptById(id);
        return ResponseEntity.ok(receipt);
    }
    
    @GetMapping("/raw-material-receipts")
    public Page<RawMaterialReceipt> getRawMaterialReceipts(
            @RequestParam(required = false) String condition,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        return rawMaterialReceiptService.getRawMaterialReceipts(condition, page, size);
    }
    
    @PostMapping("/import")
    public ResponseEntity<List<RawMaterialReceipt>> importExcel(@RequestParam("file") MultipartFile file) {
        try {
            List<RawMaterialReceipt> records = rawMaterialReciptImportExport.importFromExcel(file);
            return ResponseEntity.ok(records);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }
    
    @GetMapping("/export")
    public ResponseEntity<byte[]> exportExcel() {
        try {
            List<RawMaterialReceipt> records = rawMaterialReceiptRepository.findAll();
            ByteArrayInputStream inputStream = rawMaterialReciptImportExport.exportToExcel(records);

            HttpHeaders headers = new HttpHeaders();
            headers.add("Content-Disposition", "attachment; filename=raw_material_receipts.xlsx");

            return ResponseEntity.ok()
                    .headers(headers)
                    .contentType(MediaType.APPLICATION_OCTET_STREAM)
                    .body(inputStream.readAllBytes());
        } catch (IOException e) {
            return ResponseEntity.internalServerError().build();
        }
    }
    
    @GetMapping("/receipts")
    public Page<RawMaterialReceipt> getReceiptsByDateRange(
            @RequestParam String fromDate,
            @RequestParam String toDate,
            @RequestParam int page,
            @RequestParam int size) throws Exception {

        return rawMaterialReceiptService.getReceiptsByDateRange(fromDate, toDate, page, size);
    }
}
