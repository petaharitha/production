package com.supplier.service;

import java.io.IOException;
import java.util.List;

import org.springframework.stereotype.Service;

import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import com.supplier.entity.PurchasingQuote;

import jakarta.servlet.ServletOutputStream;

@Service
public class PurchasingQuotePdfService {

    public void generatePDF(List<PurchasingQuote> salesRFQs, ServletOutputStream servletOutputStream) throws Exception {

    	try {
			Document document = new Document();
			PdfWriter.getInstance(document, servletOutputStream);
			document.open();
			
			for(PurchasingQuote salesRFQ : salesRFQs) {
				document.add(new Paragraph("Date: " + salesRFQ.getDate()));
		        document.add(new Paragraph("Number: " + salesRFQ.getNumber()));
		        document.add(new Paragraph("Approved By: " + salesRFQ.getApprovedBy()));
		        document.add(new Paragraph("Status: " + salesRFQ.getStatus()));
		        document.add(new Paragraph("Amount: " + salesRFQ.getAmount()));

			}
			 document.close();
		} catch (Exception e) {
			throw new IOException("Error expoting to PDF", e);
		}

    }
}
