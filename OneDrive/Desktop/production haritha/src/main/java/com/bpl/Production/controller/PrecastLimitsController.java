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

import com.bpl.Production.entity.PrecastLimits;
import com.bpl.Production.service.PrecastLimitsService;

@RestController
@RequestMapping("/api/precast-limts")
public class PrecastLimitsController {

	@Autowired
	private PrecastLimitsService precastLimitsService;
	
	// Create new PrecastLimits entry
    @PostMapping
    public ResponseEntity<PrecastLimits> createPrecastLimits(@RequestBody PrecastLimits precastLimits) {
        PrecastLimits savedPrecastLimits = precastLimitsService.createPrecastLimits(precastLimits);
        return new ResponseEntity<>(savedPrecastLimits, HttpStatus.CREATED);
    }

    // Get all PrecastLimits entries
    @GetMapping
    public ResponseEntity<List<PrecastLimits>> getAllPrecastLimits() {
        List<PrecastLimits> precastLimitsList = precastLimitsService.getAllPrecastLimits();
        return new ResponseEntity<>(precastLimitsList, HttpStatus.OK);
    }

    // Get PrecastLimits by ID
    @GetMapping("/{id}")
    public ResponseEntity<PrecastLimits> getPrecastLimitsById(@PathVariable Integer id) {
        Optional<PrecastLimits> precastLimitsOptional = precastLimitsService.getPrecastLimitsById(id);
        if (precastLimitsOptional.isPresent()) {
            return new ResponseEntity<>(precastLimitsOptional.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Update PrecastLimits by ID
    @PutMapping("/{id}")
    public ResponseEntity<PrecastLimits> updatePrecastLimits(@PathVariable Integer id, @RequestBody PrecastLimits precastLimitsDetails) {
        PrecastLimits updatedPrecastLimits = precastLimitsService.updatePrecastLimits(id, precastLimitsDetails);
        if (updatedPrecastLimits != null) {
            return new ResponseEntity<>(updatedPrecastLimits, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Delete PrecastLimits by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePrecastLimits(@PathVariable Integer id) {
        precastLimitsService.deletePrecastLimits(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    @GetMapping("/limits")
    public PrecastLimits getPrecastLimits(@ RequestParam String quality, @RequestParam Integer recipeNo) {
        return precastLimitsService.getPrecastLimits(quality, recipeNo);
    }
}
