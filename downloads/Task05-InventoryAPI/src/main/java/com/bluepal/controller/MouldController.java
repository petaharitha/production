package com.bluepal.controller;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
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

import com.bluepal.entity.Mould;
import com.bluepal.service.MouldService;
import com.itextpdf.text.DocumentException;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/inventory/moulds")
public class MouldController {

	private final MouldService mouldService;

	@Autowired
	public MouldController(MouldService mouldService) {
		this.mouldService = mouldService;
	}

	@PostMapping
	public ResponseEntity<?> createMould(@RequestHeader("Authorization") String jwt,@Valid @RequestBody Mould mould, BindingResult result) {
		if (result.hasErrors()) {
			StringBuilder errorMessage = new StringBuilder();
			for (ObjectError error : result.getAllErrors()) {
				errorMessage.append(error.getDefaultMessage()).append("; ");
			}
			return ResponseEntity.badRequest().body(errorMessage.toString());
		}
		Mould savedMould = mouldService.createMould(mould);
		return ResponseEntity.status(HttpStatus.CREATED).body(savedMould);
	}

	@PutMapping("/{id}")
	public ResponseEntity<?> updateMould(@RequestHeader("Authorization") String jwt,@PathVariable Long id, @Valid @RequestBody Mould mould, BindingResult result) {
		if (result.hasErrors()) {
			StringBuilder errorMessage = new StringBuilder();
			for (ObjectError error : result.getAllErrors()) {
				errorMessage.append(error.getDefaultMessage()).append("; ");
			}
			return ResponseEntity.badRequest().body(errorMessage.toString());
		}
		try {
			Mould updatedMould = mouldService.updateMould(id, mould);
			return ResponseEntity.ok(updatedMould);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
		}
	}
	
	@GetMapping("/search")
    public Page<Mould> getMoulds(
            @RequestParam(required = false) String materialGroup,
            @RequestParam(required = false) String materialName,
            @RequestParam(required = false) Integer minimumOrderLevel,
            @PageableDefault(size = 10, sort = "materialNo") Pageable pageable) {

        // Call service method to search, sort, filter and paginate the moulds
        return mouldService.searchAndFilterMoulds(materialGroup, materialName, minimumOrderLevel, pageable);
    }

	@GetMapping("/{id}")
	public ResponseEntity<?> getMouldById(@RequestHeader("Authorization") String jwt ,@PathVariable Long id) {
		Optional<Mould> mouldOptional = mouldService.getMouldById(id);
		if (mouldOptional.isPresent()) {
			return ResponseEntity.ok(mouldOptional.get()); // return the found mould
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Mould with ID " + id + " not found");
		}
	}

	@GetMapping
	public ResponseEntity<List<Mould>> getAllMoulds(@RequestHeader("Authorization") String jwt ,@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "10") int size, @RequestParam(defaultValue = "materialNo") String sortBy,
			@RequestParam(defaultValue = "true") boolean ascending) {

		try {
			List<Mould> moulds = mouldService.getAllMoulds(page, size, sortBy, ascending);
			if (moulds.isEmpty()) {
				return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
			}
			return ResponseEntity.ok(moulds);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null); // You can also log the error
																						// here
		}
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteMould(@RequestHeader("Authorization") String jwt,@PathVariable Long id) {
		try {
			mouldService.deleteMould(id);
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
		}
	}

	// Import method
	@PostMapping("/import")
	public ResponseEntity<String> importMoulds(@RequestHeader("Authorization") String jwt ,@RequestParam("file") MultipartFile file) {
		try {
			mouldService.importMoulds(file);
			return ResponseEntity.status(HttpStatus.OK).body("File imported successfully");
		} catch (IOException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body("Error importing file: " + e.getMessage());
		}
	}

	@GetMapping("/export")
	public ResponseEntity<Resource> exportMoulds(@RequestHeader("Authorization") String jwt ) {
		try {
			String filePath = mouldService.exportMoulds();
			File file = new File(filePath);

			if (!file.exists()) {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
			}

			Resource resource = new FileSystemResource(file);

			HttpHeaders headers = new HttpHeaders();
			headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + file.getName());
			headers.add(HttpHeaders.CONTENT_TYPE, "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");

			return ResponseEntity.ok().headers(headers).body(resource);

		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
		}
	}

	@GetMapping("/mould/{materialNo}")

	public ResponseEntity<byte[]> generateMouldReport(@RequestHeader("Authorization") String jwt ,@PathVariable long materialNo) {

		try {

			// Generate the PDF report

			byte[] pdfReport = mouldService.generateMouldReport(materialNo);

			// Set headers for PDF download

			HttpHeaders headers = new HttpHeaders();

			headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=Mould_Report.pdf");

			headers.add(HttpHeaders.CONTENT_TYPE, "application/pdf");

			// Return the PDF as the response entity

			return new ResponseEntity<>(pdfReport, headers, HttpStatus.OK);

		} catch (DocumentException | IOException e) {

			// Handle exceptions and return error response

			e.printStackTrace();

			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);

		}

	}
}