package com.supplier.service;

import java.io.ByteArrayOutputStream;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.supplier.entity.Vendor;
import com.supplier.repository.VendorRepository;

@Service
public class SupplierImportExportService {

    @Autowired
    private VendorRepository vendorRepository;

    public void importSupplier(MultipartFile file) throws Exception {
        if (file.isEmpty()) {
            throw new Exception("File is empty.");
        }

        try (Workbook workbook = new XSSFWorkbook(file.getInputStream())) {
            Sheet sheet = workbook.getSheetAt(0);

            for (int i = 1; i <= sheet.getLastRowNum(); i++) {
                Row row = sheet.getRow(i);

                Vendor vendor = new Vendor();
                if (row != null) {
                    if (row.getCell(0) != null && row.getCell(0).getCellType() == CellType.STRING) {
                        vendor.setVendorIdNo(row.getCell(0).getStringCellValue());
                    }

                    if (row.getCell(1) != null && row.getCell(1).getCellType() == CellType.STRING) {
                        vendor.setVendorName(row.getCell(1).getStringCellValue());
                    }

                    if (row.getCell(2) != null && row.getCell(2).getCellType() == CellType.STRING) {
                        vendor.setAddressLine1(row.getCell(2).getStringCellValue());
                    }

                    if (row.getCell(3) != null && row.getCell(3).getCellType() == CellType.STRING) {
                        vendor.setAddressLine2(row.getCell(3).getStringCellValue());
                    }

                    if (row.getCell(4) != null && row.getCell(4).getCellType() == CellType.STRING) {
                        vendor.setAddressLine3(row.getCell(4).getStringCellValue());
                    }

                    if (row.getCell(5) != null && row.getCell(5).getCellType() == CellType.STRING) {
                        vendor.setCity(row.getCell(5).getStringCellValue());
                    }

                    if (row.getCell(6) != null && row.getCell(6).getCellType() == CellType.STRING) {
                        vendor.setDistrict(row.getCell(6).getStringCellValue());
                    }

                    if (row.getCell(7) != null && row.getCell(7).getCellType() == CellType.STRING) {
                        vendor.setState(row.getCell(7).getStringCellValue());
                    }

                    if (row.getCell(8) != null && row.getCell(8).getCellType() == CellType.STRING) {
                        vendor.setCountry(row.getCell(8).getStringCellValue());
                    }

                    if (row.getCell(9) != null && row.getCell(9).getCellType() == CellType.STRING) {
                        vendor.setPinCode(row.getCell(9).getStringCellValue());
                    }

                    if (row.getCell(10) != null && row.getCell(10).getCellType() == CellType.STRING) {
                        vendor.setPanNumber(row.getCell(10).getStringCellValue());
                    }

                    if (row.getCell(11) != null && row.getCell(11).getCellType() == CellType.STRING) {
                        vendor.setCinNumber(row.getCell(11).getStringCellValue());
                    }

                    if (row.getCell(12) != null && row.getCell(12).getCellType() == CellType.STRING) {
                        vendor.setGstNumber(row.getCell(12).getStringCellValue());
                    }

                    if (row.getCell(13) != null && row.getCell(13).getCellType() == CellType.STRING) {
                        vendor.setMsmE(row.getCell(13).getStringCellValue());
                    }

                    if (row.getCell(14) != null && row.getCell(14).getCellType() == CellType.STRING) {
                        vendor.setSalesRepFirstName(row.getCell(14).getStringCellValue());
                    }

                    if (row.getCell(15) != null && row.getCell(15).getCellType() == CellType.STRING) {
                        vendor.setSalesRepLastName(row.getCell(15).getStringCellValue());
                    }

                    if (row.getCell(16) != null && row.getCell(16).getCellType() == CellType.STRING) {
                        vendor.setSalesRepMobileNo1(row.getCell(16).getStringCellValue());
                    }

                    if (row.getCell(17) != null && row.getCell(17).getCellType() == CellType.STRING) {
                        vendor.setSalesRepEmail1(row.getCell(17).getStringCellValue());
                    }

                    // Add other fields like `accountsRepFirstName`, `logisticsRepFirstName`, etc.
                    // Assuming you have similar cells for other fields.

                    vendorRepository.save(vendor);
                }
            }
        }
    }

    public byte[] exportSupplierToExcel() {
        List<Vendor> vendors = vendorRepository.findAll();
        try (Workbook workbook = new XSSFWorkbook(); ByteArrayOutputStream outputStream = new ByteArrayOutputStream()) {
            Sheet sheet = workbook.createSheet("Vendors");

            // Create header row
            Row headerRow = sheet.createRow(0);
            String[] headers = {
                "Vendor ID No", "Vendor Name", "Address Line 1", "Address Line 2", "Address Line 3", "Village/City", 
                "District", "State", "Country", "Pin Code", "PAN Number", "CIN Number", "GST Number", "MSME", 
                "Sales Rep First Name", "Sales Rep Last Name", "Sales Rep Mobile No 1", "Sales Rep Email 1"
                // Add other headers here for `Accounts Rep`, `Logistics Rep` etc.
            };

            for (int i = 0; i < headers.length; i++) {
                Cell cell = headerRow.createCell(i);
                cell.setCellValue(headers[i]);
            }

            // Fill data rows
            int rowNum = 1;
            for (Vendor vendor : vendors) {
                Row row = sheet.createRow(rowNum++);
                row.createCell(0).setCellValue(vendor.getVendorIdNo());
                row.createCell(1).setCellValue(vendor.getVendorName());
                row.createCell(2).setCellValue(vendor.getAddressLine1());
                row.createCell(3).setCellValue(vendor.getAddressLine2());
                row.createCell(4).setCellValue(vendor.getAddressLine3());
                row.createCell(5).setCellValue(vendor.getCity());
                row.createCell(6).setCellValue(vendor.getDistrict());
                row.createCell(7).setCellValue(vendor.getState());
                row.createCell(8).setCellValue(vendor.getCountry());
                row.createCell(9).setCellValue(vendor.getPinCode());
                row.createCell(10).setCellValue(vendor.getPanNumber());
                row.createCell(11).setCellValue(vendor.getCinNumber());
                row.createCell(12).setCellValue(vendor.getGstNumber());
                row.createCell(13).setCellValue(vendor.getMsmE());
                row.createCell(14).setCellValue(vendor.getSalesRepFirstName());
                row.createCell(15).setCellValue(vendor.getSalesRepLastName());
                row.createCell(16).setCellValue(vendor.getSalesRepMobileNo1());
                row.createCell(17).setCellValue(vendor.getSalesRepEmail1());
                // Add other columns for `Accounts Rep`, `Logistics Rep` etc.
            }

            // Write the output to a byte array output stream
            workbook.write(outputStream);
            return outputStream.toByteArray(); // Return as byte array for file download
        } catch (Exception e) {
            e.printStackTrace(); // Log the error
            throw new RuntimeException("Error exporting vendors to Excel: " + e.getMessage(), e);
        }
    }
}
