package com.bpl.Production.controller;

import org.springframework.beans.factory.annotation.Autowired;

import com.bpl.Production.entity.Mixing;
import com.bpl.Production.service.MixingService;

import java.util.List;
import java.util.Optional;
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

@RestController
@RequestMapping("/api/mixing")
public class MixingController {

	@Autowired
	private MixingService mixingService;
	

    // Create a new Mixing record
    @PostMapping
    public ResponseEntity<Mixing> createMixing(@RequestBody Mixing mixing) {
        Mixing savedMixing = mixingService.createMixing(mixing);
        return new ResponseEntity<>(savedMixing, HttpStatus.CREATED);
    }

    // Get a Mixing record by its ID
    @GetMapping("/{id}")
    public ResponseEntity<Mixing> getMixingById(@PathVariable("id") Integer id) {
        Optional<Mixing> mixing = mixingService.getMixingById(id);
        return mixing.map(ResponseEntity::ok)
                     .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    // Get all Mixing records
    @GetMapping
    public List<Mixing> getAllMixings() {
        return mixingService.getAllMixings();
    }

    // Delete a Mixing record by its ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMixing(@PathVariable("id") Integer id) {
        Optional<Mixing> mixing = mixingService.getMixingById(id);
        if (mixing.isPresent()) {
            mixingService.deleteMixing(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    // Update a Mixing record
    @PutMapping("/{id}")
    public ResponseEntity<Mixing> updateMixing(@PathVariable("id") Integer id, @RequestBody Mixing mixingDetails) {
        try {
            Mixing updatedMixing = mixingService.updateMixing(id, mixingDetails);
            return ResponseEntity.ok(updatedMixing);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}
