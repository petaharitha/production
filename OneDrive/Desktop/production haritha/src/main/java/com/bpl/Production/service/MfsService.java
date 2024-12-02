package com.bpl.Production.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.bpl.Production.entity.Mfs;
import com.bpl.Production.repository.MfsRepository;

import java.util.List;
import java.util.Optional;

@Service
public class MfsService {

    private static final Logger logger = LoggerFactory.getLogger(MfsService.class); // Logger initialization

    @Autowired
    private MfsRepository mfsRepository;
    
    // Create a new Mfs
    public Mfs createMfs(Mfs mfs) {
        logger.info("Creating new Mfs record: {}", mfs);
        Mfs savedMfs = mfsRepository.save(mfs);
        logger.info("Mfs record created with ID: {}", savedMfs.getId());
        return savedMfs;
    }

    // Get Mfs by ID
    public Optional<Mfs> getMfsById(Integer id) {
        logger.info("Fetching Mfs record with ID: {}", id);
        Optional<Mfs> mfs = mfsRepository.findById(id);
        if (mfs.isPresent()) {
            logger.info("Mfs record found with ID: {}", id);
        } else {
            logger.warn("No Mfs record found with ID: {}", id);
        }
        return mfs;
    }

    // Get all Mfs
    public List<Mfs> getAllMfs() {
        logger.info("Fetching all Mfs records");
        List<Mfs> mfsList = mfsRepository.findAll();
        logger.info("Found {} Mfs records", mfsList.size());
        return mfsList;
    }

    // Delete Mfs by ID
    public void deleteMfs(Integer id) {
        logger.info("Attempting to delete Mfs record with ID: {}", id);
        if (mfsRepository.existsById(id)) {
            mfsRepository.deleteById(id);
            logger.info("Mfs record with ID: {} deleted successfully", id);
        } else {
            logger.warn("No Mfs record found with ID: {} for deletion", id);
        }
    }

    // Update Mfs by ID
    public Mfs updateMfs(Integer id, Mfs mfsDetails) {
        logger.info("Attempting to update Mfs record with ID: {}", id);
        
        Mfs mfs = mfsRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Mfs not found with id: " + id));
        
        logger.info("Updating Mfs record with ID: {}", id);
        
        // Set updated values
        mfs.setCreatedBy(mfsDetails.getCreatedBy());
        mfs.setLastModifiedBy(mfsDetails.getLastModifiedBy());
        mfs.setCreatedDate(mfsDetails.getCreatedDate());
        mfs.setLastModifiedDate(mfsDetails.getLastModifiedDate());
        mfs.setCode(mfsDetails.getCode());
        mfs.setIndustryBranch(mfsDetails.getIndustryBranch());
        mfs.setTargetGroup(mfsDetails.getTargetGroup());
        mfs.setApplications(mfsDetails.getApplications());
        mfs.setFurtherApplication(mfsDetails.getFurtherApplication());

        Mfs updatedMfs = mfsRepository.save(mfs);
        logger.info("Mfs record with ID: {} updated successfully", updatedMfs.getId());
        return updatedMfs;
    }
}
