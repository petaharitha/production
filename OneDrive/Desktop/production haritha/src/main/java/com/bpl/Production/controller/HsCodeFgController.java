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

import com.bpl.Production.entity.HsCodeFg;
import com.bpl.Production.service.HsCodeFgService;

@RestController
@RequestMapping("/api/hs_code_fg")
public class HsCodeFgController {

	@Autowired
	private HsCodeFgService hsCodeFgService;
	
	// Create or Update HsCodeFg
    @PostMapping
    public ResponseEntity<HsCodeFg> createHsCodeFg(@RequestBody HsCodeFg hsCodeFg) {
        HsCodeFg savedHsCodeFg = hsCodeFgService.createHsCodeFg(hsCodeFg);
        return new ResponseEntity<>(savedHsCodeFg, HttpStatus.CREATED);
    }

    // Get HsCodeFg by ID
    @GetMapping("/{id}")
    public ResponseEntity<HsCodeFg> getHsCodeFgById(@PathVariable Integer id) {
        Optional<HsCodeFg> hsCodeFg = hsCodeFgService.getHsCodeFgById(id);
        return hsCodeFg
                .map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // Get all HsCodeFg records
    @GetMapping
    public ResponseEntity<List<HsCodeFg>> getAllHsCodeFg() {
        List<HsCodeFg> hsCodeFgList = hsCodeFgService.getAllHsCodeFg();
        return new ResponseEntity<>(hsCodeFgList, HttpStatus.OK);
    }

    // Delete HsCodeFg by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteHsCodeFg(@PathVariable Integer id) {
        hsCodeFgService.deleteHsCodeFg(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    // Update HsCodeFg by ID
    @PutMapping("/{id}")
    public ResponseEntity<HsCodeFg> updateHsCodeFg(@PathVariable Integer id, @RequestBody HsCodeFg hsCodeFg) {
        HsCodeFg updatedHsCodeFg = hsCodeFgService.updateHsCodeFg(id, hsCodeFg);
        return updatedHsCodeFg != null
                ? new ResponseEntity<>(updatedHsCodeFg, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
