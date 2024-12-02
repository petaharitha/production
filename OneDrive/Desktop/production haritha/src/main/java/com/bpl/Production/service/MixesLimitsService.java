package com.bpl.Production.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Optional;

import com.bpl.Production.entity.MixesLimits;
import com.bpl.Production.repository.MixesLimitsRepository;

@Service
public class MixesLimitsService {

    private static final Logger logger = LoggerFactory.getLogger(MixesLimitsService.class); // Logger initialization

    @Autowired
    private MixesLimitsRepository mixesLimitsRepository;
    
    // Create MixesLimits
    public MixesLimits saveMixesLimits(MixesLimits mixesLimits) {
        logger.info("Saving or updating MixesLimits record: {}", mixesLimits);
        MixesLimits savedMixesLimits = mixesLimitsRepository.save(mixesLimits);
        logger.info("MixesLimits record saved/updated with ID: {}", savedMixesLimits.getId());
        return savedMixesLimits;
    }

    // Get MixesLimits by ID
    public MixesLimits getMixesLimitsById(Integer id) {
        logger.info("Fetching MixesLimits record with ID: {}", id);
        Optional<MixesLimits> mixesLimits = mixesLimitsRepository.findById(id);
        if (mixesLimits.isPresent()) {
            logger.info("MixesLimits record found with ID: {}", id);
            return mixesLimits.get();
        } else {
            logger.warn("No MixesLimits record found with ID: {}", id);
            return null; // Return null if not found
        }
    }

    // Get all MixesLimits
    public List<MixesLimits> getAllMixesLimits() {
        logger.info("Fetching all MixesLimits records");
        List<MixesLimits> mixesLimitsList = mixesLimitsRepository.findAll();
        logger.info("Found {} MixesLimits records", mixesLimitsList.size());
        return mixesLimitsList;
    }

    // Delete MixesLimits by ID
    public void deleteMixesLimits(Integer id) {
        logger.info("Attempting to delete MixesLimits record with ID: {}", id);
        if (mixesLimitsRepository.existsById(id)) {
            mixesLimitsRepository.deleteById(id);
            logger.info("MixesLimits record with ID: {} deleted successfully", id);
        } else {
            logger.warn("No MixesLimits record found with ID: {} for deletion", id);
        }
    }

    // Update MixesLimits by ID
    public MixesLimits updateMixesLimits(Integer id, MixesLimits mixesLimitsDetails) {
        logger.info("Attempting to update MixesLimits record with ID: {}", id);
        
        MixesLimits mixesLimits = mixesLimitsRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("MixesLimits not found with id: " + id));

        // Log the details before updating
        logger.info("Updating MixesLimits record with ID: {}: {}", id, mixesLimitsDetails);

        // Set updated values
        mixesLimits.setCreatedBy(mixesLimitsDetails.getCreatedBy());
        mixesLimits.setLastModifiedBy(mixesLimitsDetails.getLastModifiedBy());
        mixesLimits.setCreatedDate(mixesLimitsDetails.getCreatedDate());
        mixesLimits.setLastModifiedDate(mixesLimitsDetails.getLastModifiedDate());
        mixesLimits.setValidateFrom(mixesLimitsDetails.getValidateFrom());
        mixesLimits.setExpiredFrom(mixesLimitsDetails.getExpiredFrom());
        mixesLimits.setRecipeNo(mixesLimitsDetails.getRecipeNo());
        mixesLimits.setQuality(mixesLimitsDetails.getQuality());
        
        // Update the fields (repeat for all fields that need to be updated)
        mixesLimits.setLessthanTwelveMMTarget(mixesLimitsDetails.getLessthanTwelveMMTarget());
        mixesLimits.setLessthanTwelveMMMinimum(mixesLimitsDetails.getLessthanTwelveMMMinimum());
        mixesLimits.setLessthanTwelveMMMaximum(mixesLimitsDetails.getLessthanTwelveMMMaximum());
        
        mixesLimits.setLessthanSixThreeMMTarget(mixesLimitsDetails.getLessthanSixThreeMMTarget());
        mixesLimits.setLessthanSixThreeMMMinimum(mixesLimitsDetails.getLessthanSixThreeMMMinimum());
        mixesLimits.setLessthanSixThreeMMMaximum(mixesLimitsDetails.getLessthanSixThreeMMMaximum());
        
        mixesLimits.setLessthanFiveSixMMTarget(mixesLimitsDetails.getLessthanFiveSixMMTarget());
        mixesLimits.setLessthanFiveSixMMMinimum(mixesLimitsDetails.getLessthanFiveSixMMMinimum());
        mixesLimits.setLessthanFiveSixMMMaximum(mixesLimitsDetails.getLessthanFiveSixMMMaximum());
        
        mixesLimits.setLessthanFourMMTarget(mixesLimitsDetails.getLessthanFourMMTarget());
        mixesLimits.setLessthanFourMMMinimum(mixesLimitsDetails.getLessthanFourMMMinimum());
        mixesLimits.setLessthanFourMMMaximum(mixesLimitsDetails.getLessthanFourMMMaximum());

        mixesLimits.setWaterPercentageTarget(mixesLimitsDetails.getWaterPercentageTarget());
        mixesLimits.setWaterPercentageMinimum(mixesLimitsDetails.getWaterPercentageMinimum());
        mixesLimits.setWaterPercentageMaximum(mixesLimitsDetails.getWaterPercentageMaximum());

        // Save the updated entity
        MixesLimits updatedMixesLimits = mixesLimitsRepository.save(mixesLimits);
        logger.info("MixesLimits record with ID: {} updated successfully", updatedMixesLimits.getId());
        
        return updatedMixesLimits;
    }
}
