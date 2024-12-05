package com.supplier.service;

import java.io.ByteArrayOutputStream;
import java.util.List;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.supplier.entity.PurchasingPaymentMade;
import com.supplier.repository.PurchasingPaymentRepository;

@Service
public class PurchasingPaymentExportService {

	@Autowired
	private PurchasingPaymentRepository paymentRepository;

	public byte[] exportPaymentToExcel() throws Exception {
		List<PurchasingPaymentMade> rfqs = paymentRepository.findAll();

		Workbook workbook = new XSSFWorkbook();
		Sheet sheet = workbook.createSheet("Sales RFQs");

		Row headerRow = sheet.createRow(0);
		headerRow.createCell(0).setCellValue("DATE");
		headerRow.createCell(1).setCellValue("AMOUNT");
		headerRow.createCell(2).setCellValue("MODE OF PAYMENT");


		int rowNum = 1;
		for (PurchasingPaymentMade rfq : rfqs) {
			Row row = sheet.createRow(rowNum++);
			row.createCell(0).setCellValue(rfq.getDate());
			row.createCell(1).setCellValue(rfq.getAmount());
			row.createCell(2).setCellValue(rfq.getModeOfPayment());
		
		}

		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		workbook.write(outputStream);
		workbook.close();
		return outputStream.toByteArray();
	}
}
