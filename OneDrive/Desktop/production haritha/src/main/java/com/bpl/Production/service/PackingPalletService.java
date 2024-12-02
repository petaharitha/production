package com.bpl.Production.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bpl.Production.entity.PackingPallet;
import com.bpl.Production.repository.PackingPalletRepository;

@Service
public class PackingPalletService {

    private static final Logger logger = LoggerFactory.getLogger(PackingPalletService.class);

    @Autowired
    private PackingPalletRepository packingPalletRepository;
    
    // Create a new PackingPallet
    public PackingPallet createPackingPallet(PackingPallet packingPallet) {
        logger.info("Creating new PackingPallet with PalletNo: {}", packingPallet.getPalletNo());
        PackingPallet createdPallet = packingPalletRepository.save(packingPallet);
        logger.info("PackingPallet created with ID: {}", createdPallet.getId());
        return createdPallet;
    }

    // Get all PackingPallets
    public List<PackingPallet> getAllPackingPallets() {
        logger.info("Fetching all PackingPallets");
        List<PackingPallet> pallets = packingPalletRepository.findAll();
        logger.info("Fetched {} PackingPallets", pallets.size());
        return pallets;
    }

    // Get PackingPallet by ID
    public Optional<PackingPallet> getPackingPalletById(Integer id) {
        logger.info("Fetching PackingPallet with ID: {}", id);
        Optional<PackingPallet> pallet = packingPalletRepository.findById(id);
        if (pallet.isPresent()) {
            logger.info("PackingPallet found with ID: {}", id);
        } else {
            logger.warn("PackingPallet not found with ID: {}", id);
        }
        return pallet;
    }

    // Update PackingPallet by ID
    public PackingPallet updatePackingPallet(Integer id, PackingPallet packingPalletDetails) {
        logger.info("Updating PackingPallet with ID: {}", id);
        Optional<PackingPallet> packingPalletOptional = packingPalletRepository.findById(id);
        if (packingPalletOptional.isPresent()) {
            PackingPallet existingPallet = packingPalletOptional.get();
            logger.info("Found PackingPallet with ID: {}. Updating details...", id);
            existingPallet.setCreatedBy(packingPalletDetails.getCreatedBy());
            existingPallet.setLastModifiedBy(packingPalletDetails.getLastModifiedBy());
            existingPallet.setCreatedDate(packingPalletDetails.getCreatedDate());
            existingPallet.setLastModifiedDate(packingPalletDetails.getLastModifiedDate());
            existingPallet.setPalletNo(packingPalletDetails.getPalletNo());
            existingPallet.setSoNo(packingPalletDetails.getSoNo());
            existingPallet.setConsigneeId(packingPalletDetails.getConsigneeId());
            existingPallet.setConsigneeName(packingPalletDetails.getConsigneeName());
            existingPallet.setConsigneeAddress(packingPalletDetails.getConsigneeAddress());
            existingPallet.setOcPosNo(packingPalletDetails.getOcPosNo());
            existingPallet.setOcNo(packingPalletDetails.getOcNo());
            existingPallet.setMaterialNo(packingPalletDetails.getMaterialNo());
            existingPallet.setMaterialMergedWith(packingPalletDetails.getMaterialMergedWith());
            existingPallet.setQuality(packingPalletDetails.getQuality());
            existingPallet.setShape(packingPalletDetails.getShape());
            existingPallet.setDeliveryDate(packingPalletDetails.getDeliveryDate());
            existingPallet.setPieces(packingPalletDetails.getPieces());
            existingPallet.setPalletNetWeight(packingPalletDetails.getPalletNetWeight());
            existingPallet.setPalletGrossWeight(packingPalletDetails.getPalletGrossWeight());
            existingPallet.setReceived(packingPalletDetails.getReceived());
            existingPallet.setReceivedDate(packingPalletDetails.getReceivedDate());
            existingPallet.setContainerId(packingPalletDetails.getContainerId());
            existingPallet.setContainerNo(packingPalletDetails.getContainerNo());
            existingPallet.setIsPrinted(packingPalletDetails.getIsPrinted());
            existingPallet.setDimensions(packingPalletDetails.getDimensions());
            existingPallet.setOrderId(packingPalletDetails.getOrderId());
            existingPallet.setItemMarking(packingPalletDetails.getItemMarking());
            existingPallet.setExternalBrandName(packingPalletDetails.getExternalBrandName());
            existingPallet.setCustomerPalletNo(packingPalletDetails.getCustomerPalletNo());
            existingPallet.setPoPosNo(packingPalletDetails.getPoPosNo());
            existingPallet.setExternalMaterialNumber(packingPalletDetails.getExternalMaterialNumber());
            existingPallet.setHpg(packingPalletDetails.getHpg());
            PackingPallet updatedPallet = packingPalletRepository.save(existingPallet);
            logger.info("PackingPallet with ID: {} updated successfully.", id);
            return updatedPallet;
        }
        logger.warn("PackingPallet with ID: {} not found for update.", id);
        return null;
    }

    // Delete PackingPallet by ID
    public void deletePackingPallet(Integer id) {
        logger.info("Deleting PackingPallet with ID: {}", id);
        packingPalletRepository.deleteById(id);
        logger.info("PackingPallet with ID: {} deleted successfully.", id);
    }
    
    public Object getProcessedReport() {
        List<PackingPallet> list = packingPalletRepository.findAll();
        
        // Variables to calculate totals
        int grandPieces = 0;
        double grandNetWeight = 0;
        double grandGrossWeight = 0;

        // Processing logic (equivalent to your JSP logic)
        for (PackingPallet pp : list) {
            grandPieces += pp.getPieces();
            grandNetWeight += pp.getPalletNetWeight();
            grandGrossWeight += pp.getPalletGrossWeight();
        }

        // Create a map to hold the report data
        Map<String, Object> reportData = new HashMap<>();
        reportData.put("totalPieces", grandPieces);
        reportData.put("totalNetWeight", grandNetWeight);
        reportData.put("totalGrossWeight", grandGrossWeight);

        return reportData;
    }
    public List<PackingPallet> getReportByHpg(String hpg) {
        return packingPalletRepository.findByHpg(hpg);
    }
    
    
    
    
}
