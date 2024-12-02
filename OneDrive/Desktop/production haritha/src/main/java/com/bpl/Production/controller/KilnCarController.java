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

import com.bpl.Production.entity.KilnCar;
import com.bpl.Production.service.KilnCarService;

@RestController
@RequestMapping("/api/kilncar")
public class KilnCarController {

	@Autowired
	private KilnCarService kilnCarService;
    
 // Create a new KilnCar
    @PostMapping
    public ResponseEntity<KilnCar> createKilnCar(@RequestBody KilnCar kilnCar) {
        KilnCar createdKilnCar = kilnCarService.createKilnCar(kilnCar);
        return ResponseEntity.ok(createdKilnCar);
    }

    // Get KilnCar by ID
    @GetMapping("/{id}")
    public ResponseEntity<KilnCar> getKilnCarById(@PathVariable Integer id) {
        Optional<KilnCar> kilnCar = kilnCarService.getKilnCarById(id);
        return kilnCar.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Get all KilnCars
    @GetMapping
    public ResponseEntity<List<KilnCar>> getAllKilnCars() {
        List<KilnCar> kilnCars = kilnCarService.getAllKilnCars();
        return ResponseEntity.ok(kilnCars);
    }

    // Delete KilnCar by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteKilnCar(@PathVariable Integer id) {
        kilnCarService.deleteKilnCar(id);
        return ResponseEntity.noContent().build();
    }

    // Update KilnCar
    @PutMapping("/{id}")
    public ResponseEntity<KilnCar> updateKilnCar(@PathVariable Integer id, @RequestBody KilnCar kilnCarDetails) {
        KilnCar updatedKilnCar = kilnCarService.updateKilnCar(id, kilnCarDetails);
        return ResponseEntity.ok(updatedKilnCar);
    }
}
