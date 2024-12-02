package com.bpl.Production.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.bpl.Production.entity.Heating;
import com.bpl.Production.repository.HeatingRepository;

import java.util.List;
import java.util.Optional;

@Service
public class HeatingService {

    private static final Logger logger = LoggerFactory.getLogger(HeatingService.class); // Initialize logger

    @Autowired
    private HeatingRepository heatingRepository;

    // Create Heating record
    public Heating createHeating(Heating heating) {
        logger.info("Creating new Heating record: {}", heating);
        Heating savedHeating = heatingRepository.save(heating);
        logger.info("Heating record created with ID: {}", savedHeating.getId());
        return savedHeating;
    }

    // Get Heating by ID
    public Optional<Heating> getHeatingById(Integer id) {
        logger.info("Fetching Heating record with ID: {}", id);
        Optional<Heating> heating = heatingRepository.findById(id);
        if (heating.isPresent()) {
            logger.info("Heating record found with ID: {}", id);
        } else {
            logger.warn("No Heating record found with ID: {}", id);
        }
        return heating;
    }

    // Get all Heating records
    public List<Heating> getAllHeating() {
        logger.info("Fetching all Heating records");
        List<Heating> heatingList = heatingRepository.findAll();
        logger.info("Found {} Heating records", heatingList.size());
        return heatingList;
    }

    // Delete Heating record by ID
    public void deleteHeating(Integer id) {
        logger.info("Attempting to delete Heating record with ID: {}", id);
        if (heatingRepository.existsById(id)) {
            heatingRepository.deleteById(id);
            logger.info("Heating record with ID: {} deleted successfully", id);
        } else {
            logger.warn("No Heating record found with ID: {} for deletion", id);
        }
    }

    // Update Heating by ID
    public Heating updateHeating(Integer id, Heating heating) {
        logger.info("Attempting to update Heating record with ID: {}", id);

        Optional<Heating> existingHeating = heatingRepository.findById(id);
        if (existingHeating.isPresent()) {
            Heating updatedHeating = existingHeating.get();

            // Logging fields before updating
            logger.info("Updating Heating record with ID: {}", id);

            // Set the ID to ensure the correct entity is updated
            updatedHeating.setId(id); // Make sure the ID is set correctly

            // Update the values (set fields as per provided Heating object)
            updatedHeating.setCreatedBy(heating.getCreatedBy());
            updatedHeating.setLastModifiedBy(heating.getLastModifiedBy());
            updatedHeating.setCreatedDate(heating.getCreatedDate());
            updatedHeating.setLastModifiedDate(heating.getLastModifiedDate());
            updatedHeating.setDate(heating.getDate());
            updatedHeating.setShift(heating.getShift());
            updatedHeating.setCarNo(heating.getCarNo());
            updatedHeating.setHpg(heating.getHpg());
            updatedHeating.setMaterialNo(heating.getMaterialNo());
            updatedHeating.setSoNo(heating.getSoNo());
            updatedHeating.setTotalPcs(heating.getTotalPcs());
            updatedHeating.setLoadedPcs(heating.getLoadedPcs());
            updatedHeating.setRejPcs(heating.getRejPcs());
            updatedHeating.setHeatingNumber(heating.getHeatingNumber());
            updatedHeating.setRemarks(heating.getRemarks());
            updatedHeating.setQuality(heating.getQuality());
            updatedHeating.setShape(heating.getShape());
            updatedHeating.setPg(heating.getPg());
            updatedHeating.setFgid(heating.getFgid());
            updatedHeating.setReferenceFormingNumber(heating.getReferenceFormingNumber());
            updatedHeating.setPoPosNo(heating.getPoPosNo());

            // Save and return the updated Heating record
            Heating savedHeating = heatingRepository.save(updatedHeating);
            logger.info("Heating record updated successfully with ID: {}", savedHeating.getId());
            return savedHeating;
        } else {
            logger.warn("No Heating record found with ID: {} for update", id);
            return null; // Return null or throw custom exception if not found
        }
    }
}
