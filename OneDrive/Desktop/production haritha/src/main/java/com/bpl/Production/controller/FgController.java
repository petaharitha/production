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

import com.bpl.Production.entity.Fg;
import com.bpl.Production.service.FgService;

@RestController
@RequestMapping("/api/fg")
public class FgController {

	@Autowired
	private FgService fgService;
	
	// Create or Update Fg
    @PostMapping
    public ResponseEntity<Fg> createOrUpdateFg(@RequestBody Fg fg) {
        Fg savedFg = fgService.createOrUpdateFg(fg);
        return new ResponseEntity<>(savedFg, HttpStatus.CREATED);
    }

    // Get Fg by ID
    @GetMapping("/{id}")
    public ResponseEntity<Fg> getFgById(@PathVariable Integer id) {
        Optional<Fg> fgOptional = fgService.getFgById(id);
        return fgOptional.map(fg -> new ResponseEntity<>(fg, HttpStatus.OK))
                         .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // Get all Fgs
    @GetMapping
    public ResponseEntity<List<Fg>> getAllFgs() {
        List<Fg> fgList = fgService.getAllFgs();
        return new ResponseEntity<>(fgList, HttpStatus.OK);
    }

    // Delete Fg by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFg(@PathVariable Integer id) {
        fgService.deleteFg(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    // Update Fg by ID
    @PutMapping("/{id}")
    public ResponseEntity<Fg> updateFg(@PathVariable Integer id, @RequestBody Fg updatedFg) {
        Fg fg = fgService.updateFg(id, updatedFg);
        return fg != null ? new ResponseEntity<>(fg, HttpStatus.OK)
                          : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

}
