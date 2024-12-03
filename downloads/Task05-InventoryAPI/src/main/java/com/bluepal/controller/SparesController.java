package com.bluepal.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.bluepal.entity.Spares;
import com.bluepal.service.SparesService;

import jakarta.validation.Valid;

@RestController
@EnableScheduling
@RequestMapping("/api/v1")  
public class SparesController {

    @Autowired
    private SparesService sparesService;
    
 
    /**
     * Fetch all spares.
     */
    @GetMapping
    public ResponseEntity<List<Spares>> getAllSpares() {
        List<Spares> sparesList = sparesService.getAllSpares();
        return new ResponseEntity<>(sparesList, HttpStatus.OK);
    }

    /**
     * Fetch spares with pagination and optional search.
     */
    @GetMapping("/paginated")
    public ResponseEntity<Page<Spares>> getSparesWithPagination(
            @RequestParam(required = false) Integer pageNo,
            @RequestParam(required = false) Integer pageSize,
            @RequestParam(required = false) String sortField,
            @RequestParam(required = false) String sortOrder,
            @RequestParam(required = false) String searchText,
            @RequestParam(required = false) List<String> fieldNames,
            @RequestParam(required = false) List<String> fieldValues) {
        
        // If no pagination parameters are provided, default to fetching all records
        if (pageNo == null && pageSize == null && sortField == null && sortOrder == null) {
            pageNo = 0;        // Default to the first page
            pageSize = Integer.MAX_VALUE; // Fetch all records
        }

        // If fieldNames and fieldValues are provided, ensure their sizes match
        if (fieldNames != null && fieldValues != null && fieldNames.size() != fieldValues.size()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST); // Bad request if fieldNames and fieldValues don't match in size
        }

        // Fetch the spares with pagination, sorting, filtering, and additional dynamic filters
        Page<Spares> sparesPage = sparesService.getSparesWithPaginationAndFilters(pageNo, pageSize, sortField, sortOrder, searchText, fieldNames, fieldValues);

        // Return the paginated response
        return new ResponseEntity<>(sparesPage, HttpStatus.OK);
    }
    /**
     * Fetch spares based on filter criteria.
     */
//    @GetMapping("/filter")
//    public ResponseEntity<List<Spares>> getSparesByFilters(
//            @RequestParam List<String> fieldNames,
//            @RequestParam List<String> fieldValues) {
//        
//        List<Spares> filteredSpares = sparesService.getSparesByFilters(fieldNames, fieldValues);
//        return new ResponseEntity<>(filteredSpares, HttpStatus.OK);
//    }

   
    /**
     * Fetch a spare by its material number.
     */
    @GetMapping("/object/{materialNo}")
    public ResponseEntity<Spares> getSparesByMaterialNo(@PathVariable Long materialNo) {
        Optional<Spares> spare = sparesService.getSparesByMaterialNo(materialNo);
        return spare.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    /**
     * Create a new spare.
     */
    @PostMapping("/object")
    public ResponseEntity<Spares> createSpares(@RequestBody Spares spares) {
        Spares createdSpare = sparesService.createSpares(spares);
        return new ResponseEntity<>(createdSpare, HttpStatus.CREATED);
    }

    /**
     * Update an existing spare.
     */
    @PutMapping("/object/{materialNo}")
    public ResponseEntity<Spares> updateSpares(@PathVariable Long materialNo, @RequestBody @Valid Spares sparesDetails) {
        Spares updatedSpare = sparesService.updateSpares(materialNo, sparesDetails);
        return new ResponseEntity<>(updatedSpare, HttpStatus.OK);
    }


    /**
     * Delete a spare by its materialNo.
     */
    @DeleteMapping("/object/{materialNo}")
    public ResponseEntity<Void> deleteSpares(@PathVariable Long materialNo) {
        sparesService.deleteSpares(materialNo);
        return ResponseEntity.noContent().build();
    }
    /**
     * Import spares from an Excel file.
     */
    @PostMapping("/import")
    public ResponseEntity<String> importSpares(@RequestParam("file") MultipartFile file) {
        try {
            // Static user for createdBy (you can replace "System" with any other string)
            String createdBy = "System";

            // Call the service to import spares with the static user
            sparesService.importSpares(file, createdBy);

            return new ResponseEntity<>("Spares imported successfully.", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Error importing spares: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Export spares to Excel.
     */
    @GetMapping("/export")
    public ResponseEntity<byte[]> exportSparesToExcel() {
        byte[] excelData = sparesService.exportSparesToExcel();
        if (excelData != null) {
            return ResponseEntity.ok()
                    .header("Content-Type", "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet")
                    .header("Content-Disposition", "attachment; filename=spares.xlsx")
                    .body(excelData);
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }

    /**
     * Refresh the cache with the latest data from the database.
     */
//    @PostMapping("/refresh-cache")
//    public ResponseEntity<List<Spares>> refreshCache() {
//        List<Spares> refreshedSpares = sparesService.refreshCache();
//        return new ResponseEntity<>(refreshedSpares, HttpStatus.OK);
//    }

    /**
     * Synchronize cache between server instances.
     */
    
}
