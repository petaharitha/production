package com.bpl.Production.service;


import java.sql.Date;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bpl.Production.entity.FiredBrickTestResults;
import com.bpl.Production.repository.FiredBrickTestResultsRepo;

@Service
public class FiredBrickTestResultsService {

    private static final Logger logger = LoggerFactory.getLogger(FiredBrickTestResultsService.class); // Initialize logger

    @Autowired
    private FiredBrickTestResultsRepo firedBrickTestResultsRepo;

    // Create FiredBrickTestResults    
    public FiredBrickTestResults save(FiredBrickTestResults firedBrickTestResults) {
        logger.info("Saving FiredBrickTestResults: {}", firedBrickTestResults);
        FiredBrickTestResults savedEntity = firedBrickTestResultsRepo.save(firedBrickTestResults);
        logger.info("Saved FiredBrickTestResults with ID: {}", savedEntity.getId());
        return savedEntity;
    }

    // Update FiredBrickTestResults
    public FiredBrickTestResults update(FiredBrickTestResults firedBrickTestResults) {
        logger.info("Updating FiredBrickTestResults with ID: {}", firedBrickTestResults.getId());
        
        if (firedBrickTestResults.getId() != null) {
            Optional<FiredBrickTestResults> existingFiredBrickTestResults = firedBrickTestResultsRepo.findById(firedBrickTestResults.getId());
            
            if (existingFiredBrickTestResults.isPresent()) {
                // Log that the entity was found
                logger.info("Found existing FiredBrickTestResults for ID: {}", firedBrickTestResults.getId());
                
                // Update existing entity
                FiredBrickTestResults existingEntity = existingFiredBrickTestResults.get();
                // Set updated values to the existing entity
                existingEntity.setDate(firedBrickTestResults.getDate());
                existingEntity.setShift(firedBrickTestResults.getShift());
                existingEntity.setLabSampleNo(firedBrickTestResults.getLabSampleNo());
                existingEntity.setSoNo(firedBrickTestResults.getSoNo());
                existingEntity.setMaterialNo(firedBrickTestResults.getMaterialNo());
                existingEntity.setPoPosNo(firedBrickTestResults.getPoPosNo());
                existingEntity.setRecipeNo(firedBrickTestResults.getRecipeNo());
                existingEntity.setAltNo(firedBrickTestResults.getAltNo());
                existingEntity.setHpg(firedBrickTestResults.getHpg());
                existingEntity.setPg(firedBrickTestResults.getPg());
                existingEntity.setQuality(firedBrickTestResults.getQuality());
                existingEntity.setShape(firedBrickTestResults.getShape());
                existingEntity.setApPercentage(firedBrickTestResults.getApPercentage());

                // Save updated entity
                FiredBrickTestResults updatedEntity = firedBrickTestResultsRepo.save(existingEntity);
                logger.info("Updated FiredBrickTestResults with ID: {}", updatedEntity.getId());
                return updatedEntity;
            }
        }
        
        // If not found, create a new entity
        logger.info("No existing FiredBrickTestResults found for ID: {}, creating new entry.", firedBrickTestResults.getId());
        return firedBrickTestResultsRepo.save(firedBrickTestResults);
    }

    // Get all FiredBrickTestResults
    public List<FiredBrickTestResults> getAllFiredBrickTestResults() {
        logger.info("Fetching all FiredBrickTestResults");
        List<FiredBrickTestResults> results = firedBrickTestResultsRepo.findAll();
        logger.info("Found {} FiredBrickTestResults", results.size());
        return results;
    }


  

    // Delete FiredBrickTestResults by id
    public void deleteFiredBrickTestResultsById(Integer id) {
        logger.info("Deleting FiredBrickTestResults with ID: {}", id);
        firedBrickTestResultsRepo.deleteById(id);
        logger.info("Deleted FiredBrickTestResults with ID: {}", id);
    }
   
    public List<FiredBrickTestResults> getResultsByDateRange(Date fromDate, Date toDate) {
        return firedBrickTestResultsRepo.findByDateRange(fromDate, toDate);
    
    }
    // Fetch results based on custom filters
    public List<FiredBrickTestResults> getResultsBySoNo(String soNo) {
        return firedBrickTestResultsRepo.findBySoNo(soNo);
    }

//    public List<FiredBrickTestResults> getResultsByDateAndQuality(Date startDate, Date endDate, String quality, String altNo) {
//        return firedBrickTestResultsRepo.findByDateBetweenAndQualityAndAltNo(startDate, endDate, quality, altNo);
//    }

	public FiredBrickTestResults getFiredBrickTestResultsById(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}
}
