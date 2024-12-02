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

import com.bpl.Production.entity.HpgPg;
import com.bpl.Production.service.HpgPgService;

@RestController
@RequestMapping("/api/hpg-pg")
public class HpgPgController {

	@Autowired
	private HpgPgService hpgPgService;
	
	// Create HpgPg
    @PostMapping
    public ResponseEntity<HpgPg> createHpgPg(@RequestBody HpgPg hpgPg) {
        HpgPg savedHpgPg = hpgPgService.createHpgPg(hpgPg);
        return new ResponseEntity<>(savedHpgPg, HttpStatus.CREATED);
    }

    // Get HpgPg by ID
    @GetMapping("/{id}")
    public ResponseEntity<HpgPg> getHpgPgById(@PathVariable Integer id) {
        Optional<HpgPg> hpgPg = hpgPgService.getHpgPgById(id);
        return hpgPg
                .map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // Get all HpgPg records
    @GetMapping
    public ResponseEntity<List<HpgPg>> getAllHpgPg() {
        List<HpgPg> hpgPgList = hpgPgService.getAllHpgPg();
        return new ResponseEntity<>(hpgPgList, HttpStatus.OK);
    }

    // Delete HpgPg by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteHpgPg(@PathVariable Integer id) {
        hpgPgService.deleteHpgPg(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    // Update HpgPg by ID
    @PutMapping("/{id}")
    public ResponseEntity<HpgPg> updateHpgPg(@PathVariable Integer id, @RequestBody HpgPg hpgPg) {
        HpgPg updatedHpgPg = hpgPgService.updateHpgPg(id, hpgPg);
        return updatedHpgPg != null
                ? new ResponseEntity<>(updatedHpgPg, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
