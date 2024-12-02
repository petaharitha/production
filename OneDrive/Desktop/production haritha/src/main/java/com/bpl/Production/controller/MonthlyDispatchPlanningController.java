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

import com.bpl.Production.entity.MonthlyDispatchPlanning;
import com.bpl.Production.service.MonthlyDispatchPlanningService;

@RestController
@RequestMapping("/api/monthly-dispatch-planning")
public class MonthlyDispatchPlanningController {

	@Autowired
	private MonthlyDispatchPlanningService monthlyDispatchPlanningService;
	
	// Create a new MonthlyDispatchPlanning entry
    @PostMapping
    public ResponseEntity<MonthlyDispatchPlanning> create(@RequestBody MonthlyDispatchPlanning monthlyDispatchPlanning) {
        MonthlyDispatchPlanning created = monthlyDispatchPlanningService.create(monthlyDispatchPlanning);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    // Get a MonthlyDispatchPlanning entry by ID
    @GetMapping("/{id}")
    public ResponseEntity<MonthlyDispatchPlanning> getById(@PathVariable Integer id) {
        Optional<MonthlyDispatchPlanning> monthlyDispatchPlanning = monthlyDispatchPlanningService.getById(id);
        return monthlyDispatchPlanning.map(ResponseEntity::ok)
                                     .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    // Get all MonthlyDispatchPlanning entries
    @GetMapping
    public ResponseEntity<List<MonthlyDispatchPlanning>> getAll() {
        List<MonthlyDispatchPlanning> allEntries = monthlyDispatchPlanningService.getAll();
        return new ResponseEntity<>(allEntries, HttpStatus.OK);
    }

    // Update an existing MonthlyDispatchPlanning entry
    @PutMapping("/{id}")
    public ResponseEntity<MonthlyDispatchPlanning> update(@PathVariable Integer id,
                                                           @RequestBody MonthlyDispatchPlanning updatedData) {
        MonthlyDispatchPlanning updated = monthlyDispatchPlanningService.update(id, updatedData);
        if (updated != null) {
            return new ResponseEntity<>(updated, HttpStatus.OK);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    // Delete a MonthlyDispatchPlanning entry by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        boolean isDeleted = monthlyDispatchPlanningService.delete(id);
        if (isDeleted) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
}
