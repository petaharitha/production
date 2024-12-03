package com.bluepal.controller;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
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

import com.bluepal.entity.Material;
import com.bluepal.service.MaterialService;

@RestController
@RequestMapping("/api/inventory/materials")
public class MaterialController {

    @Autowired
    private MaterialService materialService;

    @PostMapping
    public ResponseEntity<Material> createMaterial(@RequestHeader("Authorization") String jwt, @RequestBody Material material) {
        return ResponseEntity.ok(materialService.createMaterial(material));
    }

    @GetMapping
    public ResponseEntity<List<Material>> getAllMaterials(
    		@RequestHeader("Authorization") String jwt ,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id") String sortBy) {
        return ResponseEntity.ok(materialService.getAllMaterials(page, size, sortBy));
    }

    @GetMapping("/search")
    public ResponseEntity<List<Material>> searchMaterials(@RequestHeader("Authorization") String jwt ,@RequestParam String keyword) {
        return ResponseEntity.ok(materialService.searchMaterials(keyword));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Material> getMaterialById(@RequestHeader("Authorization") String jwt ,@PathVariable Long id) {
        return ResponseEntity.ok(materialService.getMaterialById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Material> updateMaterial(@RequestHeader("Authorization") String jwt, @PathVariable Long id, @RequestBody Material material) {
        return ResponseEntity.ok(materialService.updateMaterial(id, material));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteMaterial(@RequestHeader("Authorization") String jwt,@PathVariable Long id) {
        materialService.deleteMaterial(id);
        return ResponseEntity.ok("Material deleted successfully.");
    }

    @PostMapping("/import")
    public ResponseEntity<String> importMaterials(@RequestHeader("Authorization") String jwt ,@RequestParam("file") MultipartFile file) {
        try {
            materialService.importMaterials(file);
            return ResponseEntity.ok("Materials imported successfully!");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error importing materials: " + e.getMessage());
        }
    }

    @GetMapping("/export")
    public ResponseEntity<Resource> exportMaterials(@RequestHeader("Authorization") String jwt ) {
        try {
            // Get the file path from the service
            String filePath = materialService.exportMaterials();

            // Create a Resource object for the file (make sure the file exists at the given path)
            Path file = Paths.get(filePath);
            Resource resource = new UrlResource(file.toUri());

            // Check if the file exists
            if (resource.exists() || resource.isReadable()) {
                // Return the file as a downloadable response
                return ResponseEntity.ok()
                        .contentType(MediaType.APPLICATION_OCTET_STREAM)
                        .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFileName().toString() + "\"")
                        .body(resource);
            } else {
                throw new RuntimeException("Could not read the file: " + filePath);
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(null); // You could return a more detailed message in case of error
        }
    }

}