package com.bpl.Production.xml;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.bpl.Production.entity.Recipe;

@Component
public class RecipeImportExport {
	
	public List<Recipe> importFromExcel(MultipartFile file) throws IOException, InstantiationException, IllegalAccessException {
        List<Recipe> recipes = new ArrayList<>();
        Workbook workbook = new XSSFWorkbook(file.getInputStream());
        Sheet sheet = workbook.getSheetAt(0);

        // Retrieve the fields from the Recipe class
        Field[] fields = Recipe.class.getDeclaredFields();

        // Skip header row
        for (int i = 1; i <= sheet.getLastRowNum(); i++) {
            Row row = sheet.getRow(i);
            if (row == null) continue;

            Recipe recipe = new Recipe();
            for (int j = 0; j < fields.length; j++) {
                fields[j].setAccessible(true);
                Cell cell = row.getCell(j);
                if (cell != null) {
                    setFieldValue(fields[j], recipe, cell);
                }
            }
            recipes.add(recipe);
        }
        workbook.close();
        return recipes;
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
    
    public byte[] exportToExcel(List<Recipe> recipes) throws IOException {
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Recipe Data");

        // Create header row
        Row headerRow = sheet.createRow(0);
        String[] headers = { "ID", "Created By", "Last Modified By", "Created Date", "Last Modified Date", "HPG", "PG", "Recipe Number", "Quality", "Alt", "Status" };

        for (int i = 0; i < headers.length; i++) {
            headerRow.createCell(i).setCellValue(headers[i]);
        }

        // Populate data rows
        int rowNum = 1;
        for (Recipe recipe : recipes) {
            Row row = sheet.createRow(rowNum++);
            row.createCell(0).setCellValue(recipe.getId());
            row.createCell(1).setCellValue(recipe.getCreatedBy());
            row.createCell(2).setCellValue(recipe.getLastModifiedBy());
            row.createCell(3).setCellValue(recipe.getCreatedDate().toString());
            row.createCell(4).setCellValue(recipe.getLastModifiedDate().toString());
            row.createCell(5).setCellValue(recipe.getHpg());
            row.createCell(6).setCellValue(recipe.getPg());
            row.createCell(7).setCellValue(recipe.getRecipeNumber());
            row.createCell(8).setCellValue(recipe.getQuality());
            row.createCell(9).setCellValue(recipe.getAlt());
            row.createCell(10).setCellValue(recipe.getStatus());
        }

        // Write to byte array
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        workbook.write(byteArrayOutputStream);
        workbook.close();

        return byteArrayOutputStream.toByteArray();
    }
}
