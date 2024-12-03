package com.bluepal.service;



import java.io.OutputStream;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.bluepal.entity.RFG;
import com.bluepal.repository.RFGRepository;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import jakarta.servlet.http.HttpServletResponse;

@Service
public class RFGService {

    private static final Logger logger = LoggerFactory.getLogger(RFGService.class);

    @Autowired
    private RFGRepository rfgRepository;

    // Create or Update RFG
    @CachePut(value = "rfgCache", key = "#rfg.productNo")
    public RFG saveRFG(RFG rfg) {
        logger.info("Saving RFG: {}", rfg);
        return rfgRepository.save(rfg);
    }
    
    @CachePut(value = "rfgCache", key = "#productNo")
    public RFG updateRFG(Integer productNo, RFG updatedRFG) {
        logger.info("Updating RFG with ProductNo: {}", productNo);
        return rfgRepository.findById(productNo).map(existingRFG -> {
            if (updatedRFG.getProductGroup() != null) existingRFG.setProductGroup(updatedRFG.getProductGroup());
            if (updatedRFG.getProductSubGroup() != null) existingRFG.setProductSubGroup(updatedRFG.getProductSubGroup());
            if (updatedRFG.getProductName() != null) existingRFG.setProductName(updatedRFG.getProductName());
            if (updatedRFG.getShapeNo() != null) existingRFG.setShapeNo(updatedRFG.getShapeNo());
            if (updatedRFG.getDrawingNo() != null) existingRFG.setDrawingNo(updatedRFG.getDrawingNo());
            if (updatedRFG.getBulkDensity() != null) existingRFG.setBulkDensity(updatedRFG.getBulkDensity());
            if (updatedRFG.getVolume() != null) existingRFG.setVolume(updatedRFG.getVolume());
            if (updatedRFG.getUnitWt() != null) existingRFG.setUnitWt(updatedRFG.getUnitWt());
            if (updatedRFG.getUnitOfMeasurement() != null) {
                existingRFG.setUnitOfMeasurement(updatedRFG.getUnitOfMeasurement());
            }
//            existingRFG.setUpdatedAt((System.currentTimeMillis())); // Update timestamp
            return rfgRepository.save(existingRFG);
        }).orElseThrow(() -> new IllegalArgumentException("RFG with ProductNo " + productNo + " not found!"));
    }

    // Get RFG by ProductNo
    @Cacheable(value = "rfgCache", key = "#productNo")
    public Optional<RFG> getRFGByProductNo(Integer productNo) {
        logger.info("Fetching RFG by ProductNo: {}", productNo);
        return rfgRepository.findByProductNo(productNo);
    }

    // Get RFG by ProductName
    @Cacheable(value = "rfgCacheByName", key = "#productName")
    public Optional<RFG> getRFGByProductName(String productName) {
        logger.info("Fetching RFG by ProductName: {}", productName);
        return rfgRepository.findByProductName(productName);
    }

    // Delete RFG by ProductNo
    @CacheEvict(value = "rfgCache", key = "#productNo")
    public void deleteRFG(Integer productNo) {
        logger.info("Deleting RFG by ProductNo: {}", productNo);
        rfgRepository.deleteById(productNo);
    }

    // Get all RFGs with pagination
    public Page<RFG> getAllRFGs(Pageable pageable) {
        logger.info("Fetching all RFGs with pageable: {}", pageable);
        return rfgRepository.findAll(pageable);
    }

    public Page<RFG> getAllRFGs(int page, int size, String sortField, String sortDirection, String search, String name,
            String email) {
        logger.info(
                "Fetching all RFGs with page: {}, size: {}, sortField: {}, sortDirection: {}, search: {}, name: {}, email: {}",
                page, size, sortField, sortDirection, search, name, email);
        Sort sort = Sort.by(
                sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.Direction.ASC : Sort.Direction.DESC,
                sortField);
        PageRequest pageable = PageRequest.of(page, size, sort);

        Specification<RFG> spec = CustomerSpecification.filterCustomers(search, name, email);
        return rfgRepository.findAll(spec, pageable);
    }
    
    public void generateAndDownloadProductSpecsReport(String searchCriteria, HttpServletResponse response) {
        logger.info("Generating and downloading Product Specs report for search criteria: {}", searchCriteria);

        // Declare the list of products
        List<RFG> products;
        
        // Check if searchCriteria is numeric (for productNo)
        if (isNumeric(searchCriteria)) {
            // Search by productNo if the searchCriteria is a number
            Integer productNo = Integer.valueOf(searchCriteria); // Convert searchCriteria to Integer
            products = rfgRepository.findByProductNoOrProductNameContainingIgnoreCase(productNo, searchCriteria);
        } else {
            // Otherwise, search by productName
            products = rfgRepository.findByProductNoOrProductNameContainingIgnoreCase(null, searchCriteria);
        }

        if (products.isEmpty()) {
            throw new RuntimeException("No products found for the given search criteria: " + searchCriteria);
        }

        // Set response headers for file download
        response.setContentType("application/pdf");
        response.setHeader("Content-Disposition", "attachment; filename=ProductSpecs_Report_" + searchCriteria + ".pdf");

        try (OutputStream out = response.getOutputStream()) {
            // Create a PDF document
            Document document = new Document(PageSize.A4);
            PdfWriter.getInstance(document, out);

            document.open();

            // Add a title
            Font titleFont = new Font(Font.FontFamily.HELVETICA, 16, Font.BOLD);
            Paragraph title = new Paragraph("Product Specs Report", titleFont);
            title.setAlignment(Element.ALIGN_CENTER);
            document.add(title);

            document.add(new Paragraph("Search Criteria: " + searchCriteria));
            document.add(new Paragraph("Generated on: " + new java.util.Date()));
            document.add(new Paragraph(" ")); // Add empty line

            // Create a table for product specs
            PdfPTable table = new PdfPTable(6); // Adjust column count as needed
            table.setWidthPercentage(100);

            // Add table headers
            String[] headers = {
                    "Product No", "Product Name", "Product Group", "Shape No", "Volume", "Unit Weight"
            };
            for (String header : headers) {
                PdfPCell headerCell = new PdfPCell(new Phrase(header));
                headerCell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(headerCell);
            }

            // Populate table with product data
            for (RFG product : products) {
                table.addCell(product.getProductNo().toString());
                table.addCell(product.getProductName());
                table.addCell(product.getProductGroup());
                table.addCell(product.getShapeNo());
                table.addCell(product.getVolume().toString());
                table.addCell(product.getUnitWt().toString());
            }

            document.add(table); // Add table to the PDF
            document.close();

            logger.info("Product Specs report generated and sent for download");

        } catch (Exception e) {
            logger.error("Error while generating or downloading Product Specs report: {}", e.getMessage());
            throw new RuntimeException("Error while generating or downloading Product Specs report: " + e.getMessage());
        }
    }

    private boolean isNumeric(String str) {
        try {
            Integer.parseInt(str);  // Try to parse the string as an integer
            return true;
        } catch (NumberFormatException e) {
            return false;  // Return false if the string is not a valid integer
        }
    }


}