package com.supplier.service;


import java.io.IOException;
import java.util.List;

import org.springframework.stereotype.Service;

import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import com.supplier.entity.PurchasingPO;

import jakarta.servlet.ServletOutputStream;

@Service
public class PurchasingPOPdfService {

    public void generatePDF(List<PurchasingPO> popdf, ServletOutputStream servletOutputStream) throws Exception {

    	try {
			Document document = new Document();
			PdfWriter.getInstance(document, servletOutputStream);
			document.open();
			
			for(PurchasingPO popdf1 : popdf) {
				document.add(new Paragraph("Date: " + popdf1.getDate()));
		        document.add(new Paragraph("Number: " + popdf1.getNumber()));
		        document.add(new Paragraph("Amount: " + popdf1.getAmount()));
		        document.add(new Paragraph("Approved By: " + popdf1.getApprovedBy()));
		        document.add(new Paragraph("Delivery By: " + popdf1.getDeliveryBy()));
		        document.add(new Paragraph("Status: " + popdf1.getStatus()));


			}
			 document.close();
		} catch (Exception e) {
			throw new IOException("Error expoting to PDF", e);
		}

    }
}
