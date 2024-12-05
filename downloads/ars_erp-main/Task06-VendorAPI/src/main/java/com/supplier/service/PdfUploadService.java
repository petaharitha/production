package com.supplier.service;

import java.sql.Blob;
import javax.sql.rowset.serial.SerialBlob;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.supplier.entity.Vendor;
import com.supplier.repository.VendorRepository;

@Service
public class PdfUploadService {

    @Autowired
    private VendorRepository vendorRepository;

    // Method to handle file upload for PAN PDF
    public String uploadPanPdf(Long vendorId, MultipartFile pdfFile) throws IOException {
        return uploadPdf(vendorId, pdfFile, "pan");
    }

    // Method to handle file upload for CIN PDF
    public String uploadCinPdf(Long vendorId, MultipartFile pdfFile) throws IOException {
        return uploadPdf(vendorId, pdfFile, "cin");
    }

    // Method to handle file upload for GST PDF
    public String uploadGstPdf(Long vendorId, MultipartFile pdfFile) throws IOException {
        return uploadPdf(vendorId, pdfFile, "gst");
    }

    // Method to handle file upload for MSME PDF
    public String uploadMsmePdf(Long vendorId, MultipartFile pdfFile) throws IOException {
        return uploadPdf(vendorId, pdfFile, "msme");
    }

    // Helper method to upload PDF file and save as Blob in the database
    private String uploadPdf(Long vendorId, MultipartFile pdfFile, String documentType) throws IOException {
        // Retrieve the vendor from the database
        Vendor vendor = vendorRepository.findById(vendorId)
                .orElseThrow(() -> new RuntimeException("Vendor not found"));

        // Check if the file is empty
        if (pdfFile.isEmpty()) {
            return "No file uploaded";
        }

        // Convert the MultipartFile content to a Blob
        Blob pdfBlob;
        try {
            pdfBlob = new SerialBlob(pdfFile.getBytes());
        } catch (Exception e) {
            throw new RuntimeException("Failed to convert file to Blob", e);
        }

        // Update the vendor's Blob field in the database based on the document type
        switch (documentType.toLowerCase()) {
            case "pan":
                vendor.setPanNumberPdf(pdfBlob);
                break;
            case "cin":
                vendor.setCinNumberPdf(pdfBlob);
                break;
            case "gst":
                vendor.setGstNumberPdf(pdfBlob);
                break;
            case "msme":
                vendor.setMsmePdf(pdfBlob);
                break;
            default:
                return "Invalid document type";
        }

        // Save the updated vendor record with the Blob
        vendorRepository.save(vendor);

        return "File uploaded successfully";
    }
}
