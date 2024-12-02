package com.bpl.Production.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.bpl.Production.entity.MixesTestResults;
import com.bpl.Production.repository.MixesTestResultsRepository;

import java.util.List;
import java.util.Optional;

@Service
public class MixesTestResultsService {

    @Autowired
    private MixesTestResultsRepository mixesTestResultsRepository;

    // Logger instance for logging
    private static final Logger logger = LoggerFactory.getLogger(MixesTestResultsService.class);

    // Create a new MixesTestResults
    public MixesTestResults create(MixesTestResults mixesTestResults) {
        logger.info("Creating a new MixesTestResults entry: {}", mixesTestResults);
        return mixesTestResultsRepository.save(mixesTestResults);
    }

    // Get all MixesTestResults
    public List<MixesTestResults> getAll() {
        logger.info("Fetching all MixesTestResults entries");
        return mixesTestResultsRepository.findAll();
    }

    // Get a MixesTestResults by ID
    public Optional<MixesTestResults> getById(Integer id) {
        logger.info("Fetching MixesTestResults with ID: {}", id);
        return mixesTestResultsRepository.findById(id);
    }

    // Delete a MixesTestResults by ID
    public boolean delete(Integer id) {
        Optional<MixesTestResults> mixesTestResults = mixesTestResultsRepository.findById(id);
        if (mixesTestResults.isPresent()) {
            logger.info("Deleting MixesTestResults with ID: {}", id);
            mixesTestResultsRepository.delete(mixesTestResults.get());
            return true;
        } else {
            logger.warn("MixesTestResults with ID {} not found for deletion", id);
        }
        return false;
    }

    // Update an existing MixesTestResults
    public MixesTestResults update(Integer id, MixesTestResults updatedMixesTestResults) {
        logger.info("Attempting to update MixesTestResults with ID: {}", id);
        if (mixesTestResultsRepository.existsById(id)) {
            updatedMixesTestResults.setId(id);

            // Set the existing data before updating fields
            MixesTestResults existingMixesTestResults = mixesTestResultsRepository.findById(id).get();

            // Update each field accordingly (only if provided in the update)
            existingMixesTestResults.setCreatedBy(updatedMixesTestResults.getCreatedBy());
            existingMixesTestResults.setLastModifiedBy(updatedMixesTestResults.getLastModifiedBy());
            existingMixesTestResults.setCreatedDate(updatedMixesTestResults.getCreatedDate());
            existingMixesTestResults.setLastModifiedDate(updatedMixesTestResults.getLastModifiedDate());
            existingMixesTestResults.setDate(updatedMixesTestResults.getDate());
            existingMixesTestResults.setShift(updatedMixesTestResults.getShift());
            existingMixesTestResults.setLabSampleNo(updatedMixesTestResults.getLabSampleNo());
            existingMixesTestResults.setSoNo(updatedMixesTestResults.getSoNo());
            existingMixesTestResults.setMaterialNo(updatedMixesTestResults.getMaterialNo());
            existingMixesTestResults.setPoPosNo(updatedMixesTestResults.getPoPosNo());
            existingMixesTestResults.setRecipeNo(updatedMixesTestResults.getRecipeNo());
            existingMixesTestResults.setAltNo(updatedMixesTestResults.getAltNo());
            existingMixesTestResults.setHpg(updatedMixesTestResults.getHpg());
            existingMixesTestResults.setPg(updatedMixesTestResults.getPg());
            existingMixesTestResults.setQuality(updatedMixesTestResults.getQuality());
            existingMixesTestResults.setSBatchNo(updatedMixesTestResults.getSBatchNo());
            existingMixesTestResults.setEBatchNo(updatedMixesTestResults.getEBatchNo());
            existingMixesTestResults.setQntyMt(updatedMixesTestResults.getQntyMt());
            existingMixesTestResults.setFreeIron(updatedMixesTestResults.getFreeIron());
            existingMixesTestResults.setMoisturePercentage(updatedMixesTestResults.getMoisturePercentage());
            existingMixesTestResults.setLessthanTwelveMM(updatedMixesTestResults.getLessthanTwelveMM());
            existingMixesTestResults.setLessthanSixThreeMM(updatedMixesTestResults.getLessthanSixThreeMM());
            existingMixesTestResults.setLessthanFiveSixMM(updatedMixesTestResults.getLessthanFiveSixMM());
            existingMixesTestResults.setLessthanThreeFifteenMM(updatedMixesTestResults.getLessthanThreeFifteenMM());
            existingMixesTestResults.setLessthanOneMM(updatedMixesTestResults.getLessthanOneMM());
            existingMixesTestResults.setLessthanZeroSixtythreeMM(updatedMixesTestResults.getLessthanZeroSixtythreeMM());
            existingMixesTestResults.setAirTemperature(updatedMixesTestResults.getAirTemperature());
            existingMixesTestResults.setWaterTemperature(updatedMixesTestResults.getWaterTemperature());
            existingMixesTestResults.setDryMixTemperature(updatedMixesTestResults.getDryMixTemperature());
            existingMixesTestResults.setWetMixTemperature(updatedMixesTestResults.getWetMixTemperature());
            existingMixesTestResults.setDryMixTime(updatedMixesTestResults.getDryMixTime());
            existingMixesTestResults.setWetMixTime(updatedMixesTestResults.getWetMixTime());
            existingMixesTestResults.setWaterPercentage(updatedMixesTestResults.getWaterPercentage());
            existingMixesTestResults.setFlowImmd(updatedMixesTestResults.getFlowImmd());
            existingMixesTestResults.setFlowAfterTwentyMin(updatedMixesTestResults.getFlowAfterTwentyMin());
            existingMixesTestResults.setTwentyfiveDegreesCelsius(updatedMixesTestResults.getTwentyfiveDegreesCelsius());
            existingMixesTestResults.setThirtyDegreesCelsius(updatedMixesTestResults.getThirtyDegreesCelsius());
            existingMixesTestResults.setApPercentageAtOneTenDegressCelsius(updatedMixesTestResults.getApPercentageAtOneTenDegressCelsius());
            existingMixesTestResults.setBdGSlashCcAtOneTenDegressCelsius(updatedMixesTestResults.getBdGSlashCcAtOneTenDegressCelsius());
            existingMixesTestResults.setCcsNSlashMmTwoAtOneTenDegressCelsius(updatedMixesTestResults.getCcsNSlashMmTwoAtOneTenDegressCelsius());
            existingMixesTestResults.setLcAtOneTenDegressCelsius(updatedMixesTestResults.getLcAtOneTenDegressCelsius());
            existingMixesTestResults.setApPercentageAtThousandDegressCelsius(updatedMixesTestResults.getApPercentageAtThousandDegressCelsius());
            existingMixesTestResults.setBdGSlashCcAtThousandDegressCelsius(updatedMixesTestResults.getBdGSlashCcAtThousandDegressCelsius());
            existingMixesTestResults.setCcsNSlashMmTwoAtThousandDegressCelsius(updatedMixesTestResults.getCcsNSlashMmTwoAtThousandDegressCelsius());
            existingMixesTestResults.setLcAtThousandDegressCelsius(updatedMixesTestResults.getLcAtThousandDegressCelsius());
            existingMixesTestResults.setAlTwoOThreePercentage(updatedMixesTestResults.getAlTwoOThreePercentage());
            existingMixesTestResults.setFeTwoOThreePercentage(updatedMixesTestResults.getFeTwoOThreePercentage());
            existingMixesTestResults.setTioTwoPercentage(updatedMixesTestResults.getTioTwoPercentage());
            existingMixesTestResults.setCaoPercentage(updatedMixesTestResults.getCaoPercentage());
            existingMixesTestResults.setMgoPercentage(updatedMixesTestResults.getMgoPercentage());
            existingMixesTestResults.setSioTwoPercentage(updatedMixesTestResults.getSioTwoPercentage());
            existingMixesTestResults.setCrTwoOThreePercentage(updatedMixesTestResults.getCrTwoOThreePercentage());
            existingMixesTestResults.setPTwoOFivePercentage(updatedMixesTestResults.getPTwoOFivePercentage());
            existingMixesTestResults.setNaTwoOPercentage(updatedMixesTestResults.getNaTwoOPercentage());
            existingMixesTestResults.setKTwoOPercentage(updatedMixesTestResults.getKTwoOPercentage());
            existingMixesTestResults.setLoiPercentage(updatedMixesTestResults.getLoiPercentage());
            existingMixesTestResults.setCPercentage(updatedMixesTestResults.getCPercentage());
            existingMixesTestResults.setSicPercentage(updatedMixesTestResults.getSicPercentage());
            existingMixesTestResults.setStatus(updatedMixesTestResults.getStatus());
            existingMixesTestResults.setLessthanFourMM(updatedMixesTestResults.getLessthanFourMM());
            existingMixesTestResults.setLessthanZeroFiftyMM(updatedMixesTestResults.getLessthanZeroFiftyMM());
            existingMixesTestResults.setLessthanZerothreefifteenMM(updatedMixesTestResults.getLessthanZerothreefifteenMM());
            existingMixesTestResults.setCcsNSlashMmEightFifteenDegressCelsius(updatedMixesTestResults.getCcsNSlashMmEightFifteenDegressCelsius());
            existingMixesTestResults.setApprovalBy(updatedMixesTestResults.getApprovalBy());
            existingMixesTestResults.setRemarks(updatedMixesTestResults.getRemarks());

            logger.info("Updating MixesTestResults with ID: {}", id);
            return mixesTestResultsRepository.save(existingMixesTestResults);
        } else {
            logger.warn("MixesTestResults with ID {} not found for update", id);
        }
        return null;
    }
}
