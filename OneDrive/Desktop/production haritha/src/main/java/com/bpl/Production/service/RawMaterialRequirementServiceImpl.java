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

import com.bpl.Production.entity.RawMaterialRequirement;
import com.bpl.Production.repository.RawMaterialRequirementRepository;
import com.bpl.request.RawMaterialRequirementRequest;
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
public class RawMaterialRequirementServiceImpl implements RawMaterialRequirementService {

    @Autowired
    private RawMaterialRequirementRepository rawMaterialRequirementRepository;

    @Override
    public RawMaterialRequirement createRawMaterialRequirement(RawMaterialRequirementRequest request) {
        RawMaterialRequirement requirement = new RawMaterialRequirement();
        mapRequestToEntity(request, requirement);

        RawMaterialRequirement savedRequirement = rawMaterialRequirementRepository.save(requirement);
        requirement.setCreatedBy(savedRequirement.getId());

        log.info("Created RawMaterialRequirement with ID: {}", savedRequirement.getId());
        return rawMaterialRequirementRepository.save(requirement);
    }

    @Override
    public RawMaterialRequirement updateRawMaterialRequirement(RawMaterialRequirementRequest request, Integer id) throws Exception {
        RawMaterialRequirement existingRequirement = rawMaterialRequirementRepository.findById(id)
                .orElseThrow(() -> new Exception("Raw Material Requirement not found with ID: " + id));

        mapRequestToEntity(request, existingRequirement);
                 existingRequirement.setUpdatedBy(id);
        log.info("Updated RawMaterialRequirement with ID: {}", id);
        return rawMaterialRequirementRepository.save(existingRequirement);
    }

    @Override
    public void deleteRawMaterialRequirement(Integer id) {
        RawMaterialRequirement requirement = getRawMaterialRequirementById(id);
        rawMaterialRequirementRepository.delete(requirement);
        log.info("Deleted RawMaterialRequirement with ID: {}", id);
    }

    @Override
    public RawMaterialRequirement getRawMaterialRequirementById(Integer id) {
        return rawMaterialRequirementRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Raw Material Requirement not found with ID: " + id));
    }

    @Override
    public List<RawMaterialRequirement> getAllRawMaterialRequirements() {
        return rawMaterialRequirementRepository.findAll();
    }

    @Override
    public Page<RawMaterialRequirement> getAllRawMaterialRequirements(Pageable pageable) {
        return rawMaterialRequirementRepository.findAll(pageable);
    }
    
    @Override
    public void generateExcel(HttpServletResponse response) throws Exception {
        log.info("Starting Excel generation");
        List<RawMaterialRequirement> entities = rawMaterialRequirementRepository.findAll();
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

        // Header Row
        HSSFRow headerRow = sheet.createRow(0);
        headerRow.createCell(0).setCellValue("SO No");
        headerRow.createCell(1).setCellValue("Material No");
        headerRow.createCell(2).setCellValue("PO Pos No");
        headerRow.createCell(3).setCellValue("Plant Load Year");
        headerRow.createCell(4).setCellValue("Plant Load Month");
        headerRow.createCell(5).setCellValue("Recipe Number");
        headerRow.createCell(6).setCellValue("Alt Number");
        headerRow.createCell(7).setCellValue("Raw Material No");
        headerRow.createCell(8).setCellValue("Raw Material Name");
        headerRow.createCell(9).setCellValue("Qty (Mtons)");
        headerRow.createCell(10).setCellValue("Unit of Measure");
        headerRow.createCell(11).setCellValue("Status");
        headerRow.createCell(12).setCellValue("Reason");
        headerRow.createCell(13).setCellValue("Quality");
        headerRow.createCell(14).setCellValue("Quantity");
        log.debug("Header row created");

        int i = 1;
        for (RawMaterialRequirement entity : entities) {
            HSSFRow dataRow = sheet.createRow(i);

            dataRow.createCell(0).setCellValue(entity.getSoNo() != null ? entity.getSoNo() : "");
            dataRow.createCell(1).setCellValue(entity.getMaterialNo() != null ? String.valueOf(entity.getMaterialNo()) : "");
            dataRow.createCell(2).setCellValue(entity.getPoPosNo() != null ? entity.getPoPosNo() : "");
            dataRow.createCell(3).setCellValue(entity.getPlantLoadYear() != null ? String.valueOf(entity.getPlantLoadYear()) : "");
            dataRow.createCell(4).setCellValue(entity.getPlantLoadMonth() != null ? String.valueOf(entity.getPlantLoadMonth()) : "");
            dataRow.createCell(5).setCellValue(entity.getRecipeNumber() != null ? String.valueOf(entity.getRecipeNumber()) : "");
            dataRow.createCell(6).setCellValue(entity.getAltNumber() != null ? String.valueOf(entity.getAltNumber()) : "");
            dataRow.createCell(7).setCellValue(entity.getRawMaterialNo() != null ? String.valueOf(entity.getRawMaterialNo()) : "");
            dataRow.createCell(8).setCellValue(entity.getRawMaterialName() != null ? entity.getRawMaterialName() : "");
            dataRow.createCell(9).setCellValue(entity.getQtyMtons() != null ? String.valueOf(entity.getQtyMtons()) : "0");
            dataRow.createCell(10).setCellValue(entity.getUnitOfMeasure() != null ? String.valueOf(entity.getUnitOfMeasure()) : "N/A");
            dataRow.createCell(11).setCellValue(entity.getStatus() != null ? String.valueOf(entity.getStatus()) : "");
            dataRow.createCell(12).setCellValue(entity.getReason() != null ? entity.getReason() : "");
            dataRow.createCell(13).setCellValue(entity.getQuality() != null ? entity.getQuality() : "");
            dataRow.createCell(14).setCellValue(entity.getQuantity() != null ? String.valueOf(entity.getQuantity()) : "0");

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
        List<RawMaterialRequirement> entities = rawMaterialRequirementRepository.findAll();
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

        PdfPTable table = new PdfPTable(15);  // 15 columns for the required fields
        table.setWidthPercentage(100f);
        table.setWidths(new int[]{3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3});
        table.setSpacingBefore(5);

        PdfPCell cell = new PdfPCell();
        cell.setBackgroundColor(Color.BLUE);
        cell.setPadding(5);
        Font font = FontFactory.getFont(FontFactory.TIMES_ROMAN);
        font.setColor(Color.WHITE);

        cell.setPhrase(new Phrase("SO No", font));
        table.addCell(cell);
        cell.setPhrase(new Phrase("Material No", font));
        table.addCell(cell);
        cell.setPhrase(new Phrase("PO Pos No", font));
        table.addCell(cell);
        cell.setPhrase(new Phrase("Plant Load Year", font));
        table.addCell(cell);
        cell.setPhrase(new Phrase("Plant Load Month", font));
        table.addCell(cell);
        cell.setPhrase(new Phrase("Recipe Number", font));
        table.addCell(cell);
        cell.setPhrase(new Phrase("Alt Number", font));
        table.addCell(cell);
        cell.setPhrase(new Phrase("Raw Material No", font));
        table.addCell(cell);
        cell.setPhrase(new Phrase("Raw Material Name", font));
        table.addCell(cell);
        cell.setPhrase(new Phrase("Qty (Mtons)", font));
        table.addCell(cell);
        cell.setPhrase(new Phrase("Unit of Measure", font));
        table.addCell(cell);
        cell.setPhrase(new Phrase("Status", font));
        table.addCell(cell);
        cell.setPhrase(new Phrase("Reason", font));
        table.addCell(cell);
        cell.setPhrase(new Phrase("Quality", font));
        table.addCell(cell);
        cell.setPhrase(new Phrase("Quantity", font));
        table.addCell(cell);
        log.debug("Header row added to the PDF table");

        for (RawMaterialRequirement entity : entities) {
            table.addCell(entity.getSoNo() != null ? entity.getSoNo() : "");
            table.addCell(entity.getMaterialNo() != null ? String.valueOf(entity.getMaterialNo()) : "");
            table.addCell(entity.getPoPosNo() != null ? entity.getPoPosNo() : "");
            table.addCell(entity.getPlantLoadYear() != null ? String.valueOf(entity.getPlantLoadYear()) : "");
            table.addCell(entity.getPlantLoadMonth() != null ? String.valueOf(entity.getPlantLoadMonth()) : "");
            table.addCell(entity.getRecipeNumber() != null ? String.valueOf(entity.getRecipeNumber()) : "");
            table.addCell(entity.getAltNumber() != null ? String.valueOf(entity.getAltNumber()) : "");
            table.addCell(entity.getRawMaterialNo() != null ? String.valueOf(entity.getRawMaterialNo()) : "");
            table.addCell(entity.getRawMaterialName() != null ? entity.getRawMaterialName() : "");
            table.addCell(entity.getQtyMtons() != null ? String.valueOf(entity.getQtyMtons()) : "0");
            table.addCell(entity.getUnitOfMeasure() != null ? String.valueOf(entity.getUnitOfMeasure()) : "N/A");
            table.addCell(entity.getStatus() != null ? String.valueOf(entity.getStatus()) : "");
            table.addCell(entity.getReason() != null ? entity.getReason() : "");
            table.addCell(entity.getQuality() != null ? entity.getQuality() : "");
            table.addCell(entity.getQuantity() != null ? String.valueOf(entity.getQuantity()) : "0");
        }

        log.info("Populated PDF table with raw material data");

        document.add(table);
        document.close();
        log.info("PDF generation completed");
    }


    private void mapRequestToEntity(RawMaterialRequirementRequest request, RawMaterialRequirement entity) {
        if (request.getSoNo() != null) entity.setSoNo(request.getSoNo());
        if (request.getMaterialNo() != null) entity.setMaterialNo(request.getMaterialNo());
        if (request.getPoPosNo() != null) entity.setPoPosNo(request.getPoPosNo());
        if (request.getPlantLoadYear() != null) entity.setPlantLoadYear(request.getPlantLoadYear());
        if (request.getPlantLoadMonth() != null) entity.setPlantLoadMonth(request.getPlantLoadMonth());
        if (request.getRecipeNumber() != null) entity.setRecipeNumber(request.getRecipeNumber());
        if (request.getAltNumber() != null) entity.setAltNumber(request.getAltNumber());
        if (request.getRawMaterialNo() != null) entity.setRawMaterialNo(request.getRawMaterialNo());
        if (request.getRawMaterialName() != null) entity.setRawMaterialName(request.getRawMaterialName());
        if (request.getQtyMtons() != null) entity.setQtyMtons(request.getQtyMtons());
        if (request.getUnitOfMeasure() != null) entity.setUnitOfMeasure(request.getUnitOfMeasure());
        if (request.getStatus() != null) entity.setStatus(request.getStatus());
        if (request.getReason() != null) entity.setReason(request.getReason());
        if (request.getQuality() != null) entity.setQuality(request.getQuality());
        if (request.getQuantity() != null) entity.setQuantity(request.getQuantity());
    }
}
