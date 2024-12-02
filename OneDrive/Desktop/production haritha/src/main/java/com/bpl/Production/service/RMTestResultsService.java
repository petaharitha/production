package com.bpl.Production.service;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bpl.Production.entity.RMTestResults;
import com.bpl.Production.entity.RawMaterialReceipt;
import com.bpl.Production.repository.RMTestResultsRepository;
import com.bpl.Production.repository.RawMaterialReceiptRepository;

import jakarta.transaction.Transactional;

@Service
public class RMTestResultsService {

    private static final Logger logger = LoggerFactory.getLogger(RMTestResultsService.class);

    @Autowired
    private RMTestResultsRepository rmTestResultsRepository;
    
    @Autowired
    private RawMaterialReceiptRepository materialReceiptRepository;

    // Create a new RMTestResults
    public RMTestResults createRMTestResult(RMTestResults rmTestResults) {
        logger.info("Creating RMTestResults with data: {}", rmTestResults);
        RMTestResults createdResult = rmTestResultsRepository.save(rmTestResults);
        logger.info("RMTestResults created successfully with ID: {}", createdResult.getId());
        return createdResult;
    }

    // Get all RMTestResults
    public List<RMTestResults> getAllRMTestResults() {
        logger.info("Fetching all RMTestResults");
        List<RMTestResults> results = rmTestResultsRepository.findAll();
        logger.info("Fetched {} RMTestResults", results.size());
        return results;
    }

    // Get RMTestResult by ID
    public Optional<RMTestResults> getRMTestResultById(Integer id) {
        logger.info("Fetching RMTestResults with ID: {}", id);
        Optional<RMTestResults> result = rmTestResultsRepository.findById(id);
        if (result.isPresent()) {
            logger.info("RMTestResults with ID: {} found", id);
        } else {
            logger.warn("RMTestResults with ID: {} not found", id);
        }
        return result;
    }

    // Update an existing RMTestResults
    public RMTestResults updateRMTestResult(Integer id, RMTestResults updatedRMTestResults) {
        logger.info("Updating RMTestResults with ID: {}", id);
        if (rmTestResultsRepository.existsById(id)) {
            updatedRMTestResults.setId(id);
            RMTestResults updatedResult = rmTestResultsRepository.save(updatedRMTestResults);
            logger.info("RMTestResults with ID: {} updated successfully", id);
            return updatedResult;
        }
        logger.warn("RMTestResults with ID: {} not found for update", id);
        return null;
    }

    // Delete RMTestResult by ID
    public boolean deleteRMTestResult(Integer id) {
        logger.info("Deleting RMTestResults with ID: {}", id);
        if (rmTestResultsRepository.existsById(id)) {
            rmTestResultsRepository.deleteById(id);
            logger.info("RMTestResults with ID: {} deleted successfully", id);
            return true;
        }
        logger.warn("RMTestResults with ID: {} not found for deletion", id);
        return false;
    }
    
    // Get RMTestResults by ID
    public RMTestResults getRMTestResultsById(Integer id) {
        logger.info("Fetching RMTestResults with ID: {}", id);
        RMTestResults result = rmTestResultsRepository.findById(id).orElse(null);
        if (result != null) {
            logger.info("RMTestResults with ID: {} found", id);
        } else {
            logger.warn("RMTestResults with ID: {} not found", id);
        }
        return result;
    }

    // Get RawMaterialReceipt by SR No
    public RawMaterialReceipt getRawMaterialReceiptBySRNo(Integer srNo) {
        logger.info("Fetching RawMaterialReceipt with SR No: {}", srNo);
        RawMaterialReceipt receipt = materialReceiptRepository.findByInwardNo(srNo);
        if (receipt != null) {
            logger.info("RawMaterialReceipt with SR No: {} found", srNo);
        } else {
            logger.warn("RawMaterialReceipt with SR No: {} not found", srNo);
        }
        return receipt;
    }

    @Transactional
    public RMTestResults getRMTestResultsWithDetails(Integer id) {
        logger.info("Fetching RMTestResults with details for ID: {}", id);
        RMTestResults rmTestResults = getRMTestResultsById(id);
        if (rmTestResults != null) {
            RawMaterialReceipt rmReceipt = getRawMaterialReceiptBySRNo(rmTestResults.getSrNo());
            rmTestResults.setComment(rmReceipt != null ? rmReceipt.getComments() : "");
            logger.info("RMTestResults with ID: {} populated with RawMaterialReceipt details", id);
        } else {
            logger.warn("RMTestResults with ID: {} not found, no details to populate", id);
        }
        return rmTestResults;
    }
}
