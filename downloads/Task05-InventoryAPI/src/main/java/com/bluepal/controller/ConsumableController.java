package com.bluepal.controller;


import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.bluepal.entity.Consumable;
import com.bluepal.service.ConsumableService;

@RestController
@RequestMapping("/api/inventory/consumables")
public class ConsumableController {

    @Autowired
    private ConsumableService consumableService;

    // Create a new consumable
    @PostMapping("/create")
    public ResponseEntity<Consumable> createConsumable(@RequestHeader("Authorization") String jwt,@RequestBody Consumable consumable) {
        Consumable createdConsumable = consumableService.createConsumable(consumable);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdConsumable);
    }

    // Update an existing consumable
    @PutMapping("/update/{id}")
    public ResponseEntity<Consumable> updateConsumable(@RequestHeader("Authorization") String jwt,@PathVariable("id") Long id, @RequestBody Consumable consumable) {
        Consumable updatedConsumable = consumableService.updateConsumable(id, consumable);
        return ResponseEntity.ok(updatedConsumable);
    }

    // Delete a consumable by ID
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteConsumable(@RequestHeader("Authorization") String jwt,@PathVariable("id") Long id) {
        consumableService.deleteConsumable(id);
        return ResponseEntity.noContent().build();
    }

    // Get a consumable by ID
    @GetMapping("/{id}")
    public ResponseEntity<Consumable> getConsumableById(@RequestHeader("Authorization") String jwt ,@PathVariable("id") Long id) {
        Consumable consumable = consumableService.getConsumableById(id);
        return ResponseEntity.ok(consumable);
    }

    // Get all consumables with pagination and sorting
    @GetMapping("/all")
    public ResponseEntity<List<Consumable>> getAllConsumables(
    		@RequestHeader("Authorization") String jwt ,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "materialName") String sortBy) {
        List<Consumable> consumables = consumableService.getAllConsumables(page, size, sortBy);
        return ResponseEntity.ok(consumables);
    }

    // Search consumables by materialName and materialGroup
    @GetMapping("/search")
    public ResponseEntity<Page<Consumable>> searchConsumables(
    		@RequestHeader("Authorization") String jwt ,
            @RequestParam(required = false) String materialName,
            @RequestParam(required = false) String materialGroup,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "materialName") String sortBy) {

        // Create Pageable object with pagination and sorting
        PageRequest pageable = PageRequest.of(page, size, Sort.by(sortBy));
        
        // Fetch results from service
        Page<Consumable> consumablesPage = consumableService.searchConsumables(materialName, materialGroup, pageable);
        
        // Return the page as a ResponseEntity
        return ResponseEntity.ok(consumablesPage);
    }

    // Export consumables to Excel
    @GetMapping("/export")
    public ResponseEntity<byte[]> exportConsumables(@RequestHeader("Authorization") String jwt ) throws IOException {
        // Generate the Excel file as a byte array
        byte[] fileContent = consumableService.exportConsumablesToExcel();

        // Set up headers for the file download
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "attachment; filename=consumables.xlsx");

        // Return the byte array in the response with correct content type
        return ResponseEntity.ok()
                             .headers(headers)
                             .contentType(MediaType.parseMediaType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"))
                             .body(fileContent);
    }

    // Import consumables from Excel
    @PostMapping("/import")
    public ResponseEntity<String> importConsumablesFromExcel(@RequestHeader("Authorization") String jwt ,@RequestParam("file") MultipartFile file) {
        try {
            consumableService.importConsumablesFromExcel(file);
            return ResponseEntity.status(HttpStatus.OK).body("Consumables imported successfully.");
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error occurred while importing consumables.");
        }
    }
}
