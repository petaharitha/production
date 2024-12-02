package com.bpl.Production.controller;

import java.util.List;
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

import com.bpl.Production.entity.MixesLimits;
import com.bpl.Production.service.MixesLimitsService;

@RestController
@RequestMapping("/api/mixes-limits")
public class MixesLimitsController {

    @Autowired
    private MixesLimitsService mixesLimitsService;
    
    // Create MixesLimits
    @PostMapping
    public ResponseEntity<MixesLimits> createMixesLimits(@RequestBody MixesLimits mixesLimits) {
        MixesLimits savedMixesLimits = mixesLimitsService.saveMixesLimits(mixesLimits);
        return new ResponseEntity<>(savedMixesLimits, HttpStatus.CREATED);
    }

    // Get MixesLimits by ID
    @GetMapping("/{id}")
    public ResponseEntity<MixesLimits> getMixesLimitsById(@PathVariable Integer id) {
        MixesLimits mixesLimits = mixesLimitsService.getMixesLimitsById(id);
        if (mixesLimits != null) {
            return new ResponseEntity<>(mixesLimits, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Get all MixesLimits
    @GetMapping
    public ResponseEntity<List<MixesLimits>> getAllMixesLimits() {
        List<MixesLimits> mixesLimitsList = mixesLimitsService.getAllMixesLimits();
        return new ResponseEntity<>(mixesLimitsList, HttpStatus.OK);
    }

    // Delete MixesLimits by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMixesLimits(@PathVariable Integer id) {
        mixesLimitsService.deleteMixesLimits(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT); 
    }

    // Update MixesLimits by ID
    @PutMapping("/{id}")
    public ResponseEntity<MixesLimits> updateMixesLimits(@PathVariable Integer id, @RequestBody MixesLimits mixesLimitsDetails) {
        MixesLimits updatedMixesLimits = mixesLimitsService.updateMixesLimits(id, mixesLimitsDetails);
        return new ResponseEntity<>(updatedMixesLimits, HttpStatus.OK);
    }
}
