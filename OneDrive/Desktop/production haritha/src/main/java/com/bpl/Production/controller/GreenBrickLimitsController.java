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

import com.bpl.Production.entity.GreenBrickLimits;
import com.bpl.Production.service.GreenBrickLimitsService;

@RestController
@RequestMapping("/api/green-brick-limits")
public class GreenBrickLimitsController {

	@Autowired
	private GreenBrickLimitsService greenBrickLimitsService;
	
	// Create GreenBrickLimit
    @PostMapping
    public ResponseEntity<GreenBrickLimits> createOrUpdateGreenBrickLimit(@RequestBody GreenBrickLimits greenBrickLimits) {
        GreenBrickLimits savedGreenBrickLimits = greenBrickLimitsService.createGreenBrickLimit(greenBrickLimits);
        return new ResponseEntity<>(savedGreenBrickLimits, HttpStatus.CREATED);
    }

    // Get GreenBrickLimit by ID
    @GetMapping("/{id}")
    public ResponseEntity<GreenBrickLimits> getGreenBrickLimitById(@PathVariable Integer id) {
        Optional<GreenBrickLimits> greenBrickLimits = greenBrickLimitsService.getGreenBrickLimitById(id);
        return greenBrickLimits
                .map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // Get all GreenBrickLimits
    @GetMapping
    public ResponseEntity<List<GreenBrickLimits>> getAllGreenBrickLimits() {
        List<GreenBrickLimits> greenBrickLimitsList = greenBrickLimitsService.getAllGreenBrickLimits();
        return new ResponseEntity<>(greenBrickLimitsList, HttpStatus.OK);
    }

    // Delete GreenBrickLimit by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteGreenBrickLimit(@PathVariable Integer id) {
        greenBrickLimitsService.deleteGreenBrickLimit(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    // Update GreenBrickLimit by ID
    @PutMapping("/{id}")
    public ResponseEntity<GreenBrickLimits> updateGreenBrickLimit(@PathVariable Integer id, 
                                                                  @RequestBody GreenBrickLimits greenBrickLimits) {
        GreenBrickLimits updatedGreenBrickLimits = greenBrickLimitsService.updateGreenBrickLimit(id, greenBrickLimits);
        return updatedGreenBrickLimits != null
                ? new ResponseEntity<>(updatedGreenBrickLimits, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
