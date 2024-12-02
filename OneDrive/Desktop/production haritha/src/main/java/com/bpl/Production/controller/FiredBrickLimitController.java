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

import com.bpl.Production.entity.FiredBrickLimits;
import com.bpl.Production.service.FiredBrickLimitService;

@RestController
@RequestMapping("/api/fired-brick-limits")
public class FiredBrickLimitController {

	@Autowired
    private FiredBrickLimitService firedBrickLimitsService;

    // Create or update a FiredBrickLimits record
    @PostMapping
    public ResponseEntity<FiredBrickLimits> createFiredBrickLimits(@RequestBody FiredBrickLimits firedBrickLimits) {
        FiredBrickLimits savedFiredBrickLimits = firedBrickLimitsService.saveFiredBrickLimits(firedBrickLimits);
        return new ResponseEntity<>(savedFiredBrickLimits, HttpStatus.CREATED);
    }

    // Get a FiredBrickLimits record by ID
    @GetMapping("/{id}")
    public ResponseEntity<FiredBrickLimits> getFiredBrickLimitsById(@PathVariable Integer id) {
        Optional<FiredBrickLimits> firedBrickLimits = firedBrickLimitsService.getFiredBrickLimitsById(id);
        return firedBrickLimits.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Get all FiredBrickLimits records
    @GetMapping
    public ResponseEntity<List<FiredBrickLimits>> getAllFiredBrickLimits() {
        List<FiredBrickLimits> firedBrickLimits = firedBrickLimitsService.getAllFiredBrickLimits();
        return new ResponseEntity<>(firedBrickLimits, HttpStatus.OK);
    }

    // Delete a FiredBrickLimits record by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFiredBrickLimits(@PathVariable Integer id) {
        firedBrickLimitsService.deleteFiredBrickLimits(id);
        return ResponseEntity.noContent().build();
    }

    // Update a FiredBrickLimits record by ID
    @PutMapping("/{id}")
    public ResponseEntity<FiredBrickLimits> updateFiredBrickLimits(
            @PathVariable Integer id, @RequestBody FiredBrickLimits firedBrickLimits) {
        FiredBrickLimits updatedFiredBrickLimits = firedBrickLimitsService.updateFiredBrickLimits(id, firedBrickLimits);
        return updatedFiredBrickLimits != null ?
                new ResponseEntity<>(updatedFiredBrickLimits, HttpStatus.OK) :
                new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}