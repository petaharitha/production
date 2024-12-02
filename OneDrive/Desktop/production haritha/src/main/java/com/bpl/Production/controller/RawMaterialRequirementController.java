package com.bpl.Production.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bpl.Production.entity.RawMaterialRequirement;
import com.bpl.Production.service.RawMaterialRequirementService;
import com.bpl.request.RawMaterialRequirementRequest;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/raw-material-requirements")
@Slf4j
public class RawMaterialRequirementController {

    @Autowired
    private RawMaterialRequirementService rawMaterialRequirementService;

    @PostMapping("/create")
    public ResponseEntity<RawMaterialRequirement> createRequirement(@Valid @RequestBody RawMaterialRequirementRequest request) {
        log.info("Received request to create RawMaterialRequirement: {}", request);

        RawMaterialRequirement createdRequirement = rawMaterialRequirementService.createRawMaterialRequirement(request);
        log.info("Successfully created RawMaterialRequirement with ID: {}", createdRequirement.getId());

        return ResponseEntity.ok(createdRequirement);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<RawMaterialRequirement> updateRequirement(
            @Valid @RequestBody RawMaterialRequirementRequest request,
            @PathVariable Integer id) {
        log.info("Received request to update RawMaterialRequirement with ID: {} and details: {}", id, request);

        try {
            RawMaterialRequirement updatedRequirement = rawMaterialRequirementService.updateRawMaterialRequirement(request, id);
            log.info("Successfully updated RawMaterialRequirement with ID: {}", updatedRequirement.getId());
            return ResponseEntity.ok(updatedRequirement);
        } catch (Exception e) {
            log.error("Error updating RawMaterialRequirement with ID: {}", id, e);
            return ResponseEntity.badRequest().body(null);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteRequirement(@PathVariable Integer id) {
        log.info("Received request to delete RawMaterialRequirement with ID: {}", id);

        try {
            rawMaterialRequirementService.deleteRawMaterialRequirement(id);
            log.info("Successfully deleted RawMaterialRequirement with ID: {}", id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            log.error("Error deleting RawMaterialRequirement with ID: {}", id, e);
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/getById/{id}")
    public ResponseEntity<RawMaterialRequirement> getRequirementById(@PathVariable Integer id) {
        log.info("Received request to fetch RawMaterialRequirement with ID: {}", id);

        try {
            RawMaterialRequirement requirement = rawMaterialRequirementService.getRawMaterialRequirementById(id);
            log.info("Successfully fetched RawMaterialRequirement with ID: {}", id);
            return ResponseEntity.ok(requirement);
        } catch (RuntimeException e) {
            log.error("Error fetching RawMaterialRequirement with ID: {}", id, e);
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<RawMaterialRequirement>> getAllRequirements() {
        log.info("Received request to fetch all RawMaterialRequirements");

        List<RawMaterialRequirement> requirements = rawMaterialRequirementService.getAllRawMaterialRequirements();
        log.info("Successfully fetched {} RawMaterialRequirements", requirements.size());

        return ResponseEntity.ok(requirements);
    }

    @GetMapping("/getAllByPage")
    public ResponseEntity<Page<RawMaterialRequirement>> getAllRequirements(Pageable pageable) {
        log.info("Received request to fetch RawMaterialRequirements with page: {} and size: {}", pageable.getPageNumber(), pageable.getPageSize());

        Page<RawMaterialRequirement> pagedRequirements = rawMaterialRequirementService.getAllRawMaterialRequirements(pageable);
        log.info("Successfully fetched {} RawMaterialRequirements on page {}", pagedRequirements.getNumberOfElements(), pageable.getPageNumber());

        return ResponseEntity.ok(pagedRequirements);
    }
    
    @GetMapping("/excel")
    public void excelReport(HttpServletResponse response) throws Exception {
        log.info("Generating Excel report.");
        response.setContentType("application/vnd.ms-excel");
        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=user_data.xls";
        response.setHeader(headerKey, headerValue);
        rawMaterialRequirementService.generateExcel(response);
        log.info("Excel report successfully generated.");
    }

    @GetMapping("/pdf")
    public void generatePdfFile(HttpServletResponse response) throws Exception {
        log.info("Generating PDF report.");
        response.setContentType("application/pdf");
        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=Report.pdf";
        response.setHeader(headerKey, headerValue);
        rawMaterialRequirementService.generatePdf(response);
        log.info("PDF report successfully generated.");
    }
}
