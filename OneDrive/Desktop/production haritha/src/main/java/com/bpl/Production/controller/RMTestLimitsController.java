package com.bpl.Production.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
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

import com.bpl.Production.entity.RMTestLimits;
import com.bpl.Production.service.RMTestLimitsService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/RMTestLimits")
public class RMTestLimitsController {

    @Autowired
    private RMTestLimitsService rmTestLimitsService;

    // Create a new entity
    @PostMapping
    public ResponseEntity<RMTestLimits> createEntity(@Valid @RequestBody RMTestLimits rmTestLimits) {
    	RMTestLimits createdEntity = rmTestLimitsService.createLimits(rmTestLimits);
        return new ResponseEntity<>(createdEntity, HttpStatus.CREATED);
    }

    // Update an existing entity
    @PutMapping("/{id}")
    public ResponseEntity<RMTestLimits> updateEntity(@PathVariable Integer id, @Valid @RequestBody RMTestLimits rmTestLimits) {
    	RMTestLimits updatedEntity = rmTestLimitsService.updateLimits(id, rmTestLimits);
        return new ResponseEntity<>(updatedEntity, HttpStatus.OK);
    }

    // Delete an entity
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEntity(@PathVariable Integer id) {
    	rmTestLimitsService.deleteLimit(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    // Edit an existing entity (same as update)
    @PutMapping("/{id}/edit")
    public ResponseEntity<RMTestLimits> editEntity(@PathVariable Integer id, @Valid @RequestBody RMTestLimits rmTestLimits) {
    	RMTestLimits editedEntity = rmTestLimitsService.editLimit(id, rmTestLimits);
        return new ResponseEntity<>(editedEntity, HttpStatus.OK);
    }
    
    @GetMapping
    public Page<RMTestLimits> getEntitiesWithPagination(
            @RequestParam(defaultValue = "0") int page, 
            @RequestParam(defaultValue = "10") int size) {
        return rmTestLimitsService.getEntitiesWithPagination(page, size);
    }
}
