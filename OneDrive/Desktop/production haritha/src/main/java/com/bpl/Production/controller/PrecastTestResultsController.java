package com.bpl.Production.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
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

import com.bpl.Production.entity.PrecastTestResults;
import com.bpl.Production.service.PrecastTestResultsService;

@RestController
@RequestMapping("/api/precast-test-results")
public class PrecastTestResultsController {

	@Autowired
	private PrecastTestResultsService precastTestResultsService;
	
	// Create a new PrecastTestResults
    @PostMapping
    public ResponseEntity<PrecastTestResults> createPrecastTestResults(@RequestBody PrecastTestResults precastTestResults) {
        PrecastTestResults savedPrecastTestResults = precastTestResultsService.createPrecastTestResults(precastTestResults);
        return new ResponseEntity<>(savedPrecastTestResults, HttpStatus.CREATED);
    }

    // Get all PrecastTestResults
    @GetMapping
    public ResponseEntity<List<PrecastTestResults>> getAllPrecastTestResults() {
        List<PrecastTestResults> precastTestResultsList = precastTestResultsService.getAllPrecastTestResults();
        return new ResponseEntity<>(precastTestResultsList, HttpStatus.OK);
    }

    // Get PrecastTestResults by ID
    @GetMapping("/{id}")
    public ResponseEntity<PrecastTestResults> getPrecastTestResultsById(@PathVariable Integer id) {
        Optional<PrecastTestResults> precastTestResultsOptional = precastTestResultsService.getPrecastTestResultsById(id);
        if (precastTestResultsOptional.isPresent()) {
            return new ResponseEntity<>(precastTestResultsOptional.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Update PrecastTestResults by ID
    @PutMapping("/{id}")
    public ResponseEntity<PrecastTestResults> updatePrecastTestResults(@PathVariable Integer id, @RequestBody PrecastTestResults precastTestResultsDetails) {
        PrecastTestResults updatedPrecastTestResults = precastTestResultsService.updatePrecastTestResults(id, precastTestResultsDetails);
        if (updatedPrecastTestResults != null) {
            return new ResponseEntity<>(updatedPrecastTestResults, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Delete PrecastTestResults by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePrecastTestResults(@PathVariable Integer id) {
        precastTestResultsService.deletePrecastTestResults(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    
    @GetMapping("/precastTestResults")
    public List<PrecastTestResults> getPrecastTestResults(
            @RequestParam String fromDate,
            @RequestParam String toDate,
            @RequestParam String quality,
            @RequestParam Integer altNo) throws ParseException {
        
        // Convert string dates to java.util.Date
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        Date from = sdf.parse(fromDate);
        Date to = sdf.parse(toDate);

        return precastTestResultsService.getPrecastTestResults(from, to, quality, altNo);
    }
    @GetMapping("/sNo")
    public List<PrecastTestResults> getPrecastTestResults(@RequestParam String soNo) {
        return precastTestResultsService.getPrecastTestResults(soNo);
    }
}
