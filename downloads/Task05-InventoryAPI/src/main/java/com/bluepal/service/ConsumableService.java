package com.bluepal.service;


import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.bluepal.entity.Consumable;
import com.bluepal.repository.ConsumableRepository;

@Service
public class ConsumableService {

    private static final Logger logger = LoggerFactory.getLogger(ConsumableService.class);

    @Autowired
    private ConsumableRepository consumableRepository;

    // CRUD Operations with Caching and Logging
    @CachePut(value = "consumables", key = "#result.materialNo")
    public Consumable createConsumable(Consumable consumable) {
        logger.info("Creating a new consumable: {}", consumable.getMaterialName());
        Consumable savedConsumable = consumableRepository.save(consumable);
        logger.info("Consumable created with Material No: {}", savedConsumable.getMaterialNo());
        return savedConsumable;
    }

    @CachePut(value = "consumables", key = "#id")
    public Consumable updateConsumable(Long id, Consumable consumable) {
        logger.info("Updating consumable with id: {}", id);
        if (consumableRepository.existsById(id)) {
            consumable.setMaterialNo(id); // Ensure the ID is not lost during update.
            Consumable updatedConsumable = consumableRepository.save(consumable);
            logger.info("Consumable with Material No {} updated successfully.", id);
            return updatedConsumable;
        } else {
            logger.warn("Consumable with id {} not found.", id);
            throw new RuntimeException("Consumable not found");
        }
    }

    @CacheEvict(value = "consumables", key = "#id")
    public void deleteConsumable(Long id) {
        logger.info("Attempting to delete consumable with id: {}", id);
        if (consumableRepository.existsById(id)) {
            consumableRepository.deleteById(id);
            logger.info("Consumable with id {} deleted successfully.", id);
        } else {
            logger.warn("Consumable with id {} not found.", id);
            throw new RuntimeException("Consumable not found");
        }
    }

    @Cacheable(value = "consumables", key = "#id")
    public Consumable getConsumableById(Long id) {
        logger.info("Fetching consumable with id: {}", id);
        return consumableRepository.findById(id).orElseThrow(() -> {
            logger.warn("Consumable with id {} not found.", id);
            return new RuntimeException("Consumable not found");
        });
    }

    @Cacheable(value = "consumables", key = "#page + '-' + #size + '-' + #sortBy")
    public List<Consumable> getAllConsumables(int page, int size, String sortBy) {
        logger.info("Fetching all consumables with pagination - page: {}, size: {}, sortBy: {}", page, size, sortBy);
        
        // Fetch consumables with pagination and sorting
        Page<Consumable> consumablesPage = consumableRepository.findAll(PageRequest.of(page, size, Sort.by(sortBy)));
        
        // Return the content of the page
        logger.info("Fetched {} consumables.", consumablesPage.getContent().size());
        return consumablesPage.getContent();
    }

    public Page<Consumable> searchConsumables(String materialName, String materialGroup, Pageable pageable) {
        logger.info("Searching consumables with Material Name: {}, Material Group: {}, page: {}, size: {}, sortBy: {}", 
            materialName, materialGroup, pageable.getPageNumber(), pageable.getPageSize(), pageable.getSort());

        // Fetch consumables with pagination and sorting
        Page<Consumable> consumablesPage = consumableRepository.findAll(pageable);
        
        // Filter results based on materialName and materialGroup
        List<Consumable> consumables = consumablesPage.getContent().stream()
                .filter(consumable -> (materialName == null || consumable.getMaterialName().contains(materialName)) &&
                                      (materialGroup == null || consumable.getMaterialGroup().contains(materialGroup)))
                .collect(Collectors.toList());

        logger.info("Found {} consumables matching the search criteria.", consumables.size());
        
        // Return the filtered results as a Page
        return new PageImpl<>(consumables, pageable, consumablesPage.getTotalElements());
    }


    // Export all consumables to Excel
    // Export all consumables to Excel
    public byte[] exportConsumablesToExcel() throws IOException {
        // Fetch all consumables from the repository
        List<Consumable> consumables = consumableRepository.findAll();

        // Create an Excel workbook
        try (XSSFWorkbook workbook = new XSSFWorkbook()) {
            // Create a sheet for the consumables data
            Sheet sheet = workbook.createSheet("Consumables");

            // Create header row (column names)
            Row header = sheet.createRow(0);
            header.createCell(0).setCellValue("Material No");
            header.createCell(1).setCellValue("Material Group");
            header.createCell(2).setCellValue("Material Sub-Group");
            header.createCell(3).setCellValue("Material Name");
            header.createCell(4).setCellValue("Length");
            header.createCell(5).setCellValue("Breadth");
            header.createCell(6).setCellValue("Height");
            header.createCell(7).setCellValue("Original OEM");
            header.createCell(8).setCellValue("Part No");
            header.createCell(9).setCellValue("Drawing No");
            header.createCell(10).setCellValue("Unit of Measurement");
            header.createCell(11).setCellValue("Min Order Level");
            header.createCell(12).setCellValue("Lead Time");

            // Fill data rows with consumable details
            int rowNum = 1;
            for (Consumable consumable : consumables) {
                Row row = sheet.createRow(rowNum++);
                row.createCell(0).setCellValue(consumable.getMaterialNo());
                row.createCell(1).setCellValue(consumable.getMaterialGroup());
                row.createCell(2).setCellValue(consumable.getMaterialSubGroup());
                row.createCell(3).setCellValue(consumable.getMaterialName());
                row.createCell(4).setCellValue(consumable.getLength() != null ? consumable.getLength() : 0);
                row.createCell(5).setCellValue(consumable.getBreadth() != null ? consumable.getBreadth() : 0);
                row.createCell(6).setCellValue(consumable.getHeight() != null ? consumable.getHeight() : 0);
                row.createCell(7).setCellValue(consumable.getOriginalOEM());
                row.createCell(8).setCellValue(consumable.getPartNo());
                row.createCell(9).setCellValue(consumable.getDrawingNo());
                row.createCell(10).setCellValue(consumable.getUnitOfMeasurement());
                row.createCell(11).setCellValue(consumable.getMinimumOrderLevel() != null ? consumable.getMinimumOrderLevel() : 0);
                row.createCell(12).setCellValue(consumable.getLeadTime() != null ? consumable.getLeadTime() : 0);
            }

            // Write to ByteArrayOutputStream
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            workbook.write(out);

            // Return the byte array of the workbook content
            return out.toByteArray();
        }
    }
    // Import consumables from Excel with logging
    public void importConsumablesFromExcel(MultipartFile file) throws IOException {
        logger.info("Starting the import of consumables from Excel file: {}", file.getOriginalFilename());

        try (InputStream is = file.getInputStream()) {
            XSSFWorkbook workbook = new XSSFWorkbook(is);
            Sheet sheet = workbook.getSheetAt(0);
            Iterator<Row> rowIterator = sheet.iterator();

            // Skip the header row
            if (rowIterator.hasNext()) {
                rowIterator.next(); // Skip the header row
                logger.info("Header row skipped.");
            }

            // Iterate over the rows and map them to Consumable entities
            int importedCount = 0;
            while (rowIterator.hasNext()) {
                Row row = rowIterator.next();
                Consumable consumable = new Consumable();

                // Map each column of the row to the consumable entity
                consumable.setMaterialNo(null); // Material No will be auto-generated
                consumable.setMaterialGroup(row.getCell(1).getStringCellValue());
                consumable.setMaterialSubGroup(row.getCell(2).getStringCellValue());
                consumable.setMaterialName(row.getCell(3).getStringCellValue());
                consumable.setLength(row.getCell(4).getNumericCellValue());
                consumable.setBreadth(row.getCell(5).getNumericCellValue());
                consumable.setHeight(row.getCell(6).getNumericCellValue());
                consumable.setOriginalOEM(row.getCell(7).getStringCellValue());
                consumable.setPartNo(row.getCell(8).getStringCellValue());
                consumable.setDrawingNo(row.getCell(9).getStringCellValue());
                consumable.setUnitOfMeasurement(row.getCell(10).getStringCellValue());
                consumable.setMinimumOrderLevel((int) row.getCell(11).getNumericCellValue());
                consumable.setLeadTime((int) row.getCell(12).getNumericCellValue());

                // Save the consumable to the repository
                consumableRepository.save(consumable);
                importedCount++;
            }
            logger.info("Successfully imported {} consumables from Excel.", importedCount);

        } catch (IOException e) {
            logger.error("Error occurred while importing consumables from Excel file: {}", file.getOriginalFilename(), e);
            throw e; // Re-throw the exception to be handled by the caller if necessary
        }
    }
}
