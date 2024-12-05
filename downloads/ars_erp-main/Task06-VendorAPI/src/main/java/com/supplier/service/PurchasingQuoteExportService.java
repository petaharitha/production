package com.supplier.service;

import java.io.ByteArrayOutputStream;
import java.util.List;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.supplier.entity.PurchasingQuote;
import com.supplier.repository.PurchasingQuoteRepository;

@Service
public class PurchasingQuoteExportService {

	@Autowired
	private PurchasingQuoteRepository salesQuoteRepo;

	public byte[] exportQuotesToExcel() throws Exception {
		List<PurchasingQuote> rfqs = salesQuoteRepo.findAll();

		Workbook workbook = new XSSFWorkbook();
		Sheet sheet = workbook.createSheet("Sales RFQs");

		Row headerRow = sheet.createRow(0);
		headerRow.createCell(0).setCellValue("DATE");
		headerRow.createCell(1).setCellValue("NUMBER");
		headerRow.createCell(2).setCellValue("APPROVED BY");
		headerRow.createCell(3).setCellValue("STATUS");
		headerRow.createCell(4).setCellValue("AMOUNT");

		int rowNum = 1;
		for (PurchasingQuote rfq : rfqs) {
			Row row = sheet.createRow(rowNum++);
			row.createCell(0).setCellValue(rfq.getDate());
			row.createCell(1).setCellValue(rfq.getNumber());
			row.createCell(2).setCellValue(rfq.getApprovedBy());
			row.createCell(3).setCellValue(rfq.getStatus());
			row.createCell(4).setCellValue(rfq.getAmount());
		}

		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		workbook.write(outputStream);
		workbook.close();
		return outputStream.toByteArray();
	}
}
