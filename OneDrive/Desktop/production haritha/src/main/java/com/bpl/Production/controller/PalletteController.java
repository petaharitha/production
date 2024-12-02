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

import com.bpl.Production.entity.Pallette;
import com.bpl.Production.service.PalletteService;

@RestController
@RequestMapping("/api/pallete")
public class PalletteController {

	@Autowired
	private PalletteService palletteService;
	
	// Create a new Pallette
    @PostMapping
    public ResponseEntity<Pallette> createPallette(@RequestBody Pallette pallette) {
        Pallette createdPallette = palletteService.createPallette(pallette);
        return ResponseEntity.ok(createdPallette);
    }

    // Get all Pallette entities
    @GetMapping
    public ResponseEntity<List<Pallette>> getAllPallettes() {
        List<Pallette> pallettes = palletteService.getAllPallettes();
        return ResponseEntity.ok(pallettes);
    }

    // Get Pallette by ID
    @GetMapping("/{id}")
    public ResponseEntity<Pallette> getPalletteById(@PathVariable Integer id) {
        Optional<Pallette> pallette = palletteService.getPalletteById(id);
        if (pallette.isPresent()) {
            return ResponseEntity.ok(pallette.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Update Pallette by ID
    @PutMapping("/{id}")
    public ResponseEntity<Pallette> updatePallette(@PathVariable Integer id, @RequestBody Pallette palletteDetails) {
        Pallette updatedPallette = palletteService.updatePallette(id, palletteDetails);
        if (updatedPallette != null) {
            return ResponseEntity.ok(updatedPallette);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Delete Pallette by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePallette(@PathVariable Integer id) {
        palletteService.deletePallette(id);
        return ResponseEntity.noContent().build();
    }
}
