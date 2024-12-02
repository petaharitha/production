package com.bpl.Production.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

import com.bpl.Production.entity.RawMaterialIssue;
import com.bpl.Production.service.RawMaterialIssueService;
import com.bpl.request.RawMaterialIssueRequest;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/raw-material-issues")
@Slf4j
public class RawMaterialIssueController {

    @Autowired
    private RawMaterialIssueService rawMaterialIssueService;

    @PostMapping("/create")
    public ResponseEntity<RawMaterialIssue> createRawMaterialIssue(@Valid @RequestBody RawMaterialIssueRequest rawMaterialIssueRequest) {
        log.info("Received request to create RawMaterialIssue: {}", rawMaterialIssueRequest);

        RawMaterialIssue createdIssue = rawMaterialIssueService.createRawMaterialIssue(rawMaterialIssueRequest);
        log.info("Successfully created RawMaterialIssue with ID: {}", createdIssue.getId());

        return ResponseEntity.status(HttpStatus.CREATED).body(createdIssue);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<RawMaterialIssue> updateRawMaterialIssue(@Valid @RequestBody RawMaterialIssueRequest rawMaterialIssueRequest, @PathVariable Integer id) throws Exception {
        log.info("Received request to update RawMaterialIssue with ID: {} and details: {}", id, rawMaterialIssueRequest);

        RawMaterialIssue updatedIssue = rawMaterialIssueService.updateRawMaterialIssue(rawMaterialIssueRequest, id);
        log.info("Successfully updated RawMaterialIssue with ID: {}", updatedIssue.getId());

        return ResponseEntity.ok(updatedIssue);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteRawMaterialIssue(@PathVariable Integer id) {
        log.info("Received request to delete RawMaterialIssue with ID: {}", id);

        rawMaterialIssueService.deleteRawMaterialIssue(id);
        log.info("Successfully deleted RawMaterialIssue with ID: {}", id);

        return ResponseEntity.noContent().build();
    }

    @GetMapping("/getByID/{id}")
    public ResponseEntity<RawMaterialIssue> getRawMaterialIssueById(@PathVariable Integer id) {
        log.info("Received request to fetch RawMaterialIssue with ID: {}", id);

        RawMaterialIssue issue = rawMaterialIssueService.getRawMaterialIssueById(id);
        log.info("Successfully fetched RawMaterialIssue with ID: {}", id);

        return ResponseEntity.ok(issue);
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<RawMaterialIssue>> getAllRawMaterialIssues() {
        log.info("Received request to fetch all RawMaterialIssues");

        List<RawMaterialIssue> issues = rawMaterialIssueService.getAllRawMaterialIssues();
        log.info("Successfully fetched {} RawMaterialIssues", issues.size());

        return ResponseEntity.ok(issues);
    }

    @GetMapping("/getAllByPage")
    public ResponseEntity<Page<RawMaterialIssue>> getAllRawMaterialIssues(Pageable pageable) {
        log.info("Received request to fetch RawMaterialIssues with page: {} and size: {}", pageable.getPageNumber(), pageable.getPageSize());

        Page<RawMaterialIssue> issues = rawMaterialIssueService.getAllRawMaterialIssues(pageable);
        log.info("Successfully fetched {} RawMaterialIssues on page {}", issues.getNumberOfElements(), pageable.getPageNumber());

        return ResponseEntity.ok(issues);
    }
    
    @GetMapping("/excel")
    public void excelReport(HttpServletResponse response) throws Exception {
        log.info("Generating Excel report.");
        response.setContentType("application/vnd.ms-excel");
        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=user_data.xls";
        response.setHeader(headerKey, headerValue);
        rawMaterialIssueService.generateExcel(response);
        log.info("Excel report successfully generated.");
    }

    @GetMapping("/pdf")
    public void generatePdfFile(HttpServletResponse response) throws Exception {
        log.info("Generating PDF report.");
        response.setContentType("application/pdf");
        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=Report.pdf";
        response.setHeader(headerKey, headerValue);
        rawMaterialIssueService.generatePdf(response);
        log.info("PDF report successfully generated.");
    }
}
