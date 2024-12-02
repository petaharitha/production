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
import org.springframework.web.bind.annotation.RestController;

import com.bpl.Production.entity.MixesTestResults;
import com.bpl.Production.service.MixesTestResultsService;

@RestController
@RequestMapping("/api/mixes-test-results")
public class MixesTestResultsController {

	@Autowired
	private MixesTestResultsService mixesTestResultsService;
	
	// Create a new MixesTestResults
    @PostMapping
    public ResponseEntity<MixesTestResults> create(@RequestBody MixesTestResults mixesTestResults) {
        MixesTestResults createdMixesTestResults = mixesTestResultsService.create(mixesTestResults);
        return new ResponseEntity<>(createdMixesTestResults, HttpStatus.CREATED);
    }

    // Get all MixesTestResults
    @GetMapping
    public ResponseEntity<List<MixesTestResults>> getAll() {
        List<MixesTestResults> mixesTestResultsList = mixesTestResultsService.getAll();
        return new ResponseEntity<>(mixesTestResultsList, HttpStatus.OK);
    }

    // Get a MixesTestResults by ID
    @GetMapping("/{id}")
    public ResponseEntity<MixesTestResults> getById(@PathVariable Integer id) {
        Optional<MixesTestResults> mixesTestResults = mixesTestResultsService.getById(id);
        return mixesTestResults.map(result -> new ResponseEntity<>(result, HttpStatus.OK))
                               .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // Delete a MixesTestResults by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        boolean isDeleted = mixesTestResultsService.delete(id);
        return isDeleted ? new ResponseEntity<>(HttpStatus.NO_CONTENT) 
                         : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    // Update an existing MixesTestResults
    @PutMapping("/{id}")
    public ResponseEntity<MixesTestResults> update(@PathVariable Integer id, @RequestBody MixesTestResults updatedMixesTestResults) {
        MixesTestResults updatedResult = mixesTestResultsService.update(id, updatedMixesTestResults);
        return updatedResult!=null ? new ResponseEntity<>(updatedResult, HttpStatus.OK)
        	                                            : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
