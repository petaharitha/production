package com.bpl.Production.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bpl.Production.entity.PrecastTestResults;
import com.bpl.Production.repository.PrecastTestResultsRepository;

@Service
public class PrecastTestResultsService {

    private static final Logger logger = LoggerFactory.getLogger(PrecastTestResultsService.class); // Logger initialization

    @Autowired
    private PrecastTestResultsRepository precastTestResultsRepository;
    
    // Create a new PrecastTestResults
    public PrecastTestResults createPrecastTestResults(PrecastTestResults precastTestResults) {
        logger.info("Creating new PrecastTestResults entry: {}", precastTestResults);
        PrecastTestResults createdPrecastTestResults = precastTestResultsRepository.save(precastTestResults);
        logger.info("Created PrecastTestResults entry with ID: {}", createdPrecastTestResults.getId());
        return createdPrecastTestResults;
    }

    // Get all PrecastTestResults
    public List<PrecastTestResults> getAllPrecastTestResults() {
        logger.info("Fetching all PrecastTestResults entries");
        List<PrecastTestResults> results = precastTestResultsRepository.findAll();
        logger.info("Found {} PrecastTestResults entries", results.size());
        return results;
    }

    // Get PrecastTestResults by ID
    public Optional<PrecastTestResults> getPrecastTestResultsById(Integer id) {
        logger.info("Fetching PrecastTestResults entry with ID: {}", id);
        Optional<PrecastTestResults> precastTestResults = precastTestResultsRepository.findById(id);
        if (precastTestResults.isPresent()) {
            logger.info("Found PrecastTestResults entry: {}", precastTestResults.get());
        } else {
            logger.warn("PrecastTestResults entry with ID {} not found", id);
        }
        return precastTestResults;
    }

    // Update PrecastTestResults by ID
    public PrecastTestResults updatePrecastTestResults(Integer id, PrecastTestResults precastTestResultsDetails) {
        logger.info("Updating PrecastTestResults entry with ID: {}", id);
        Optional<PrecastTestResults> existingPrecastTestResultsOptional = precastTestResultsRepository.findById(id);
        if (existingPrecastTestResultsOptional.isPresent()) {
            PrecastTestResults precastTestResults = existingPrecastTestResultsOptional.get();
            
            // Log and update fields
            logger.info("Updating fields for PrecastTestResults with ID: {}", id);
            precastTestResults.setCreatedBy(precastTestResultsDetails.getCreatedBy());
            precastTestResults.setLastModifiedBy(precastTestResultsDetails.getLastModifiedBy());
            precastTestResults.setCreatedDate(precastTestResultsDetails.getCreatedDate());
            precastTestResults.setLastModifiedDate(precastTestResultsDetails.getLastModifiedDate());
            precastTestResults.setDate(precastTestResultsDetails.getDate());
            precastTestResults.setShift(precastTestResultsDetails.getShift());
            precastTestResults.setLabSampleNo(precastTestResultsDetails.getLabSampleNo());
            precastTestResults.setSoNo(precastTestResultsDetails.getSoNo());
            precastTestResults.setMaterialNo(precastTestResultsDetails.getMaterialNo());
            precastTestResults.setPoPosNo(precastTestResultsDetails.getPoPosNo());
            precastTestResults.setRecipeNo(precastTestResultsDetails.getRecipeNo());
            precastTestResults.setAltNo(precastTestResultsDetails.getAltNo());
            precastTestResults.setHpg(precastTestResultsDetails.getHpg());
            precastTestResults.setPg(precastTestResultsDetails.getPg());
            precastTestResults.setQuality(precastTestResultsDetails.getQuality());
            precastTestResults.setShape(precastTestResultsDetails.getShape());
            precastTestResults.setBatchNo(precastTestResultsDetails.getBatchNo());
            precastTestResults.setPpNo(precastTestResultsDetails.getPpNo());
            precastTestResults.setApPercentageAtThreefiftyDegressCelsius(precastTestResultsDetails.getApPercentageAtThreefiftyDegressCelsius());
            precastTestResults.setBdGSlashCcAtThreefiftyDegressCelsius(precastTestResultsDetails.getBdGSlashCcAtThreefiftyDegressCelsius());
            precastTestResults.setCcsNSlashMmTwoAtThreefiftyDegressCelsius(precastTestResultsDetails.getCcsNSlashMmTwoAtThreefiftyDegressCelsius());
            precastTestResults.setLcAtThreefiftyDegressCelsius(precastTestResultsDetails.getLcAtThreefiftyDegressCelsius());
            precastTestResults.setStatus(precastTestResultsDetails.getStatus());
            precastTestResults.setApprovalBy(precastTestResultsDetails.getApprovalBy());
            precastTestResults.setRemarks(precastTestResultsDetails.getRemarks());

            PrecastTestResults updatedPrecastTestResults = precastTestResultsRepository.save(precastTestResults);
            logger.info("Updated PrecastTestResults entry with ID: {}", updatedPrecastTestResults.getId());
            return updatedPrecastTestResults;
        } else {
            logger.warn("PrecastTestResults entry with ID {} not found, unable to update", id);
            return null; // or throw an exception
        }
    }

    // Delete PrecastTestResults by ID
    public void deletePrecastTestResults(Integer id) {
        logger.info("Deleting PrecastTestResults entry with ID: {}", id);
        precastTestResultsRepository.deleteById(id);
        logger.info("Deleted PrecastTestResults entry with ID: {}", id);
    }
    
    public List<PrecastTestResults> getPrecastTestResults(Date fromDate, Date toDate, String quality, Integer altNo ) {
        return precastTestResultsRepository.findByDateBetweenAndQualityAndAltNo(fromDate, toDate, quality, altNo);
    }
    public List<PrecastTestResults> getPrecastTestResults(String soNo) {
        return precastTestResultsRepository.findBySoNo(soNo);
    }

}
