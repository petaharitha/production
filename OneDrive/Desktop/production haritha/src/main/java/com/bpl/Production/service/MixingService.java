package com.bpl.Production.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.bpl.Production.entity.Mixing;
import com.bpl.Production.repository.MixingRepository;

import java.util.List;
import java.util.Optional;

@Service
public class MixingService {

    @Autowired
    private MixingRepository mixingRepository;

    // Logger instance for logging
    private static final Logger logger = LoggerFactory.getLogger(MixingService.class);

    // Create a new Mixing record
    public Mixing createMixing(Mixing mixing) {
        logger.info("Creating a new Mixing entry: {}", mixing);
        return mixingRepository.save(mixing);
    }

    // Get a Mixing record by its ID
    public Optional<Mixing> getMixingById(Integer id) {
        logger.info("Fetching Mixing entry with ID: {}", id);
        return mixingRepository.findById(id);
    }

    // Get all Mixing records
    public List<Mixing> getAllMixings() {
        logger.info("Fetching all Mixing entries");
        return mixingRepository.findAll();
    }

    // Delete a Mixing record by its ID
    public void deleteMixing(Integer id) {
        if (mixingRepository.existsById(id)) {
            logger.info("Deleting Mixing entry with ID: {}", id);
            mixingRepository.deleteById(id);
        } else {
            logger.warn("Mixing entry with ID {} not found for deletion", id);
        }
    }

    // Update a Mixing record
    public Mixing updateMixing(Integer id, Mixing mixingDetails) {
        logger.info("Attempting to update Mixing entry with ID: {}", id);
        if (mixingRepository.existsById(id)) {
            // Get the existing mixing entry from the repository
            Mixing existingMixing = mixingRepository.findById(id).get();

            // Update the fields from the incoming mixingDetails
            existingMixing.setCreatedBy(mixingDetails.getCreatedBy());
            existingMixing.setLastModifiedBy(mixingDetails.getLastModifiedBy());
            existingMixing.setCreatedDate(mixingDetails.getCreatedDate());
            existingMixing.setLastModifiedDate(mixingDetails.getLastModifiedDate());
            existingMixing.setDate(mixingDetails.getDate());
            existingMixing.setShift(mixingDetails.getShift());
            existingMixing.setMixingNumber(mixingDetails.getMixingNumber());
            existingMixing.setHpg(mixingDetails.getHpg());
            existingMixing.setRecipeNo(mixingDetails.getRecipeNo());
            existingMixing.setAltNo(mixingDetails.getAltNo());
            existingMixing.setTotalQtyInMt(mixingDetails.getTotalQtyInMt());
            existingMixing.setMixOkQtyInMt(mixingDetails.getMixOkQtyInMt());
            existingMixing.setMixRejQtyInMt(mixingDetails.getMixRejQtyInMt());
            existingMixing.setHandOverPressTable(mixingDetails.getHandOverPressTable());
            existingMixing.setRemarks(mixingDetails.getRemarks());
            existingMixing.setQuality(mixingDetails.getQuality());
            existingMixing.setPg(mixingDetails.getPg());
            existingMixing.setFgid(mixingDetails.getFgid());
            existingMixing.setSoNo(mixingDetails.getSoNo());

            logger.info("Updating Mixing entry with ID: {}", id);
            return mixingRepository.save(existingMixing);
        } else {
            logger.warn("Mixing entry with ID {} not found for update", id);
            throw new RuntimeException("Mixing entry with ID " + id + " not found");
        }
    }
}
