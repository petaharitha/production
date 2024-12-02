package com.bpl.Production.controller;

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

import com.bpl.Production.entity.DispatchPlanning;
import com.bpl.Production.service.DispatchPlanningService;

@RestController
@RequestMapping("/api/dispatch-planning")
public class DispatchPlanningController {

	@Autowired
	private DispatchPlanningService dispatchPlanningService;
	// Create or update DispatchPlanning
    @PostMapping
    public ResponseEntity<DispatchPlanning> createOrUpdateDispatchPlanning(@RequestBody DispatchPlanning dispatchPlanning) {
        DispatchPlanning savedDispatchPlanning = dispatchPlanningService.saveOrUpdateDispatchPlanning(dispatchPlanning);
        return new ResponseEntity<>(savedDispatchPlanning, HttpStatus.CREATED);
    }

    // Get DispatchPlanning by ID
    @GetMapping("/{id}")
    public ResponseEntity<DispatchPlanning> getDispatchPlanningById(@PathVariable Integer id) {
        Optional<DispatchPlanning> dispatchPlanning = dispatchPlanningService.getDispatchPlanningById(id);
        return dispatchPlanning.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Get all DispatchPlannings
    @GetMapping
    public ResponseEntity<Iterable<DispatchPlanning>> getAllDispatchPlannings() {
        return ResponseEntity.ok(dispatchPlanningService.getAllDispatchPlannings());
    }

    // Delete DispatchPlanning by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDispatchPlanning(@PathVariable Integer id) {
        if (dispatchPlanningService.deleteDispatchPlanning(id)) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    // Update DispatchPlanning by ID
    @PutMapping("/{id}")
    public ResponseEntity<DispatchPlanning> updateDispatchPlanning(@PathVariable Integer id, @RequestBody DispatchPlanning updatedDispatchPlanning) {
        DispatchPlanning updatedEntity = dispatchPlanningService.updateDispatchPlanning(id, updatedDispatchPlanning);
        if (updatedEntity != null) {
            return new ResponseEntity<>(updatedEntity, HttpStatus.OK);
        }
        return ResponseEntity.notFound().build();
    }
}
