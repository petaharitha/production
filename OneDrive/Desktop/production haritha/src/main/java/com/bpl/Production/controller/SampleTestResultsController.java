package com.bpl.Production.controller;

import java.text.ParseException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
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

import com.bpl.Production.entity.SampleTestResults;
import com.bpl.Production.service.SampleTestResultsService;

@RestController
@RequestMapping("/api/sample-test-results")
public class SampleTestResultsController {

    @Autowired
    private SampleTestResultsService sampleTestResultsService;

    @PostMapping
    public ResponseEntity<SampleTestResults> createSampleTestResult(@RequestBody SampleTestResults sampleTestResult) {
        SampleTestResults createdResult = sampleTestResultsService.createSampleTestResult(sampleTestResult);
        return ResponseEntity.ok(createdResult);
    }

    @PutMapping("/{id}")
    public ResponseEntity<SampleTestResults> updateSampleTestResult(@PathVariable Integer id, @RequestBody SampleTestResults sampleTestResult) {
        SampleTestResults updatedResult = sampleTestResultsService.updateSampleTestResult(id, sampleTestResult);
        return ResponseEntity.ok(updatedResult);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSampleTestResult(@PathVariable Integer id) {
        sampleTestResultsService.deleteSampleTestResult(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<SampleTestResults> getSampleTestResultById(@PathVariable Integer id) {
        SampleTestResults result = sampleTestResultsService.getSampleTestResultById(id);
        return ResponseEntity.ok(result);
    }

    @GetMapping
    public ResponseEntity<List<SampleTestResults>> getAllSampleTestResults() {
        List<SampleTestResults> results = sampleTestResultsService.getAllSampleTestResults();
        return ResponseEntity.ok(results);
    }
    
    @GetMapping("/sample-test-results")
    public Page<SampleTestResults> getSampleTestResults(
            @RequestParam(required = false) String fromDate,
            @RequestParam(required = false) String toDate,
            @RequestParam(required = false) String tag,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) throws ParseException {
        return sampleTestResultsService.getFilteredResults(fromDate, toDate, tag, PageRequest.of(page, size));
    }
}