package com.bpl.Production.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Optional;

import com.bpl.Production.entity.KilnCar;
import com.bpl.Production.repository.KilnCarRepository;

@Service
public class KilnCarService {

    private static final Logger logger = LoggerFactory.getLogger(KilnCarService.class); // Logger initialization

    @Autowired
    private KilnCarRepository kilnCarRepository;
    
    // Create a new KilnCar
    public KilnCar createKilnCar(KilnCar kilnCar) {
        logger.info("Creating new KilnCar record: {}", kilnCar);
        KilnCar savedKilnCar = kilnCarRepository.save(kilnCar);
        logger.info("KilnCar record created with ID: {}", savedKilnCar.getId());
        return savedKilnCar;
    }

    // Get KilnCar by ID
    public Optional<KilnCar> getKilnCarById(Integer id) {
        logger.info("Fetching KilnCar record with ID: {}", id);
        Optional<KilnCar> kilnCar = kilnCarRepository.findById(id);
        if (kilnCar.isPresent()) {
            logger.info("KilnCar record found with ID: {}", id);
        } else {
            logger.warn("No KilnCar record found with ID: {}", id);
        }
        return kilnCar;
    }

    // Get all KilnCars
    public List<KilnCar> getAllKilnCars() {
        logger.info("Fetching all KilnCar records");
        List<KilnCar> kilnCars = kilnCarRepository.findAll();
        logger.info("Found {} KilnCar records", kilnCars.size());
        return kilnCars;
    }

    // Delete KilnCar by ID
    public void deleteKilnCar(Integer id) {
        logger.info("Attempting to delete KilnCar record with ID: {}", id);
        if (kilnCarRepository.existsById(id)) {
            kilnCarRepository.deleteById(id);
            logger.info("KilnCar record with ID: {} deleted successfully", id);
        } else {
            logger.warn("No KilnCar record found with ID: {} for deletion", id);
        }
    }

    // Update KilnCar
    public KilnCar updateKilnCar(Integer id, KilnCar kilnCarDetails) {
        logger.info("Attempting to update KilnCar record with ID: {}", id);

        KilnCar kilnCar = kilnCarRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("KilnCar not found with id: " + id));

        // Logging the update process
        logger.info("Updating KilnCar record with ID: {}", id);

        // Set updated values
        kilnCar.setCreatedBy(kilnCarDetails.getCreatedBy());
        kilnCar.setLastModifiedBy(kilnCarDetails.getLastModifiedBy());
        kilnCar.setCreatedDate(kilnCarDetails.getCreatedDate());
        kilnCar.setLastModifiedDate(kilnCarDetails.getLastModifiedDate());
        kilnCar.setNo(kilnCarDetails.getNo());

        // Save and return updated KilnCar record
        KilnCar updatedKilnCar = kilnCarRepository.save(kilnCar);
        logger.info("KilnCar record with ID: {} updated successfully", updatedKilnCar.getId());
        return updatedKilnCar;
    }
}
