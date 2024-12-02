package com.bpl.Production.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.bpl.Production.entity.SampleTestResults;
import com.bpl.Production.repository.SampleTestResultsRepository;

@Service
public class SampleTestResultsService {

    private static final Logger logger = LoggerFactory.getLogger(SampleTestResultsService.class);

    @Autowired
    private SampleTestResultsRepository sampleTestResultsRepository;

    // Create SampleTestResult
    public SampleTestResults createSampleTestResult(SampleTestResults sampleTestResult) {
        logger.info("Creating SampleTestResult with receivedFrom: {}, labSampleNo: {}", sampleTestResult.getReceivedFrom(), sampleTestResult.getLabSampleNo());
        SampleTestResults createdResult = sampleTestResultsRepository.save(sampleTestResult);
        logger.info("SampleTestResult created successfully with ID: {}", createdResult.getId());
        return createdResult;
    }

    // Update SampleTestResult
    public SampleTestResults updateSampleTestResult(Integer id, SampleTestResults sampleTestResult) {
        logger.info("Updating SampleTestResult with ID: {}", id);
        SampleTestResults existingResult = sampleTestResultsRepository.findById(id)
                .orElseThrow(() -> {
                    logger.error("SampleTestResult not found with ID: {}", id);
                    return new RuntimeException("SampleTestResult not found with id: " + id);
                });

        sampleTestResult.setId(existingResult.getId()); // Ensure the ID remains the same
        SampleTestResults updatedResult = sampleTestResultsRepository.save(sampleTestResult);
        logger.info("SampleTestResult with ID: {} updated successfully", updatedResult.getId());
        return updatedResult;
    }

    // Delete SampleTestResult
    public void deleteSampleTestResult(Integer id) {
        logger.info("Deleting SampleTestResult with ID: {}", id);
        sampleTestResultsRepository.deleteById(id);
        logger.info("SampleTestResult with ID: {} deleted successfully", id);
    }

    // Get SampleTestResult by ID
    public SampleTestResults getSampleTestResultById(Integer id) {
        logger.info("Fetching SampleTestResult with ID: {}", id);
        SampleTestResults result = sampleTestResultsRepository.findById(id)
                .orElseThrow(() -> {
                    logger.error("SampleTestResult not found with ID: {}", id);
                    return new RuntimeException("SampleTestResult not found with id: " + id);
                });
        logger.info("SampleTestResult with ID: {} found", id);
        return result;
    }

    // Get all SampleTestResults
    public List<SampleTestResults> getAllSampleTestResults() {
        logger.info("Fetching all SampleTestResults");
        List<SampleTestResults> results = sampleTestResultsRepository.findAll();
        logger.info("Fetched {} SampleTestResults", results.size());
        return results;
    }

    // Get filtered SampleTestResults
    public Page<SampleTestResults> getFilteredResults(String fromDateStr, String toDateStr, String tagStr, Pageable pageable) throws ParseException {
        logger.info("Fetching filtered SampleTestResults with fromDate: {}, toDate: {}, tag: {}", fromDateStr, toDateStr, tagStr);

        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        Date fromDate = fromDateStr != null && !fromDateStr.isEmpty() ? sdf.parse(fromDateStr) : null;
        Date toDateInitial = toDateStr != null && !toDateStr.isEmpty() ? sdf.parse(toDateStr) : null;

        // Ensure 'toDate' is finalized
        final Date toDate = fromDate != null && toDateInitial == null ? fromDate : toDateInitial;

        Specification<SampleTestResults> specification = Specification.where(null);

        if (fromDate != null && toDate != null) {
            specification = specification.and((root, query, criteriaBuilder) ->
                    criteriaBuilder.between(root.get("sampleReceivedDate"), fromDate, toDate));
            logger.info("Added date filter from {} to {}", fromDate, toDate);
        }

        if (tagStr != null && !tagStr.isEmpty()) {
            specification = specification.and((root, query, criteriaBuilder) ->
                    criteriaBuilder.or(
                            criteriaBuilder.like(root.get("receivedFrom"), "%" + tagStr + "%"),
                            criteriaBuilder.like(root.get("purpose"), "%" + tagStr + "%"),
                            criteriaBuilder.like(root.get("freeIron"), "%" + tagStr + "%"),
                            criteriaBuilder.like(root.get("labSampleNo"), "%" + tagStr + "%")
                    ));
            logger.info("Added tag filter with value: {}", tagStr);
        }

        Page<SampleTestResults> resultsPage = sampleTestResultsRepository.findAll(specification, pageable);
        logger.info("Fetched {} SampleTestResults with filters", resultsPage.getTotalElements());
        return resultsPage;
    }
}
