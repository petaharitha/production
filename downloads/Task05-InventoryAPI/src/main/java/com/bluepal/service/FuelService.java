package com.bluepal.service;



import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.bluepal.entity.Fuel;
import com.bluepal.repository.FuelRepo;



@Service
public class FuelService {
	
	@Autowired
	private FuelRepo fuelRepo;

	// Logger setup for the class
    private static final Logger logger = LoggerFactory.getLogger(FuelService.class);

    // Caching and logging for create operation
    @CachePut(value = "fuels", key = "#result.materialNo")
    public Fuel createFuel(Fuel fuel) {
        logger.info("Creating new Fuel with materialNo: {}", fuel.getMaterialNo());
        Fuel createdFuel = fuelRepo.save(fuel);
        logger.info("Fuel created successfully with materialNo: {}", createdFuel.getMaterialNo());
        return createdFuel;
    }

    // Caching and logging for getting all fuels
    @Cacheable(value = "fuels", key = "'allFuels'")
    public List<Fuel> getAllFuels() {
        logger.info("Fetching all fuels from the database");
        return fuelRepo.findAll();
    }

    // Caching and logging for getting fuel by materialNo
    @Cacheable(value = "fuels", key = "#materialNo")
    public Fuel getFuelById(Long materialNo) {
        logger.info("Fetching fuel with materialNo: {}", materialNo);
        return fuelRepo.findById(materialNo).orElse(null);
    }

    // Caching and logging for updating a fuel
    @CachePut(value = "fuels", key = "#materialNo")
    public Fuel updateFuel(Long materialNo, Fuel fuelDetails) {
        logger.info("Updating fuel with materialNo: {}", materialNo);
        Fuel fuel = fuelRepo.findById(materialNo).orElse(null);
        if (fuel != null) {
            fuel.setMaterialGroup(fuelDetails.getMaterialGroup());
            fuel.setMaterialSubGroup(fuelDetails.getMaterialSubGroup());
            fuel.setMaterialName(fuelDetails.getMaterialName());
            fuel.setCalrificValue(fuelDetails.getCalrificValue());
            fuel.setOtherDesc(fuelDetails.getOtherDesc());
            fuel.setUnitOfMeasurement(fuelDetails.getUnitOfMeasurement());
            fuel.setMinimumOrderLevel(fuelDetails.getMinimumOrderLevel());
            fuel.setLeadTime(fuelDetails.getLeadTime());
            Fuel updatedFuel = fuelRepo.save(fuel);
            logger.info("Fuel updated successfully with materialNo: {}", updatedFuel.getMaterialNo());
            return updatedFuel;
        }
        logger.warn("Fuel with materialNo: {} not found", materialNo);
        return null;
    }

    // Caching and logging for deleting a fuel
    public void deleteFuel(Long materialNo) {
        logger.info("Deleting fuel with materialNo: {}", materialNo);
        fuelRepo.deleteById(materialNo);
        logger.info("Fuel with materialNo: {} deleted successfully", materialNo);
    }

    // Search and Pagination with logging
    public Page<Fuel> searchFuels(String keyword, int page, int size, String sortBy, String sortDirection) {
        logger.info("Searching fuels with keyword: {}, page: {}, size: {}, sortBy: {}, sortDirection: {}", keyword, page, size, sortBy, sortDirection);
        
        PageRequest pageRequest = PageRequest.of(page, size, Sort.by(sortBy));
        if ("desc".equalsIgnoreCase(sortDirection)) {
            pageRequest = PageRequest.of(page, size, Sort.by(sortBy).descending());
        }
        
        return fuelRepo.findAll(pageRequest);
    }
}

