package com.bpl.Production.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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

import com.bpl.Production.entity.RMTestResults;
import com.bpl.Production.service.RMTestResultsService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/rmtestresults")
public class RMTestResultsController {

    @Autowired
    private RMTestResultsService rmTestResultsService;

    // Create RMTestResult
    @PostMapping
    public ResponseEntity<RMTestResults> createRMTestResult(@Valid @RequestBody RMTestResults rmTestResults) {
        RMTestResults createdRMTestResult = rmTestResultsService.createRMTestResult(rmTestResults);
        return new ResponseEntity<>(createdRMTestResult, HttpStatus.CREATED);
    }

    // Get all RMTestResults
    @GetMapping
    public ResponseEntity<List<RMTestResults>> getAllRMTestResults() {
        List<RMTestResults> rmTestResultsList = rmTestResultsService.getAllRMTestResults();
        return new ResponseEntity<>(rmTestResultsList, HttpStatus.OK);
    }

    // Get RMTestResult by ID
    @GetMapping("/{id}")
    public ResponseEntity<RMTestResults> getRMTestResultById(@PathVariable Integer id) {
        Optional<RMTestResults> rmTestResults = rmTestResultsService.getRMTestResultById(id);
        if (rmTestResults.isPresent()) {
            return new ResponseEntity<>(rmTestResults.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    // Update RMTestResult
    @PutMapping("/{id}")
    public ResponseEntity<RMTestResults> updateRMTestResult(@PathVariable Integer id, 
                                                            @Valid @RequestBody RMTestResults updatedRMTestResults) {
        RMTestResults updated = rmTestResultsService.updateRMTestResult(id, updatedRMTestResults);
        if (updated != null) {
            return new ResponseEntity<>(updated, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    // Delete RMTestResult
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRMTestResult(@PathVariable Integer id) {
        if (rmTestResultsService.deleteRMTestResult(id)) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    
    @GetMapping("/getById")
    public RMTestResults getRMTestResults(@RequestParam Integer id) {
        return rmTestResultsService.getRMTestResultsWithDetails(id);
    }

    // This endpoint can be used to return RMTestResults details along with the RawMaterialReceipt comment
    @GetMapping("/details/{id}")
    public RMTestResults getRMTestResultsDetails(@PathVariable Integer id) {
        return rmTestResultsService.getRMTestResultsWithDetails(id);
    }
}

