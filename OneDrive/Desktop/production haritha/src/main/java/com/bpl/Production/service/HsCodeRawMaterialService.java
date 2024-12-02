package com.bpl.Production.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.bpl.Production.entity.HsCodeRawMaterial;
import com.bpl.Production.repository.HsCodeRawMaterialRepository;

@Service
public class HsCodeRawMaterialService {

    private static final Logger logger = LoggerFactory.getLogger(HsCodeRawMaterialService.class); // Initialize logger

    @Autowired
    private HsCodeRawMaterialRepository hsCodeRawMaterialRepository;

    // Create HsCodeRawMaterial record
    public HsCodeRawMaterial createHsCodeRawMaterial(HsCodeRawMaterial hsCodeRawMaterial) {
        logger.info("Creating new HsCodeRawMaterial record: {}", hsCodeRawMaterial);
        HsCodeRawMaterial savedHsCodeRawMaterial = hsCodeRawMaterialRepository.save(hsCodeRawMaterial);
        logger.info("HsCodeRawMaterial record created with ID: {}", savedHsCodeRawMaterial.getId());
        return savedHsCodeRawMaterial;
    }

    // Get HsCodeRawMaterial by ID
    public Optional<HsCodeRawMaterial> getHsCodeRawMaterialById(Integer id) {
        logger.info("Fetching HsCodeRawMaterial record with ID: {}", id);
        Optional<HsCodeRawMaterial> hsCodeRawMaterial = hsCodeRawMaterialRepository.findById(id);
        if (hsCodeRawMaterial.isPresent()) {
            logger.info("HsCodeRawMaterial record found with ID: {}", id);
        } else {
            logger.warn("No HsCodeRawMaterial record found with ID: {}", id);
        }
        return hsCodeRawMaterial;
    }

    // Get all HsCodeRawMaterial records
    public List<HsCodeRawMaterial> getAllHsCodeRawMaterials() {
        logger.info("Fetching all HsCodeRawMaterial records");
        List<HsCodeRawMaterial> hsCodeRawMaterials = hsCodeRawMaterialRepository.findAll();
        logger.info("Found {} HsCodeRawMaterial records", hsCodeRawMaterials.size());
        return hsCodeRawMaterials;
    }

    // Delete HsCodeRawMaterial by ID
    public void deleteHsCodeRawMaterial(Integer id) {
        logger.info("Attempting to delete HsCodeRawMaterial record with ID: {}", id);
        if (hsCodeRawMaterialRepository.existsById(id)) {
            hsCodeRawMaterialRepository.deleteById(id);
            logger.info("HsCodeRawMaterial record with ID: {} deleted successfully", id);
        } else {
            logger.warn("No HsCodeRawMaterial record found with ID: {} for deletion", id);
        }
    }

    // Update HsCodeRawMaterial by ID
    public HsCodeRawMaterial updateHsCodeRawMaterial(Integer id, HsCodeRawMaterial hsCodeRawMaterial) {
        logger.info("Attempting to update HsCodeRawMaterial record with ID: {}", id);

        Optional<HsCodeRawMaterial> existingHsCodeRawMaterial = hsCodeRawMaterialRepository.findById(id);
        if (existingHsCodeRawMaterial.isPresent()) {
            HsCodeRawMaterial updatedHsCodeRawMaterial = existingHsCodeRawMaterial.get();

            // Logging fields before updating
            logger.info("Updating HsCodeRawMaterial record with ID: {}", id);

            // Ensure that the ID is set to the correct value
            updatedHsCodeRawMaterial.setId(id);

            // Set other fields to the new values
            updatedHsCodeRawMaterial.setCreatedBy(hsCodeRawMaterial.getCreatedBy());
            updatedHsCodeRawMaterial.setLastModifiedBy(hsCodeRawMaterial.getLastModifiedBy());
            updatedHsCodeRawMaterial.setCreatedDate(hsCodeRawMaterial.getCreatedDate());
            updatedHsCodeRawMaterial.setLastModifiedDate(hsCodeRawMaterial.getLastModifiedDate());
            updatedHsCodeRawMaterial.setHsCode(hsCodeRawMaterial.getHsCode());
            updatedHsCodeRawMaterial.setHscDescription(hsCodeRawMaterial.getHscDescription());

            // Save the updated HsCodeRawMaterial record
            HsCodeRawMaterial savedHsCodeRawMaterial = hsCodeRawMaterialRepository.save(updatedHsCodeRawMaterial);
            logger.info("HsCodeRawMaterial record updated successfully with ID: {}", savedHsCodeRawMaterial.getId());
            return savedHsCodeRawMaterial;
        } else {
            logger.warn("No HsCodeRawMaterial record found with ID: {} for update", id);
            return null; // Return null or throw custom exception if not found
        }
    }
}
