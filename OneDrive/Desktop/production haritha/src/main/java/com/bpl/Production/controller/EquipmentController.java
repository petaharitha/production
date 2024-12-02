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

import com.bpl.Production.entity.Equipment;
import com.bpl.Production.service.EquipmentService;

@RestController
@RequestMapping("/api/equipment")
public class EquipmentController {

	@Autowired
	private EquipmentService equipmentService;
	
	// Create or Update Equipment
    @PostMapping
    public ResponseEntity<Equipment> createeEquipment(@RequestBody Equipment equipment) {
        Equipment updatedEquipment = equipmentService.createEquipment(equipment);
        return new ResponseEntity<>(updatedEquipment, HttpStatus.CREATED);
    }

    // Get Equipment by ID
    @GetMapping("/{id}")
    public ResponseEntity<Equipment> getEquipmentById(@PathVariable("id") Integer id) {
        Optional<Equipment> equipment = equipmentService.getEquipmentById(id);
        return equipment.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                        .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // Get All Equipment
    @GetMapping
    public ResponseEntity<List<Equipment>> getAllEquipments() {
        List<Equipment> equipments = equipmentService.getAllEquipments();
        return new ResponseEntity<>(equipments, HttpStatus.OK);
    }

    // Delete Equipment by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEquipment(@PathVariable("id") Integer id) {
        Optional<Equipment> equipment = equipmentService.getEquipmentById(id);
        if (equipment.isPresent()) {
            equipmentService.deleteEquipment(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);  // Success, no content returned
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);  // Equipment not found
        }
    }

    // Update Equipment by ID
    @PutMapping("/{id}")
    public ResponseEntity<Equipment> updateEquipment(@PathVariable("id") Integer id, @RequestBody Equipment updatedEquipment) {
        Equipment updated = equipmentService.updateEquipment(id, updatedEquipment);
        if (updated != null) {
            return new ResponseEntity<>(updated, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);  // Equipment not found
        }
    }
}
