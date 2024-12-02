package com.bpl.Production.service;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bpl.Production.entity.PrecastLimits;
import com.bpl.Production.repository.PrecastLimitsRepository;

@Service
public class PrecastLimitsService {

    private static final Logger logger = LoggerFactory.getLogger(PrecastLimitsService.class); // Logger initialization

    @Autowired
    private PrecastLimitsRepository precastLimitsRepository;
    
    // Create new PrecastLimits entry
    public PrecastLimits createPrecastLimits(PrecastLimits precastLimits) {
        logger.info("Creating new PrecastLimits entry: {}", precastLimits);
        PrecastLimits createdPrecastLimits = precastLimitsRepository.save(precastLimits);
        logger.info("Created PrecastLimits entry with ID: {}", createdPrecastLimits.getId());
        return createdPrecastLimits;
    }

    // Get all PrecastLimits entries
    public List<PrecastLimits> getAllPrecastLimits() {
        logger.info("Fetching all PrecastLimits entries");
        List<PrecastLimits> precastLimits = precastLimitsRepository.findAll();
        logger.info("Found {} PrecastLimits entries", precastLimits.size());
        return precastLimits;
    }

    // Get PrecastLimits by ID
    public Optional<PrecastLimits> getPrecastLimitsById(Integer id) {
        logger.info("Fetching PrecastLimits entry with ID: {}", id);
        Optional<PrecastLimits> precastLimits = precastLimitsRepository.findById(id);
        if (precastLimits.isPresent()) {
            logger.info("Found PrecastLimits entry: {}", precastLimits.get());
        } else {
            logger.warn("PrecastLimits entry with ID {} not found", id);
        }
        return precastLimits;
    }

    // Update PrecastLimits by ID
    public PrecastLimits updatePrecastLimits(Integer id, PrecastLimits precastLimitsDetails) {
        logger.info("Updating PrecastLimits entry with ID: {}", id);
        Optional<PrecastLimits> existingPrecastLimits = precastLimitsRepository.findById(id);
        if (existingPrecastLimits.isPresent()) {
            PrecastLimits precastLimits = existingPrecastLimits.get();
            
            // Update fields
            logger.info("Updating fields for PrecastLimits with ID: {}", id);
            precastLimits.setCreatedBy(precastLimitsDetails.getCreatedBy());
            precastLimits.setLastModifiedBy(precastLimitsDetails.getLastModifiedBy());
            precastLimits.setCreatedDate(precastLimitsDetails.getCreatedDate());
            precastLimits.setLastModifiedDate(precastLimitsDetails.getLastModifiedDate());
            precastLimits.setValidateFrom(precastLimitsDetails.getValidateFrom());
            precastLimits.setExpiredFrom(precastLimitsDetails.getExpiredFrom());
            precastLimits.setRecipeNo(precastLimitsDetails.getRecipeNo());
            precastLimits.setQuality(precastLimitsDetails.getQuality());
            precastLimits.setApPercentageAtThreefiftyDegressCelsiusTarget(precastLimitsDetails.getApPercentageAtThreefiftyDegressCelsiusTarget());
            precastLimits.setApPercentageAtThreefiftyDegressCelsiusMaximum(precastLimitsDetails.getApPercentageAtThreefiftyDegressCelsiusMaximum());
            precastLimits.setApPercentageAtThreefiftyDegressCelsiusMinimum(precastLimitsDetails.getApPercentageAtThreefiftyDegressCelsiusMinimum());
            precastLimits.setBdGSlashCcAtThreefiftyDegressCelsiusTarget(precastLimitsDetails.getBdGSlashCcAtThreefiftyDegressCelsiusTarget());
            precastLimits.setBdGSlashCcAtThreefiftyDegressCelsiusMaximum(precastLimitsDetails.getBdGSlashCcAtThreefiftyDegressCelsiusMaximum());
            precastLimits.setBdGSlashCcAtThreefiftyDegressCelsiusMinimum(precastLimitsDetails.getBdGSlashCcAtThreefiftyDegressCelsiusMinimum());
            precastLimits.setCcsNSlashMmTwoAtThreefiftyDegressCelsiusTarget(precastLimitsDetails.getCcsNSlashMmTwoAtThreefiftyDegressCelsiusTarget());
            precastLimits.setCcsNSlashMmTwoAtThreefiftyDegressCelsiusMaximum(precastLimitsDetails.getCcsNSlashMmTwoAtThreefiftyDegressCelsiusMaximum());
            precastLimits.setCcsNSlashMmTwoAtThreefiftyDegressCelsiusMinimum(precastLimitsDetails.getCcsNSlashMmTwoAtThreefiftyDegressCelsiusMinimum());

            PrecastLimits updatedPrecastLimits = precastLimitsRepository.save(precastLimits);
            logger.info("Updated PrecastLimits entry with ID: {}", updatedPrecastLimits.getId());
            return updatedPrecastLimits;
        } else {
            logger.warn("PrecastLimits entry with ID {} not found, unable to update", id);
            return null; // or throw an exception
        }
    }

    // Delete PrecastLimits by ID
    public void deletePrecastLimits(Integer id) {
        logger.info("Deleting PrecastLimits entry with ID: {}", id);
        precastLimitsRepository.deleteById(id);
        logger.info("Deleted PrecastLimits entry with ID: {}", id);
    }
    public PrecastLimits getPrecastLimits(String quality, Integer recipeNo) {
        List<PrecastLimits> limits = precastLimitsRepository.findByQualityAndRecipeNoAndExpiredFromIsNull(quality, recipeNo);
        return limits.isEmpty() ? null : limits.get(0);
    }
}
