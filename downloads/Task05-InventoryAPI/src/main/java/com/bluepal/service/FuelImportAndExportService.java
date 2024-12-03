package com.bluepal.service;


import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.bluepal.entity.Fuel;
import com.bluepal.repository.FuelRepo;



@Service
public class FuelImportAndExportService {
	

	@Autowired
	private FuelRepo fuelRepo;
	
	private static final Logger logger = LoggerFactory.getLogger(FuelImportAndExportService.class);

    public void importFuelsFromExcel(MultipartFile file) throws IOException {
        logger.info("Starting the import of fuels from Excel file: {}", file.getOriginalFilename());

        try (InputStream is = file.getInputStream(); Workbook workbook = new XSSFWorkbook(is)) {
            Sheet sheet = workbook.getSheetAt(0);
            if (sheet == null) {
                throw new IOException("The Excel file does not contain a valid sheet.");
            }

            int importedCount = 0;
            for (int i = 1; i <= sheet.getLastRowNum(); i++) { // Skip header row
                Row row = sheet.getRow(i);
                if (row == null) continue; // Skip empty rows

                Fuel fuel = new Fuel();

                try {
                    // Map each column to the Fuel entity
                    fuel.setMaterialNo(null); // Auto-generated
                    fuel.setMaterialGroup(getStringCellValue(row.getCell(1)));
                    fuel.setMaterialSubGroup(getStringCellValue(row.getCell(2)));
                    fuel.setMaterialName(getStringCellValue(row.getCell(3)));
                    fuel.setCalrificValue(getStringCellValue(row.getCell(4)));
                    fuel.setOtherDesc(getStringCellValue(row.getCell(5)));
                    fuel.setUnitOfMeasurement(getStringCellValue(row.getCell(6)));
                    fuel.setMinimumOrderLevel(getLongCellValue(row.getCell(7)));
                    fuel.setLeadTime(getLongCellValue(row.getCell(8)));

                    // Save to repository
                    fuelRepo.save(fuel);
                    importedCount++;
                } catch (Exception e) {
                    logger.warn("Skipping row {} due to invalid data: {}", i, e.getMessage());
                }
            }
            logger.info("Successfully imported {} fuel entries from Excel.", importedCount);
        } catch (Exception e) {
            logger.error("Error occurred while importing fuels from Excel file: {}", file.getOriginalFilename(), e);
            throw new IOException("Failed to process the Excel file: " + e.getMessage(), e);
        }
    }

    // Helper method to retrieve cell value as String
    private String getStringCellValue(Cell cell) {
        if (cell == null) return null;
        if (cell.getCellType() == CellType.STRING) {
            return cell.getStringCellValue().trim();
        } else if (cell.getCellType() == CellType.NUMERIC) {
            return String.valueOf((long) cell.getNumericCellValue());
        }
        return null;
    }

    // Helper method to retrieve cell value as Long
    private Long getLongCellValue(Cell cell) {
        if (cell == null) return null;
        if (cell.getCellType() == CellType.NUMERIC) {
            return (long) cell.getNumericCellValue();
        } else if (cell.getCellType() == CellType.STRING) {
            try {
                return Long.parseLong(cell.getStringCellValue().trim());
            } catch (NumberFormatException e) {
                throw new RuntimeException("Invalid numeric value: " + cell.getStringCellValue());
            }
        }
        return null;
    }
   // Export Fuels to Excel
   public ByteArrayInputStream exportFuelsToExcel() throws IOException {
	List<Fuel> fuels = fuelRepo.findAll();
	try (Workbook workbook = new XSSFWorkbook(); ByteArrayOutputStream out = new ByteArrayOutputStream()) {
		Sheet sheet = workbook.createSheet("Fuels");

		// Create header row
		Row header = sheet.createRow(0);
		header.createCell(0).setCellValue("Material No");
		header.createCell(1).setCellValue("Material Group");
		header.createCell(2).setCellValue("Material Sub Group");
		header.createCell(3).setCellValue("Material Name");
		header.createCell(4).setCellValue("Calrific Value");
		header.createCell(5).setCellValue("Other Desc");
		header.createCell(6).setCellValue("Unit of Measurement");
		header.createCell(7).setCellValue("Minimum Order Level");
		header.createCell(8).setCellValue("Lead Time");

		// Add fuel data rows
		int rowNum = 1;
		for (Fuel fuel : fuels) {
			Row row = sheet.createRow(rowNum++);
			row.createCell(0).setCellValue(fuel.getMaterialNo());
			row.createCell(1).setCellValue(fuel.getMaterialGroup());
			row.createCell(2).setCellValue(fuel.getMaterialSubGroup());
			row.createCell(3).setCellValue(fuel.getMaterialName());
			row.createCell(4).setCellValue(fuel.getCalrificValue());
			row.createCell(5).setCellValue(fuel.getOtherDesc());
			row.createCell(6).setCellValue(fuel.getUnitOfMeasurement());
			row.createCell(7).setCellValue(fuel.getMinimumOrderLevel());
			row.createCell(8).setCellValue(fuel.getLeadTime());
		}

		workbook.write(out);
		return new ByteArrayInputStream(out.toByteArray());
	}
}
}

