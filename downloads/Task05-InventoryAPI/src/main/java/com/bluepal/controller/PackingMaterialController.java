package com.bluepal.controller;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

import com.bluepal.entity.PackingMaterial;
import com.bluepal.service.PackingMaterialService;

@RestController
@RequestMapping("/api/inventory/packing-materials")
public class PackingMaterialController {

    @Autowired
    private PackingMaterialService packingMaterialService;

    // Create a new PackingMaterial
    @PostMapping
    public ResponseEntity<PackingMaterial> createPackingMaterial(@RequestHeader("Authorization") String jwt ,@RequestBody PackingMaterial packingMaterial) {
        PackingMaterial createdMaterial = packingMaterialService.createPackingMaterial(packingMaterial);
        return ResponseEntity.ok(createdMaterial);
    }

    // Get a PackingMaterial by ID
    @GetMapping("/{id}")
    public ResponseEntity<PackingMaterial> getPackingMaterialById(@RequestHeader("Authorization") String jwt ,@PathVariable Long id) {
        PackingMaterial packingMaterial = packingMaterialService.getPackingMaterialById(id);
        if (packingMaterial != null) {
            return ResponseEntity.ok(packingMaterial);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Get all PackingMaterials with Pagination
    @GetMapping
    public ResponseEntity<Page<PackingMaterial>> getAllPackingMaterials(@RequestHeader("Authorization") String jwt , Pageable pageable) {
        Page<PackingMaterial> packingMaterials = packingMaterialService.getAllPackingMaterials(pageable);
        return ResponseEntity.ok(packingMaterials);
    }

    // Update PackingMaterial by ID
    @PutMapping("/{id}")
    public ResponseEntity<PackingMaterial> updatePackingMaterial(@RequestHeader("Authorization") String jwt ,@PathVariable Long id, @RequestBody PackingMaterial packingMaterial) {
        PackingMaterial updatedMaterial = packingMaterialService.updatePackingMaterial(id, packingMaterial);
        return ResponseEntity.ok(updatedMaterial);
    }

    // Delete PackingMaterial by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePackingMaterial(@RequestHeader("Authorization") String jwt ,@PathVariable Long id) {
        packingMaterialService.deletePackingMaterial(id);
        return ResponseEntity.noContent().build();
    }

    // Search PackingMaterials with Pagination and Sorting
    @GetMapping("/search")
    public ResponseEntity<Page<PackingMaterial>> searchPackingMaterials(
    		@RequestHeader("Authorization") String jwt ,
            @RequestParam(required = false) String materialNo,
            @RequestParam(required = false) String materialGroup,
            @RequestParam(required = false) String materialName,
            Pageable pageable) {
        Page<PackingMaterial> result = packingMaterialService.searchPackingMaterials(materialNo, materialGroup, materialName, pageable);
        return ResponseEntity.ok(result);
    }

    // Import PackingMaterials from Excel File
    @PostMapping("/import")
    public ResponseEntity<String> importPackingMaterials(@RequestHeader("Authorization") String jwt ,@RequestParam("file") MultipartFile file) {
        try {
            packingMaterialService.importPackingMaterials(file);
            return ResponseEntity.ok("Packing materials imported successfully");
        } catch (IOException e) {
            return ResponseEntity.status(500).body("Failed to import packing materials: " + e.getMessage());
        }
    }

    // Export PackingMaterials to Excel
    @GetMapping("/export")
    public ResponseEntity<String> exportPackingMaterialsToExcel(@RequestHeader("Authorization") String jwt ) {
        try {
            List<PackingMaterial> materials = packingMaterialService.getAllPackingMaterials(Pageable.unpaged()).getContent();
            String outputFilePath = "packing_materials.xlsx";
            packingMaterialService.exportPackingMaterialsToExcel(materials, outputFilePath);
            return ResponseEntity.ok("Packing materials exported successfully to " + outputFilePath);
        } catch (IOException e) {
            return ResponseEntity.status(500).body("Failed to export packing materials: " + e.getMessage());
        }
    }
    @GetMapping("/generate-report")
    public ResponseEntity<byte[]> generateReport(@RequestHeader("Authorization") String jwt ,@RequestParam(required = false) String materialNo,
                                                 @RequestParam(required = false) String materialName) {
        ByteArrayOutputStream outputStream = packingMaterialService.generateMouldSpecsReport(materialNo, materialName);
        return ResponseEntity.ok()
                .header("Content-Disposition", "attachment; filename=mould_specs_report.pdf")
                .body(outputStream.toByteArray());
    }
  
}