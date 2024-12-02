package com.bpl.Production.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.bpl.Production.entity.GreenBrickLimits;
import com.bpl.Production.repository.GreenBrickLimitsRepository;

@Service
public class GreenBrickLimitsService {

    private static final Logger logger = LoggerFactory.getLogger(GreenBrickLimitsService.class); // Initialize logger

    @Autowired
    private GreenBrickLimitsRepository greenBrickLimitsRepository;

    // Create or update GreenBrickLimit
    public GreenBrickLimits createGreenBrickLimit(GreenBrickLimits greenBrickLimits) {
        logger.info("Creating or updating GreenBrickLimit: {}", greenBrickLimits);
        GreenBrickLimits savedGreenBrickLimits = greenBrickLimitsRepository.save(greenBrickLimits);
        logger.info("GreenBrickLimit created/updated with ID: {}", savedGreenBrickLimits.getId());
        return savedGreenBrickLimits;
    }

    // Get GreenBrickLimit by ID
    public Optional<GreenBrickLimits> getGreenBrickLimitById(Integer id) {
        logger.info("Fetching GreenBrickLimit with ID: {}", id);
        Optional<GreenBrickLimits> greenBrickLimit = greenBrickLimitsRepository.findById(id);
        if (greenBrickLimit.isPresent()) {
            logger.info("GreenBrickLimit found with ID: {}", id);
        } else {
            logger.warn("No GreenBrickLimit found with ID: {}", id);
        }
        return greenBrickLimit;
    }

    // Get all GreenBrickLimits
    public List<GreenBrickLimits> getAllGreenBrickLimits() {
        logger.info("Fetching all GreenBrickLimits");
        List<GreenBrickLimits> greenBrickLimits = greenBrickLimitsRepository.findAll();
        logger.info("Found {} GreenBrickLimits", greenBrickLimits.size());
        return greenBrickLimits;
    }

    // Delete GreenBrickLimit by ID
    public void deleteGreenBrickLimit(Integer id) {
        logger.info("Attempting to delete GreenBrickLimit with ID: {}", id);
        if (greenBrickLimitsRepository.existsById(id)) {
            greenBrickLimitsRepository.deleteById(id);
            logger.info("GreenBrickLimit with ID: {} deleted successfully", id);
        } else {
            logger.warn("No GreenBrickLimit found with ID: {} for deletion", id);
        }
    }

    // Update GreenBrickLimit
    public GreenBrickLimits updateGreenBrickLimit(Integer id, GreenBrickLimits greenBrickLimits) {
        logger.info("Attempting to update GreenBrickLimit with ID: {}", id);

        // Check if the GreenBrickLimit exists
        Optional<GreenBrickLimits> existingGreenBrickLimit = greenBrickLimitsRepository.findById(id);
        if (existingGreenBrickLimit.isPresent()) {
            GreenBrickLimits updatedGreenBrickLimit = existingGreenBrickLimit.get();
            logger.info("GreenBrickLimit found for update with ID: {}", id);

            // Updating fields with new values
            updatedGreenBrickLimit.setCreatedBy(greenBrickLimits.getCreatedBy());
            updatedGreenBrickLimit.setLastModifiedBy(greenBrickLimits.getLastModifiedBy());
            updatedGreenBrickLimit.setCreatedDate(greenBrickLimits.getCreatedDate());
            updatedGreenBrickLimit.setLastModifiedDate(greenBrickLimits.getLastModifiedDate());
            updatedGreenBrickLimit.setValidateFrom(greenBrickLimits.getValidateFrom());
            updatedGreenBrickLimit.setExpiredFrom(greenBrickLimits.getExpiredFrom());
            updatedGreenBrickLimit.setRecipeNo(greenBrickLimits.getRecipeNo());
            updatedGreenBrickLimit.setQuality(greenBrickLimits.getQuality());
            updatedGreenBrickLimit.setMoisturePercentageTarget(greenBrickLimits.getMoisturePercentageTarget());
            updatedGreenBrickLimit.setMoisturePercentageMinimum(greenBrickLimits.getMoisturePercentageMinimum());
            updatedGreenBrickLimit.setMoisturePercentageMaximum(greenBrickLimits.getMoisturePercentageMaximum());
            updatedGreenBrickLimit.setLessthanOneMMTarget(greenBrickLimits.getLessthanOneMMTarget());
            updatedGreenBrickLimit.setLessthanOneMMMinimum(greenBrickLimits.getLessthanOneMMMinimum());
            updatedGreenBrickLimit.setLessthanOneMMMaximum(greenBrickLimits.getLessthanOneMMMaximum());
            updatedGreenBrickLimit.setLessthanZeroOnehundredsixMMTarget(greenBrickLimits.getLessthanZeroOnehundredsixMMTarget());
            updatedGreenBrickLimit.setLessthanZeroOnehundredsixMMMinimum(greenBrickLimits.getLessthanZeroOnehundredsixMMMinimum());
            updatedGreenBrickLimit.setLessthanZeroOnehundredsixMMMaximum(greenBrickLimits.getLessthanZeroOnehundredsixMMMaximum());
            updatedGreenBrickLimit.setGbdGmSlashCCTarget(greenBrickLimits.getGbdGmSlashCCTarget());
            updatedGreenBrickLimit.setGbdGmSlashCCMinimum(greenBrickLimits.getGbdGmSlashCCMinimum());
            updatedGreenBrickLimit.setGbdGmSlashCCMaximum(greenBrickLimits.getGbdGmSlashCCMaximum());

            // Save and return the updated GreenBrickLimit
            GreenBrickLimits savedGreenBrickLimit = greenBrickLimitsRepository.save(updatedGreenBrickLimit);
            logger.info("GreenBrickLimit updated with ID: {}", savedGreenBrickLimit.getId());
            return savedGreenBrickLimit;
        } else {
            // If the GreenBrickLimit doesn't exist, log the error
            logger.warn("No GreenBrickLimit found with ID: {} for update", id);
            return null;
        }
    }
}
