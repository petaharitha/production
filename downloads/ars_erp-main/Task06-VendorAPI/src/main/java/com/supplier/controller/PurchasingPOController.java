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

import com.supplier.entity.PurchasingPO;
import com.supplier.repository.PurchasingPORepository;
import com.supplier.service.PurchasingPOExcelService;
import com.supplier.service.PurchasingPOPdfService;
import com.supplier.service.PurchasingPOService;

import jakarta.servlet.ServletOutputStream;

@RestController
@RequestMapping("/api/vendors/purchasing-po")
public class PurchasingPOController {

    @Autowired
    private PurchasingPOService poService;
    
    @Autowired
    private PurchasingPORepository poRepo;
    
    @Autowired
    private PurchasingPOPdfService poPdfService;
    
    @Autowired
    private PurchasingPOExcelService excelService;

    @PostMapping
    public ResponseEntity<PurchasingPO> createPO(@RequestHeader ("Authorization") String jwt, @RequestBody PurchasingPO po) {
        return ResponseEntity.status(HttpStatus.CREATED).body(poService.createPO(po));
    }

    @GetMapping
    public ResponseEntity<List<PurchasingPO>> getAllPOs(@RequestHeader ("Authorization") String jwt) {
        return ResponseEntity.ok(poService.getAllPOs());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PurchasingPO> getPOById(@RequestHeader ("Authorization") String jwt,@PathVariable Long id) {
        return ResponseEntity.ok(poService.getPOById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<PurchasingPO> updatePO(@RequestHeader ("Authorization") String jwt,@PathVariable Long id, @RequestBody PurchasingPO poDetails) {
        return ResponseEntity.ok(poService.updatePO(id, poDetails));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePO(@RequestHeader ("Authorization") String jwt,@PathVariable Long id) {
        poService.deletePO(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/export")
    public ResponseEntity<byte[]> exportRFQsToExcel(@RequestHeader ("Authorization") String jwt) {
        try {
            byte[] excelFile = excelService.exportPurchasingPoToExcel();
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
    	List<PurchasingPO> salesRFQs = poRepo.findAll();
    	poPdfService.generatePDF(salesRFQs, servletOutputStream );
    }
}

