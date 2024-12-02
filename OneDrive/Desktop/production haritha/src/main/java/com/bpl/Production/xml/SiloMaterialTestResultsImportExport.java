package com.bpl.Production.xml;

import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.Date;
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
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.bpl.Production.entity.SiloMaterialTestResult;
import com.bpl.Production.repository.SiloMaterialTestResultRepository;

@Component
public class SiloMaterialTestResultsImportExport {
	
	@Autowired
	private SiloMaterialTestResultRepository siloMaterialTestResultRepository;
	
	private Logger logger = LoggerFactory.getLogger(SiloMaterialTestResultsImportExport.class);
	
	public void importSiloMaterialTestResults(MultipartFile file) throws IOException {
	    logger.info("Importing silo material test results from file: {}", file.getOriginalFilename());

	    if (!file.getOriginalFilename().endsWith(".xlsx")) {
	        throw new IllegalArgumentException("Invalid file type. Please upload an Excel file.");
	    }

	    try (Workbook workbook = new XSSFWorkbook(file.getInputStream())) {
	        Sheet sheet = workbook.getSheetAt(0); // Get the first sheet

	        for (Row row : sheet) {
	            if (row.getRowNum() == 0) continue; // Skip header row

	            SiloMaterialTestResult result = new SiloMaterialTestResult();

	            try {
	                // Assuming row.getCell(i) corresponds to each field in the SiloMaterialTestResult entity
	                result.setCreatedBy(getIntCellValue(row.getCell(0)));
	                result.setLastModifiedBy(getIntCellValue(row.getCell(1)));
	                result.setCreatedDate(getTimestampCellValue(row.getCell(2)));
	                result.setLastModifiedDate(getTimestampCellValue(row.getCell(3)));
	                result.setDate(getDateCellValue(row.getCell(4)));
	                result.setShift(getStringCellValue(row.getCell(5)));
	                result.setLabSampleNo(getStringCellValue(row.getCell(6)));
	                result.setMaterialNo(getIntCellValue(row.getCell(7)));
	                result.setSmNo(getStringCellValue(row.getCell(8)));
	                result.setSmDescription(getStringCellValue(row.getCell(9)));
	                result.setSiloNo(getStringCellValue(row.getCell(10)));
	                result.setFreeIron(getNumericCellValue(row.getCell(11)));
	                result.setMoisturePercentage(getNumericCellValue(row.getCell(12)));
	                result.setLessthanTwelveMM(getNumericCellValue(row.getCell(13)));
	                result.setLessthanSixThirtyMM(getNumericCellValue(row.getCell(14)));
	                result.setLessthanFiveMM(getNumericCellValue(row.getCell(15)));
	                result.setLessthanFourMM(getNumericCellValue(row.getCell(16)));
	                result.setLessthanThreeFifteenMM(getNumericCellValue(row.getCell(17)));
	                result.setLessthanTwoMM(getNumericCellValue(row.getCell(18)));
	                result.setLessthanOneMM(getNumericCellValue(row.getCell(19)));
	                result.setLessthanZeroFiftyMM(getNumericCellValue(row.getCell(20)));
	                result.setLessthanZeroTwentyMM(getNumericCellValue(row.getCell(21)));
	                result.setLessthanZeroOnehundredsixMM(getNumericCellValue(row.getCell(22)));
	                result.setLessthanZeroNineMM(getNumericCellValue(row.getCell(23)));
	                result.setLessthanZeroSixtythreeMM(getNumericCellValue(row.getCell(24)));
	                result.setMolasisGmSlashCC(getNumericCellValue(row.getCell(25)));
	                result.setStatus(getIntCellValue(row.getCell(26)));
	                result.setApprovalBy(getIntCellValue(row.getCell(27)));
	                result.setRemarks(getStringCellValue(row.getCell(28)));

	                siloMaterialTestResultRepository.save(result); // Save valid result
	            } catch (Exception e) {
	                logger.error("Error processing row {}: {}", row.getRowNum(), e.getMessage());
	            }
	        }
	    } catch (Exception e) {
	        logger.error("Error reading the Excel file: {}", e.getMessage(), e);
	        throw new IOException("Error reading the Excel file: " + e.getMessage(), e);
	    }
	}

	// Helper methods to handle cell types and null checks

	private Integer getIntCellValue(Cell cell) {
	    if (cell != null && cell.getCellType() == CellType.NUMERIC) {
	        return (int) cell.getNumericCellValue();
	    }
	    return null; // or 0 based on your logic
	}

	private Double getNumericCellValue(Cell cell) {
	    if (cell != null && cell.getCellType() == CellType.NUMERIC) {
	        return cell.getNumericCellValue();
	    }
	    return null; // or 0.0 based on your logic
	}

	private String getStringCellValue(Cell cell) {
	    if (cell != null && cell.getCellType() == CellType.STRING) {
	        return cell.getStringCellValue();
	    }
	    return null; // or "" based on your logic
	}

	private Timestamp getTimestampCellValue(Cell cell) {
	    if (cell != null && cell.getCellType() == CellType.NUMERIC) {
	        return new Timestamp(cell.getDateCellValue().getTime());
	    }
	    return null;
	}

	private Date getDateCellValue(Cell cell) {
	    if (cell != null && cell.getCellType() == CellType.NUMERIC) {
	        return cell.getDateCellValue();
	    }
	    return null;
	}

	
	public String exportSiloMaterialTestResults() {
	    logger.info("Exporting silo material test results to Excel.");
	    List<SiloMaterialTestResult> results = siloMaterialTestResultRepository.findAll();
	    String filePath = "SiloMaterialTestResults.xlsx";

	    try (Workbook workbook = new XSSFWorkbook(); FileOutputStream outputStream = new FileOutputStream(filePath)) {

	        Sheet sheet = workbook.createSheet("SiloMaterialTestResults");
	        int rowNum = 0;

	        // Create header row
	        Row headerRow = sheet.createRow(rowNum++);
	        headerRow.createCell(0).setCellValue("Created By");
	        headerRow.createCell(1).setCellValue("Last Modified By");
	        headerRow.createCell(2).setCellValue("Created Date");
	        headerRow.createCell(3).setCellValue("Last Modified Date");
	        headerRow.createCell(4).setCellValue("Date");
	        headerRow.createCell(5).setCellValue("Shift");
	        headerRow.createCell(6).setCellValue("Lab Sample No");
	        headerRow.createCell(7).setCellValue("Material No");
	        headerRow.createCell(8).setCellValue("SM No");
	        headerRow.createCell(9).setCellValue("SM Description");
	        headerRow.createCell(10).setCellValue("Silo No");
	        headerRow.createCell(11).setCellValue("Free Iron");
	        headerRow.createCell(12).setCellValue("Moisture Percentage");
	        headerRow.createCell(13).setCellValue("Less than 12mm");
	        headerRow.createCell(14).setCellValue("Less than 6.30mm");
	        headerRow.createCell(15).setCellValue("Less than 5mm");
	        headerRow.createCell(16).setCellValue("Less than 4mm");
	        headerRow.createCell(17).setCellValue("Less than 3.15mm");
	        headerRow.createCell(18).setCellValue("Less than 2mm");
	        headerRow.createCell(19).setCellValue("Less than 1mm");
	        headerRow.createCell(20).setCellValue("Less than 0.50mm");
	        headerRow.createCell(21).setCellValue("Less than 0.20mm");
	        headerRow.createCell(22).setCellValue("Less than 0.106mm");
	        headerRow.createCell(23).setCellValue("Less than 0.09mm");
	        headerRow.createCell(24).setCellValue("Less than 0.063mm");
	        headerRow.createCell(25).setCellValue("Molasses (gm/cc)");
	        headerRow.createCell(26).setCellValue("Status");
	        headerRow.createCell(27).setCellValue("Approval By");
	        headerRow.createCell(28).setCellValue("Remarks");

	        // Write data rows
	        for (SiloMaterialTestResult result : results) {
	            Row row = sheet.createRow(rowNum++);
	            row.createCell(0).setCellValue(result.getCreatedBy());
	            row.createCell(1).setCellValue(result.getLastModifiedBy());
	            row.createCell(2).setCellValue(result.getCreatedDate());
	            row.createCell(3).setCellValue(result.getLastModifiedDate());
	            row.createCell(4).setCellValue(result.getDate());
	            row.createCell(5).setCellValue(result.getShift());
	            row.createCell(6).setCellValue(result.getLabSampleNo());
	            row.createCell(7).setCellValue(result.getMaterialNo());
	            row.createCell(8).setCellValue(result.getSmNo());
	            row.createCell(9).setCellValue(result.getSmDescription());
	            row.createCell(10).setCellValue(result.getSiloNo());
	            row.createCell(11).setCellValue(result.getFreeIron());
	            row.createCell(12).setCellValue(result.getMoisturePercentage());
	            row.createCell(13).setCellValue(result.getLessthanTwelveMM());
	            row.createCell(14).setCellValue(result.getLessthanSixThirtyMM());
	            row.createCell(15).setCellValue(result.getLessthanFiveMM());
	            row.createCell(16).setCellValue(result.getLessthanFourMM());
	            row.createCell(17).setCellValue(result.getLessthanThreeFifteenMM());
	            row.createCell(18).setCellValue(result.getLessthanTwoMM());
	            row.createCell(19).setCellValue(result.getLessthanOneMM());
	            row.createCell(20).setCellValue(result.getLessthanZeroFiftyMM());
	            row.createCell(21).setCellValue(result.getLessthanZeroTwentyMM());
	            row.createCell(22).setCellValue(result.getLessthanZeroOnehundredsixMM());
	            row.createCell(23).setCellValue(result.getLessthanZeroNineMM());
	            row.createCell(24).setCellValue(result.getLessthanZeroSixtythreeMM());
	            row.createCell(25).setCellValue(result.getMolasisGmSlashCC());
	            row.createCell(26).setCellValue(result.getStatus());
	            row.createCell(27).setCellValue(result.getApprovalBy());
	            row.createCell(28).setCellValue(result.getRemarks());
	        }

	        // Write the workbook to the output stream
	        workbook.write(outputStream);
	    } catch (Exception e) {
	        logger.error("Error exporting silo material test results: {}", e.getMessage(), e);
	        return "Error exporting the data.";
	    }

	    return filePath; // Return the file path for download
	}


}
