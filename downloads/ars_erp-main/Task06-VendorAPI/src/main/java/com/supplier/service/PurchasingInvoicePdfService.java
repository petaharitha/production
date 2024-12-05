package com.supplier.service;

import java.io.IOException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import com.supplier.entity.PurchasingInvoice;

import jakarta.servlet.ServletOutputStream;

@Service
public class PurchasingInvoicePdfService {

    private static final Logger logger = LoggerFactory.getLogger(PurchasingInvoicePdfService.class);

    @Cacheable(value = "pdfCache", key = "#popdf.hashCode()") // Cache by the hashcode of the list
    public void generatePDF(List<PurchasingInvoice> popdf, ServletOutputStream servletOutputStream) throws Exception {
        logger.info("Generating PDF for {} invoices", popdf.size());
        
        try {
            Document document = new Document();
            PdfWriter.getInstance(document, servletOutputStream);
            document.open();

            for (PurchasingInvoice invoice : popdf) {
                document.add(new Paragraph("Date: " + invoice.getDate()));
                document.add(new Paragraph("Number: " + invoice.getNumber()));
                document.add(new Paragraph("Particulars: " + invoice.getParticulars())); // Fixed field name
                document.add(new Paragraph("Amount: " + invoice.getAmount()));
                document.add(new Paragraph("Due By: " + invoice.getDueBy()));
            }
            document.close();
            logger.info("PDF generated successfully with {} invoices", popdf.size());
        } catch (Exception e) {
            logger.error("Error exporting to PDF", e);
            throw new IOException("Error exporting to PDF", e);
        }
    }
}
