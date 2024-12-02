package com.bpl.Production.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.bpl.Production.entity.FiredBrickLimits;
import com.bpl.Production.repository.FiredBrickLimitRepo;

@Service
public class FiredBrickLimitService {

    private static final Logger logger = LoggerFactory.getLogger(FiredBrickLimitService.class);

    @Autowired
    private FiredBrickLimitRepo firedBrickLimitRepo;

    // Create  a FiredBrickLimits record
    public FiredBrickLimits saveFiredBrickLimits(FiredBrickLimits firedBrickLimits) {
        logger.info("Creating new FiredBrickLimits record: {}", firedBrickLimits);
        return firedBrickLimitRepo.save(firedBrickLimits);
    }

    // Get a single FiredBrickLimits record by ID
    public Optional<FiredBrickLimits> getFiredBrickLimitsById(Integer id) {
        logger.info("Fetching FiredBrickLimits record with ID: {}", id);
        return firedBrickLimitRepo.findById(id);
    }

    // Get all FiredBrickLimits records
    public List<FiredBrickLimits> getAllFiredBrickLimits() {
        logger.info("Fetching all FiredBrickLimits records.");
        return firedBrickLimitRepo.findAll();
    }

    // Delete a FiredBrickLimits record by ID
    public void deleteFiredBrickLimits(Integer id) {
        logger.info("Deleting FiredBrickLimits record with ID: {}", id);
        firedBrickLimitRepo.deleteById(id);
    }

    // Update a FiredBrickLimits record
    public FiredBrickLimits updateFiredBrickLimits(Integer id, FiredBrickLimits firedBrickLimits) {
        // Check if the record exists first
        Optional<FiredBrickLimits> existingRecordOptional = firedBrickLimitRepo.findById(id);
        
        if (existingRecordOptional.isPresent()) {
            FiredBrickLimits existingRecord = existingRecordOptional.get();

            // Update the fields that can be changed
            logger.info("Updating FiredBrickLimits record with ID: {}", id);

            existingRecord.setCreatedBy(firedBrickLimits.getCreatedBy());
            existingRecord.setLastModifiedBy(firedBrickLimits.getLastModifiedBy());
            existingRecord.setCreatedDate(firedBrickLimits.getCreatedDate());
            existingRecord.setLastModifiedDate(firedBrickLimits.getLastModifiedDate());
            existingRecord.setValidateFrom(firedBrickLimits.getValidateFrom());
            existingRecord.setExpiredFrom(firedBrickLimits.getExpiredFrom());
            existingRecord.setRecipeNo(firedBrickLimits.getRecipeNo());
            existingRecord.setQuality(firedBrickLimits.getQuality());
            existingRecord.setApPercentageTarget(firedBrickLimits.getApPercentageTarget());
            existingRecord.setApPercentageMinimum(firedBrickLimits.getApPercentageMinimum());
            existingRecord.setApPercentageMaximum(firedBrickLimits.getApPercentageMaximum());
            existingRecord.setBdGSlashCcTarget(firedBrickLimits.getBdGSlashCcTarget());
            existingRecord.setBdGSlashCcMinimum(firedBrickLimits.getBdGSlashCcMinimum());
            existingRecord.setBdGSlashCcMaximum(firedBrickLimits.getBdGSlashCcMaximum());
            existingRecord.setCcsNSlashMmTwoTarget(firedBrickLimits.getCcsNSlashMmTwoTarget());
            existingRecord.setCcsNSlashMmTwoMinimum(firedBrickLimits.getCcsNSlashMmTwoMinimum());
            existingRecord.setCcsNSlashMmTwoMaximum(firedBrickLimits.getCcsNSlashMmTwoMaximum());
            existingRecord.setRulTZeroFiveTarget(firedBrickLimits.getRulTZeroFiveTarget());
            existingRecord.setRulTZeroFiveMinimum(firedBrickLimits.getRulTZeroFiveMinimum());
            existingRecord.setRulTZeroFiveMaximum(firedBrickLimits.getRulTZeroFiveMaximum());
            existingRecord.setPlcPercentageTarget(firedBrickLimits.getPlcPercentageTarget());
            existingRecord.setPlcPercentageMinimum(firedBrickLimits.getPlcPercentageMinimum());
            existingRecord.setPlcPercentageMaximum(firedBrickLimits.getPlcPercentageMaximum());
            existingRecord.setAlTwoOThreePercentageTarget(firedBrickLimits.getAlTwoOThreePercentageTarget());
            existingRecord.setAlTwoOThreePercentageMinimum(firedBrickLimits.getAlTwoOThreePercentageMinimum());
            existingRecord.setAlTwoOThreePercentageMaximum(firedBrickLimits.getAlTwoOThreePercentageMaximum());
            existingRecord.setFeTwoOThreePercentageTarget(firedBrickLimits.getFeTwoOThreePercentageTarget());
            existingRecord.setFeTwoOThreePercentageMinimum(firedBrickLimits.getFeTwoOThreePercentageMinimum());
            existingRecord.setFeTwoOThreePercentageMaximum(firedBrickLimits.getFeTwoOThreePercentageMaximum());
            existingRecord.setTioTwoPercentageTarget(firedBrickLimits.getTioTwoPercentageTarget());
            existingRecord.setTioTwoPercentageMinimum(firedBrickLimits.getTioTwoPercentageMinimum());
            existingRecord.setTioTwoPercentageMaximum(firedBrickLimits.getTioTwoPercentageMaximum());
            existingRecord.setSioTwoPercentageTarget(firedBrickLimits.getSioTwoPercentageTarget());
            existingRecord.setSioTwoPercentageMinimum(firedBrickLimits.getSioTwoPercentageMinimum());
            existingRecord.setSioTwoPercentageMaximum(firedBrickLimits.getSioTwoPercentageMaximum());
            existingRecord.setCrTwoOThreePercentageTarget(firedBrickLimits.getCrTwoOThreePercentageTarget());
            existingRecord.setCrTwoOThreePercentageMinimum(firedBrickLimits.getCrTwoOThreePercentageMinimum());
            existingRecord.setCrTwoOThreePercentageMaximum(firedBrickLimits.getCrTwoOThreePercentageMaximum());
            existingRecord.setMgoPercentageTarget(firedBrickLimits.getMgoPercentageTarget());
            existingRecord.setMgoPercentageMinimum(firedBrickLimits.getMgoPercentageMinimum());
            existingRecord.setMgoPercentageMaximum(firedBrickLimits.getMgoPercentageMaximum());
            existingRecord.setPTwoOFivePercentageTarget(firedBrickLimits.getPTwoOFivePercentageTarget());
            existingRecord.setPTwoOFivePercentageMinimum(firedBrickLimits.getPTwoOFivePercentageMinimum());
            existingRecord.setPTwoOFivePercentageMaximum(firedBrickLimits.getPTwoOFivePercentageMaximum());
            existingRecord.setCaoPercentageTarget(firedBrickLimits.getCaoPercentageTarget());
            existingRecord.setCaoPercentageMinimum(firedBrickLimits.getCaoPercentageMinimum());
            existingRecord.setCaoPercentageMaximum(firedBrickLimits.getCaoPercentageMaximum());
            existingRecord.setKTwoOPercentageTarget(firedBrickLimits.getKTwoOPercentageTarget());
            existingRecord.setKTwoOPercentageMinimum(firedBrickLimits.getKTwoOPercentageMinimum());
            existingRecord.setKTwoOPercentageMaximum(firedBrickLimits.getKTwoOPercentageMaximum());
            existingRecord.setNaTwoOPercentageTarget(firedBrickLimits.getNaTwoOPercentageTarget());
            existingRecord.setNaTwoOPercentageMinimum(firedBrickLimits.getNaTwoOPercentageMinimum());
            existingRecord.setNaTwoOPercentageMaximum(firedBrickLimits.getNaTwoOPercentageMaximum());
            existingRecord.setZroPercentageTarget(firedBrickLimits.getZroPercentageTarget());
            existingRecord.setZroPercentageMinimum(firedBrickLimits.getZroPercentageMinimum());
            existingRecord.setZroPercentageMaximum(firedBrickLimits.getZroPercentageMaximum());
            existingRecord.setSicPercentageTarget(firedBrickLimits.getSicPercentageTarget());
            existingRecord.setSicPercentageMinimum(firedBrickLimits.getSicPercentageMinimum());
            existingRecord.setSicPercentageMaximum(firedBrickLimits.getSicPercentageMaximum());

            // Save the updated entity
            FiredBrickLimits updatedRecord = firedBrickLimitRepo.save(existingRecord);
            logger.info("Updated FiredBrickLimits record with ID: {}", id);
            return updatedRecord;
        } else {
            logger.warn("No FiredBrickLimits record found with ID: {}", id);
            return null; // or you can throw an exception here
        }
    }
}
