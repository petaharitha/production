package com.supplier.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.supplier.service.PdfUploadService;

@RestController
@RequestMapping("api/vendors/pdf")  // Or another appropriate path
public class PdfController {
    
	@Autowired
    private PdfUploadService pdfUploadService;

    // Endpoint to upload PAN PDF
    @PostMapping("/{vendorId}/upload-pan")
    public ResponseEntity<String> uploadPanPdf(@PathVariable Long vendorId, @RequestParam("file") MultipartFile file) {
        try {
            String response = pdfUploadService.uploadPanPdf(vendorId, file);
            return ResponseEntity.ok(response);
        } catch (IOException e) {
            return ResponseEntity.badRequest().body("Failed to upload PAN PDF: " + e.getMessage());
        }
    }

    // Endpoint to upload CIN PDF
    @PostMapping("/{vendorId}/upload-cin")
    public ResponseEntity<String> uploadCinPdf(@PathVariable Long vendorId, @RequestParam("file") MultipartFile file) {
        try {
            String response = pdfUploadService.uploadCinPdf(vendorId, file);
            return ResponseEntity.ok(response);
        } catch (IOException e) {
            return ResponseEntity.badRequest().body("Failed to upload CIN PDF: " + e.getMessage());
        }
    }

    // Endpoint to upload GST PDF
    @PostMapping("/{vendorId}/upload-gst")
    public ResponseEntity<String> uploadGstPdf(@PathVariable Long vendorId, @RequestParam("file") MultipartFile file) {
        try {
            String response = pdfUploadService.uploadGstPdf(vendorId, file);
            return ResponseEntity.ok(response);
        } catch (IOException e) {
            return ResponseEntity.badRequest().body("Failed to upload GST PDF: " + e.getMessage());
        }
    }

    // Endpoint to upload MSME PDF
    @PostMapping("/{vendorId}/upload-msme")
    public ResponseEntity<String> uploadMsmePdf(@PathVariable Long vendorId, @RequestParam("file") MultipartFile file) {
        try {
            String response = pdfUploadService.uploadMsmePdf(vendorId, file);
            return ResponseEntity.ok(response);
        } catch (IOException e) {
            return ResponseEntity.badRequest().body("Failed to upload MSME PDF: " + e.getMessage());
        }
    }
}
