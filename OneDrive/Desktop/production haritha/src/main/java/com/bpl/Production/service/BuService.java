package com.bpl.Production.service;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bpl.Production.entity.Bu;
import com.bpl.Production.repository.BuRepository;

@Service
public class BuService {

    // Logger initialization
    private static final Logger logger = LoggerFactory.getLogger(BuService.class);

    @Autowired
    private BuRepository buRepository;

    // Create a new Bu
    public Bu createBu(Bu bu) {
        logger.info("Creating new Bu: {}", bu);
        try {
            Bu savedBu = buRepository.save(bu);
            logger.info("Bu created successfully with ID: {}", savedBu.getId());
            return savedBu;
        } catch (Exception e) {
            logger.error("Error creating Bu: {}", e.getMessage());
            throw new RuntimeException("Error creating Bu", e);
        }
    }

    // Get all Bu entities
    public List<Bu> getAllBu() {
        logger.info("Fetching all Bu records");
        try {
            List<Bu> buList = buRepository.findAll();
            logger.info("Fetched {} Bu records", buList.size());
            return buList;
        } catch (Exception e) {
            logger.error("Error fetching Bu records: {}", e.getMessage());
            throw new RuntimeException("Error fetching Bu records", e);
        }
    }

    // Get Bu by ID
    public Optional<Bu> getBuById(Integer id) {
        logger.info("Fetching Bu by ID: {}", id);
        try {
            Optional<Bu> bu = buRepository.findById(id);
            if (bu.isPresent()) {
                logger.info("Found Bu with ID: {}", id);
            } else {
                logger.warn("Bu with ID: {} not found", id);
            }
            return bu;
        } catch (Exception e) {
            logger.error("Error fetching Bu with ID {}: {}", id, e.getMessage());
            throw new RuntimeException("Error fetching Bu by ID", e);
        }
    }

    // Update an existing Bu
    public Bu updateBu(Integer id, Bu buDetails) {
        logger.info("Updating Bu with ID: {}", id);
        try {
            Optional<Bu> buOptional = buRepository.findById(id);
            if (buOptional.isPresent()) {
                Bu existingBu = buOptional.get();
                existingBu.setCreatedBy(buDetails.getCreatedBy());
                existingBu.setLastModifiedBy(buDetails.getLastModifiedBy());
                existingBu.setCreatedDate(buDetails.getCreatedDate());
                existingBu.setLastModifiedDate(buDetails.getLastModifiedDate());
                existingBu.setCode(buDetails.getCode());
                existingBu.setDescription(buDetails.getDescription());

                Bu updatedBu = buRepository.save(existingBu);
                logger.info("Bu with ID: {} updated successfully", updatedBu.getId());
                return updatedBu;
            } else {
                logger.warn("Bu with ID: {} not found for update", id);
                return null;
            }
        } catch (Exception e) {
            logger.error("Error updating Bu with ID {}: {}", id, e.getMessage());
            throw new RuntimeException("Error updating Bu", e);
        }
    }

    // Delete Bu by ID
    public void deleteBu(Integer id) {
        logger.info("Deleting Bu with ID: {}", id);
        try {
            Optional<Bu> buOptional = buRepository.findById(id);
            if (buOptional.isPresent()) {
                buRepository.deleteById(id);
                logger.info("Bu with ID: {} deleted successfully", id);
            } else {
                logger.warn("Bu with ID: {} not found for deletion", id);
            }
        } catch (Exception e) {
            logger.error("Error deleting Bu with ID {}: {}", id, e.getMessage());
            throw new RuntimeException("Error deleting Bu", e);
        }
    }
}