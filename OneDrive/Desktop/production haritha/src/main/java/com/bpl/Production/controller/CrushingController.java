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

import com.bpl.Production.entity.Crushing;
import com.bpl.Production.service.CrushingService;

@RestController
@RequestMapping("/api/crushing")
public class CrushingController {

	@Autowired
	private CrushingService crushingService;
	
	// Create a new crushing entry
    @PostMapping
    public ResponseEntity<Crushing> createCrushing(@RequestBody Crushing crushing) {
        Crushing createdCrushing = crushingService.saveCrushing(crushing);
        return new ResponseEntity<>(createdCrushing, HttpStatus.CREATED);
    }

    // Get crushing entry by ID
    @GetMapping("/{id}")
    public ResponseEntity<Crushing> getCrushingById(@PathVariable Integer id) {
        Optional<Crushing> crushing = crushingService.getCrushingById(id);
        if (crushing.isPresent()) {
            return new ResponseEntity<>(crushing.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    // Update an existing crushing entry
    @PutMapping("/{id}")
    public ResponseEntity<Crushing> updateCrushing(@PathVariable Integer id, @RequestBody Crushing crushingDetails) {
        Crushing updatedCrushing = crushingService.updateCrushing(id, crushingDetails);
        if (updatedCrushing != null) {
            return new ResponseEntity<>(updatedCrushing, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    // Delete crushing entry by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCrushing(@PathVariable Integer id) {
        boolean isDeleted = crushingService.deleteCrushing(id);
        if (isDeleted) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    // Get all crushing entries
    @GetMapping
    public ResponseEntity<Iterable<Crushing>> getAllCrushing() {
        Iterable<Crushing> crushingList = crushingService.getAllCrushing();
        return new ResponseEntity<>(crushingList, HttpStatus.OK);
    }
}
