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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bpl.Production.entity.PackingPallet;
import com.bpl.Production.service.PackingPalletService;

@RestController
@RequestMapping("/api/packing-pallet")
public class PackingPalletController {
	
	@Autowired
	private PackingPalletService packingPalletService;
	
	// Create a new PackingPallet
    @PostMapping
    public ResponseEntity<PackingPallet> createPackingPallet(@RequestBody PackingPallet packingPallet) {
        PackingPallet createdPackingPallet = packingPalletService.createPackingPallet(packingPallet);
        return ResponseEntity.ok(createdPackingPallet);
    }

    // Get all PackingPallets
    @GetMapping
    public ResponseEntity<List<PackingPallet>> getAllPackingPallets() {
        List<PackingPallet> packingPallets = packingPalletService.getAllPackingPallets();
        return ResponseEntity.ok(packingPallets);
    }

    // Get PackingPallet by ID
    @GetMapping("/{id}")
    public ResponseEntity<PackingPallet> getPackingPalletById(@PathVariable Integer id) {
        Optional<PackingPallet> packingPalletOptional = packingPalletService.getPackingPalletById(id);
        if (packingPalletOptional.isPresent()) {
            return ResponseEntity.ok(packingPalletOptional.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Update PackingPallet by ID
    @PutMapping("/{id}")
    public ResponseEntity<PackingPallet> updatePackingPallet(
            @PathVariable Integer id, 
            @RequestBody PackingPallet packingPalletDetails) {
        PackingPallet updatedPackingPallet = packingPalletService.updatePackingPallet(id, packingPalletDetails);
        if (updatedPackingPallet != null) {
            return ResponseEntity.ok(updatedPackingPallet);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Delete PackingPallet by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePackingPallet(@PathVariable Integer id) {
        packingPalletService.deletePackingPallet(id);
        return ResponseEntity.noContent().build();
    }
    
    @GetMapping("/report")
    public Object getReport() {
        return packingPalletService.getProcessedReport();
    }
    
    @GetMapping("/report/hpg")
    public List<PackingPallet> getFinishedGoodsReport(@RequestParam String hpg) {
        return packingPalletService.getReportByHpg(hpg);
    }
}
