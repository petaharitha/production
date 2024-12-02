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

import com.bpl.Production.entity.HsCodeRawMaterial;
import com.bpl.Production.service.HsCodeRawMaterialService;

@RestController
@RequestMapping("/api/hs-code-raw-material")
public class HsCodeRawMaterialContrller {

	@Autowired
	private HsCodeRawMaterialService hsCodeRawMaterialService;
	
	// Create or Update HsCodeRawMaterial
    @PostMapping
    public ResponseEntity<HsCodeRawMaterial> createOrUpdateHsCodeRawMaterial(@RequestBody HsCodeRawMaterial hsCodeRawMaterial) {
        HsCodeRawMaterial savedHsCodeRawMaterial = hsCodeRawMaterialService.createHsCodeRawMaterial(hsCodeRawMaterial);
        return new ResponseEntity<>(savedHsCodeRawMaterial, HttpStatus.CREATED);
    }

    // Get HsCodeRawMaterial by ID
    @GetMapping("/{id}")
    public ResponseEntity<HsCodeRawMaterial> getHsCodeRawMaterialById(@PathVariable Integer id) {
        Optional<HsCodeRawMaterial> hsCodeRawMaterial = hsCodeRawMaterialService.getHsCodeRawMaterialById(id);
        return hsCodeRawMaterial
                .map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // Get all HsCodeRawMaterial records
    @GetMapping
    public ResponseEntity<List<HsCodeRawMaterial>> getAllHsCodeRawMaterials() {
        List<HsCodeRawMaterial> hsCodeRawMaterialList = hsCodeRawMaterialService.getAllHsCodeRawMaterials();
        return new ResponseEntity<>(hsCodeRawMaterialList, HttpStatus.OK);
    }

    // Delete HsCodeRawMaterial by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteHsCodeRawMaterial(@PathVariable Integer id) {
        hsCodeRawMaterialService.deleteHsCodeRawMaterial(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    // Update HsCodeRawMaterial by ID
    @PutMapping("/{id}")
    public ResponseEntity<HsCodeRawMaterial> updateHsCodeRawMaterial(@PathVariable Integer id, @RequestBody HsCodeRawMaterial hsCodeRawMaterial) {
        HsCodeRawMaterial updatedHsCodeRawMaterial = hsCodeRawMaterialService.updateHsCodeRawMaterial(id, hsCodeRawMaterial);
        return updatedHsCodeRawMaterial != null
                ? new ResponseEntity<>(updatedHsCodeRawMaterial, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
