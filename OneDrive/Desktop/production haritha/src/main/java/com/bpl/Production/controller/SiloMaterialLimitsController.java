package com.bpl.Production.controller;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

import com.bpl.Production.entity.SiloMaterialLimits;
import com.bpl.Production.service.SiloMaterialLimitsService;
import com.bpl.Production.xml.SiloMaterialLimitsImportExport;

@RestController
@RequestMapping("/api/silo-material-limits")
public class SiloMaterialLimitsController {

    @Autowired
    private SiloMaterialLimitsService service;
    
    @Autowired
    private SiloMaterialLimitsImportExport siloMaterialLimitsImportExport;

    // Create
    @PostMapping
    public ResponseEntity<SiloMaterialLimits> createSiloMaterialLimit(@RequestBody SiloMaterialLimits siloMaterialLimits) {
        SiloMaterialLimits created = service.createSiloMaterialLimit(siloMaterialLimits);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    // Update
    @PutMapping("/{id}")
    public ResponseEntity<SiloMaterialLimits> updateSiloMaterialLimit(
            @PathVariable Integer id,
            @RequestBody SiloMaterialLimits siloMaterialLimits) {
        SiloMaterialLimits updated = service.updateSiloMaterialLimit(id, siloMaterialLimits);
        if (updated != null) {
            return new ResponseEntity<>(updated, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    // Delete
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSiloMaterialLimit(@PathVariable Integer id) {
        if (service.deleteSiloMaterialLimit(id)) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    // Get all with pagination
    @GetMapping
    public ResponseEntity<Page<SiloMaterialLimits>> getAllSiloMaterialLimits(
            @RequestParam int page,
            @RequestParam int size,
            @RequestParam String sortBy,
            @RequestParam String direction) {
        Page<SiloMaterialLimits> result = service.getSiloMaterialLimits(page, size, sortBy, direction);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    // Filter method with pagination
    @GetMapping("/filter")
    public ResponseEntity<Page<SiloMaterialLimits>> filterSiloMaterialLimits(
            @RequestParam String smNo,
            @RequestParam String smDescription,
            @RequestParam int page,
            @RequestParam int size,
            @RequestParam String sortBy,
            @RequestParam String direction) {
        Page<SiloMaterialLimits> result = service.filterSiloMaterialLimits(smNo, smDescription, page, size, sortBy, direction);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
    
    // Export Excel
    @GetMapping("/export")
    public ResponseEntity<byte[]> exportExcel() throws IOException, IllegalAccessException {
        List<SiloMaterialLimits> data = fetchData(); // Replace with actual service call
        return siloMaterialLimitsImportExport.exportToExcel(data, SiloMaterialLimits.class);
    }

    // Import Excel
    @PostMapping("/import")
    public List<SiloMaterialLimits> importExcel(@RequestParam("file") MultipartFile file) throws IOException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
        return siloMaterialLimitsImportExport.importFromExcel(file, SiloMaterialLimits.class);
    }
    
    private List<SiloMaterialLimits> fetchData() {
        // Replace with actual DB fetching logic
        return List.of(
                new SiloMaterialLimits(/* Initialize your entity here */)
        );
    }
}
