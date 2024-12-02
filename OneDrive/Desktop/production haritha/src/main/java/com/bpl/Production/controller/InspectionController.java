package com.bpl.Production.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bpl.Production.entity.Inspection;
import com.bpl.Production.service.InspectionService;

@RestController
@RequestMapping("/api/inspection")
public class InspectionController {

	@Autowired
	private InspectionService inspectionService;
	
	// Create Inspection
    @PostMapping
    public ResponseEntity<Inspection> createInspection(@RequestBody Inspection inspection) {
        Inspection createdInspection = inspectionService.createInspection(inspection);
        return ResponseEntity.ok(createdInspection);
    }

    // Get Inspection by ID
    @GetMapping("/{id}")
    public ResponseEntity<Inspection> getInspectionById(@PathVariable Integer id) {
        Optional<Inspection> inspection = inspectionService.getInspectionById(id);
        if (inspection.isPresent()) {
            return ResponseEntity.ok(inspection.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Get all Inspections
    @GetMapping
    public ResponseEntity<List<Inspection>> getAllInspections() {
        List<Inspection> inspections = inspectionService.getAllInspections();
        return ResponseEntity.ok(inspections);
    }

    // Delete Inspection by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteInspection(@PathVariable Integer id) {
        inspectionService.deleteInspection(id);
        return ResponseEntity.noContent().build();
    }

    // Update Inspection
    @PutMapping("/{id}")
    public ResponseEntity<Inspection> updateInspection(@PathVariable Integer id, @RequestBody Inspection inspectionDetails) {
        Inspection updatedInspection = inspectionService.updateInspection(id, inspectionDetails);
        return ResponseEntity.ok(updatedInspection);
    }
}
