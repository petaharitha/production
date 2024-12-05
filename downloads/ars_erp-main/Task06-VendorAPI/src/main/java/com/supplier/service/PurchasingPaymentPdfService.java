package com.supplier.service;

import java.io.IOException;
import java.util.List;

import org.springframework.stereotype.Service;

import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import com.supplier.entity.PurchasingPaymentMade;

import jakarta.servlet.ServletOutputStream;

@Service
public class PurchasingPaymentPdfService {

    public void generatePDF(List<PurchasingPaymentMade> popdf, ServletOutputStream servletOutputStream) throws Exception {

    	try {
			Document document = new Document();
			PdfWriter.getInstance(document, servletOutputStream);
			document.open();
			
			for(PurchasingPaymentMade popdf1 : popdf) {
				document.add(new Paragraph("Date: " + popdf1.getDate()));
		        document.add(new Paragraph("Amount: " + popdf1.getAmount()));
		        document.add(new Paragraph("Mode Of Payment: " + popdf1.getModeOfPayment()));


			}
			 document.close();
		} catch (Exception e) {
			throw new IOException("Error expoting to PDF", e);
		}

    }
}
