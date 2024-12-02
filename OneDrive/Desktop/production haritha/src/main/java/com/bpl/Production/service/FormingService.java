package com.bpl.Production.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bpl.Production.entity.Forming;
import com.bpl.Production.repository.FormingRepo;

@Service
public class FormingService {

    private static final Logger logger = LoggerFactory.getLogger(FormingService.class); // Initialize logger

    @Autowired
    private FormingRepo formingRepo;

    // Create or Update Forming
    public Forming createForming(Forming forming) {
        logger.info("Creating or updating Forming: {}", forming);
        Forming savedForming = formingRepo.save(forming);
        logger.info("Forming saved with ID: {}", savedForming.getId());
        return savedForming;
    }

    // Get all Forming records
    public List<Forming> getAllFormings() {
        logger.info("Fetching all Forming records");
        List<Forming> formings = formingRepo.findAll();
        logger.info("Found {} Forming records", formings.size());
        return formings;
    }

    // Get a Forming by ID
    public Optional<Forming> getFormingById(Integer id) {
        logger.info("Fetching Forming record with ID: {}", id);
        Optional<Forming> forming = formingRepo.findById(id);
        if (forming.isPresent()) {
            logger.info("Forming found with ID: {}", id);
        } else {
            logger.warn("No Forming found with ID: {}", id);
        }
        return forming;
    }

    // Delete a Forming by ID
    public void deleteForming(Integer id) {
        logger.info("Deleting Forming with ID: {}", id);
        formingRepo.deleteById(id);
        logger.info("Forming with ID: {} deleted", id);
    }

    // Update a Forming entity
    public Forming updateForming(Integer id, Forming formingDetails) {
        logger.info("Updating Forming with ID: {}", id);
        
        Optional<Forming> existingForming = formingRepo.findById(id);
        if (existingForming.isPresent()) {
            Forming forming = existingForming.get();
            logger.info("Forming found for update with ID: {}", id);

            // Update fields
            forming.setCreatedBy(formingDetails.getCreatedBy());
            forming.setLastModifiedBy(formingDetails.getLastModifiedBy());
            forming.setDate(formingDetails.getDate());
            forming.setShift(formingDetails.getShift());
            forming.setFormingNumber(formingDetails.getFormingNumber());
            forming.setHpg(formingDetails.getHpg());
            forming.setMaterialNo(formingDetails.getMaterialNo());
            forming.setSoNo(formingDetails.getSoNo());
            forming.setProductionPcs(formingDetails.getProductionPcs());
            forming.setOkPcs(formingDetails.getOkPcs());
            forming.setRejPcs(formingDetails.getRejPcs());
            forming.setRemarks(formingDetails.getRemarks());
            forming.setQuality(formingDetails.getQuality());
            forming.setShape(formingDetails.getShape());
            forming.setPg(formingDetails.getPg());
            forming.setFgid(formingDetails.getFgid());
            forming.setReferenceMixingNumber(formingDetails.getReferenceMixingNumber());
            forming.setPoPosNo(formingDetails.getPoPosNo());

            Forming updatedForming = formingRepo.save(forming);
            logger.info("Forming updated with ID: {}", updatedForming.getId());
            return updatedForming;
        } else {
            logger.warn("No Forming found with ID: {} for update", id);
            return null;
        }
    }
    public List<Forming> getFormingData(List<String> hpgList, List<String> shiftList, Date fromDate, Date toDate) {
        return formingRepo.findByHpgInAndShiftInAndDateBetween(hpgList, shiftList, fromDate, toDate);
    }
    public List<Forming> getResultsByDateRange(Date fromDate, Date toDate) {
        return formingRepo.findByDateBetween(fromDate, toDate);
    }

    public List<Forming> getResultsByHpgAndShift(List<String> hpgList, List<String> shiftList, Date fromDate, Date toDate) {
        return formingRepo.findByHpgInAndShiftInAndDateBetween(hpgList, shiftList, fromDate, toDate);
    }
}
