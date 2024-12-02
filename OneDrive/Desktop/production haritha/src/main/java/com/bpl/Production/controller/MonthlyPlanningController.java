package com.bpl.Production.controller;

import org.springframework.beans.factory.annotation.Autowired;
import com.bpl.Production.entity.MonthlyPlanning;
import com.bpl.Production.service.MonthlyPlanningService;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/monthly-planning")
public class MonthlyPlanningController {

    @Autowired
    private MonthlyPlanningService monthlyPlanningService;
    
    // Create or Update MonthlyPlanning
    @PostMapping
    public ResponseEntity<MonthlyPlanning> createOrUpdateMonthlyPlanning(@RequestBody MonthlyPlanning monthlyPlanning) {
        MonthlyPlanning savedMonthlyPlanning = monthlyPlanningService.createOrUpdateMonthlyPlanning(monthlyPlanning);
        return ResponseEntity.ok(savedMonthlyPlanning);
    }

    // Get all MonthlyPlannings
    @GetMapping
    public ResponseEntity<List<MonthlyPlanning>> getAllMonthlyPlannings() {
        List<MonthlyPlanning> monthlyPlannings = monthlyPlanningService.getAllMonthlyPlannings();
        return ResponseEntity.ok(monthlyPlannings);
    }

    // Get MonthlyPlanning by ID
    @GetMapping("/{id}")
    public ResponseEntity<MonthlyPlanning> getMonthlyPlanningById(@PathVariable Integer id) {
        Optional<MonthlyPlanning> monthlyPlanning = monthlyPlanningService.getMonthlyPlanningById(id);
        if (monthlyPlanning.isPresent()) {
            return ResponseEntity.ok(monthlyPlanning.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Delete MonthlyPlanning by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMonthlyPlanning(@PathVariable Integer id) {
        Optional<MonthlyPlanning> existingPlanning = monthlyPlanningService.getMonthlyPlanningById(id);
        if (existingPlanning.isPresent()) {
            monthlyPlanningService.deleteMonthlyPlanning(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Update MonthlyPlanning by ID
    @PutMapping("/{id}")
    public ResponseEntity<MonthlyPlanning> updateMonthlyPlanning(@PathVariable Integer id, @RequestBody MonthlyPlanning updatedMonthlyPlanning) {
        Optional<MonthlyPlanning> existingPlanning = monthlyPlanningService.getMonthlyPlanningById(id);
        
        if (existingPlanning.isPresent()) {
            MonthlyPlanning updatedPlanning = monthlyPlanningService.updateMonthlyPlanning(id, updatedMonthlyPlanning);
            return ResponseEntity.ok(updatedPlanning);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
