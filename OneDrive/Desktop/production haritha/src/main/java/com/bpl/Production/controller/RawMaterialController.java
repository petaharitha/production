package com.bpl.Production.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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

import com.bpl.Production.entity.RawMaterial;
import com.bpl.Production.service.RawMaterialService;
import com.bpl.request.RawMaterialRequest;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/raw-materials")
@Slf4j
public class RawMaterialController {

    @Autowired
    private RawMaterialService rawMaterialService;

    @PostMapping("/create")
    public ResponseEntity<RawMaterial> createRawMaterial(@Valid @RequestBody RawMaterialRequest rawMaterial) {
        log.info("Received request to create RawMaterial: {}", rawMaterial);

        RawMaterial createdRawMaterial = rawMaterialService.createRawMaterial(rawMaterial);
        log.info("Successfully created RawMaterial with ID: {}", createdRawMaterial.getId());

        return ResponseEntity.ok(createdRawMaterial);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<RawMaterial> updateRawMaterial(
            @PathVariable Integer id,
            @Valid @RequestBody RawMaterialRequest updateRequest) throws Exception {

        log.info("Received request to update RawMaterial with ID: {} and details: {}", id, updateRequest);

        RawMaterial updatedRawMaterial = rawMaterialService.updateRawMaterial(updateRequest, id);
        log.info("Successfully updated RawMaterial with ID: {}", updatedRawMaterial.getId());

        return ResponseEntity.ok(updatedRawMaterial);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteRawMaterial(@PathVariable Integer id) {
        log.info("Received request to delete RawMaterial with ID: {}", id);

        rawMaterialService.deleteRawMaterial(id);
        log.info("Successfully deleted RawMaterial with ID: {}", id);

        return ResponseEntity.noContent().build();
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<RawMaterial> getRawMaterialById(@PathVariable Integer id) {
        log.info("Received request to find RawMaterial with ID: {}", id);

        RawMaterial rawMaterial = rawMaterialService.getRawMaterialById(id);
        log.info("Successfully fetched RawMaterial with ID: {}", id);

        return ResponseEntity.ok(rawMaterial);
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<RawMaterial>> getAllRawMaterials() {
        log.info("Received request to fetch all RawMaterials");

        List<RawMaterial> rawMaterials = rawMaterialService.getAllRawMaterials();
        log.info("Successfully fetched {} RawMaterials", rawMaterials.size());

        return ResponseEntity.ok(rawMaterials);
    }

    @GetMapping("/raw-materialsByPage")
    public Page<RawMaterial> getRawMaterials(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {

        log.info("Received request to fetch RawMaterials with page: {} and size: {}", page, size);

        Pageable pageable = PageRequest.of(page, size);
        Page<RawMaterial> rawMaterialsPage = rawMaterialService.getAllRawMaterials(pageable);

        log.info("Successfully fetched {} RawMaterials on page {}", rawMaterialsPage.getNumberOfElements(), page);

        return rawMaterialsPage;
    }
    
    @GetMapping("/excel")
    public void excelReport(HttpServletResponse response) throws Exception {
        log.info("Generating Excel report.");
        response.setContentType("application/vnd.ms-excel");
        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=user_data.xls";
        response.setHeader(headerKey, headerValue);
        rawMaterialService.generateExcel(response);
        log.info("Excel report successfully generated.");
    }

    @GetMapping("/pdf")
    public void generatePdfFile(HttpServletResponse response) throws Exception {
        log.info("Generating PDF report.");
        response.setContentType("application/pdf");
        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=Report.pdf";
        response.setHeader(headerKey, headerValue);
        rawMaterialService.generatePdf(response);
        log.info("PDF report successfully generated.");
    }
}
