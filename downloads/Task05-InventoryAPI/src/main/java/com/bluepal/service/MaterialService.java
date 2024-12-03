package com.bluepal.service;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.bluepal.entity.Material;
import com.bluepal.repository.MaterialRepository;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import lombok.extern.slf4j.Slf4j;


@Service
@Slf4j
public class MaterialService {

    @Autowired
    private MaterialRepository materialRepository;

    private static final Logger logger = LoggerFactory.getLogger(MaterialService.class);

    @Cacheable(value = "materials", key = "#id")
    public Material getMaterialById(Long id) {
        logger.info("Fetching material with id: {}", id);
        return materialRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Material not found with id: " + id));
    }

    @CacheEvict(value = "materials", allEntries = true)
    public Material createMaterial(Material material) {
        logger.info("Creating material: {}", material);
        return materialRepository.save(material);
    }

    @CacheEvict(value = "materials", key = "#id")
    public void deleteMaterial(Long id) {
        logger.info("Deleting material with id: {}", id);
        materialRepository.deleteById(id);
    }

    @CachePut(value = "materials", key = "#id")
    public Material updateMaterial(Long id, Material updatedMaterial) {
        logger.info("Updating material with id: {}", id);
        Material material = getMaterialById(id);
        updatedMaterial.setMaterialNo(material.getMaterialNo());
        return materialRepository.save(updatedMaterial);
    }

    public List<Material> getAllMaterials(int page, int size, String sortBy) {
        logger.info("Fetching all materials with pagination - page: {}, size: {}, sortBy: {}", page, size, sortBy);
        return materialRepository.findAll(PageRequest.of(page, size, Sort.by(sortBy))).getContent();
    }

    public List<Material> searchMaterials(String keyword) {
        logger.info("Searching materials with keyword: {}", keyword);
        return materialRepository.findByMaterialNameContainingIgnoreCase(keyword);
    }


    public String exportMaterials() {
        logger.info("Exporting materials to Excel.");
        List<Material> materials = materialRepository.findAll();
        String filePath = "Materials.xlsx";

        try (Workbook workbook = new XSSFWorkbook();
             FileOutputStream outputStream = new FileOutputStream(filePath)) {

            Sheet sheet = workbook.createSheet("Materials");
            int rowNum = 0;

            // Create Header Row
            Row headerRow = sheet.createRow(rowNum++);
            headerRow.createCell(0).setCellValue("Material No");
            headerRow.createCell(1).setCellValue("Material Group");
            headerRow.createCell(2).setCellValue("Material Sub-Group");
            headerRow.createCell(3).setCellValue("Material Name");
            headerRow.createCell(4).setCellValue("Size");
            headerRow.createCell(5).setCellValue("Unit of Measurement");
            headerRow.createCell(6).setCellValue("Minimum Order Level");
            headerRow.createCell(7).setCellValue("Lead Time");
            headerRow.createCell(8).setCellValue("Al2O3 Min");
            headerRow.createCell(9).setCellValue("Al2O3 Max");
            headerRow.createCell(10).setCellValue("Al2O3 Typical");
            headerRow.createCell(11).setCellValue("SiO2 Min");
            headerRow.createCell(12).setCellValue("SiO2 Max");
            headerRow.createCell(13).setCellValue("SiO2 Typical");
            headerRow.createCell(14).setCellValue("Fe2O3 Min");
            headerRow.createCell(15).setCellValue("Fe2O3 Max");
            headerRow.createCell(16).setCellValue("Fe2O3 Typical");

            // Fill Data Rows
            for (Material material : materials) {
                Row row = sheet.createRow(rowNum++);
                row.createCell(0).setCellValue(material.getMaterialNo());
                row.createCell(1).setCellValue(material.getMaterialGroup());
                row.createCell(2).setCellValue(material.getMaterialSubGroup());
                row.createCell(3).setCellValue(material.getMaterialName());
                row.createCell(4).setCellValue(material.getSize());
                row.createCell(5).setCellValue(material.getUnitOfMeasurement());
                row.createCell(6).setCellValue(material.getMinimumOrderLevel());
                row.createCell(7).setCellValue(material.getLeadTime());
                row.createCell(8).setCellValue(material.getAl2o3Min());
                row.createCell(9).setCellValue(material.getAl2o3Max());
                row.createCell(10).setCellValue(material.getAl2o3Typical());
                row.createCell(11).setCellValue(material.getSio2Min());
                row.createCell(12).setCellValue(material.getSio2Max());
                row.createCell(13).setCellValue(material.getSio2Typical());
                row.createCell(14).setCellValue(material.getFe2o3Min());
                row.createCell(15).setCellValue(material.getFe2o3Max());
                row.createCell(16).setCellValue(material.getFe2o3Typical());
            }

            workbook.write(outputStream);
            logger.info("Materials exported to file: {}", filePath);
            return filePath;

        } catch (Exception e) {
            logger.error("Error while exporting materials: {}", e.getMessage());
            throw new RuntimeException("Error while exporting materials: " + e.getMessage());
        }
    }

    @CacheEvict(value = "materials", allEntries = true)
    public void importMaterials(MultipartFile file) {
        logger.info("Importing materials from file: {}", file.getOriginalFilename());
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();

        try (InputStream inputStream = file.getInputStream();
             Workbook workbook = new XSSFWorkbook(inputStream)) {

            Sheet sheet = workbook.getSheetAt(0);
            List<Material> materials = new ArrayList<>();

            for (Row row : sheet) {
                if (row.getRowNum() == 0) continue; // Skip header row

                Material material = new Material();

                // Get Material Number (Long)
                try {
                    String materialNoAsString = getCellValueAsString(row.getCell(0)).trim();
                    if (!materialNoAsString.isEmpty()) {
                        material.setMaterialNo(Long.parseLong(materialNoAsString));
                    } else {
                        logger.error("Material number is empty at row {}", row.getRowNum());
                        continue; // Skip this row if the material number is missing
                    }
                } catch (NumberFormatException e) {
                    logger.error("Invalid material number format at row {}: {}", row.getRowNum(), e.getMessage());
                    continue; // Skip this row if the material number is invalid
                }

                // Get Material Group (String)
                material.setMaterialGroup(getCellValueAsString(row.getCell(1)));

                // Get Material Sub-Group (String)
                material.setMaterialSubGroup(getCellValueAsString(row.getCell(2)));

                // Get Material Name (String)
                material.setMaterialName(getCellValueAsString(row.getCell(3)));

                // Get Size (String)
                material.setSize(getCellValueAsString(row.getCell(4)));

                // Get Unit of Measurement (String)
                material.setUnitOfMeasurement(getCellValueAsString(row.getCell(5)));

                // Get Minimum Order Level (Numeric)
                material.setMinimumOrderLevel(getCellValueAsInt(row.getCell(6)));

                // Get Lead Time (Numeric)
                material.setLeadTime(getCellValueAsInt(row.getCell(7)));

                // Get Al2o3 Min (String)
                material.setAl2o3Min(getCellValueAsString(row.getCell(8)));

                // Get Al2o3 Max (String)
                material.setAl2o3Max(getCellValueAsString(row.getCell(9)));

                // Get Al2o3 Typical (String)
                material.setAl2o3Typical(getCellValueAsString(row.getCell(10)));

                // Get Sio2 Min (String)
                material.setSio2Min(getCellValueAsString(row.getCell(11)));

                // Get Sio2 Max (String)
                material.setSio2Max(getCellValueAsString(row.getCell(12)));

                // Get Sio2 Typical (String)
                material.setSio2Typical(getCellValueAsString(row.getCell(13)));

                // Get Fe2o3 Min (String)
                material.setFe2o3Min(getCellValueAsString(row.getCell(14)));

                // Get Fe2o3 Max (String)
                material.setFe2o3Max(getCellValueAsString(row.getCell(15)));

                // Get Fe2o3 Typical (String)
                material.setFe2o3Typical(getCellValueAsString(row.getCell(16)));

                // Validate the material object before saving
                Set<ConstraintViolation<Material>> violations = validator.validate(material);
                if (!violations.isEmpty()) {
                    for (ConstraintViolation<Material> violation : violations) {
                        logger.error("Validation error: {} - {} at row {}",
                                violation.getMessage(), violation.getPropertyPath(), row.getRowNum());
                    }
                    continue; // Skip this invalid row and continue with the next row
                }

                materials.add(material);
            }

            materialRepository.saveAll(materials);
            logger.info("Successfully imported {} materials.", materials.size());
        } catch (Exception e) {
            logger.error("Error while importing materials: {}", e.getMessage());
            throw new RuntimeException("Error while importing materials: " + e.getMessage());
        }
    }

    // Utility methods for getting cell values as string and int
    private String getCellValueAsString(Cell cell) {
        if (cell == null) return "";
        switch (cell.getCellType()) {
            case STRING:
                return cell.getStringCellValue();
            case NUMERIC:
                return String.valueOf(cell.getNumericCellValue());
            case BOOLEAN:
                return String.valueOf(cell.getBooleanCellValue());
            default:
                return "";
        }
    }

    private int getCellValueAsInt(Cell cell) {
        if (cell == null) return 0;
        switch (cell.getCellType()) {
            case NUMERIC:
                return (int) cell.getNumericCellValue();
            case STRING:
                try {
                    return Integer.parseInt(cell.getStringCellValue());
                } catch (NumberFormatException e) {
                    return 0; // default value if unable to parse
                }
            default:
                return 0; // default value for other types
        }
    }

}