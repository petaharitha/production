package com.bluepal.service;

import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.bluepal.entity.Mould;
import com.bluepal.repository.MouldRepository;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

import jakarta.persistence.criteria.Predicate;
import jakarta.validation.ValidationException;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class MouldService {
	
	@Autowired
	private CacheManager cacheManager;

	private static final Logger logger = LoggerFactory.getLogger(MouldService.class);
	private final MouldRepository mouldRepository;

	public MouldService(MouldRepository mouldRepository) {
		this.mouldRepository = mouldRepository;
	}
    
	@CacheEvict(value = "moulds", allEntries = true)
	public Mould createMould(Mould mould) {
	    Mould savedMould = mouldRepository.save(mould); // Save the entity first
	    logger.info("Creating new mould with Material No: {}", savedMould.getMaterialNo()); // Log after saving
	    return savedMould; // Return the saved entity
	}
	@CachePut(value = "moulds", key = "#id")
	public Mould updateMould(Long id, Mould updatedMould) {
	    Mould existingMould = mouldRepository.findById(id)
	            .orElseThrow(() -> new RuntimeException("Mould not found with ID: " + id));
	    // Update fields
	    existingMould.setMaterialGroup(updatedMould.getMaterialGroup());
	    existingMould.setMaterialSubGroup(updatedMould.getMaterialSubGroup());
	    existingMould.setMaterialName(updatedMould.getMaterialName());
	    existingMould.setLength(updatedMould.getLength());
	    existingMould.setBreadth(updatedMould.getBreadth());
	    existingMould.setHeight(updatedMould.getHeight());
	    existingMould.setDiameter(updatedMould.getDiameter());
	    existingMould.setThickness(updatedMould.getThickness());
	    existingMould.setGsm(updatedMould.getGsm());
	    existingMould.setUnitOfMeasurement(updatedMould.getUnitOfMeasurement());
	    existingMould.setMinimumOrderLevel(updatedMould.getMinimumOrderLevel());
	    existingMould.setLeadTime(updatedMould.getLeadTime());
	    existingMould.setDrawingNo(updatedMould.getDrawingNo());
	    existingMould.setUpdatedAt(LocalDateTime.now());

	    Mould savedMould = mouldRepository.save(existingMould); // Save the updated entity
	    logger.info("Updated mould with ID: {}", id);
	    return savedMould;
	}


	@Cacheable(value = "moulds", key = "#id")
	public Optional<Mould> getMouldById(Long id) {
		logger.info("Fetching mould with ID: {}", id);
		return mouldRepository.findById(id);
	}


	@Cacheable(value = "moulds", key = "'all-' + #page + '-' + #size + '-' + #sortBy + '-' + #ascending")
	public List<Mould> getAllMoulds(int page, int size, String sortBy, boolean ascending) {
	    logger.info("Fetching moulds with pagination: page {}, size {}, sorted by {}", page, size, sortBy);
	    Pageable pageable = PageRequest.of(page, size,
	            ascending ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending());
	    List<Mould> moulds = mouldRepository.findAll(pageable).getContent();

	    // Populate individual cache entries for each mould
	    Cache cache = cacheManager.getCache("moulds");
	    if (cache != null) {
	        for (Mould mould : moulds) {
	            cache.put(mould.getMaterialNo(), mould);
	        }
	    }

	    return moulds;
	}
	// Delete Mould with Cache Eviction
    @CacheEvict(value = "moulds", allEntries = true)
	public void deleteMould(Long id) {
		logger.info("Deleting mould with ID: {}", id);
		Optional<Mould> existingMould = mouldRepository.findById(id);
		if (existingMould.isPresent()) {
			mouldRepository.delete(existingMould.get());
		} else {
			logger.error("Mould with ID {} not found", id);
			throw new ValidationException("Mould with ID " + id + " not found");
		}
	}
    @Cacheable(value = "mouldsSearchCache", key = "#materialGroup + #materialName + #minimumOrderLevel + #pageable.pageNumber + #pageable.pageSize")
    public Page<Mould> searchAndFilterMoulds(String materialGroup, String materialName, Integer minimumOrderLevel, Pageable pageable) {
        // Dynamic Specification for filters
        Specification<Mould> spec = (root, query, criteriaBuilder) -> {
            Predicate predicate = criteriaBuilder.conjunction();

            // Filter by material group
            if (materialGroup != null && !materialGroup.isBlank()) {
                predicate = criteriaBuilder.and(predicate, criteriaBuilder.equal(root.get("materialGroup"), materialGroup));
            }

            // Filter by material name (contains)
            if (materialName != null && !materialName.isBlank()) {
                predicate = criteriaBuilder.and(predicate, criteriaBuilder.like(root.get("materialName"), "%" + materialName + "%"));
            }

            // Filter by minimum order level (greater than)
            if (minimumOrderLevel != null) {
                predicate = criteriaBuilder.and(predicate, criteriaBuilder.greaterThan(root.get("minimumOrderLevel"), minimumOrderLevel));
            }

            return predicate;
        };

        // Fetch results with pagination, sorting, and filtering
        return mouldRepository.findAll(spec, pageable);
    }
	@CacheEvict(value = "moulds", allEntries = true)  
	public void importMoulds(MultipartFile file) throws IOException {
		logger.info("Importing moulds from file: {}", file.getOriginalFilename());

		if (!file.getOriginalFilename().endsWith(".xlsx")) {
			throw new IllegalArgumentException("Invalid file type. Please upload an Excel file.");
		}

		try (Workbook workbook = new XSSFWorkbook(file.getInputStream())) {
			Sheet sheet = workbook.getSheetAt(0); // Get the first sheet

			for (Row row : sheet) {
				if (row.getRowNum() == 0)
					continue; // Skip header row

				Mould mould = new Mould();

				try {
					// Set fields safely with null-checks and validation
					mould.setMaterialGroup(getStringCellValue(row.getCell(1)));
					mould.setMaterialSubGroup(getStringCellValue(row.getCell(2)));
					mould.setMaterialName(getStringCellValue(row.getCell(3)));
					mould.setLength(getNumericCellValue(row.getCell(4)));
					mould.setBreadth(getNumericCellValue(row.getCell(5)));
					mould.setHeight(getNumericCellValue(row.getCell(6)));
					mould.setDiameter(getNumericCellValue(row.getCell(7)));
					mould.setThickness(getNumericCellValue(row.getCell(8)));
					mould.setGsm(getStringCellValue(row.getCell(9)));
					mould.setUnitOfMeasurement(getStringCellValue(row.getCell(10)));
					mould.setMinimumOrderLevel(getIntCellValue(row.getCell(11)));
					mould.setLeadTime(getIntCellValue(row.getCell(12)));
					mould.setDrawingNo(getStringCellValue(row.getCell(13)));

					mouldRepository.save(mould); // Save valid mould
				} catch (Exception e) {
					logger.error("Error processing row {}: {}", row.getRowNum(), e.getMessage());
				}
			}
		} catch (Exception e) {
			logger.error("Error reading the Excel file: {}", e.getMessage(), e);
			throw new IOException("Error reading the Excel file: " + e.getMessage(), e);
		}

	}

	private String getStringCellValue(Cell cell) {
		return cell == null ? "" : cell.getStringCellValue().trim();
	}

	private double getNumericCellValue(Cell cell) {
		if (cell == null) {
			return 0;
		}
		if (cell.getCellType() == CellType.NUMERIC) {
			return cell.getNumericCellValue();
		} else {
			return 0;
		}
	}

	private int getIntCellValue(Cell cell) {
		return (int) getNumericCellValue(cell);
	}
	
	public String exportMoulds() {
		logger.info("Exporting moulds to Excel.");
		List<Mould> moulds = mouldRepository.findAll();
		String filePath = "Moulds.xlsx";

		try (Workbook workbook = new XSSFWorkbook(); FileOutputStream outputStream = new FileOutputStream(filePath)) {

			Sheet sheet = workbook.createSheet("Moulds");
			int rowNum = 0;

			Row headerRow = sheet.createRow(rowNum++);
			headerRow.createCell(0).setCellValue("Material No");
			headerRow.createCell(1).setCellValue("Material Group");
			headerRow.createCell(2).setCellValue("Material Sub-Group");
			headerRow.createCell(3).setCellValue("Material Name");
			headerRow.createCell(4).setCellValue("Length");
			headerRow.createCell(5).setCellValue("Breadth");
			headerRow.createCell(6).setCellValue("Height");
			headerRow.createCell(7).setCellValue("Diameter");
			headerRow.createCell(8).setCellValue("Thickness");
			headerRow.createCell(9).setCellValue("GSM");
			headerRow.createCell(10).setCellValue("Unit of Measurement");
			headerRow.createCell(11).setCellValue("Minimum Order Level");
			headerRow.createCell(12).setCellValue("Lead Time");
			headerRow.createCell(13).setCellValue("Drawing No");

			for (Mould mould : moulds) {
				Row row = sheet.createRow(rowNum++);
				row.createCell(0).setCellValue(mould.getMaterialNo());
				row.createCell(1).setCellValue(mould.getMaterialGroup());
				row.createCell(2).setCellValue(mould.getMaterialSubGroup());
				row.createCell(3).setCellValue(mould.getMaterialName());
				row.createCell(4).setCellValue(mould.getLength());
				row.createCell(5).setCellValue(mould.getBreadth());
				row.createCell(6).setCellValue(mould.getHeight());
				row.createCell(7).setCellValue(mould.getDiameter());
				row.createCell(8).setCellValue(mould.getThickness());
				row.createCell(9).setCellValue(mould.getGsm());
				row.createCell(10).setCellValue(mould.getUnitOfMeasurement());
				row.createCell(11).setCellValue(mould.getMinimumOrderLevel());
				row.createCell(12).setCellValue(mould.getLeadTime());
				row.createCell(13).setCellValue(mould.getDrawingNo());
			}

			workbook.write(outputStream);
		} catch (IOException e) {
			logger.error("Error exporting moulds to Excel: {}", e.getMessage(), e);
		}

		return filePath;
	}

	// Method to generate a report based on Material No or Name

	public byte[] generateMouldReport(long materialNo) throws DocumentException, IOException {

		// Search for Moulds by Material No or Material Name

		List<Mould> moulds = mouldRepository.findByMaterialNoOrMaterialNameContaining(materialNo,
				String.valueOf(materialNo));

		// Create a ByteArrayOutputStream to write the PDF to a byte array

		ByteArrayOutputStream baos = new ByteArrayOutputStream();

		// Creating a new PDF document

		Document document = new Document();

		PdfWriter.getInstance(document, baos);

		document.open();

		// Add title to PDF, centered and in bold

		Font titleFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 16);

		Paragraph title = new Paragraph("Mould Specifications Report", titleFont);

		title.setAlignment(Element.ALIGN_CENTER); // Center the title

		document.add(title);

		// Add some space after the title

		document.add(Chunk.NEWLINE);

		document.add(Chunk.NEWLINE);

		// Loop through the moulds and add their details to the document step by step

		for (Mould mould : moulds) {

			// Add Mould Material Number

			Paragraph materialNoParagraph = new Paragraph("Material No: " + mould.getMaterialNo());

			materialNoParagraph.setAlignment(Element.ALIGN_LEFT); // Left align

			document.add(materialNoParagraph);

			// Add Mould Material Group

			Paragraph materialGroup = new Paragraph("Material Group: " + mould.getMaterialGroup());

			materialGroup.setAlignment(Element.ALIGN_LEFT); // Left align

			document.add(materialGroup);

			// Add Mould Material Name

			Paragraph materialName = new Paragraph("Material Name: " + mould.getMaterialName());

			materialName.setAlignment(Element.ALIGN_LEFT); // Left align

			document.add(materialName);

			// Add Mould Length

			Paragraph length = new Paragraph("Length: " + mould.getLength() + " cm");

			length.setAlignment(Element.ALIGN_LEFT); // Left align

			document.add(length);

			// Add Mould Breadth

			Paragraph breadth = new Paragraph("Breadth: " + mould.getBreadth() + " cm");

			breadth.setAlignment(Element.ALIGN_LEFT); // Left align

			document.add(breadth);

			// Add Mould Height

			Paragraph height = new Paragraph("Height: " + mould.getHeight() + " cm");

			height.setAlignment(Element.ALIGN_LEFT); // Left align

			document.add(height);

			// Add Mould Unit of Measurement

			Paragraph unitOfMeasurement = new Paragraph("Unit of Measurement: " + mould.getUnitOfMeasurement());

			unitOfMeasurement.setAlignment(Element.ALIGN_LEFT); // Left align

			document.add(unitOfMeasurement);

			// Add Mould Minimum Order Level

			Paragraph minimumOrderLevel = new Paragraph("Minimum Order Level: " + mould.getMinimumOrderLevel());

			minimumOrderLevel.setAlignment(Element.ALIGN_LEFT); // Left align

			document.add(minimumOrderLevel);

			// Add space between moulds

			document.add(Chunk.NEWLINE);

		}

		// Close the document

		document.close();

		// Return the PDF as a byte array

		return baos.toByteArray();

	}

}