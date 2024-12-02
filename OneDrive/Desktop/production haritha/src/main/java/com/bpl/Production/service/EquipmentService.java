package com.bpl.Production.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bpl.Production.entity.Equipment;
import com.bpl.Production.repository.EquipmentRepo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class EquipmentService {

    @Autowired
    private EquipmentRepo equipmentRepo;
    
    // Logger instance for logging
    private static final Logger logger = LoggerFactory.getLogger(EquipmentService.class);

    // Create or Update Equipment
    public Equipment createEquipment(Equipment equipment) {
        logger.info("Attempting to create or update Equipment: {}", equipment);
        return equipmentRepo.save(equipment);
    }

    // Get Equipment by ID
    public Optional<Equipment> getEquipmentById(Integer id) {
        logger.info("Fetching Equipment with ID: {}", id);
        Optional<Equipment> equipment = equipmentRepo.findById(id);
        if (equipment.isPresent()) {
            logger.info("Found Equipment with ID: {}", id);
        } else {
            logger.warn("Equipment with ID {} not found", id);
        }
        return equipment;
    }

    // Get all Equipment
    public List<Equipment> getAllEquipments() {
        logger.info("Fetching all Equipment");
        return equipmentRepo.findAll();
    }

    // Delete Equipment by ID
    public void deleteEquipment(Integer id) {
        logger.info("Attempting to delete Equipment with ID: {}", id);
        equipmentRepo.deleteById(id);
        logger.info("Equipment with ID: {} successfully deleted", id);
    }

    // Update Equipment
    public Equipment updateEquipment(Integer id, Equipment updatedEquipment) {
        logger.info("Attempting to update Equipment with ID: {}", id);
        Optional<Equipment> existingEquipmentOptional = equipmentRepo.findById(id);
        
        if (existingEquipmentOptional.isPresent()) {
            Equipment existingEquipment = existingEquipmentOptional.get();
            logger.info("Updating Equipment with ID: {}", id);

            // Update fields
            existingEquipment.setCreatedBy(updatedEquipment.getCreatedBy());
            existingEquipment.setLastModifiedBy(updatedEquipment.getLastModifiedBy());
            existingEquipment.setCreatedDate(updatedEquipment.getCreatedDate());
            existingEquipment.setLastModifiedDate(updatedEquipment.getLastModifiedDate());
            existingEquipment.setName(updatedEquipment.getName());
            existingEquipment.setDescription(updatedEquipment.getDescription());

            // Save the updated Equipment
            logger.info("Successfully updated Equipment with ID: {}", id);
            return equipmentRepo.save(existingEquipment);
        } else {
            logger.warn("Equipment with ID {} not found for update", id);
            return null; // Return null or handle this case differently (throw exception)
        }
    }
}
