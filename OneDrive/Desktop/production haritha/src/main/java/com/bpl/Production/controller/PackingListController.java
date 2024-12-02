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

import com.bpl.Production.entity.PackingList;
import com.bpl.Production.service.PackingListService;

@RestController
@RequestMapping("/api/packing-list")
public class PackingListController {

	@Autowired
	private PackingListService packingListService;
	
	// Create a new PackingList
    @PostMapping
    public ResponseEntity<PackingList> createPackingList(@RequestBody PackingList packingList) {
        PackingList createdPackingList = packingListService.createPackingList(packingList);
        return ResponseEntity.ok(createdPackingList);
    }

    // Get all PackingLists
    @GetMapping
    public ResponseEntity<List<PackingList>> getAllPackingLists() {
        List<PackingList> packingLists = packingListService.getAllPackingLists();
        return ResponseEntity.ok(packingLists);
    }

    // Get PackingList by ID
    @GetMapping("/{id}")
    public ResponseEntity<PackingList> getPackingListById(@PathVariable Integer id) {
        Optional<PackingList> packingListOptional = packingListService.getPackingListById(id);
        if (packingListOptional.isPresent()) {
            return ResponseEntity.ok(packingListOptional.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Update PackingList by ID
    @PutMapping("/{id}")
    public ResponseEntity<PackingList> updatePackingList(@PathVariable Integer id, @RequestBody PackingList packingList) {
        PackingList updatedPackingList = packingListService.updatePackingList(id, packingList);
        if (updatedPackingList != null) {
            return ResponseEntity.ok(updatedPackingList);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Delete PackingList by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePackingList(@PathVariable Integer id) {
        packingListService.deletePackingList(id);
        return ResponseEntity.noContent().build();
    }
}
