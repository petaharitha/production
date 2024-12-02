package com.bpl.Production.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Optional;

import com.bpl.Production.entity.Inspection;
import com.bpl.Production.repository.InspectionRepository;

@Service
public class InspectionService {

    private static final Logger logger = LoggerFactory.getLogger(InspectionService.class); // Logger initialization

    @Autowired
    private InspectionRepository inspectionRepository;

    // Create new Inspection record
    public Inspection createInspection(Inspection inspection) {
        logger.info("Creating new Inspection record: {}", inspection);
        Inspection savedInspection = inspectionRepository.save(inspection);
        logger.info("Inspection record created with ID: {}", savedInspection.getId());
        return savedInspection;
    }

    // Get Inspection by ID
    public Optional<Inspection> getInspectionById(Integer id) {
        logger.info("Fetching Inspection record with ID: {}", id);
        Optional<Inspection> inspection = inspectionRepository.findById(id);
        if (inspection.isPresent()) {
            logger.info("Inspection record found with ID: {}", id);
        } else {
            logger.warn("No Inspection record found with ID: {}", id);
        }
        return inspection;
    }

    // Get all Inspections
    public List<Inspection> getAllInspections() {
        logger.info("Fetching all Inspection records");
        List<Inspection> inspections = inspectionRepository.findAll();
        logger.info("Found {} Inspection records", inspections.size());
        return inspections;
    }

    // Delete Inspection by ID
    public void deleteInspection(Integer id) {
        logger.info("Attempting to delete Inspection record with ID: {}", id);
        if (inspectionRepository.existsById(id)) {
            inspectionRepository.deleteById(id);
            logger.info("Inspection record with ID: {} deleted successfully", id);
        } else {
            logger.warn("No Inspection record found with ID: {} for deletion", id);
        }
    }

    // Update Inspection
    public Inspection updateInspection(Integer id, Inspection inspectionDetails) {
        logger.info("Attempting to update Inspection record with ID: {}", id);

        Inspection inspection = inspectionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Inspection not found with id: " + id));

        // Logging the update action
        logger.info("Updating Inspection record with ID: {}", id);

        // Set updated values
        inspection.setCreatedBy(inspectionDetails.getCreatedBy());
        inspection.setLastModifiedBy(inspectionDetails.getLastModifiedBy());
        inspection.setCreatedDate(inspectionDetails.getCreatedDate());
        inspection.setLastModifiedDate(inspectionDetails.getLastModifiedDate());
        inspection.setDate(inspectionDetails.getDate());
        inspection.setShift(inspectionDetails.getShift());
        inspection.setHpg(inspectionDetails.getHpg());
        inspection.setMaterialNo(inspectionDetails.getMaterialNo());
        inspection.setSoNo(inspectionDetails.getSoNo());
        inspection.setPcsUL(inspectionDetails.getPcsUL());
        inspection.setCornerBreaks(inspectionDetails.getCornerBreaks());
        inspection.setIronSpots(inspectionDetails.getIronSpots());
        inspection.setBodyCracks(inspectionDetails.getBodyCracks());
        inspection.setBurntRejns(inspectionDetails.getBurntRejns());
        inspection.setWarphase(inspectionDetails.getWarphase());
        inspection.setLowMeltingSpots(inspectionDetails.getLowMeltingSpots());
        inspection.setSizeVariations(inspectionDetails.getSizeVariations());
        inspection.setLaminations(inspectionDetails.getLaminations());
        inspection.setExcess(inspectionDetails.getExcess());
        inspection.setSymmetry(inspectionDetails.getSymmetry());
        inspection.setTaper(inspectionDetails.getTaper());
        inspection.setLabSample(inspectionDetails.getLabSample());
        inspection.setOtherRejns(inspectionDetails.getOtherRejns());
        inspection.setSoft(inspectionDetails.getSoft());
        inspection.setGoodPcs(inspectionDetails.getGoodPcs());
        inspection.setRemarks(inspectionDetails.getRemarks());
        inspection.setQuality(inspectionDetails.getQuality());
        inspection.setShape(inspectionDetails.getShape());
        inspection.setPg(inspectionDetails.getPg());
        inspection.setFgid(inspectionDetails.getFgid());
        inspection.setTotrej(inspectionDetails.getTotrej());
        inspection.setQualityNumber(inspectionDetails.getQualityNumber());
        inspection.setPoPosNo(inspectionDetails.getPoPosNo());
        inspection.setMarketingClearance(inspectionDetails.getMarketingClearance());
        inspection.setExcessMovedToFreeStock(inspectionDetails.getExcessMovedToFreeStock());

        // Save and return updated Inspection record
        Inspection updatedInspection = inspectionRepository.save(inspection);
        logger.info("Inspection record with ID: {} updated successfully", updatedInspection.getId());
        return updatedInspection;
    }
}
