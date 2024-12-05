package com.supplier.service;


import java.io.ByteArrayOutputStream;
import java.util.List;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.supplier.entity.PurchasingPO;
import com.supplier.repository.PurchasingPORepository;

@Service
public class PurchasingPOExcelService {

	@Autowired
	private PurchasingPORepository purchasingPoRepo;
	
	

	public byte[] exportPurchasingPoToExcel() throws Exception {
		List<PurchasingPO> rfqs = purchasingPoRepo.findAll();

		Workbook workbook = new XSSFWorkbook();
		Sheet sheet = workbook.createSheet("Purchasing PO");

		Row headerRow = sheet.createRow(0);
		headerRow.createCell(0).setCellValue("Date");
		headerRow.createCell(1).setCellValue("Number");
		headerRow.createCell(2).setCellValue("Amount");
		headerRow.createCell(3).setCellValue("Approved By");
		headerRow.createCell(4).setCellValue("Delivery By");
		headerRow.createCell(5).setCellValue("Status");

		int rowNum = 1;
		for (PurchasingPO rfq : rfqs) {
			Row row = sheet.createRow(rowNum++);
			row.createCell(0).setCellValue(rfq.getDate());
			row.createCell(1).setCellValue(rfq.getNumber());
			row.createCell(2).setCellValue(rfq.getAmount());
			row.createCell(3).setCellValue(rfq.getApprovedBy());
			row.createCell(4).setCellValue(rfq.getDeliveryBy());
			row.createCell(5).setCellValue(rfq.getStatus());
		}

		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		workbook.write(outputStream);
		workbook.close();
		return outputStream.toByteArray();
	}
}
