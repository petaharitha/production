package com.bpl.Production.controller;

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

import com.bpl.Production.entity.Forming;
import com.bpl.Production.service.FormingService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/forming")
public class FormingController {

	@Autowired
	private FormingService formingService;

    // Create Forming
    @PostMapping
    public ResponseEntity<Forming> createForming(@Valid @RequestBody Forming forming) {
        Forming savedForming = formingService.createForming(forming);
        return new ResponseEntity<>(savedForming, HttpStatus.CREATED);
    }

    // Get all Forming records
    @GetMapping
    public ResponseEntity<List<Forming>> getAllFormings() {
        List<Forming> formings = formingService.getAllFormings();
        return new ResponseEntity<>(formings, HttpStatus.OK);
    }

    // Get Forming by ID
    @GetMapping("/{id}")
    public ResponseEntity<Forming> getFormingById(@PathVariable Integer id) {
        Optional<Forming> forming = formingService.getFormingById(id);
        if (forming.isPresent()) {
            return new ResponseEntity<>(forming.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    // Delete Forming by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteForming(@PathVariable Integer id) {
        Optional<Forming> forming = formingService.getFormingById(id);
        if (forming.isPresent()) {
            formingService.deleteForming(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    // Update Forming
    @PutMapping("/{id}")
    public ResponseEntity<Forming> updateForming(@PathVariable Integer id, 
                                                 @Valid @RequestBody Forming formingDetails) {
        Forming updatedForming = formingService.updateForming(id, formingDetails);
        if (updatedForming != null) {
            return new ResponseEntity<>(updatedForming, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    
    @GetMapping("/formingData")
    public ResponseEntity<List<Forming>> getFormingData(
            @RequestParam List<String> hpg,
            @RequestParam List<String> shift,
            @RequestParam(required = false) Date fromDate,
            @RequestParam(required = false) Date toDate) {
        List<Forming> formings = formingService.getFormingData(hpg, shift, fromDate, toDate);
        return ResponseEntity.ok(formings);
    }
    
    @GetMapping("/getResultsByDateRange")
    public ResponseEntity<List<Forming>> getResultsByDateRange(
            @RequestParam(required = false) Date fromDate,
            @RequestParam(required = false) Date toDate,
            @RequestParam(required = false) List<String> hpg,
            @RequestParam(required = false) List<String> shift) {
        List<Forming> results;
        if (hpg != null && shift != null) {
            results = formingService.getResultsByHpgAndShift(hpg, shift, fromDate, toDate);
        } else {
            results = formingService.getResultsByDateRange(fromDate, toDate);
        }
        return ResponseEntity.ok(results);
    }
}
