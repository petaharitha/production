package com.bluepal.controller;



import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
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

import com.bluepal.entity.Fuel;
import com.bluepal.service.FuelImportAndExportService;
import com.bluepal.service.FuelService;

@RestController
@RequestMapping("/api/inventory/fuels")
public class FuelController {

	@Autowired
    private FuelService fuelService;
	
	@Autowired
	private FuelImportAndExportService importExportService;

    // Create a new Fuel entry
    @PostMapping
    public Fuel createFuel(@RequestHeader("Authorization") String jwt,@RequestBody Fuel fuel) {
        return fuelService.createFuel(fuel);
    }

    // Get all Fuels
    @GetMapping
    public List<Fuel> getAllFuels(@RequestHeader("Authorization") String jwt ) {
        return fuelService.getAllFuels();
    }

    // Get Fuel by ID
    @GetMapping("/{id}")
    public Fuel getFuelById(@RequestHeader("Authorization") String jwt ,@PathVariable("id") Long id) {
        return fuelService.getFuelById(id);
    }

    // Update a Fuel
    @PutMapping("/{id}")
    public Fuel updateFuel(@RequestHeader("Authorization") String jwt,@PathVariable("id") Long id, @RequestBody Fuel fuel) {
        return fuelService.updateFuel(id, fuel);
    }

    // Delete a Fuel
    @DeleteMapping("/{id}")
    public void deleteFuel(@RequestHeader("Authorization") String jwt,@PathVariable("id") Long id) {
        fuelService.deleteFuel(id);
    }

    // Search with pagination
    @GetMapping("/search")
    public Page<Fuel> searchFuels(
    		@RequestHeader("Authorization") String jwt ,
            @RequestParam(name = "keyword", required = false) String keyword,
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "10") int size,
            @RequestParam(name = "sortBy", defaultValue = "materialNo") String sortBy,
            @RequestParam(name = "sortDirection", defaultValue = "asc") String sortDirection) {
        return fuelService.searchFuels(keyword, page, size, sortBy, sortDirection);
    }
    
     @PostMapping("/import")
    public ResponseEntity<String> importFuels(@RequestHeader("Authorization") String jwt ,@RequestParam("file") MultipartFile file) {
        if (file.isEmpty()) {
            return ResponseEntity.badRequest().body("The uploaded file is empty. Please provide a valid Excel file.");
        }

        try {
            importExportService.importFuelsFromExcel(file);
            return ResponseEntity.ok("Fuels imported successfully.");
        } catch (IOException e) {
            return ResponseEntity.badRequest().body("Error importing fuels: " + e.getMessage());
        }
    }
    @GetMapping("/export")
    public ResponseEntity<byte[]> exportFuelsToExcel(@RequestHeader("Authorization") String jwt ) {
        try {
            ByteArrayInputStream in = importExportService.exportFuelsToExcel();

            // Setting headers for file download
            HttpHeaders headers = new HttpHeaders();
            headers.add("Content-Disposition", "attachment; filename=fuels.xlsx");

            // Convert input stream to byte array
            byte[] bytes = in.readAllBytes();

            return new ResponseEntity<>(bytes, headers, HttpStatus.OK);
        } catch (IOException e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}

