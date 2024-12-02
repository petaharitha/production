package com.bpl.Production.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.bpl.Production.entity.GreenBrickTestResults;
import com.bpl.Production.repository.GreenBrickTestResultsRepository;

import java.util.List;
import java.util.Optional;

@Service
public class GreenBrickTestResultsService {

    private static final Logger logger = LoggerFactory.getLogger(GreenBrickTestResultsService.class); // Initialize logger

    @Autowired
    private GreenBrickTestResultsRepository greenBrickTestResultsRepository;

    // Create GreenBrickTestResults
    public GreenBrickTestResults createGreenBrickTestResults(GreenBrickTestResults greenBrickTestResults) {
        logger.info("Creating new GreenBrickTestResults: {}", greenBrickTestResults);
        GreenBrickTestResults savedTestResults = greenBrickTestResultsRepository.save(greenBrickTestResults);
        logger.info("GreenBrickTestResults created with ID: {}", savedTestResults.getId());
        return savedTestResults;
    }

    // Get GreenBrickTestResults by ID
    public Optional<GreenBrickTestResults> getGreenBrickTestResultsById(Integer id) {
        logger.info("Fetching GreenBrickTestResults with ID: {}", id);
        Optional<GreenBrickTestResults> greenBrickTestResults = greenBrickTestResultsRepository.findById(id);
        if (greenBrickTestResults.isPresent()) {
            logger.info("GreenBrickTestResults found with ID: {}", id);
        } else {
            logger.warn("No GreenBrickTestResults found with ID: {}", id);
        }
        return greenBrickTestResults;
    }

    // Get All GreenBrickTestResults
    public List<GreenBrickTestResults> getAllGreenBrickTestResults() {
        logger.info("Fetching all GreenBrickTestResults");
        List<GreenBrickTestResults> results = greenBrickTestResultsRepository.findAll();
        logger.info("Found {} GreenBrickTestResults", results.size());
        return results;
    }

    // Delete GreenBrickTestResults by ID
    public void deleteGreenBrickTestResults(Integer id) {
        logger.info("Attempting to delete GreenBrickTestResults with ID: {}", id);
        if (greenBrickTestResultsRepository.existsById(id)) {
            greenBrickTestResultsRepository.deleteById(id);
            logger.info("GreenBrickTestResults with ID: {} deleted successfully", id);
        } else {
            logger.warn("No GreenBrickTestResults found with ID: {} for deletion", id);
        }
    }

    // Update GreenBrickTestResults by ID
    public GreenBrickTestResults updateGreenBrickTestResults(Integer id, GreenBrickTestResults greenBrickTestResults) {
        logger.info("Attempting to update GreenBrickTestResults with ID: {}", id);

        // Check if GreenBrickTestResults exists
        Optional<GreenBrickTestResults> existingTestResults = greenBrickTestResultsRepository.findById(id);
        if (existingTestResults.isPresent()) {
            GreenBrickTestResults updatedTestResults = existingTestResults.get();

            // Logging fields before updating
            logger.info("Updating GreenBrickTestResults with ID: {}", id);

            // Set the ID to ensure the correct entity is updated
            updatedTestResults.setId(id); // Make sure the ID is set correctly

            // Update the values (set fields as per provided GreenBrickTestResults)
            updatedTestResults.setCreatedBy(greenBrickTestResults.getCreatedBy());
            updatedTestResults.setLastModifiedBy(greenBrickTestResults.getLastModifiedBy());
            updatedTestResults.setCreatedDate(greenBrickTestResults.getCreatedDate());
            updatedTestResults.setLastModifiedDate(greenBrickTestResults.getLastModifiedDate());
            updatedTestResults.setDate(greenBrickTestResults.getDate());
            updatedTestResults.setShift(greenBrickTestResults.getShift());
            updatedTestResults.setLabSampleNo(greenBrickTestResults.getLabSampleNo());
            updatedTestResults.setSoNo(greenBrickTestResults.getSoNo());
            updatedTestResults.setMaterialNo(greenBrickTestResults.getMaterialNo());
            updatedTestResults.setPoPosNo(greenBrickTestResults.getPoPosNo());
            updatedTestResults.setRecipeNo(greenBrickTestResults.getRecipeNo());
            updatedTestResults.setAltNo(greenBrickTestResults.getAltNo());
            updatedTestResults.setHpg(greenBrickTestResults.getHpg());
            updatedTestResults.setPg(greenBrickTestResults.getPg());
            updatedTestResults.setQuality(greenBrickTestResults.getQuality());
            updatedTestResults.setShape(greenBrickTestResults.getShape());
            updatedTestResults.setFreeIron(greenBrickTestResults.getFreeIron());
            updatedTestResults.setPressNo(greenBrickTestResults.getPressNo());
            updatedTestResults.setMoisturePercentage(greenBrickTestResults.getMoisturePercentage());
            updatedTestResults.setLessthanThreeFifteenMM(greenBrickTestResults.getLessthanThreeFifteenMM());
            updatedTestResults.setLessthanTwoMM(greenBrickTestResults.getLessthanTwoMM());
            updatedTestResults.setLessthanOneMM(greenBrickTestResults.getLessthanOneMM());
            updatedTestResults.setLessthanZeroFiftyMM(greenBrickTestResults.getLessthanZeroFiftyMM());
            updatedTestResults.setLessthanZeroTwentyMM(greenBrickTestResults.getLessthanZeroTwentyMM());
            updatedTestResults.setLessthanZeroOnehundredsixMM(greenBrickTestResults.getLessthanZeroOnehundredsixMM());
            updatedTestResults.setLessthanZeroNineMM(greenBrickTestResults.getLessthanZeroNineMM());
            updatedTestResults.setGbdGmSlashCC(greenBrickTestResults.getGbdGmSlashCC());
            updatedTestResults.setStatus(greenBrickTestResults.getStatus());
            updatedTestResults.setApprovalBy(greenBrickTestResults.getApprovalBy());
            updatedTestResults.setRemarks(greenBrickTestResults.getRemarks());

            // Save and return the updated GreenBrickTestResults
            GreenBrickTestResults savedTestResults = greenBrickTestResultsRepository.save(updatedTestResults);
            logger.info("GreenBrickTestResults updated successfully with ID: {}", savedTestResults.getId());
            return savedTestResults;
        } else {
            // If the GreenBrickTestResults doesn't exist, log the error
            logger.warn("No GreenBrickTestResults found with ID: {} for update", id);
            return null;
        }
    }
}
