package com.bpl.Production.controller;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bpl.Production.entity.FiredBrickTestResults;
import com.bpl.Production.service.FiredBrickTestResultsService;

@RestController
@RequestMapping("/api/fired-brick-test-results")
public class FiredBrickTestResultsController {

    @Autowired
    private FiredBrickTestResultsService firedBrickTestResultsService;

    // Get all FiredBrickTestResults
    @GetMapping
    public ResponseEntity<List<FiredBrickTestResults>> getAllFiredBrickTestResults() {
        List<FiredBrickTestResults> firedBrickTestResultsList = firedBrickTestResultsService.getAllFiredBrickTestResults();
        return new ResponseEntity<>(firedBrickTestResultsList, HttpStatus.OK);
    }

    // Get a FiredBrickTestResult by ID
    
    // Fetch results by ID
    @GetMapping("/{id}")
    public ResponseEntity<FiredBrickTestResults> getFiredBrickTestResultsById(@PathVariable Integer id) {
        FiredBrickTestResults result = firedBrickTestResultsService.getFiredBrickTestResultsById(id);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    

    // Create or Update FiredBrickTestResults
    @PostMapping
    public ResponseEntity<FiredBrickTestResults> createOrUpdateFiredBrickTestResults(@RequestBody FiredBrickTestResults firedBrickTestResults) {
        FiredBrickTestResults savedFiredBrickTestResults = firedBrickTestResultsService.save(firedBrickTestResults);
        return new ResponseEntity<>(savedFiredBrickTestResults, HttpStatus.CREATED);
    }

    // Delete FiredBrickTestResults by id
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFiredBrickTestResultsById(@PathVariable Integer id) {
        firedBrickTestResultsService.deleteFiredBrickTestResultsById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    
 // Get FiredBrickTestResults by date range
    @GetMapping("/dateRange")
    public ResponseEntity<List<FiredBrickTestResults>> getResultsByDateRange(@RequestParam Date fromDate, @RequestParam Date toDate) {
        List<FiredBrickTestResults> results = firedBrickTestResultsService.getResultsByDateRange(fromDate, toDate);
        return ResponseEntity.ok(results);
    }

//    // Get FiredBrickTestResults by date range, quality, and altNo
//    @GetMapping("/filter")
//    public ResponseEntity<List<FiredBrickTestResults>> getResultsByFilter(
//            @RequestParam Date fromDate,
//            @RequestParam Date toDate,
//            @RequestParam String quality,
//            @RequestParam String altNo) {
//        List<FiredBrickTestResults> results = firedBrickTestResultsService.getResultsByDateAndQuality(fromDate, toDate, quality, altNo);
//        return ResponseEntity.ok(results);
//    }

    // Get FiredBrickTestResults by SO number
    @GetMapping("/soNo/{soNo}")
    public ResponseEntity<List<FiredBrickTestResults>> getResultsBySoNo(@PathVariable String soNo) {
        List<FiredBrickTestResults> results = firedBrickTestResultsService.getResultsBySoNo(soNo);
        return ResponseEntity.ok(results);
    }
}
