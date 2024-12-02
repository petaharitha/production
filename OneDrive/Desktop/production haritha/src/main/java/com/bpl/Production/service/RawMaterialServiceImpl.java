package com.bpl.Production.service;



import java.awt.Color;
import java.util.List;

import org.apache.poi.hpsf.DocumentSummaryInformation;
import org.apache.poi.hpsf.SummaryInformation;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.bpl.Production.entity.RawMaterial;
import com.bpl.Production.repository.RawMaterialRepository;
import com.bpl.request.RawMaterialRequest;
import com.lowagie.text.Document;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class RawMaterialServiceImpl implements RawMaterialService {

    @Autowired
    private RawMaterialRepository rawMaterialRepository;

    @Override
    public RawMaterial createRawMaterial(RawMaterialRequest rawMaterialRequest) {
        log.info("Creating new RawMaterial with details: {}", rawMaterialRequest);

        // Map fields from the request to the entity
        RawMaterial rawMaterial = new RawMaterial();
        rawMaterial.setRawMaterialNo(rawMaterialRequest.getRawMaterialNo());
        rawMaterial.setRawMaterialDescription(rawMaterialRequest.getRawMaterialDescription());
        rawMaterial.setHsCode(rawMaterialRequest.getHsCode());
        rawMaterial.setHscDescription(rawMaterialRequest.getHscDescription());
        rawMaterial.setSmNo(rawMaterialRequest.getSmNo());
        rawMaterial.setSmDescription(rawMaterialRequest.getSmDescription());
        rawMaterial.setUnitOfMeasure(rawMaterialRequest.getUnitOfMeasure());

        // Save and return the created raw material
        RawMaterial saveMaterial = rawMaterialRepository.save(rawMaterial);
        log.info("RawMaterial with ID: {} created successfully", saveMaterial.getId());

        rawMaterial.setCreatedBy(saveMaterial.getId());
        RawMaterial finalRawMaterial = rawMaterialRepository.save(rawMaterial);
        log.info("Final saved RawMaterial: {}", finalRawMaterial);

        return finalRawMaterial;
    }

    @Override
    public RawMaterial updateRawMaterial(RawMaterialRequest updateRequest, Integer id) throws Exception {
        log.info("Updating RawMaterial with ID: {} and details: {}", id, updateRequest);

        // Fetch the existing raw material by ID
        RawMaterial existingRawMaterial = rawMaterialRepository.findById(id)
                .orElseThrow(() -> {
                    log.error("Raw Material not found with ID: {}", id);
                    return new Exception("Raw Material not found with ID: " + id);
                });

        log.info("Existing RawMaterial details: {}", existingRawMaterial);

        // Update fields only if they are provided in the request
        if (updateRequest.getRawMaterialNo() != null) {
            existingRawMaterial.setRawMaterialNo(updateRequest.getRawMaterialNo());
        }
        if (updateRequest.getRawMaterialDescription() != null) {
            existingRawMaterial.setRawMaterialDescription(updateRequest.getRawMaterialDescription());
        }
        if (updateRequest.getHsCode() != null) {
            existingRawMaterial.setHsCode(updateRequest.getHsCode());
        }
        if (updateRequest.getHscDescription() != null) {
            existingRawMaterial.setHscDescription(updateRequest.getHscDescription());
        }
        if (updateRequest.getSmNo() != null) {
            existingRawMaterial.setSmNo(updateRequest.getSmNo());
        }
        if (updateRequest.getSmDescription() != null) {
            existingRawMaterial.setSmDescription(updateRequest.getSmDescription());
        }
        if (updateRequest.getUnitOfMeasure() != null) {
            existingRawMaterial.setUnitOfMeasure(updateRequest.getUnitOfMeasure());
        }

        existingRawMaterial.setUpdatedBy(existingRawMaterial.getId());
        RawMaterial updatedRawMaterial = rawMaterialRepository.save(existingRawMaterial);

        log.info("Raw Material with ID: {} updated successfully", id);
        return updatedRawMaterial;
    }

    @Override
    public void deleteRawMaterial(Integer id) {
        log.info("Deleting RawMaterial with ID: {}", id);
        RawMaterial rawMaterialById = getRawMaterialById(id);
        rawMaterialRepository.deleteById(rawMaterialById.getId());
        log.info("RawMaterial with ID: {} deleted successfully", id);
    }

    @Override
    public RawMaterial getRawMaterialById(Integer id) {
        log.info("Fetching RawMaterial by ID: {}", id);
        RawMaterial rawMaterial = rawMaterialRepository.findById(id)
                .orElseThrow(() -> {
                    log.error("Raw Material not found with ID: {}", id);
                    return new RuntimeException("Raw Material not found with id: " + id);
                });
        log.info("Fetched RawMaterial: {}", rawMaterial);
        return rawMaterial;
    }

    @Override
    public List<RawMaterial> getAllRawMaterials() {
        log.info("Fetching all RawMaterials");
        List<RawMaterial> rawMaterials = rawMaterialRepository.findAll();
        log.info("Fetched {} RawMaterials", rawMaterials.size());
        return rawMaterials;
    }

    @Override
    public Page<RawMaterial> getAllRawMaterials(Pageable pageable) {
        log.info("Fetching paginated RawMaterials with pageable: {}", pageable);
        Page<RawMaterial> rawMaterialsPage = rawMaterialRepository.findAll(pageable);
        log.info("Fetched {} RawMaterials on the current page", rawMaterialsPage.getNumberOfElements());
        return rawMaterialsPage;
    }
    
    @Override
    public void generateExcel(HttpServletResponse response) throws Exception {
        log.info("Starting Excel generation");
        List<RawMaterial> entities = rawMaterialRepository.findAll();  
        log.info("Fetched {} raw materials from the database", entities.size());

        HSSFWorkbook workbook = new HSSFWorkbook();
        workbook.createInformationProperties();

        DocumentSummaryInformation dsi = workbook.getDocumentSummaryInformation();
        dsi.setCompany("BluePal Solution");
        log.debug("Document Summary Information set");

        SummaryInformation si = workbook.getSummaryInformation();
        si.setAuthor("BluePal People");
        si.setTitle("Raw Material Reports");
        log.debug("Summary Information set");

        HSSFSheet sheet = workbook.createSheet("Raw Material Data");
        log.debug("Created sheet: Raw Material Data");

        HSSFRow headerRow = sheet.createRow(0);
        headerRow.createCell(0).setCellValue("Raw Material No");
        headerRow.createCell(1).setCellValue("Raw Material Description");
        headerRow.createCell(2).setCellValue("HS Code");
        headerRow.createCell(3).setCellValue("HS Code Description");
        headerRow.createCell(4).setCellValue("SM No");
        headerRow.createCell(5).setCellValue("SM Description");
        headerRow.createCell(6).setCellValue("Unit of Measure");
        log.debug("Header row created");

        int i = 1;
        for (RawMaterial entity : entities) {
            HSSFRow dataRow = sheet.createRow(i);

            dataRow.createCell(0).setCellValue(entity.getRawMaterialNo() != null ?String.valueOf( entity.getRawMaterialNo()) : "");
            dataRow.createCell(1).setCellValue(entity.getRawMaterialDescription() != null ? entity.getRawMaterialDescription() : "");
            dataRow.createCell(2).setCellValue(entity.getHsCode() != null ? entity.getHsCode() : "");
            dataRow.createCell(3).setCellValue(entity.getHscDescription() != null ? entity.getHscDescription() : "");
            dataRow.createCell(4).setCellValue(entity.getSmNo() != null ?String.valueOf( entity.getSmNo()) : "");
            dataRow.createCell(5).setCellValue(entity.getSmDescription() != null ? entity.getSmDescription() : "");
            dataRow.createCell(6).setCellValue(entity.getUnitOfMeasure() != null ? entity.getUnitOfMeasure().toString() : "N/A");

            i++;
        }

        log.info("Populated Excel sheet with raw material data");

        response.setContentType("application/vnd.ms-excel");
        response.setHeader("Content-Disposition", "attachment;filename=Raw_Material_Reports.xls");

        try (ServletOutputStream outputStream = response.getOutputStream()) {
            workbook.write(outputStream);
            log.info("Excel file successfully written to the response");
        }

        workbook.close();
        log.info("Excel generation completed");
    }

    @Override
    public void generatePdf(HttpServletResponse response) throws Exception {
        log.info("Starting PDF generation");
        List<RawMaterial> entities = rawMaterialRepository.findAll();  
        log.info("Fetched {} raw materials from the database", entities.size());

        Document document = new Document(PageSize.A4);
        PdfWriter.getInstance(document, response.getOutputStream());
        document.open();
        log.debug("PDF document opened");

        Font fontTitle = FontFactory.getFont(FontFactory.TIMES_ROMAN);
        fontTitle.setSize(20);
        Paragraph paragraph1 = new Paragraph("Raw Material Reports", fontTitle);
        paragraph1.setAlignment(Paragraph.ALIGN_CENTER);
        document.add(paragraph1);
        log.debug("Added title to the PDF");

        PdfPTable table = new PdfPTable(7);  // 7 columns for the required fields
        table.setWidthPercentage(100f);
        table.setWidths(new int[]{3, 4, 3, 4, 3, 4, 3});
        table.setSpacingBefore(5);

        PdfPCell cell = new PdfPCell();
        cell.setBackgroundColor(Color.BLUE);
        cell.setPadding(5);
        Font font = FontFactory.getFont(FontFactory.TIMES_ROMAN);
        font.setColor(Color.WHITE);

        cell.setPhrase(new Phrase("Raw Material No", font));
        table.addCell(cell);
        cell.setPhrase(new Phrase("Raw Material Description", font));
        table.addCell(cell);
        cell.setPhrase(new Phrase("HS Code", font));
        table.addCell(cell);
        cell.setPhrase(new Phrase("HS Code Description", font));
        table.addCell(cell);
        cell.setPhrase(new Phrase("SM No", font));
        table.addCell(cell);
        cell.setPhrase(new Phrase("SM Description", font));
        table.addCell(cell);
        cell.setPhrase(new Phrase("Unit of Measure", font));
        table.addCell(cell);
        log.debug("Header row added to the PDF table");

        for (RawMaterial entity : entities) {
            table.addCell(entity.getRawMaterialNo() != null ? String.valueOf(entity.getRawMaterialNo()) : "");
            table.addCell(entity.getRawMaterialDescription() != null ? entity.getRawMaterialDescription() : "");
            table.addCell(entity.getHsCode() != null ? entity.getHsCode() : "");
            table.addCell(entity.getHscDescription() != null ? entity.getHscDescription() : "");
            table.addCell(entity.getSmNo() != null ? String.valueOf(entity.getSmNo()) : "");
            table.addCell(entity.getSmDescription() != null ? entity.getSmDescription() : "");
            table.addCell(entity.getUnitOfMeasure() != null ? String.valueOf(entity.getUnitOfMeasure()) : "N/A");
        }

        log.info("Populated PDF table with raw material data");

        document.add(table);
        document.close();
        log.info("PDF generation completed");
    }

}
