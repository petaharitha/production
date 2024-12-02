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

import com.bpl.Production.entity.GreenBrickTestResults;
import com.bpl.Production.service.GreenBrickTestResultsService;

@RestController
@RequestMapping("/api/green-brick-test-results")
public class GreenBrickTestResultsController {

	@Autowired
	private GreenBrickTestResultsService greenBrickTestResultsService;
	
	// Create or update GreenBrickTestResults
    @PostMapping
    public ResponseEntity<GreenBrickTestResults> createOrUpdateGreenBrickTestResults(@RequestBody GreenBrickTestResults greenBrickTestResults) {
        GreenBrickTestResults savedGreenBrickTestResults = greenBrickTestResultsService.createGreenBrickTestResults(greenBrickTestResults);
        return new ResponseEntity<>(savedGreenBrickTestResults, HttpStatus.CREATED);
    }

    // Get GreenBrickTestResults by ID
    @GetMapping("/{id}")
    public ResponseEntity<GreenBrickTestResults> getGreenBrickTestResultsById(@PathVariable Integer id) {
        Optional<GreenBrickTestResults> greenBrickTestResults = greenBrickTestResultsService.getGreenBrickTestResultsById(id);
        return greenBrickTestResults
                .map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // Get all GreenBrickTestResults
    @GetMapping
    public ResponseEntity<List<GreenBrickTestResults>> getAllGreenBrickTestResults() {
        List<GreenBrickTestResults> greenBrickTestResultsList = greenBrickTestResultsService.getAllGreenBrickTestResults();
        return new ResponseEntity<>(greenBrickTestResultsList, HttpStatus.OK);
    }

    // Delete GreenBrickTestResults by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteGreenBrickTestResults(@PathVariable Integer id) {
        greenBrickTestResultsService.deleteGreenBrickTestResults(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    // Update GreenBrickTestResults by ID
    @PutMapping("/{id}")
    public ResponseEntity<GreenBrickTestResults> updateGreenBrickTestResults(@PathVariable Integer id, 
                                                                            @RequestBody GreenBrickTestResults greenBrickTestResults) {
        GreenBrickTestResults updatedGreenBrickTestResults = greenBrickTestResultsService.updateGreenBrickTestResults(id, greenBrickTestResults);
        return updatedGreenBrickTestResults != null
                ? new ResponseEntity<>(updatedGreenBrickTestResults, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
