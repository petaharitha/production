package com.bluepal.controller;


import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.bluepal.entity.RFG;
import com.bluepal.service.RFGImportExportService;
import com.bluepal.service.RFGService;

import jakarta.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/api/inventory/rfg")
public class RFGController {

@Autowired
private RFGService rfgService;

@Autowired
private RFGImportExportService rfgImportExportService;

// Create 
@PostMapping("/create")
public ResponseEntity<RFG> createOrUpdateRFG(@RequestHeader("Authorization") String jwt,@RequestBody RFG rfg) {
RFG savedRFG = rfgService.saveRFG(rfg);
return ResponseEntity.ok(savedRFG);
}

//Update an existing RFG
	@PostMapping("/update/{productNo}")
	public ResponseEntity<RFG> updateRFG(@RequestHeader("Authorization") String jwt,@PathVariable Integer productNo, @RequestBody RFG rfg) {
		try {
			RFG updatedRFG = rfgService.updateRFG(productNo, rfg);
			return ResponseEntity.ok(updatedRFG);
		} catch (IllegalArgumentException e) {
			return ResponseEntity.badRequest().body(null);
		}
	}

// Get RFG by ProductNo
@GetMapping("/productNo/{productNo}")
public ResponseEntity<RFG> getRFGByProductNo(@RequestHeader("Authorization") String jwt ,@PathVariable Integer productNo) {
Optional<RFG> rfg = rfgService.getRFGByProductNo(productNo);
return rfg.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
}

// Get RFG by ProductName
@GetMapping("/productName/{productName}")
public ResponseEntity<RFG> getRFGByProductName(@RequestHeader("Authorization") String jwt ,@PathVariable String productName) {
Optional<RFG> rfg = rfgService.getRFGByProductName(productName);
return rfg.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
}

// Get all RFGs with pagination
@GetMapping
public ResponseEntity<Page<RFG>> getAllRFGs(@RequestHeader("Authorization") String jwt ,Pageable pageable) {
org.springframework.data.domain.Page<RFG> rfgPage = rfgService.getAllRFGs(pageable);
return ResponseEntity.ok(rfgPage);
}

// Delete RFG
@DeleteMapping("/{productNo}")
public ResponseEntity<Void> deleteRFG(@RequestHeader("Authorization") String jwt,@PathVariable Integer productNo) {
rfgService.deleteRFG(productNo);
return ResponseEntity.noContent().build();
}

// Import RFG data from Excel file
@PostMapping("/import")
public ResponseEntity<String> importRFG(@RequestHeader("Authorization") String jwt ,@RequestParam("file") MultipartFile file) {
try {
rfgImportExportService.importRFG(file);
return ResponseEntity.ok("File imported successfully");
} catch (Exception e) {
return ResponseEntity.status(500).body("Failed to import file: " + e.getMessage());
}
}

// Export RFG data to Excel file
@GetMapping("/export")
public ResponseEntity<byte[]> exportRFG(@RequestHeader("Authorization") String jwt ) throws Exception{
try {
byte[] excelData = rfgImportExportService.exportRFGToExcel();

HttpHeaders headers = new HttpHeaders();
headers.add("Content-Disposition", "attachment; filename=rfg_data.xlsx");
headers.add("Content-Type", "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");

return ResponseEntity.ok()
     .headers(headers)
     .contentType(MediaType.APPLICATION_OCTET_STREAM)
     .body(excelData);

} catch (Exception e) {
return ResponseEntity.status(500).body(null);
}
}
@GetMapping("/generate-product-specs-report")
public void generateAndDownloadProductSpecsReport(@RequestHeader("Authorization") String jwt ,@RequestParam String searchCriteria, HttpServletResponse response) {
    try {
        rfgService.generateAndDownloadProductSpecsReport(searchCriteria, response);
    } catch (Exception e) {
        response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        try { response.getWriter().write("Error generating report: " + e.getMessage()); } 
        catch (Exception ex) { ex.printStackTrace(); }
    }
}
}