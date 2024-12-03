package com.bluepal.service;



import java.io.ByteArrayOutputStream;
import java.math.BigDecimal;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.bluepal.entity.RFG;
import com.bluepal.repository.RFGRepository;



@Service
public class RFGImportExportService {

    @Autowired
    private RFGRepository rfgRepository;

    public void importRFG(MultipartFile file) throws Exception {
        if (file.isEmpty()) {
            throw new Exception("File is empty.");
        }

        try (Workbook workbook = new XSSFWorkbook(file.getInputStream())) {
            Sheet sheet = workbook.getSheetAt(0);

            for (int i = 1; i <= sheet.getLastRowNum(); i++) {
                Row row = sheet.getRow(i);

                RFG rfg = new RFG();
                if (row != null) {
                    rfg.setProductNo((int) row.getCell(0).getNumericCellValue());
                    rfg.setProductGroup(row.getCell(1).getStringCellValue());
                    rfg.setProductSubGroup(row.getCell(2).getStringCellValue());
                    rfg.setProductSubGroupII(row.getCell(3).getStringCellValue());
                    rfg.setProductName(row.getCell(4).getStringCellValue());
                    rfg.setShapeNo(row.getCell(5).getStringCellValue());
                    rfg.setDrawingNo(row.getCell(6).getStringCellValue());
                    rfg.setBulkDensity(BigDecimal.valueOf(row.getCell(7).getNumericCellValue()));
                    rfg.setVolume(BigDecimal.valueOf(row.getCell(8).getNumericCellValue()));
                    rfg.setUnitWt(BigDecimal.valueOf(row.getCell(9).getNumericCellValue()));
                    rfg.setUnitOfMeasurement(row.getCell(10).getStringCellValue());
                    rfg.setMinimumOrderLevel((int) row.getCell(11).getNumericCellValue());
                    rfg.setLeadTime((int) row.getCell(12).getNumericCellValue());
                    rfg.setAl2o3Min(BigDecimal.valueOf(row.getCell(13).getNumericCellValue()));
                    rfg.setAl2o3Max(BigDecimal.valueOf(row.getCell(14).getNumericCellValue()));
                    rfg.setAl2o3Typical(BigDecimal.valueOf(row.getCell(15).getNumericCellValue()));
                    rfg.setSio2Min(BigDecimal.valueOf(row.getCell(16).getNumericCellValue()));
                    rfg.setSio2Max(BigDecimal.valueOf(row.getCell(17).getNumericCellValue()));
                    rfg.setSio2Typical(BigDecimal.valueOf(row.getCell(18).getNumericCellValue()));
                    rfg.setFe2o3Min(BigDecimal.valueOf(row.getCell(19).getNumericCellValue()));
                    rfg.setFe2o3Max(BigDecimal.valueOf(row.getCell(20).getNumericCellValue()));
                    rfg.setFe2o3Typical(BigDecimal.valueOf(row.getCell(21).getNumericCellValue()));
                    rfg.setChemical4Min(BigDecimal.valueOf(row.getCell(22).getNumericCellValue()));
                    rfg.setChemical4Max(BigDecimal.valueOf(row.getCell(23).getNumericCellValue()));
                    rfg.setChemical4Typical(BigDecimal.valueOf(row.getCell(24).getNumericCellValue()));
                    rfg.setChemical5Min(BigDecimal.valueOf(row.getCell(25).getNumericCellValue()));
                    rfg.setChemical5Max(BigDecimal.valueOf(row.getCell(26).getNumericCellValue()));
                    rfg.setChemical5Typical(BigDecimal.valueOf(row.getCell(27).getNumericCellValue()));
                    rfg.setChemical6Min(BigDecimal.valueOf(row.getCell(28).getNumericCellValue()));
                    rfg.setChemical6Max(BigDecimal.valueOf(row.getCell(29).getNumericCellValue()));
                    rfg.setChemical6Typical(BigDecimal.valueOf(row.getCell(30).getNumericCellValue()));
                    rfg.setChemical7Min(BigDecimal.valueOf(row.getCell(31).getNumericCellValue()));
                    rfg.setChemical7Max(BigDecimal.valueOf(row.getCell(32).getNumericCellValue()));
                    rfg.setChemical7Typical(BigDecimal.valueOf(row.getCell(33).getNumericCellValue()));
                    rfg.setChemical8Min(BigDecimal.valueOf(row.getCell(34).getNumericCellValue()));
                    rfg.setChemical8Max(BigDecimal.valueOf(row.getCell(35).getNumericCellValue()));
                    rfg.setChemical8Typical(BigDecimal.valueOf(row.getCell(36).getNumericCellValue()));
                    rfg.setBulkDensityMin(BigDecimal.valueOf(row.getCell(37).getNumericCellValue()));
                    rfg.setBulkDensityMax(BigDecimal.valueOf(row.getCell(38).getNumericCellValue()));
                    rfg.setBulkDensityTypical(BigDecimal.valueOf(row.getCell(39).getNumericCellValue()));
                    rfg.setApparentPorosityMin(BigDecimal.valueOf(row.getCell(40).getNumericCellValue()));
                    rfg.setApparentPorosityMax(BigDecimal.valueOf(row.getCell(41).getNumericCellValue()));
                    rfg.setApparentPorosityTypical(BigDecimal.valueOf(row.getCell(42).getNumericCellValue()));
                    rfg.setPhysical4Min(BigDecimal.valueOf(row.getCell(43).getNumericCellValue()));
                    rfg.setPhysical4Max(BigDecimal.valueOf(row.getCell(44).getNumericCellValue()));
                    rfg.setPhysical4Typical(BigDecimal.valueOf(row.getCell(45).getNumericCellValue()));
                    rfg.setPhysical5Min(BigDecimal.valueOf(row.getCell(46).getNumericCellValue()));
                    rfg.setPhysical5Max(BigDecimal.valueOf(row.getCell(47).getNumericCellValue()));
                    rfg.setPhysical5Typical(BigDecimal.valueOf(row.getCell(48).getNumericCellValue()));
                    rfg.setPhysical6Min(BigDecimal.valueOf(row.getCell(49).getNumericCellValue()));
                    rfg.setPhysical6Max(BigDecimal.valueOf(row.getCell(50).getNumericCellValue()));
                    rfg.setPhysical6Typical(BigDecimal.valueOf(row.getCell(51).getNumericCellValue()));
                    rfg.setPhysical7Min(BigDecimal.valueOf(row.getCell(52).getNumericCellValue()));
                    rfg.setPhysical7Max(BigDecimal.valueOf(row.getCell(53).getNumericCellValue()));
                    rfg.setPhysical7Typical(BigDecimal.valueOf(row.getCell(54).getNumericCellValue()));
                    rfg.setPhysical8Min(BigDecimal.valueOf(row.getCell(55).getNumericCellValue()));
                    rfg.setPhysical8Max(BigDecimal.valueOf(row.getCell(56).getNumericCellValue()));
                    rfg.setPhysical8Typical(BigDecimal.valueOf(row.getCell(57).getNumericCellValue()));
                   

                    rfgRepository.save(rfg);
                }
            }
        }
    }

    public byte[] exportRFGToExcel() {
        List<RFG> rfgs = rfgRepository.findAll();
        try (Workbook workbook = new XSSFWorkbook(); ByteArrayOutputStream outputStream = new ByteArrayOutputStream()) {
            Sheet sheet = workbook.createSheet("RFG");

            // Create header row
            Row headerRow = sheet.createRow(0);
            String[] headers = {
                "Product No", "Product Group", "Product Sub Group", "Product Sub Group II", "Product Name", "Shape No", 
                "Drawing No", "Bulk Density", "Volume", "Unit Weight", "Unit Of Measurement", "Minimum Order Level", 
                "Lead Time", "Al2O3 Min", "Al2O3 Max", "Al2O3 Typical", "SiO2 Min", "SiO2 Max", "SiO2 Typical", 
                "Fe2O3 Min", "Fe2O3 Max", "Fe2O3 Typical", "Chemical 4 Min", "Chemical 4 Max", "Chemical 4 Typical", 
                "Chemical 5 Min", "Chemical 5 Max", "Chemical 5 Typical", "Chemical 6 Min", "Chemical 6 Max", 
                "Chemical 6 Typical", "Chemical 7 Min", "Chemical 7 Max", "Chemical 7 Typical", "Chemical 8 Min", 
                "Chemical 8 Max", "Chemical 8 Typical", "Bulk Density Min", "Bulk Density Max", "Bulk Density Typical", 
                "Apparent Porosity Min", "Apparent Porosity Max", "Apparent Porosity Typical", "Physical 4 Min", 
                "Physical 4 Max", "Physical 4 Typical", "Physical 5 Min", "Physical 5 Max", "Physical 5 Typical", 
                "Physical 6 Min", "Physical 6 Max", "Physical 6 Typical", "Physical 7 Min", "Physical 7 Max", 
                "Physical 7 Typical", "Physical 8 Min", "Physical 8 Max", "Physical 8 Typical", "Created At", 
                "Updated At", "Created By", "Updated By"
            };

            for (int i = 0; i < headers.length; i++) {
                Cell cell = headerRow.createCell(i);
                cell.setCellValue(headers[i]);
            }

            // Fill data rows
            int rowNum = 1;
            for (RFG rfg : rfgs) {
                Row row = sheet.createRow(rowNum++);
                row.createCell(0).setCellValue(rfg.getProductNo());
                row.createCell(1).setCellValue(rfg.getProductGroup());
                row.createCell(2).setCellValue(rfg.getProductSubGroup());
                row.createCell(3).setCellValue(rfg.getProductSubGroupII());
                row.createCell(4).setCellValue(rfg.getProductName());
                row.createCell(5).setCellValue(rfg.getShapeNo());
                row.createCell(6).setCellValue(rfg.getDrawingNo());
                row.createCell(7).setCellValue(rfg.getBulkDensity().toString());
                row.createCell(8).setCellValue(rfg.getVolume().toString());
                row.createCell(9).setCellValue(rfg.getUnitWt().toString());
                row.createCell(10).setCellValue(rfg.getUnitOfMeasurement());
                row.createCell(11).setCellValue(rfg.getMinimumOrderLevel());
                row.createCell(12).setCellValue(rfg.getLeadTime());
                row.createCell(13).setCellValue(rfg.getAl2o3Min().toString());
                row.createCell(14).setCellValue(rfg.getAl2o3Max().toString());
                row.createCell(15).setCellValue(rfg.getAl2o3Typical().toString());
                row.createCell(16).setCellValue(rfg.getSio2Min().toString());
                row.createCell(17).setCellValue(rfg.getSio2Max().toString());
                row.createCell(18).setCellValue(rfg.getSio2Typical().toString());
                row.createCell(19).setCellValue(rfg.getFe2o3Min().toString());
                row.createCell(20).setCellValue(rfg.getFe2o3Max().toString());
                row.createCell(21).setCellValue(rfg.getFe2o3Typical().toString());
                row.createCell(22).setCellValue(rfg.getChemical4Min().toString());
                row.createCell(23).setCellValue(rfg.getChemical4Max().toString());
                row.createCell(24).setCellValue(rfg.getChemical4Typical().toString());
                row.createCell(25).setCellValue(rfg.getChemical5Min().toString());
                row.createCell(26).setCellValue(rfg.getChemical5Max().toString());
                row.createCell(27).setCellValue(rfg.getChemical5Typical().toString());
                row.createCell(28).setCellValue(rfg.getChemical6Min().toString());
                row.createCell(29).setCellValue(rfg.getChemical6Max().toString());
                row.createCell(30).setCellValue(rfg.getChemical6Typical().toString());
                row.createCell(31).setCellValue(rfg.getChemical7Min().toString());
                row.createCell(32).setCellValue(rfg.getChemical7Max().toString());
                row.createCell(33).setCellValue(rfg.getChemical7Typical().toString());
                row.createCell(34).setCellValue(rfg.getChemical8Min().toString());
                row.createCell(35).setCellValue(rfg.getChemical8Max().toString());
                row.createCell(36).setCellValue(rfg.getChemical8Typical().toString());
                row.createCell(37).setCellValue(rfg.getBulkDensityMin().toString());
                row.createCell(38).setCellValue(rfg.getBulkDensityMax().toString());
                row.createCell(39).setCellValue(rfg.getBulkDensityTypical().toString());
                row.createCell(40).setCellValue(rfg.getApparentPorosityMin().toString());
                row.createCell(41).setCellValue(rfg.getApparentPorosityMax().toString());
                row.createCell(42).setCellValue(rfg.getApparentPorosityTypical().toString());
                row.createCell(43).setCellValue(rfg.getPhysical4Min().toString());
                row.createCell(44).setCellValue(rfg.getPhysical4Max().toString());
                row.createCell(45).setCellValue(rfg.getPhysical4Typical().toString());
                row.createCell(46).setCellValue(rfg.getPhysical5Min().toString());
                row.createCell(47).setCellValue(rfg.getPhysical5Max().toString());
                row.createCell(48).setCellValue(rfg.getPhysical5Typical().toString());
                row.createCell(49).setCellValue(rfg.getPhysical6Min().toString());
                row.createCell(50).setCellValue(rfg.getPhysical6Max().toString());
                row.createCell(51).setCellValue(rfg.getPhysical6Typical().toString());
                row.createCell(52).setCellValue(rfg.getPhysical7Min().toString());
                row.createCell(53).setCellValue(rfg.getPhysical7Max().toString());
                row.createCell(54).setCellValue(rfg.getPhysical7Typical().toString());
                row.createCell(55).setCellValue(rfg.getPhysical8Min().toString());
                row.createCell(56).setCellValue(rfg.getPhysical8Max().toString());
                row.createCell(57).setCellValue(rfg.getPhysical8Typical().toString());
                
            }

            // Write the output to a byte array output stream
            workbook.write(outputStream);
            return outputStream.toByteArray(); // Return as byte array for file download
        } catch (Exception e) {
            e.printStackTrace(); // Log the error
            throw new RuntimeException("Error exporting RFG to Excel: " + e.getMessage(), e);
        }
    }
}
