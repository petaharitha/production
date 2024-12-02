package com.bpl.Production.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.bpl.Production.entity.RMTestLimits;
import com.bpl.Production.repository.RMTestLimitsRepository;

@Service
public class RMTestLimitsService {

    private static final Logger logger = LoggerFactory.getLogger(RMTestLimitsService.class);

    @Autowired
    private RMTestLimitsRepository rmTestLimitsRepository;

    // Create a new entity
    public RMTestLimits createLimits(RMTestLimits rmTestLimits) {
        logger.info("Creating new RMTestLimits entity with data: {}", rmTestLimits);
        RMTestLimits savedEntity = rmTestLimitsRepository.save(rmTestLimits);
        logger.info("RMTestLimits entity created successfully with ID: {}", savedEntity.getId());
        return savedEntity;
    }

    // Update an existing entity
    public RMTestLimits updateLimits(Integer id, RMTestLimits rmTestLimits) {
        logger.info("Updating RMTestLimits entity with ID: {}", id);
        rmTestLimits.setId(id); // Set the ID of the entity to ensure the correct one is updated
        RMTestLimits updatedEntity = rmTestLimitsRepository.save(rmTestLimits); // Save the updated entity (this will update it)
        logger.info("RMTestLimits entity updated successfully with ID: {}", updatedEntity.getId());
        return updatedEntity;
    }

    // Delete an entity
    public void deleteLimit(Integer id) {
        try {
            logger.info("Deleting RMTestLimits entity with ID: {}", id);
            rmTestLimitsRepository.deleteById(id);
            logger.info("RMTestLimits entity deleted successfully with ID: {}", id);
        } catch (Exception e) {
            logger.error("Error occurred while deleting RMTestLimits entity with ID: {}", id, e);
        }
    }

    // Edit an existing entity (essentially the same as update)
    public RMTestLimits editLimit(Integer id, RMTestLimits rmTestLimits) {
        logger.info("Editing RMTestLimits entity with ID: {}", id);
        return updateLimits(id, rmTestLimits);
    }
    
    // Pagination method to fetch entities with pagination support
    public Page<RMTestLimits> getEntitiesWithPagination(int page, int size) {
        logger.info("Fetching RMTestLimits entities with pagination, page: {}, size: {}", page, size);
        Pageable pageable = PageRequest.of(page, size);
        Page<RMTestLimits> entities = rmTestLimitsRepository.findAll(pageable);
        logger.info("Fetched {} RMTestLimits entities from page: {} with size: {}", entities.getTotalElements(), page, size);
        return entities;
    }
}
