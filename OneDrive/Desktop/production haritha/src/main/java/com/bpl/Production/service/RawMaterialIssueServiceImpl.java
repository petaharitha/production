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

import com.bpl.Production.entity.RawMaterialIssue;
import com.bpl.Production.repository.RawMaterialIssueRepository;
import com.bpl.request.RawMaterialIssueRequest;
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
public class RawMaterialIssueServiceImpl implements RawMaterialIssueService {

    @Autowired
    private RawMaterialIssueRepository rawMaterialIssueRepository;

    @Override
    public RawMaterialIssue createRawMaterialIssue(RawMaterialIssueRequest rawMaterialIssueRequest) {
        log.info("Creating a new RawMaterialIssue with details: {}", rawMaterialIssueRequest);
        RawMaterialIssue rawMaterialIssue = new RawMaterialIssue();
        rawMaterialIssue.setDate(rawMaterialIssueRequest.getDate());
        rawMaterialIssue.setShift(rawMaterialIssueRequest.getShift());
        rawMaterialIssue.setRmNo(rawMaterialIssueRequest.getRmNo());
        rawMaterialIssue.setRmDescription(rawMaterialIssueRequest.getRmDescription());
        rawMaterialIssue.setUnitOfMeasure(rawMaterialIssueRequest.getUnitOfMeasure());
        rawMaterialIssue.setIssuesQty(rawMaterialIssueRequest.getIssuesQty());

        RawMaterialIssue saveissue = rawMaterialIssueRepository.save(rawMaterialIssue);
        log.info("Saved RawMaterialIssue with ID: {}", saveissue.getId());

        rawMaterialIssue.setCreatedBy(saveissue.getId());

        RawMaterialIssue finalIssue = rawMaterialIssueRepository.save(rawMaterialIssue);
        log.info("Final saved RawMaterialIssue: {}", finalIssue);

        return finalIssue;
    }

    @Override
    public RawMaterialIssue updateRawMaterialIssue(RawMaterialIssueRequest rawMaterialIssueRequest, Integer id) throws Exception {
        log.info("Updating RawMaterialIssue with ID: {} and details: {}", id, rawMaterialIssueRequest);

        RawMaterialIssue existingRawMaterialIssue = rawMaterialIssueRepository.findById(id)
                .orElseThrow(() -> {
                    log.error("Raw Material Issue not found with ID: {}", id);
                    return new Exception("Raw Material Issue not found with ID: " + id);
                });

        log.info("Existing RawMaterialIssue details: {}", existingRawMaterialIssue);

        if (rawMaterialIssueRequest.getDate() != null) {
            existingRawMaterialIssue.setDate(rawMaterialIssueRequest.getDate());
        }
        if (rawMaterialIssueRequest.getShift() != null) {
            existingRawMaterialIssue.setShift(rawMaterialIssueRequest.getShift());
        }
        if (rawMaterialIssueRequest.getRmNo() != null) {
            existingRawMaterialIssue.setRmNo(rawMaterialIssueRequest.getRmNo());
        }
        if (rawMaterialIssueRequest.getRmDescription() != null) {
            existingRawMaterialIssue.setRmDescription(rawMaterialIssueRequest.getRmDescription());
        }
        if (rawMaterialIssueRequest.getUnitOfMeasure() != null) {
            existingRawMaterialIssue.setUnitOfMeasure(rawMaterialIssueRequest.getUnitOfMeasure());
        }
        if (rawMaterialIssueRequest.getIssuesQty() != 0) {
            existingRawMaterialIssue.setIssuesQty(rawMaterialIssueRequest.getIssuesQty());
        }

        existingRawMaterialIssue.setUpdatedBy(existingRawMaterialIssue.getId());
        RawMaterialIssue updatedIssue = rawMaterialIssueRepository.save(existingRawMaterialIssue);

        log.info("Updated RawMaterialIssue: {}", updatedIssue);

        return updatedIssue;
    }

    @Override
    public void deleteRawMaterialIssue(Integer id) {
        log.info("Deleting RawMaterialIssue with ID: {}", id);
        RawMaterialIssue rawMaterialIssueById = getRawMaterialIssueById(id);
        rawMaterialIssueRepository.deleteById(rawMaterialIssueById.getId());
        log.info("Deleted RawMaterialIssue with ID: {}", id);
    }

    @Override
    public RawMaterialIssue getRawMaterialIssueById(Integer id) {
        log.info("Fetching RawMaterialIssue by ID: {}", id);
        RawMaterialIssue issue = rawMaterialIssueRepository.findById(id)
                .orElseThrow(() -> {
                    log.error("Raw Material Issue not found with ID: {}", id);
                    return new RuntimeException("Raw Material Issue not found with ID: " + id);
                });
        log.info("Fetched RawMaterialIssue: {}", issue);
        return issue;
    }

    @Override
    public List<RawMaterialIssue> getAllRawMaterialIssues() {
        log.info("Fetching all RawMaterialIssues");
        List<RawMaterialIssue> issues = rawMaterialIssueRepository.findAll();
        log.info("Fetched {} RawMaterialIssues", issues.size());
        return issues;
    }

    @Override
    public Page<RawMaterialIssue> getAllRawMaterialIssues(Pageable pageable) {
        log.info("Fetching paginated RawMaterialIssues with pageable: {}", pageable);
        Page<RawMaterialIssue> issues = rawMaterialIssueRepository.findAll(pageable);
        log.info("Fetched {} RawMaterialIssues on the current page", issues.getNumberOfElements());
        return issues;
    }
    
    @Override
    public void generateExcel(HttpServletResponse response) throws Exception {
        log.info("Starting Excel generation");
        List<RawMaterialIssue> entities = rawMaterialIssueRepository.findAll();  
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
        headerRow.createCell(0).setCellValue("Date");
        headerRow.createCell(1).setCellValue("Shift");
        headerRow.createCell(2).setCellValue("Raw Material No");
        headerRow.createCell(3).setCellValue("Raw Material Description");
        headerRow.createCell(4).setCellValue("Unit of Measure");
        headerRow.createCell(5).setCellValue("Issues Quantity");
        log.debug("Header row created");

        int i = 1;
        for (RawMaterialIssue entity : entities) {
            HSSFRow dataRow = sheet.createRow(i);

            dataRow.createCell(0).setCellValue(entity.getDate() != null ? entity.getDate().toString() : "");
            dataRow.createCell(1).setCellValue(entity.getShift() != null ? entity.getShift() : "");
            dataRow.createCell(2).setCellValue(entity.getRmNo() != null ? String.valueOf(entity.getRmNo()) : "");
            dataRow.createCell(3).setCellValue(entity.getRmDescription() != null ? entity.getRmDescription() : "");
            dataRow.createCell(4).setCellValue(entity.getUnitOfMeasure() != null ? String.valueOf(entity.getUnitOfMeasure()) : "N/A");
            dataRow.createCell(5).setCellValue(entity.getIssuesQty() != 0 ? String.valueOf(entity.getIssuesQty()) : "0");

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
        List<RawMaterialIssue> entities = rawMaterialIssueRepository.findAll();  
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

        PdfPTable table = new PdfPTable(6);  // 6 columns for the required fields
        table.setWidthPercentage(100f);
        table.setWidths(new int[]{3, 3, 3, 4, 3, 3});
        table.setSpacingBefore(5);

        PdfPCell cell = new PdfPCell();
        cell.setBackgroundColor(Color.BLUE);
        cell.setPadding(5);
        Font font = FontFactory.getFont(FontFactory.TIMES_ROMAN);
        font.setColor(Color.WHITE);

        cell.setPhrase(new Phrase("Date", font));
        table.addCell(cell);
        cell.setPhrase(new Phrase("Shift", font));
        table.addCell(cell);
        cell.setPhrase(new Phrase("Raw Material No", font));
        table.addCell(cell);
        cell.setPhrase(new Phrase("Raw Material Description", font));
        table.addCell(cell);
        cell.setPhrase(new Phrase("Unit of Measure", font));
        table.addCell(cell);
        cell.setPhrase(new Phrase("Issues Quantity", font));
        table.addCell(cell);
        log.debug("Header row added to the PDF table");

        for (RawMaterialIssue entity : entities) {
            table.addCell(entity.getDate() != null ? entity.getDate().toString() : "");
            table.addCell(entity.getShift() != null ? entity.getShift() : "");
            table.addCell(entity.getRmNo() != null ? String.valueOf(entity.getRmNo()) : "");
            table.addCell(entity.getRmDescription() != null ? entity.getRmDescription() : "");
            table.addCell(entity.getUnitOfMeasure() != null ? String.valueOf(entity.getUnitOfMeasure()) : "N/A");
            table.addCell(entity.getIssuesQty() != 0 ? String.valueOf(entity.getIssuesQty()) : "0");
        }

        log.info("Populated PDF table with raw material data");

        document.add(table);
        document.close();
        log.info("PDF generation completed");
    }

}
