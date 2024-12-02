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

import com.bpl.Production.entity.RawMaterialStock;
import com.bpl.Production.repository.RawMaterialStockRepository;
import com.bpl.request.RawMaterialStockRequest;
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
public class RawMaterialStockServiceImpl implements RawMaterialStockService {

    @Autowired
    private RawMaterialStockRepository rawMaterialStockRepository;

    @Override
    public RawMaterialStock createRawMaterialStock(RawMaterialStockRequest request) {
        log.info("Creating RawMaterialStock with details: {}", request);

        RawMaterialStock stock = new RawMaterialStock();
        mapRequestToEntity(request, stock);

        RawMaterialStock savedStock = rawMaterialStockRepository.save(stock);
        stock.setCreatedBy(savedStock.getId());

        log.info("Created RawMaterialStock with ID: {}", savedStock.getId());
        return rawMaterialStockRepository.save(stock);
    }

    @Override
    public RawMaterialStock updateRawMaterialStock(RawMaterialStockRequest request, Integer id) throws Exception {
        log.info("Updating RawMaterialStock with ID: {} and details: {}", id, request);

        RawMaterialStock existingStock = rawMaterialStockRepository.findById(id)
                .orElseThrow(() -> {
                    log.error("Raw Material Stock not found with ID: {}", id);
                    return new Exception("Raw Material Stock not found with ID: " + id);
                });

        mapRequestToEntity(request, existingStock);
        existingStock.setLastModifiedBy(id);

        log.info("Updated RawMaterialStock with ID: {}", id);
        return rawMaterialStockRepository.save(existingStock);
    }

    @Override
    public void deleteRawMaterialStock(Integer id) {
        log.info("Deleting RawMaterialStock with ID: {}", id);

        RawMaterialStock stock = getRawMaterialStockById(id);
        rawMaterialStockRepository.delete(stock);

        log.info("Deleted RawMaterialStock with ID: {}", id);
    }

    @Override
    public RawMaterialStock getRawMaterialStockById(Integer id) {
        log.info("Fetching RawMaterialStock with ID: {}", id);

        RawMaterialStock stock = rawMaterialStockRepository.findById(id)
                .orElseThrow(() -> {
                    log.error("Raw Material Stock not found with ID: {}", id);
                    return new RuntimeException("Raw Material Stock not found with ID: " + id);
                });

        log.info("Fetched RawMaterialStock: {}", stock);
        return stock;
    }

    @Override
    public List<RawMaterialStock> getAllRawMaterialStocks() {
        log.info("Fetching all RawMaterialStocks");

        List<RawMaterialStock> stocks = rawMaterialStockRepository.findAll();
        log.info("Fetched {} RawMaterialStocks", stocks.size());

        return stocks;
    }

    @Override
    public Page<RawMaterialStock> getAllRawMaterialStocks(Pageable pageable) {
        log.info("Fetching paginated RawMaterialStocks with pageable: {}", pageable);

        Page<RawMaterialStock> stocksPage = rawMaterialStockRepository.findAll(pageable);
        log.info("Fetched {} RawMaterialStocks on the current page", stocksPage.getNumberOfElements());

        return stocksPage;
    }

    private void mapRequestToEntity(RawMaterialStockRequest request, RawMaterialStock entity) {
        if (request.getRmNo() != null) {
            log.debug("Setting RawMaterialStock number: {}", request.getRmNo());
            entity.setRmNo(request.getRmNo());
        }
        if (request.getRmDescription() != null) {
            log.debug("Setting RawMaterialStock description: {}", request.getRmDescription());
            entity.setRmDescription(request.getRmDescription());
        }
        if (request.getUnitOfMeasure() != null) {
            log.debug("Setting unit of measure: {}", request.getUnitOfMeasure());
            entity.setUnitOfMeasure(request.getUnitOfMeasure());
        }
        if (request.getLeadTime() != null) {
            log.debug("Setting lead time: {}", request.getLeadTime());
            entity.setLeadTime(request.getLeadTime());
        }
        if (request.getMinimumStock() != null) {
            log.debug("Setting minimum stock: {}", request.getMinimumStock());
            entity.setMinimumStock(request.getMinimumStock());
        }
        if (request.getRawMaterialStock() != null) {
            log.debug("Setting raw material stock: {}", request.getRawMaterialStock());
            entity.setRawMaterialStock(request.getRawMaterialStock());
        }
    }
    
    @Override
    public void generateExcel(HttpServletResponse response) throws Exception {
        log.info("Starting Excel generation");
        List<RawMaterialStock> entities = rawMaterialStockRepository.findAll();  
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
        headerRow.createCell(2).setCellValue("Unit of Measure");
        headerRow.createCell(3).setCellValue("Lead Time");
        headerRow.createCell(4).setCellValue("Minimum Stock");
        headerRow.createCell(5).setCellValue("Raw Material Stock");
        log.debug("Header row created");

        int i = 1;
        for (RawMaterialStock entity : entities) {
            HSSFRow dataRow = sheet.createRow(i);

           
            dataRow.createCell(0).setCellValue(entity.getRmNo() != null ? String.valueOf(entity.getRmNo()) : "");
            dataRow.createCell(1).setCellValue(entity.getRmDescription() != null ? entity.getRmDescription() : "");
            dataRow.createCell(2).setCellValue(entity.getUnitOfMeasure() != null ? String.valueOf(entity.getUnitOfMeasure()) : "N/A");
            dataRow.createCell(3).setCellValue(entity.getLeadTime() != null ? entity.getLeadTime().toString() : "");
            dataRow.createCell(4).setCellValue(entity.getMinimumStock() != null ? String.valueOf(entity.getMinimumStock()) : "0");
            dataRow.createCell(5).setCellValue(entity.getRawMaterialStock() != null ? String.valueOf(entity.getRawMaterialStock()) : "0");

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
        List<RawMaterialStock> entities = rawMaterialStockRepository.findAll();  
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

        PdfPTable table = new PdfPTable(6);  
        table.setWidthPercentage(100f);
        table.setWidths(new int[]{3, 3, 3, 4, 3, 3});
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
        cell.setPhrase(new Phrase("Unit of Measure", font));
        table.addCell(cell);
        cell.setPhrase(new Phrase("Lead Time", font));
        table.addCell(cell);
        cell.setPhrase(new Phrase("Minimum Stock", font));
        table.addCell(cell);
        cell.setPhrase(new Phrase("Raw Material Stock", font));
        table.addCell(cell);
        log.debug("Header row added to the PDF table");

        for (RawMaterialStock entity : entities) {
        
            table.addCell(entity.getRmNo() != null ? String.valueOf(entity.getRmNo()) : "");
            table.addCell(entity.getRmDescription() != null ? entity.getRmDescription() : "");
            table.addCell(entity.getUnitOfMeasure() != null ? String.valueOf(entity.getUnitOfMeasure()) : "N/A");
            table.addCell(entity.getLeadTime() != null ? entity.getLeadTime().toString() : "");
            table.addCell(entity.getMinimumStock() != null ? String.valueOf(entity.getMinimumStock()) : "0");
            table.addCell(entity.getRawMaterialStock() != null ? String.valueOf(entity.getRawMaterialStock()) : "0");
        }

        log.info("Populated PDF table with raw material data");

        document.add(table);
        document.close();
        log.info("PDF generation completed");
    }

}
