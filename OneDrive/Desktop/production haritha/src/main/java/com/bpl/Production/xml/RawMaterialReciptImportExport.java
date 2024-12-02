package com.bpl.Production.xml;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.bpl.Production.entity.RawMaterialReceipt;

@Component
public class RawMaterialReciptImportExport {
	
	public List<RawMaterialReceipt> importFromExcel(MultipartFile file) throws IOException, InstantiationException, IllegalAccessException {
        List<RawMaterialReceipt> records = new ArrayList<>();
        Workbook workbook = new XSSFWorkbook(file.getInputStream());
        Sheet sheet = workbook.getSheetAt(0);
        Field[] fields = RawMaterialReceipt.class.getDeclaredFields();

        // Skip header row
        for (int i = 1; i <= sheet.getLastRowNum(); i++) {
            Row row = sheet.getRow(i);
            if (row == null) continue;

            RawMaterialReceipt instance = new RawMaterialReceipt();
            for (int j = 0; j < fields.length; j++) {
                fields[j].setAccessible(true);
                Cell cell = row.getCell(j);
                if (cell != null) {
                    setFieldValue(fields[j], instance, cell);
                }
            }
            records.add(instance);
        }
        workbook.close();
        return records;
    }

    private void setFieldValue(Field field, Object instance, Cell cell) throws IllegalAccessException {
        switch (cell.getCellType()) {
            case STRING:
                field.set(instance, cell.getStringCellValue());
                break;
            case NUMERIC:
                if (Date.class.isAssignableFrom(field.getType())) {
                    field.set(instance, cell.getDateCellValue());
                } else if (field.getType() == Double.class || field.getType() == double.class) {
                    field.set(instance, cell.getNumericCellValue());
                } else if (field.getType() == Integer.class || field.getType() == int.class) {
                    field.set(instance, (int) cell.getNumericCellValue());
                }
                break;
            case BOOLEAN:
                field.set(instance, cell.getBooleanCellValue());
                break;
            default:
                field.set(instance, null);
        }
    }
    
    public ByteArrayInputStream exportToExcel(List<RawMaterialReceipt> records) throws IOException {
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("RawMaterialReceipts");
        Field[] fields = RawMaterialReceipt.class.getDeclaredFields();

        // Create header row
        Row headerRow = sheet.createRow(0);
        for (int i = 0; i < fields.length; i++) {
            fields[i].setAccessible(true);
            Cell cell = headerRow.createCell(i);
            cell.setCellValue(fields[i].getName());
        }

        // Fill data rows
        for (int i = 0; i < records.size(); i++) {
            Row row = sheet.createRow(i + 1);
            RawMaterialReceipt record = records.get(i);
            for (int j = 0; j < fields.length; j++) {
                fields[j].setAccessible(true);
                Cell cell = row.createCell(j);
                try {
                    Object value = fields[j].get(record);
                    if (value != null) {
                        if (value instanceof String) {
                            cell.setCellValue((String) value);
                        } else if (value instanceof Number) {
                            cell.setCellValue(((Number) value).doubleValue());
                        } else if (value instanceof Date) {
                            cell.setCellValue((Date) value);
                        }
                    }
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }

        // Write to ByteArrayOutputStream
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        workbook.write(outputStream);
        workbook.close();

        return new ByteArrayInputStream(outputStream.toByteArray());
    }
}
