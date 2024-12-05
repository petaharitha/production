package com.supplier.service;

import java.io.ByteArrayOutputStream;
import java.util.List;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.supplier.entity.PurchasingInvoice;
import com.supplier.repository.PurchasingInvoiceRepository;

@Service
public class PurchasingInvoiceExportService {

    private static final Logger logger = LoggerFactory.getLogger(PurchasingInvoiceExportService.class);

    @Autowired
    private PurchasingInvoiceRepository invoiceRepository;

    @Cacheable(value = "excelCache", key = "'invoicesExcel'") // Cache for the exported Excel file
    public byte[] exportInvoiceToExcel() throws Exception {
        logger.info("Exporting invoices to Excel.");

        List<PurchasingInvoice> rfqs = invoiceRepository.findAll();
        
        // Create a new workbook and a sheet
        try (Workbook workbook = new XSSFWorkbook(); ByteArrayOutputStream outputStream = new ByteArrayOutputStream()) {
            Sheet sheet = workbook.createSheet("Sales Invoices");

            // Create header row
            Row headerRow = sheet.createRow(0);
            headerRow.createCell(0).setCellValue("DATE");
            headerRow.createCell(1).setCellValue("NUMBER");
            headerRow.createCell(2).setCellValue("PARTICULARS");
            headerRow.createCell(3).setCellValue("AMOUNT");
            headerRow.createCell(4).setCellValue("DUE BY");

            // Populate the sheet with invoice data
            int rowNum = 1;
            for (PurchasingInvoice rfq : rfqs) {
                Row row = sheet.createRow(rowNum++);
                row.createCell(0).setCellValue(rfq.getDate());
                row.createCell(1).setCellValue(rfq.getNumber());
                row.createCell(2).setCellValue(rfq.getParticulars());
                row.createCell(3).setCellValue(rfq.getAmount());
                row.createCell(4).setCellValue(rfq.getDueBy());
            }

            // Write the workbook to the output stream
            workbook.write(outputStream);
            logger.info("Successfully exported {} invoices to Excel.", rfqs.size());
            return outputStream.toByteArray();
        } catch (Exception e) {
            logger.error("Error while exporting invoices to Excel", e);
            throw e; // rethrowing the exception
        }
    }
}
