package com.bluepal.service;

import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.bluepal.entity.PackingMaterial;
import com.bluepal.repository.PackingMaterialRepository;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

@Service
@EnableCaching
public class PackingMaterialService {

    private static final Logger logger = LoggerFactory.getLogger(PackingMaterialService.class);

    @Autowired
    private PackingMaterialRepository packingMaterialRepository;

    // Create a new PackingMaterial
    public PackingMaterial createPackingMaterial(PackingMaterial packingMaterial) {
        logger.info("Creating new packing material: {}", packingMaterial);
        return packingMaterialRepository.save(packingMaterial);
    }

    // Get PackingMaterial by ID
    public PackingMaterial getPackingMaterialById(Long id) {
        logger.info("Fetching packing material with ID: {}", id);
        return packingMaterialRepository.findById(id).orElse(null); // Return null if not found
    }

    // Get all PackingMaterials with Pagination and caching
    @Cacheable(value = "packingMaterialsCache")
    public Page<PackingMaterial> getAllPackingMaterials(Pageable pageable) {
        logger.info("Fetching all packing materials with pagination: {}", pageable);
        return packingMaterialRepository.findAll(pageable);
    }

    // Update PackingMaterial by ID
    public PackingMaterial updatePackingMaterial(Long id, PackingMaterial packingMaterial) {
        logger.info("Updating packing material with ID: {}", id);
        // If not found, simply return the existing object or create a new one
        PackingMaterial existingMaterial = packingMaterialRepository.findById(id).orElse(new PackingMaterial());
        // Update fields only if material is found, otherwise it's a new entry
        existingMaterial.setMaterialGroup(packingMaterial.getMaterialGroup());
        existingMaterial.setMaterialNo(packingMaterial.getMaterialNo());
        existingMaterial.setMaterialName(packingMaterial.getMaterialName());
        existingMaterial.setLength(packingMaterial.getLength());
        existingMaterial.setBreadth(packingMaterial.getBreadth());
        existingMaterial.setHeight(packingMaterial.getHeight());
        existingMaterial.setDiameter(packingMaterial.getDiameter());
        existingMaterial.setThickness(packingMaterial.getThickness());
        existingMaterial.setGsm(packingMaterial.getGsm());
        existingMaterial.setUnitOfMeasurement(packingMaterial.getUnitOfMeasurement());
        existingMaterial.setMinimumOrderLevel(packingMaterial.getMinimumOrderLevel());
        existingMaterial.setLeadTimes(packingMaterial.getLeadTimes());
        existingMaterial.setDrawingNo(packingMaterial.getDrawingNo());

        return packingMaterialRepository.save(existingMaterial);
    }

    // Delete PackingMaterial by ID
    public void deletePackingMaterial(Long id) {
        logger.info("Deleting packing material with ID: {}", id);
        packingMaterialRepository.deleteById(id); // Delete without checking, no need for exception handling
    }

    // Search PackingMaterials with Pagination and Sorting and caching
    @Cacheable(value = "packingMaterialsSearchCache", key = "#materialNo + #materialGroup + #materialName")
    public Page<PackingMaterial> searchPackingMaterials(String materialNo, String materialGroup, String materialName, Pageable pageable) {
        logger.info("Searching packing materials with filters: materialNo={}, materialGroup={}, materialName={}", materialNo, materialGroup, materialName);
        return packingMaterialRepository.findByMaterialNoContainingOrMaterialGroupContainingOrMaterialNameContaining(
                materialNo != null ? materialNo : "",
                materialGroup != null ? materialGroup : "",
                materialName != null ? materialName : "",
                pageable);
    }

    // Import Packing Materials from an Excel File
    public void importPackingMaterials(MultipartFile file) throws IOException {
        logger.info("Importing packing materials from Excel file");

        // Create a workbook from the file input stream
        Workbook workbook = new XSSFWorkbook(file.getInputStream());
        Sheet sheet = workbook.getSheetAt(0);  // Get the first sheet

        Iterator<Row> rowIterator = sheet.iterator();
        while (rowIterator.hasNext()) {
            Row row = rowIterator.next();

            // Skip the header row if present
            if (row.getRowNum() == 0) {
                continue;
            }

            PackingMaterial packingMaterial = new PackingMaterial();

            // Read values from the Excel row and set them to the PackingMaterial object
            packingMaterial.setMaterialGroup(getCellValue(row.getCell(0)));
            packingMaterial.setMaterialNo(getCellValue(row.getCell(1)));
            packingMaterial.setMaterialName(getCellValue(row.getCell(2)));

            // Validate and set numeric values (length, breadth, height)
            double length = getNumericCellValue(row.getCell(3));
            if (length <= 0) {
                logger.warn("Invalid length value at row {}: {}", row.getRowNum(), length);
                continue;  // Skip this row or handle as needed
            }
            packingMaterial.setLength(length);

            double breadth = getNumericCellValue(row.getCell(4));
            if (breadth <= 0) {
                logger.warn("Invalid breadth value at row {}: {}", row.getRowNum(), breadth);
                continue;  // Skip this row or handle as needed
            }
            packingMaterial.setBreadth(breadth);

            double height = getNumericCellValue(row.getCell(5));
            if (height <= 0) {
                logger.warn("Invalid height value at row {}: {}", row.getRowNum(), height);
                continue;  // Skip this row or handle as needed
            }
            packingMaterial.setHeight(height);

            packingMaterial.setUnitOfMeasurement(getCellValue(row.getCell(6)));

            // Validate and set Minimum Order Level and Lead Times (they must be greater than 0)
            int minimumOrderLevel = (int) getNumericCellValue(row.getCell(7));
            if (minimumOrderLevel <= 0) {
                logger.warn("Invalid minimum order level at row {}: {}", row.getRowNum(), minimumOrderLevel);
                continue;  // Skip this row or handle as needed
            }
            packingMaterial.setMinimumOrderLevel(minimumOrderLevel);

            int leadTimes = (int) getNumericCellValue(row.getCell(8));
            if (leadTimes <= 0) {
                logger.warn("Invalid lead time at row {}: {}", row.getRowNum(), leadTimes);
                continue;  // Skip this row or handle as needed
            }
            packingMaterial.setLeadTimes(leadTimes);

            // Save the packing material entity to the database
            packingMaterialRepository.save(packingMaterial);
            logger.info("Successfully imported packing material with Material No: {}", packingMaterial.getMaterialNo());
        }

        workbook.close();
    }

    // Helper method to get the String value from a cell (for non-numeric cells)
    private String getCellValue(Cell cell) {
        if (cell == null) {
            return null;
        }

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

    // Helper method to get the numeric value from a cell (for numeric cells)
    private double getNumericCellValue(Cell cell) {
        if (cell == null) {
            return 0.0;
        }

        switch (cell.getCellType()) {
            case NUMERIC:
                return cell.getNumericCellValue();
            case STRING:
                try {
                    return Double.parseDouble(cell.getStringCellValue());
                } catch (NumberFormatException e) {
                    return 0.0;
                }
            default:
                return 0.0;
        }
    }

    // Export Packing Materials to Excel
    public void exportPackingMaterialsToExcel(List<PackingMaterial> materials, String outputFilePath) throws IOException {
        logger.info("Exporting packing materials to Excel file at: {}", outputFilePath);

        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Packing Materials");

        // Create header row
        Row headerRow = sheet.createRow(0);
        headerRow.createCell(0).setCellValue("Material Group");
        headerRow.createCell(1).setCellValue("Material No");
        headerRow.createCell(2).setCellValue("Material Name");
        headerRow.createCell(3).setCellValue("Length");
        headerRow.createCell(4).setCellValue("Breadth");
        headerRow.createCell(5).setCellValue("Height");
        headerRow.createCell(6).setCellValue("Unit of Measurement");
        headerRow.createCell(7).setCellValue("Minimum Order Level");
        headerRow.createCell(8).setCellValue("Lead Time");

        // Write data rows
        int rowNum = 1;
        for (PackingMaterial material : materials) {
            Row row = sheet.createRow(rowNum++);
            row.createCell(0).setCellValue(material.getMaterialGroup());
            row.createCell(1).setCellValue(material.getMaterialNo());
            row.createCell(2).setCellValue(material.getMaterialName());
            row.createCell(3).setCellValue(material.getLength());
            row.createCell(4).setCellValue(material.getBreadth());
            row.createCell(5).setCellValue(material.getHeight());
            row.createCell(6).setCellValue(material.getUnitOfMeasurement());
            row.createCell(7).setCellValue(material.getMinimumOrderLevel());
            row.createCell(8).setCellValue(material.getLeadTimes());
        }

        // Write to output file
        try (FileOutputStream fileOut = new FileOutputStream(outputFilePath)) {
            workbook.write(fileOut);
        }

        workbook.close();
    }
    
    public ByteArrayOutputStream generateMouldSpecsReport(String materialNo, String materialName) {
        logger.info("Generating PDF report for Mould Specs, Material No: {}, Material Name: {}", materialNo, materialName);

        // Fetch materials by search criteria
        List<PackingMaterial> materials = packingMaterialRepository
                .findByMaterialNoContainingOrMaterialNameContaining(
                        Optional.ofNullable(materialNo).orElse(""),
                        Optional.ofNullable(materialName).orElse("")
                );

        // Initialize PDF document
        Document document = new Document();
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

        try {
            PdfWriter.getInstance(document, outputStream);
            document.open();

            // Add title
            document.add(new Paragraph("Mould Specs Report"));
            document.add(new Paragraph("Generated Report for Material No: " + materialNo + " or Material Name: " + materialName));
            document.add(new Paragraph("\n"));

            // Add material details to the document
            if (materials.isEmpty()) {
                document.add(new Paragraph("No matching records found."));
            } else {
                for (PackingMaterial material : materials) {
                    document.add(new Paragraph("Material No: " + material.getMaterialNo()));
                    document.add(new Paragraph("Material Name: " + material.getMaterialName()));
                    document.add(new Paragraph("Material Group: " + material.getMaterialGroup()));
                    document.add(new Paragraph("Length: " + material.getLength()));
                    document.add(new Paragraph("Breadth: " + material.getBreadth()));
                    document.add(new Paragraph("Height: " + material.getHeight()));
                    document.add(new Paragraph("Diameter: " + Optional.ofNullable(material.getDiameter()).orElse(0.0)));
                    document.add(new Paragraph("Thickness: " + Optional.ofNullable(material.getThickness()).orElse(0.0)));
                    document.add(new Paragraph("GSM: " + Optional.ofNullable(material.getGsm()).orElse("N/A")));
                    document.add(new Paragraph("Unit of Measurement: " + material.getUnitOfMeasurement()));
                    document.add(new Paragraph("Minimum Order Level: " + material.getMinimumOrderLevel()));
                    document.add(new Paragraph("Lead Time: " + material.getLeadTimes()));
                    document.add(new Paragraph("Drawing No: " + Optional.ofNullable(material.getDrawingNo()).orElse("N/A")));
                    document.add(new Paragraph("\n"));
                }
            }

        } catch (DocumentException e) {
            logger.error("Error occurred while generating PDF report: {}", e.getMessage());
        } finally {
            document.close();
        }

        return outputStream;
    }
}