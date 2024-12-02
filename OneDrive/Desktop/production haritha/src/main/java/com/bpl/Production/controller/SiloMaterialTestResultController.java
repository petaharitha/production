package com.bpl.Production.controller;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
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

import com.bpl.Production.entity.SiloMaterialTestResult;
import com.bpl.Production.service.SiloMaterialTestResultService;
import com.bpl.Production.xml.SiloMaterialTestResultsImportExport;

@RestController
@RequestMapping("/api/silo-material-test-results")
public class SiloMaterialTestResultController {

    @Autowired
    private SiloMaterialTestResultService service;
    
    @Autowired
    private SiloMaterialTestResultsImportExport siloMaterialTestResultsImportExport;

    @PostMapping
    public ResponseEntity<SiloMaterialTestResult> create(@Validated @RequestBody SiloMaterialTestResult result) {
        return new ResponseEntity<>(service.create(result), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<SiloMaterialTestResult> update(@PathVariable Integer id, @Validated @RequestBody SiloMaterialTestResult result) {
        if (!id.equals(result.getId())) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(service.update(result), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        service.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SiloMaterialTestResult> getById(@PathVariable Integer id) {
        Optional<SiloMaterialTestResult> result = service.findById(id);
        return result.map(ResponseEntity::ok).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping
    public ResponseEntity<Page<SiloMaterialTestResult>> getAll(Pageable pageable) {
        return new ResponseEntity<>(service.findAll(pageable), HttpStatus.OK);
    }

    @GetMapping("/filter")
    public ResponseEntity<List<SiloMaterialTestResult>> filterByShift(@RequestParam String shift) {
        return new ResponseEntity<>(service.filterByShift(shift), HttpStatus.OK);
    }
    
    @PostMapping("/import")
    public ResponseEntity<String> importSiloMaterialTestResults(@RequestParam("file") MultipartFile file) {
        try {
            siloMaterialTestResultsImportExport.importSiloMaterialTestResults(file);
            return new ResponseEntity<>("Silo Material Test Results imported successfully.", HttpStatus.OK);
        } catch (IOException e) {
            return new ResponseEntity<>("Failed to import the file. Error: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>("Invalid file type. Please upload an Excel file.", HttpStatus.BAD_REQUEST);
        }
    }

    // Endpoint for exporting silo material test results to an Excel file
    @GetMapping("/export")
    public ResponseEntity<String> exportSiloMaterialTestResults() {
        try {
            String filePath = siloMaterialTestResultsImportExport.exportSiloMaterialTestResults();
            return new ResponseEntity<>("Silo Material Test Results exported successfully. File path: " + filePath, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Error exporting the data. Error: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
