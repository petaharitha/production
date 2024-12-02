package com.bpl.Production.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.bpl.Production.entity.HsCodeFg;
import com.bpl.Production.repository.HsCodeFgRepository;

@Service
public class HsCodeFgService {

    private static final Logger logger = LoggerFactory.getLogger(HsCodeFgService.class); // Initialize logger

    @Autowired
    private HsCodeFgRepository hsCodeFgRepository;

    // Create HsCodeFg record
    public HsCodeFg createHsCodeFg(HsCodeFg hsCodeFg) {
        logger.info("Creating new HsCodeFg record: {}", hsCodeFg);
        HsCodeFg savedHsCodeFg = hsCodeFgRepository.save(hsCodeFg);
        logger.info("HsCodeFg record created with ID: {}", savedHsCodeFg.getId());
        return savedHsCodeFg;
    }

    // Get HsCodeFg by ID
    public Optional<HsCodeFg> getHsCodeFgById(Integer id) {
        logger.info("Fetching HsCodeFg record with ID: {}", id);
        Optional<HsCodeFg> hsCodeFg = hsCodeFgRepository.findById(id);
        if (hsCodeFg.isPresent()) {
            logger.info("HsCodeFg record found with ID: {}", id);
        } else {
            logger.warn("No HsCodeFg record found with ID: {}", id);
        }
        return hsCodeFg;
    }

    // Get all HsCodeFg records
    public List<HsCodeFg> getAllHsCodeFg() {
        logger.info("Fetching all HsCodeFg records");
        List<HsCodeFg> hsCodeFgList = hsCodeFgRepository.findAll();
        logger.info("Found {} HsCodeFg records", hsCodeFgList.size());
        return hsCodeFgList;
    }

    // Delete HsCodeFg record by ID
    public void deleteHsCodeFg(Integer id) {
        logger.info("Attempting to delete HsCodeFg record with ID: {}", id);
        if (hsCodeFgRepository.existsById(id)) {
            hsCodeFgRepository.deleteById(id);
            logger.info("HsCodeFg record with ID: {} deleted successfully", id);
        } else {
            logger.warn("No HsCodeFg record found with ID: {} for deletion", id);
        }
    }

    // Update HsCodeFg by ID
    public HsCodeFg updateHsCodeFg(Integer id, HsCodeFg hsCodeFg) {
        logger.info("Attempting to update HsCodeFg record with ID: {}", id);

        Optional<HsCodeFg> existingHsCodeFg = hsCodeFgRepository.findById(id);
        if (existingHsCodeFg.isPresent()) {
            HsCodeFg updatedHsCodeFg = existingHsCodeFg.get();

            // Logging fields before updating
            logger.info("Updating HsCodeFg record with ID: {}", id);

            // Ensure that the ID is set to the correct value
            updatedHsCodeFg.setId(id);

            // Set other fields to the new values
            updatedHsCodeFg.setCreatedBy(hsCodeFg.getCreatedBy());
            updatedHsCodeFg.setLastModifiedBy(hsCodeFg.getLastModifiedBy());
            updatedHsCodeFg.setCreatedDate(hsCodeFg.getCreatedDate());
            updatedHsCodeFg.setLastModifiedDate(hsCodeFg.getLastModifiedDate());
            updatedHsCodeFg.setHsCode(hsCodeFg.getHsCode());
            updatedHsCodeFg.setHscDescription(hsCodeFg.getHscDescription());

            // Save the updated HsCodeFg record
            HsCodeFg savedHsCodeFg = hsCodeFgRepository.save(updatedHsCodeFg);
            logger.info("HsCodeFg record updated successfully with ID: {}", savedHsCodeFg.getId());
            return savedHsCodeFg;
        } else {
            logger.warn("No HsCodeFg record found with ID: {} for update", id);
            return null; // Return null or throw custom exception if not found
        }
    }
}
