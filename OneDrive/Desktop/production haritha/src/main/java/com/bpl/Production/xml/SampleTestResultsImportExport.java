package com.bpl.Production.xml;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Component;

import com.bpl.Production.entity.SampleTestResults;

@Component
public class SampleTestResultsImportExport {
	
	public List<SampleTestResults> importFromExcel(InputStream inputStream) throws Exception {
        List<SampleTestResults> results = new ArrayList<>();
        Workbook workbook = new XSSFWorkbook(inputStream);
        Sheet sheet = workbook.getSheetAt(0);

        // Skip the header row
        Iterator<Row> rowIterator = sheet.iterator();
        rowIterator.next();

        while (rowIterator.hasNext()) {
            Row row = rowIterator.next();
            SampleTestResults result = SampleTestResults.builder()
                    .id((int) row.getCell(0).getNumericCellValue()) // Assuming ID is in the first column
                    .createdBy((int) row.getCell(1).getNumericCellValue())
                    .lastModifiedBy((int) row.getCell(2).getNumericCellValue())
                    .createdDate(Timestamp.valueOf(row.getCell(3).getStringCellValue()))  // Assuming the date is in string format
                    .lastModifiedDate(row.getCell(4) != null ? Timestamp.valueOf(row.getCell(4).getStringCellValue()) : null)
                    .sampleReceivedDate(row.getCell(5).getDateCellValue())
                    .shift(row.getCell(6).getStringCellValue())
                    .labSampleNo(row.getCell(7).getStringCellValue())
                    .description(row.getCell(8).getStringCellValue())
                    .receivedFrom(row.getCell(9).getStringCellValue())
                    .pupose(row.getCell(10).getStringCellValue())
                    .freeIron(row.getCell(11).getStringCellValue())
                    .moisturePercentage(row.getCell(12).getNumericCellValue())
                    .lessthanTwelveMM(row.getCell(13).getNumericCellValue())
                    .lessthanTenMM(row.getCell(14).getNumericCellValue())
                    .lessthanSixThirtyMM(row.getCell(15).getNumericCellValue())
                    .status(getIntegerCellValue(row.getCell(16)))
                    .remarks(row.getCell(17).getStringCellValue())
                    .build();

            results.add(result);
        }

        workbook.close();
        return results;
    }
	
	private Integer getIntegerCellValue(Cell cell) {
		// TODO Auto-generated method stub
		return null;
	}

	public byte[] exportToExcel(List<SampleTestResults> results) throws IOException {
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("SampleTestResults");

        // Create header row
        String[] columns = { 
            "ID", "Created By", "Last Modified By", "Created Date", "Last Modified Date", 
            "Sample Received Date", "Shift", "Lab Sample No", "Description", "Received From", 
            "Purpose", "Free Iron", "Moisture Percentage", "Less Than Twelve MM", "Less Than Ten MM",
            "Less Than Six Thirty MM", "Status", "Remarks" // Add all required columns
        };
        
        Row headerRow = sheet.createRow(0);
        for (int i = 0; i < columns.length; i++) {
            headerRow.createCell(i).setCellValue(columns[i]);
        }

        // Populate data rows
        int rowNum = 1;
        for (SampleTestResults result : results) {
            Row row = sheet.createRow(rowNum++);
            row.createCell(0).setCellValue(result.getId());
            row.createCell(1).setCellValue(result.getCreatedBy());
            row.createCell(2).setCellValue(result.getLastModifiedBy());
            row.createCell(3).setCellValue(result.getCreatedDate().toString());
            row.createCell(4).setCellValue(result.getLastModifiedDate() != null ? result.getLastModifiedDate().toString() : "");
            row.createCell(5).setCellValue(result.getSampleReceivedDate().toString());
            row.createCell(6).setCellValue(result.getShift());
            row.createCell(7).setCellValue(result.getLabSampleNo());
            row.createCell(8).setCellValue(result.getDescription());
            row.createCell(9).setCellValue(result.getReceivedFrom());
            row.createCell(10).setCellValue(result.getPupose());
            row.createCell(11).setCellValue(result.getFreeIron());
            row.createCell(12).setCellValue(result.getMoisturePercentage());
            row.createCell(13).setCellValue(result.getLessthanTwelveMM());
            row.createCell(14).setCellValue(result.getLessthanTenMM());
            row.createCell(15).setCellValue(result.getLessthanSixThirtyMM());
            row.createCell(16).setCellValue(result.getStatus());
            row.createCell(17).setCellValue(result.getRemarks());
        }

        // Write the workbook to output stream
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        workbook.write(outputStream);
        workbook.close();

        return outputStream.toByteArray();
    }
}
