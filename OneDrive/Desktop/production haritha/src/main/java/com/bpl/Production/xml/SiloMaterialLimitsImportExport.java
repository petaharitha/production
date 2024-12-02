package com.bpl.Production.xml;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
public class SiloMaterialLimitsImportExport {
	
	public <T> List<T> importFromExcel(MultipartFile file, Class<T> clazz) throws IOException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
        List<T> records = new ArrayList<>();

        Workbook workbook = new XSSFWorkbook(file.getInputStream());
        Sheet sheet = workbook.getSheetAt(0);
        Field[] fields = clazz.getDeclaredFields();

        // Skip header row (assuming it's row 0)
        for (int i = 1; i <= sheet.getLastRowNum(); i++) {
            Row row = sheet.getRow(i);
            if (row == null) continue;

            T instance = clazz.getDeclaredConstructor().newInstance();
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
                if (field.getType() == Double.class || field.getType() == double.class) {
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
    
    public <T> ResponseEntity<byte[]> exportToExcel(List<T> data, Class<T> clazz) throws IOException, IllegalAccessException {
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet(clazz.getSimpleName());

        // Header Row
        Row headerRow = sheet.createRow(0);
        Field[] fields = clazz.getDeclaredFields();
        for (int i = 0; i < fields.length; i++) {
            Cell cell = headerRow.createCell(i);
            cell.setCellValue(fields[i].getName());
        }

        // Data Rows
        int rowIdx = 1;
        for (T record : data) {
            Row row = sheet.createRow(rowIdx++);
            for (int i = 0; i < fields.length; i++) {
                fields[i].setAccessible(true);
                Object value = fields[i].get(record);
                if (value != null) {
                    row.createCell(i).setCellValue(value.toString());
                }
            }
        }

        // Write to ByteArrayOutputStream
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        workbook.write(out);
        workbook.close();

        // Prepare Response
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "attachment; filename=" + clazz.getSimpleName() + ".xlsx");

        return new ResponseEntity<>(out.toByteArray(), headers, HttpStatus.OK);
    }

}
