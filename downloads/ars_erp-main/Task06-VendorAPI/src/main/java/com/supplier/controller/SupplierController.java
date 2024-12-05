package com.supplier.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.supplier.entity.Vendor;
import com.supplier.service.SupplierImportExportService;
import com.supplier.service.SupplierService;

@RestController
@RequestMapping("/api/vendors")
public class SupplierController {

    @Autowired
    private SupplierService supplierService;
    
    @Autowired
    private SupplierImportExportService importExportService;

    @PostMapping
    public ResponseEntity<Vendor> createSupplier(@RequestHeader ("Authorization") String jwt,  @RequestBody Vendor vendor) {
        return ResponseEntity.status(HttpStatus.CREATED).body(supplierService.createSupplier(vendor));
    }

    @GetMapping
    public ResponseEntity<List<Vendor>> getAllSuppliers(@RequestHeader ("Authorization") String jwt) {
        return ResponseEntity.ok(supplierService.getAllVendors());
    }

//    @GetMapping("/{id}")
//    public ResponseEntity<Vendor> getSupplierById(@RequestHeader ("Authorization") String jwt, @PathVariable Long id) {
//        return ResponseEntity.ok(supplierService.getSupplierById(id));
//    }

//    @PutMapping("/{vendorId}/updateAndRefresh")
//    public ResponseEntity<String> updateVendorAndRefreshRFQ(
//            @PathVariable Long vendorId,
//            @RequestBody Vendor updatedVendorData,
//            @RequestParam Long purchasingRFQId) {
//    	supplierService.updateVendorAndRefreshRFQ(vendorId, updatedVendorData, purchasingRFQId);
//        return ResponseEntity.ok("Vendor and associated PurchasingRFQ updated successfully!");
//    }
    @PutMapping("/{vendorId}/updateAndRefresh")
    public ResponseEntity<String> updateVendorAndRefreshRFQ(
            @PathVariable Long vendorId,
            @RequestBody Vendor updatedVendorData) {
        supplierService.updateVendorAndRefreshRFQ(vendorId, updatedVendorData);
        return ResponseEntity.ok("Vendor updated successfully!");
    }

    

    @PostMapping("/importClients")
    public ResponseEntity<String> importClients(@RequestHeader ("Authorization") String jwt, @RequestParam("file") MultipartFile file) {
        try {
            importExportService.importSupplier(file);
            return ResponseEntity.ok("Clients imported successfully");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Failed to import clients: " + e.getMessage());
        }
    }
    
    @GetMapping("/exportClients/excel")
    public ResponseEntity<byte[]> exportClientsToExcel(@RequestHeader ("Authorization") String jwt) {
        byte[] excelFile = importExportService.exportSupplierToExcel();
        return ResponseEntity.ok()
                .header("Content-Disposition", "attachment; filename=clients.xlsx")
                .body(excelFile);
    }
    
//    @DeleteMapping("/{id}")
//    public ResponseEntity<String> deleteSupplier(@PathVariable Long id) {
//        try {
//            vendorService.deleteSupplier(id);
//            return ResponseEntity.ok("Supplier with ID " + id + " deleted successfully.");
//        } catch (Exception e) {
//            return ResponseEntity.status(500).body("Error deleting supplier: " + e.getMessage());
//        }
//    }

}

