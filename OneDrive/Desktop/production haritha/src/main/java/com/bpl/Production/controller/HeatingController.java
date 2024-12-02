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

import com.bpl.Production.entity.Heating;
import com.bpl.Production.service.HeatingService;

@RestController
@RequestMapping("/api/heating")
public class HeatingController {

	@Autowired
	private HeatingService heatingService;
	
	// Create or update Heating
    @PostMapping
    public ResponseEntity<Heating> createHeating(@RequestBody Heating heating) {
        Heating savedHeating = heatingService.createHeating(heating);
        return new ResponseEntity<>(savedHeating, HttpStatus.CREATED);
    }

    // Get Heating by ID
    @GetMapping("/{id}")
    public ResponseEntity<Heating> getHeatingById(@PathVariable Integer id) {
        Optional<Heating> heating = heatingService.getHeatingById(id);
        return heating
                .map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // Get all Heating records
    @GetMapping
    public ResponseEntity<List<Heating>> getAllHeating() {
        List<Heating> heatingList = heatingService.getAllHeating();
        return new ResponseEntity<>(heatingList, HttpStatus.OK);
    }

    // Delete Heating by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteHeating(@PathVariable Integer id) {
        heatingService.deleteHeating(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    // Update Heating by ID
    @PutMapping("/{id}")
    public ResponseEntity<Heating> updateHeating(@PathVariable Integer id, @RequestBody Heating heating) {
        Heating updatedHeating = heatingService.updateHeating(id, heating);
        return updatedHeating != null
                ? new ResponseEntity<>(updatedHeating, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
