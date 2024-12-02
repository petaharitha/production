package com.bpl.Production.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bpl.Production.entity.RawMaterialStock;
import com.bpl.Production.service.RawMaterialStockService;
import com.bpl.request.RawMaterialStockRequest;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/raw-material-stocks")
@Slf4j
public class RawMaterialStockController {

    @Autowired
    private RawMaterialStockService rawMaterialStockService;

    @PostMapping("/create")
    public ResponseEntity<RawMaterialStock> createStock(@Valid @RequestBody RawMaterialStockRequest request) {
        log.info("Received request to create RawMaterialStock: {}", request);

        RawMaterialStock stock = rawMaterialStockService.createRawMaterialStock(request);
        log.info("Successfully created RawMaterialStock with ID: {}", stock.getId());

        return ResponseEntity.ok(stock);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<RawMaterialStock> updateStock(@Valid @RequestBody RawMaterialStockRequest request, @PathVariable Integer id) throws Exception {
        log.info("Received request to update RawMaterialStock with ID: {} and details: {}", id, request);

        RawMaterialStock stock = rawMaterialStockService.updateRawMaterialStock(request, id);
        log.info("Successfully updated RawMaterialStock with ID: {}", stock.getId());

        return ResponseEntity.ok(stock);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteStock(@PathVariable Integer id) {
        log.info("Received request to delete RawMaterialStock with ID: {}", id);

        rawMaterialStockService.deleteRawMaterialStock(id);
        log.info("Successfully deleted RawMaterialStock with ID: {}", id);

        return ResponseEntity.noContent().build();
    }

    @GetMapping("/getById/{id}")
    public ResponseEntity<RawMaterialStock> getStockById(@PathVariable Integer id) {
        log.info("Received request to fetch RawMaterialStock with ID: {}", id);

        RawMaterialStock stock = rawMaterialStockService.getRawMaterialStockById(id);
        log.info("Successfully fetched RawMaterialStock with ID: {}", id);

        return ResponseEntity.ok(stock);
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<RawMaterialStock>> getAllStocks() {
        log.info("Received request to fetch all RawMaterialStocks");

        List<RawMaterialStock> stocks = rawMaterialStockService.getAllRawMaterialStocks();
        log.info("Successfully fetched {} RawMaterialStocks", stocks.size());

        return ResponseEntity.ok(stocks);
    }

    @GetMapping("/getAllByPage")
    public ResponseEntity<Page<RawMaterialStock>> getAllStocks(Pageable pageable) {
        log.info("Received request to fetch RawMaterialStocks with page: {} and size: {}", pageable.getPageNumber(), pageable.getPageSize());

        Page<RawMaterialStock> stocks = rawMaterialStockService.getAllRawMaterialStocks(pageable);
        log.info("Successfully fetched {} RawMaterialStocks on page {}", stocks.getNumberOfElements(), pageable.getPageNumber());

        return ResponseEntity.ok(stocks);
    }
    
    @GetMapping("/excel")
    public void excelReport(HttpServletResponse response) throws Exception {
        log.info("Generating Excel report.");
        response.setContentType("application/vnd.ms-excel");
        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=user_data.xls";
        response.setHeader(headerKey, headerValue);
        rawMaterialStockService.generateExcel(response);
        log.info("Excel report successfully generated.");
    }

    @GetMapping("/pdf")
    public void generatePdfFile(HttpServletResponse response) throws Exception {
        log.info("Generating PDF report.");
        response.setContentType("application/pdf");
        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=Report.pdf";
        response.setHeader(headerKey, headerValue);
        rawMaterialStockService.generatePdf(response);
        log.info("PDF report successfully generated.");
    }
}
