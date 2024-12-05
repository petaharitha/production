//package com.supplier.controller;
//
//import java.util.List;
//import java.util.Map;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.MediaType;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.DeleteMapping;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.PutMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestHeader;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RestController;
//
//import com.supplier.entity.Comment;
//import com.supplier.entity.PurchasingQuote;
//import com.supplier.repository.PurchasingQuoteRepository;
//import com.supplier.service.PurchasingQuoteExportService;
//import com.supplier.service.PurchasingQuotePdfService;
//import com.supplier.service.PurchasingQuoteService;
//
//import jakarta.servlet.ServletOutputStream;
//
//@RestController
//@RequestMapping("/api/vendors/purchasing-quote")
//public class PurchasingQuoteController {
//
//    @Autowired
//    private PurchasingQuoteService quoteService;
//    
//    @Autowired
//    private PurchasingQuotePdfService quotePdfService;
//    
//    @Autowired
//    private PurchasingQuoteRepository quoteRepository;
//    
//    @Autowired
//    private PurchasingQuoteExportService exportService;
//
//    @PostMapping
//    public ResponseEntity<PurchasingQuote> createQuote(@RequestHeader ("Authorization") String jwt, @RequestBody PurchasingQuote quote) {
//        return ResponseEntity.status(HttpStatus.CREATED).body(quoteService.createQuote(quote));
//    }
//
//    @GetMapping
//    public ResponseEntity<List<PurchasingQuote>> getAllQuotes(@RequestHeader ("Authorization") String jwt) {
//        return ResponseEntity.ok(quoteService.getAllQuotes());
//    }
//
//    @GetMapping("/{id}")
//    public ResponseEntity<PurchasingQuote> getQuoteById(@RequestHeader ("Authorization") String jwt, @PathVariable Long id) {
//        return ResponseEntity.ok(quoteService.getQuoteById(id));
//    }
//
//    @PutMapping("/{id}")
//    public ResponseEntity<PurchasingQuote> updateQuote(@RequestHeader ("Authorization") String jwt, @PathVariable Long id, @RequestBody PurchasingQuote quoteDetails) {
//        return ResponseEntity.ok(quoteService.updateQuote(id, quoteDetails));
//    }
//
//    @DeleteMapping("/{id}")
//    public ResponseEntity<Void> deleteQuote(@RequestHeader ("Authorization") String jwt, @PathVariable Long id) {
//        quoteService.deleteQuote(id);
//        return ResponseEntity.noContent().build();
//    }
//
//    @PostMapping("/{quoteId}/comments")
//    public ResponseEntity<Void> addComment(@RequestHeader ("Authorization") String jwt, @PathVariable Long quoteId, @RequestBody String comment) {
//        quoteService.addComment(quoteId, comment);
//        return ResponseEntity.status(201).build();
//    }
//
//    @GetMapping("/{quoteId}/comments")
//    public ResponseEntity<List<Comment>> getCommentsForQuote(@RequestHeader ("Authorization") String jwt, @PathVariable Long quoteId) {
//        return ResponseEntity.ok(quoteService.getComments(quoteId));
//    }
//
//    @GetMapping("/compare")
//    public ResponseEntity<Map<String, Object>> compareQuotes(@RequestHeader ("Authorization") String jwt, @RequestParam List<Long> quoteIds) {
//        Map<String, Object> comparisonResult = quoteService.compareQuotes(quoteIds);
//        return ResponseEntity.ok(comparisonResult);
//    }
//
//
//    @GetMapping("/export")
//    public ResponseEntity<byte[]> exportRFQsToExcel(@RequestHeader ("Authorization") String jwt) {
//        try {
//            byte[] excelFile = exportService.exportQuotesToExcel();
//            return ResponseEntity.ok()
//                    .contentType(MediaType.APPLICATION_OCTET_STREAM)
//                    .header("Content-Disposition", "attachment; filename=purchasing_Quote.xlsx")
//                    .body(excelFile);
//        } catch (Exception e) {
//            return ResponseEntity.internalServerError().build();
//        }
//    }
//
//    @GetMapping("/pdf")
//    public void exportToPdf(@RequestHeader ("Authorization") String jwt, ServletOutputStream servletOutputStream) throws Exception{
//    	List<PurchasingQuote> salesRFQs = quoteRepository.findAll();
//    	quotePdfService.generatePDF(salesRFQs, servletOutputStream );
//    }
//}
//
