package com.bpl.Production.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.bpl.Production.entity.CommercialInvoiceDetails;
import com.bpl.Production.repository.CommercialInvoiceDetailsRepository;

@Service
public class CommercialInvoiceDetailsService {
	
	@Autowired
	private CommercialInvoiceDetailsRepository commercialInvoiceDetailsRepository;
	
	private static final Logger logger = LoggerFactory.getLogger(CommercialInvoiceDetailsService.class);  // Initialize logger


	    // Create a new CommercialInvoiceDetail
	    public CommercialInvoiceDetails createCommercialInvoiceDetails(CommercialInvoiceDetails commercialInvoiceDetails) {
	        logger.info("Creating new CommercialInvoiceDetails: {}", commercialInvoiceDetails);
	        return commercialInvoiceDetailsRepository.save(commercialInvoiceDetails);
	    }

	    // Get a CommercialInvoiceDetail by ID
	    public Optional<CommercialInvoiceDetails> getCommercialInvoiceDetailsById(Long id) {
	        logger.info("Fetching CommercialInvoiceDetails with ID: {}", id);
	        return commercialInvoiceDetailsRepository.findById(id);
	    }

	    // Get all CommercialInvoiceDetails
	    public List<CommercialInvoiceDetails> getAllCommercialInvoiceDetails() {
	        logger.info("Fetching all CommercialInvoiceDetails");
	        return commercialInvoiceDetailsRepository.findAll();
	    }

	    // Delete a CommercialInvoiceDetail by ID
	    public void deleteCommercialInvoiceDetails(Long id) {
	        logger.info("Deleting CommercialInvoiceDetails with ID: {}", id);
	        commercialInvoiceDetailsRepository.deleteById(id);
	    }

	    // Update an existing CommercialInvoiceDetail
	    public CommercialInvoiceDetails updateCommercialInvoiceDetails(Long id, CommercialInvoiceDetails commercialInvoiceDetails) {
	        logger.info("Updating CommercialInvoiceDetails with ID: {}", id);
	        
	        // Check if the CommercialInvoiceDetails exists
	        Optional<CommercialInvoiceDetails> existingInvoiceDetails = commercialInvoiceDetailsRepository.findById(id);
	        
	        if (existingInvoiceDetails.isPresent()) {
	            // Setting the ID to ensure it's updated (this is the primary key)
	            CommercialInvoiceDetails updatedInvoiceDetails = existingInvoiceDetails.get();
	            updatedInvoiceDetails.setCommercialInvoiceNo(commercialInvoiceDetails.getCommercialInvoiceNo());
	            updatedInvoiceDetails.setSoNo(commercialInvoiceDetails.getSoNo());
	            updatedInvoiceDetails.setMaterialNo(commercialInvoiceDetails.getMaterialNo());
	            updatedInvoiceDetails.setPoPosNo(commercialInvoiceDetails.getPoPosNo());
	            updatedInvoiceDetails.setMaterialCost(commercialInvoiceDetails.getMaterialCost());
	            updatedInvoiceDetails.setPackingCost(commercialInvoiceDetails.getPackingCost());
	            updatedInvoiceDetails.setFreight(commercialInvoiceDetails.getFreight());
	            updatedInvoiceDetails.setTotal(commercialInvoiceDetails.getTotal());
	            updatedInvoiceDetails.setQuality(commercialInvoiceDetails.getQuality());
	            updatedInvoiceDetails.setShape(commercialInvoiceDetails.getShape());
	            updatedInvoiceDetails.setScheme(commercialInvoiceDetails.getScheme());
	            updatedInvoiceDetails.setIncentive(commercialInvoiceDetails.getIncentive());
	            updatedInvoiceDetails.setItemCeChapterNo(commercialInvoiceDetails.getItemCeChapterNo());
	            updatedInvoiceDetails.setItemCeDescription(commercialInvoiceDetails.getItemCeDescription());
	            updatedInvoiceDetails.setExternalQuality(commercialInvoiceDetails.getExternalQuality());
	            updatedInvoiceDetails.setNoOfPallets(commercialInvoiceDetails.getNoOfPallets());
	            updatedInvoiceDetails.setQtyPcs(commercialInvoiceDetails.getQtyPcs());
	            updatedInvoiceDetails.setQtyMtons(commercialInvoiceDetails.getQtyMtons());
	            updatedInvoiceDetails.setUom(commercialInvoiceDetails.getUom());
	            updatedInvoiceDetails.setRemarks(commercialInvoiceDetails.getRemarks());
	            
	            updatedInvoiceDetails.setLastModifiedBy(commercialInvoiceDetails.getLastModifiedBy());
	            updatedInvoiceDetails.setLastModifiedDate(commercialInvoiceDetails.getLastModifiedDate());

	            // Log the update action
	            logger.info("Updated CommercialInvoiceDetails: {}", updatedInvoiceDetails);

	            return commercialInvoiceDetailsRepository.save(updatedInvoiceDetails);
	        } else {
	            // Log if the CommercialInvoiceDetails does not exist
	            logger.warn("CommercialInvoiceDetails not found for id: {}", id);
	            throw new RuntimeException("CommercialInvoiceDetails not found for ID: " + id);
	        }
	    }
	}