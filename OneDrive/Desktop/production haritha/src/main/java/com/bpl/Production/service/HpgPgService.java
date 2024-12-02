package com.bpl.Production.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.bpl.Production.entity.HpgPg;
import com.bpl.Production.repository.HpgPgRepository;

import java.util.List;
import java.util.Optional;

@Service
public class HpgPgService {

    private static final Logger logger = LoggerFactory.getLogger(HpgPgService.class); // Initialize logger

    @Autowired
    private HpgPgRepository hpgPgRepository;

    // Create HpgPg record
    public HpgPg createHpgPg(HpgPg hpgPg) {
        logger.info("Creating new HpgPg record: {}", hpgPg);
        HpgPg savedHpgPg = hpgPgRepository.save(hpgPg);
        logger.info("HpgPg record created with ID: {}", savedHpgPg.getId());
        return savedHpgPg;
    }

    // Get HpgPg by ID
    public Optional<HpgPg> getHpgPgById(Integer id) {
        logger.info("Fetching HpgPg record with ID: {}", id);
        Optional<HpgPg> hpgPg = hpgPgRepository.findById(id);
        if (hpgPg.isPresent()) {
            logger.info("HpgPg record found with ID: {}", id);
        } else {
            logger.warn("No HpgPg record found with ID: {}", id);
        }
        return hpgPg;
    }

    // Get all HpgPg records
    public List<HpgPg> getAllHpgPg() {
        logger.info("Fetching all HpgPg records");
        List<HpgPg> hpgPgList = hpgPgRepository.findAll();
        logger.info("Found {} HpgPg records", hpgPgList.size());
        return hpgPgList;
    }

    // Delete HpgPg record by ID
    public void deleteHpgPg(Integer id) {
        logger.info("Attempting to delete HpgPg record with ID: {}", id);
        if (hpgPgRepository.existsById(id)) {
            hpgPgRepository.deleteById(id);
            logger.info("HpgPg record with ID: {} deleted successfully", id);
        } else {
            logger.warn("No HpgPg record found with ID: {} for deletion", id);
        }
    }

    // Update HpgPg by ID
    public HpgPg updateHpgPg(Integer id, HpgPg hpgPg) {
        logger.info("Attempting to update HpgPg record with ID: {}", id);

        Optional<HpgPg> existingHpgPg = hpgPgRepository.findById(id);
        if (existingHpgPg.isPresent()) {
            HpgPg updatedHpgPg = existingHpgPg.get();

            // Logging fields before updating
            logger.info("Updating HpgPg record with ID: {}", id);

            // Ensure that the ID is set to the correct value
            updatedHpgPg.setId(id); 

            // Set other fields to the new values
            updatedHpgPg.setCreatedBy(hpgPg.getCreatedBy());
            updatedHpgPg.setLastModifiedBy(hpgPg.getLastModifiedBy());
            updatedHpgPg.setCreatedDate(hpgPg.getCreatedDate());
            updatedHpgPg.setLastModifiedDate(hpgPg.getLastModifiedDate());
            updatedHpgPg.setHpg(hpgPg.getHpg());
            updatedHpgPg.setPg(hpgPg.getPg());
            updatedHpgPg.setDescription(hpgPg.getDescription());

            // Save the updated HpgPg record
            HpgPg savedHpgPg = hpgPgRepository.save(updatedHpgPg);
            logger.info("HpgPg record updated successfully with ID: {}", savedHpgPg.getId());
            return savedHpgPg;
        } else {
            logger.warn("No HpgPg record found with ID: {} for update", id);
            return null; // Return null or throw custom exception if not found
        }
    }
}
