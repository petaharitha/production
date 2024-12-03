package com.bluepal.service;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import javax.sql.DataSource;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.bluepal.entity.Spares;
import com.bluepal.repository.SparesRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.annotation.PostConstruct;
import jakarta.transaction.Transactional;

@Service
public class SparesService {
	@Autowired
    private RedisTemplate<String, String> redisTemplate; // Inject RedisTemplate for publishing
    
    private final ChannelTopic topic = new ChannelTopic("sparesCacheSync"); // Redis channel for cache sync

//    private static final Logger logger = LoggerFactory.getLogger(CacheSynchronizationService.class);

    @Autowired
    private ObjectMapper objectMapper; // Jackson ObjectMapper for serializing and deserializing


    private static final Logger logger = LoggerFactory.getLogger(SparesService.class);

    @Autowired
    private SparesRepository sparesRepository;
    
    @Autowired
    private CachePublisher cachePublisher;
    
    @Autowired
    private DataSource dataSource;

//    @Autowired
//    @Qualifier("concurrentMapCacheManager")
//    private ConcurrentMapCacheManager cacheManager;  // For managing cache programmatically
    @Autowired
    @Qualifier("redisCacheManager")    
    private CacheManager cacheManager;
	private static boolean isCachePreloaded;

	/**
     * Preload cache with all spares at startup.
     */
	 @PostConstruct
	    public void preloadCache() {
	        try {
	            // Fetch all spares from the database and load them into the cache
	            List<Spares> sparesList = sparesRepository.findAll();
	            for (Spares spare : sparesList) {
	                // Serialize the Spares object to JSON string
	                String spareJson = objectMapper.writeValueAsString(spare);
	                // Manually putting the spares into Redis cache as a JSON string
	                redisTemplate.opsForValue().set("spares:" + spare.getMaterialNo(), spareJson);
	            }
	            isCachePreloaded = true;
	            logger.info("Cache preloaded with {} spares.", sparesList.size());
	        } catch (Exception e) {
	            logger.error("Error preloading cache from database: {}", e.getMessage(), e);
	        }
	    }

	    /**
	     * Method to check if the cache is preloaded.
	     */
	    public boolean isCachePreloaded() {
	        return isCachePreloaded;
	    }

	    /**
	     * Fetch all spares, return from cache if preloaded, otherwise return from database.
	     */
	    @Cacheable(value = "spares")
	    public List<Spares> getAllSpares() {
	        if (isCachePreloaded) {
	            logger.info("Fetching all spares from cache.");

	            // Retrieve spares from Redis using RedisTemplate by scanning keys
	            List<Spares> cachedSpares = new ArrayList<>();

	            // Retrieve keys for all cached spares from Redis (assuming keys are prefixed "spares:")
	            Set<String> keys = redisTemplate.keys("spares:*");
	            for (String key : keys) {
	                String spareJson = redisTemplate.opsForValue().get(key);
	                if (spareJson != null) {
	                    try {
	                        // Deserialize JSON string back to Spares object
	                        Spares spare = objectMapper.readValue(spareJson, Spares.class);
	                        cachedSpares.add(spare);
	                    } catch (JsonProcessingException e) {
	                        logger.error("Error deserializing spare: {}", e.getMessage(), e);
	                    }
	                }
	            }

	            // Return the cached list of spares if available
	            if (!cachedSpares.isEmpty()) {
	                logger.info("Returning spares from cache.");
	                return cachedSpares;
	            } else {
	                logger.info("Cache is empty, fetching spares from the database.");
	                // If the cache doesn't contain spares, fall back to fetching from the database
	                return sparesRepository.findAll();
	            }
	        } else {
	            logger.info("Cache not preloaded. Returning all spares from database.");
	            // If the cache is not preloaded, return spares directly from the database
	            return sparesRepository.findAll();
	        }
	    }
	    @Cacheable(value = "spares", key = "'page_'+#pageNo+'_'+#pageSize+'_'+#sortField+'_'+#sortOrder+'_'+#searchText+'_'+#fieldNames+'_'+#fieldValues", unless = "#result.isEmpty()")
	    public Page<Spares> getSparesWithPaginationAndFilters(int pageNo, int pageSize, String sortField, String sortOrder, String searchText, List<String> fieldNames, List<String> fieldValues) {
	        logger.info("Fetching spares with pagination and filters - pageNo: {}, pageSize: {}, sortField: {}, sortOrder: {}, searchText: {}, fieldNames: {}, fieldValues: {}", 
	                    pageNo, pageSize, sortField, sortOrder, searchText, fieldNames, fieldValues);

	        // Default values for parameters if none are provided
	        if (pageNo == 0 && pageSize == Integer.MAX_VALUE) {
	            pageSize = Integer.MAX_VALUE; // Fetch all records if no pagination
	        }
	        if (sortField == null || sortField.isEmpty()) {
	            sortField = "materialName"; // Default field to sort by
	        }
	        if (sortOrder == null || sortOrder.isEmpty()) {
	            sortOrder = "asc"; // Default sort order
	        }

	        // First, try fetching data from cache if it is preloaded
	        if (isCachePreloaded) {
	            logger.info("Fetching spares from cache.");

	            // Get all keys for cached spares in Redis
	            Set<String> keys = redisTemplate.keys("spares:*");
	            List<Spares> allCachedSpares = new ArrayList<>();

	            // Iterate over the cache to collect all spares
	            for (String key : keys) {
	                String spareJson = redisTemplate.opsForValue().get(key);
	                if (spareJson != null) {
	                    try {
	                        Spares spare = objectMapper.readValue(spareJson, Spares.class);
	                        allCachedSpares.add(spare);
	                    } catch (JsonProcessingException e) {
	                        logger.error("Error deserializing spare from cache: {}", e.getMessage());
	                    }
	                }
	            }

	            // Apply filters and pagination if cache has data
	            if (!allCachedSpares.isEmpty()) {
	                // Apply search filter if searchText is provided
	                if (searchText != null && !searchText.isEmpty()) {
	                    allCachedSpares = allCachedSpares.stream()
	                            .filter(spare -> spare.getMaterialGroup().contains(searchText) || spare.getMaterialName().contains(searchText))
	                            .collect(Collectors.toList());
	                }

	                // Apply dynamic field-based filtering
	                if (fieldNames != null && fieldValues != null && fieldNames.size() == fieldValues.size()) {
	                    allCachedSpares = allCachedSpares.stream()
	                            .filter(spare -> applyFilters(spare, fieldNames, fieldValues))
	                            .collect(Collectors.toList());
	                }

	                // Apply sorting
	                Sort sort = Sort.by(Sort.Order.by(sortField).with(Sort.Direction.fromString(sortOrder)));

	                // Apply pagination
	                int fromIndex = Math.min(pageNo * pageSize, allCachedSpares.size());
	                int toIndex = Math.min((pageNo + 1) * pageSize, allCachedSpares.size());
	                List<Spares> paginatedSpares = allCachedSpares.subList(fromIndex, toIndex);

	                // Return paginated and filtered result from cache
	                return new PageImpl<>(paginatedSpares, PageRequest.of(pageNo, pageSize, sort), allCachedSpares.size());
	            }
	        }

	        // If cache is empty or not preloaded, fetch data from the database
	        logger.info("Cache is empty or not preloaded. Fetching spares from the database.");

	        // Construct the sort object
	        Sort sort = Sort.by(Sort.Order.by(sortField).with(Sort.Direction.fromString(sortOrder)));

	        Page<Spares> sparesPage;

	        // Case when pagination and sorting is required
	        if (searchText != null && !searchText.isEmpty()) {
	            // Fetch spares based on the search text with pagination and sorting
	            sparesPage = sparesRepository.findByMaterialGroupContainingOrMaterialNameContaining(
	                    searchText, searchText, PageRequest.of(pageNo, pageSize, sort)
	            );
	        } else {
	            // Fetch spares from the database with pagination and sorting
	            sparesPage = sparesRepository.findAll(PageRequest.of(pageNo, pageSize, sort));
	        }

	        // Apply dynamic field-based filtering after fetching from the database
	        if (fieldNames != null && fieldValues != null && fieldNames.size() == fieldValues.size()) {
	            sparesPage = filterPage(sparesPage, fieldNames, fieldValues);
	        }

	        // Cache the fetched records for future requests
	        sparesPage.getContent().forEach(spare -> {
	            try {
	                String spareJson = objectMapper.writeValueAsString(spare);
	                redisTemplate.opsForValue().set("spares:" + spare.getMaterialNo(), spareJson);
	            } catch (JsonProcessingException e) {
	                logger.error("Error serializing spare to cache: {}", e.getMessage());
	            }
	        });

	        return sparesPage;
	    }

	    // Helper method to apply dynamic filters to a single spare object
	    private boolean applyFilters(Spares spare, List<String> fieldNames, List<String> fieldValues) {
	        for (int i = 0; i < fieldNames.size(); i++) {
	            String fieldName = fieldNames.get(i);
	            String fieldValue = fieldValues.get(i);

	            // Dynamically check the value of the field
	            Object field = getFieldValue(spare, fieldName);
	            if (field == null || !field.toString().equals(fieldValue)) {
	                return false;
	            }
	        }
	        return true;
	    }

	    // Helper method to filter a Page of spares based on dynamic field names and values
	    private Page<Spares> filterPage(Page<Spares> sparesPage, List<String> fieldNames, List<String> fieldValues) {
	        List<Spares> filteredSpares = sparesPage.getContent().stream()
	                .filter(spare -> applyFilters(spare, fieldNames, fieldValues))
	                .collect(Collectors.toList());

	        return new PageImpl<>(filteredSpares, sparesPage.getPageable(), filteredSpares.size());
	    }

	    // Helper method to dynamically get the value of a field by name
	    private Object getFieldValue(Spares spare, String fieldName) {
	        try {
	            Field field = Spares.class.getDeclaredField(fieldName);
	            field.setAccessible(true);
	            return field.get(spare);
	        } catch (NoSuchFieldException | IllegalAccessException e) {
	            logger.error("Error retrieving field value for '{}': {}", fieldName, e.getMessage());
	            return null;
	        }
	    }


    /**
     * Fetch spares based on material number.
     */
    @Cacheable(value = "spares", key = "#materialNo", unless = "#result == null")
    public Optional<Spares> getSparesByMaterialNo(Long materialNo) {
        logger.info("Fetching spare with Material No: {}", materialNo);

        // Check if the spare is already cached in Redis
        String spareJson = redisTemplate.opsForValue().get("spares:" + materialNo);
        if (spareJson != null) {
            try {
                // Deserialize JSON string back to Spares object
                Spares spare = objectMapper.readValue(spareJson, Spares.class);
                logger.info("Spare with Material No: {} found in cache.", materialNo);
                return Optional.of(spare);  // Return the cached spare
            } catch (JsonProcessingException e) {
                logger.error("Error deserializing spare from cache: {}", e.getMessage(), e);
            }
        }

        // If not found in cache, fetch from the database
        Optional<Spares> spareFromDatabase = sparesRepository.findByMaterialNo(materialNo);
        if (spareFromDatabase.isPresent()) {
            // Cache the fetched spare in Redis for future requests
            try {
                String spareJsonToCache = objectMapper.writeValueAsString(spareFromDatabase.get());
                redisTemplate.opsForValue().set("spares:" + materialNo, spareJsonToCache);
                logger.info("Spare with Material No: {} found in database. Adding to cache.", materialNo);
            } catch (JsonProcessingException e) {
                logger.error("Error serializing spare to cache: {}", e.getMessage(), e);
            }
        } else {
            logger.warn("Spare with Material No: {} not found in the database.", materialNo);
        }

        // Return the result from the database (or empty if not found)
        return spareFromDatabase;
    }

    /**
     * Update a spare and refresh the cache using materialNo.
     */
    @Transactional
    @CachePut(value = "spares", key = "#materialNo")
    public Spares updateSpares(Long materialNo, Spares sparesDetails) {
        logger.info("Updating spare with Material No: {}", materialNo);

        // Fetch spare from the database to update
        Spares spares = sparesRepository.findByMaterialNo(materialNo)
                .orElseThrow(() -> new RuntimeException("Spares not found with Material No: " + materialNo));

        // Update fields based on sparesDetails
        spares.setUpdatedAt(LocalDateTime.now());
        spares.setUpdatedBy("System");
        spares.setMaterialGroup(sparesDetails.getMaterialGroup());
        spares.setMaterialSubGroup(sparesDetails.getMaterialSubGroup());
        spares.setMaterialName(sparesDetails.getMaterialName());
        spares.setLength(sparesDetails.getLength());
        spares.setBreadth(sparesDetails.getBreadth());
        spares.setHeight(sparesDetails.getHeight());
        spares.setOriginalOEM(sparesDetails.getOriginalOEM());
        spares.setPartNo(sparesDetails.getPartNo());
        spares.setDrawingNo(sparesDetails.getDrawingNo());
        spares.setUnitOfMeasure(sparesDetails.getUnitOfMeasure());
        spares.setMinimumOrderLevel(sparesDetails.getMinimumOrderLevel());
        spares.setLeadTime(sparesDetails.getLeadTime());

        // Save the updated spare in the database
        Spares updatedSpares = sparesRepository.save(spares);

        // Manually update the cache
        try {
            String spareJson = objectMapper.writeValueAsString(updatedSpares);
            redisTemplate.opsForValue().set("spares:" + materialNo, spareJson);
        } catch (JsonProcessingException e) {
            logger.error("Error serializing spare to cache: {}", e.getMessage());
        }

        // Publish cache update
        cachePublisher.publishCacheUpdate("CACHE_UPDATE", "sparesCache", String.valueOf(updatedSpares.getMaterialNo()));

        return updatedSpares;
    }

   
    
    /**
     * Delete a spare and evict it from the cache using materialNo.
     */
    @Transactional
    public void deleteSpares(Long materialNo) {
        logger.info("Deleting spare with Material No: {}", materialNo);

        // Fetch spare from the database to ensure it exists before deletion
        Spares spareToDelete = sparesRepository.findByMaterialNo(materialNo)
                .orElseThrow(() -> new RuntimeException("Spares not found with Material No: " + materialNo));

        // Publish cache delete event before deleting from the database
        cachePublisher.publishCacheUpdate("CACHE_DELETE", "sparesCache", String.valueOf(materialNo));

        // Delete the spare from the database
        sparesRepository.deleteByMaterialNo(materialNo);

        // Manually evict cache entry (remove from Redis)
        redisTemplate.delete("spares:" + materialNo);  // Remove specific cache entry

        // Log the eviction action
        logger.info("Cache entry for Material No: {} has been removed.", materialNo);
    }

    /**
     * Create a new spare and update the cache.
     */
    @CachePut(value = "spares", key = "#spares.materialNo")
    public Spares createSpares(Spares spares) {
    	if (spares == null || spares.getMaterialNo() == null) {
            logger.debug("Spares or materialNo is null");
        }
        // Set creation metadata
        spares.setCreatedAt(LocalDateTime.now());
        spares.setCreatedBy("System");
        logger.info("Creating a new spare: {}", spares);

        // Save the spares object to the database
        Spares createdSpare = sparesRepository.save(spares);
        logger.info("Spare with Material No: {} saved to the database.", createdSpare.getMaterialNo());

        // Publish cache update for synchronization
        cachePublisher.publishCacheUpdate("CACHE_UPDATE", "spares", String.valueOf(createdSpare.getMaterialNo()));
        logger.info("Cache updated for Spare with Material No: {}", createdSpare.getMaterialNo());

        // The @CachePut annotation ensures the updated object is stored in the cache
        return createdSpare;
    }
    
    /**
     * Import spares from an Excel file.
     */
    public void importSpares(MultipartFile file, String user) throws Exception {
        logger.info("Importing spares from file: {}", file.getOriginalFilename());
        if (file.isEmpty()) {
            logger.error("File is empty.");
            throw new Exception("File is empty.");
        }

        try (InputStream inputStream = file.getInputStream(); Workbook workbook = new XSSFWorkbook(inputStream)) {
            Sheet sheet = workbook.getSheetAt(0);
            Iterator<Row> rowIterator = sheet.iterator();

            // Skip the header row
            if (rowIterator.hasNext()) {
                rowIterator.next();
            }

            while (rowIterator.hasNext()) {
                Row row = rowIterator.next();
                if (row != null) {
                    Spares spare = new Spares();
                    // Set properties from the row
                    spare.setMaterialNo((long) row.getCell(0).getNumericCellValue());
                    spare.setMaterialGroup(row.getCell(1).getStringCellValue());
                    spare.setMaterialSubGroup(row.getCell(2).getStringCellValue());
                    spare.setMaterialName(row.getCell(3).getStringCellValue());
                    spare.setLength((long) row.getCell(4).getNumericCellValue());
                    spare.setBreadth((long) row.getCell(5).getNumericCellValue());
                    spare.setHeight((long) row.getCell(6).getNumericCellValue());
                    spare.setOriginalOEM(row.getCell(7).getStringCellValue());
                    spare.setPartNo(row.getCell(8).getStringCellValue());
                    spare.setDrawingNo(row.getCell(9).getStringCellValue());
                    spare.setUnitOfMeasure(row.getCell(10).getStringCellValue());
                    spare.setMinimumOrderLevel((long) row.getCell(11).getNumericCellValue());
                    spare.setLeadTime((long) row.getCell(12).getNumericCellValue());

                    // Set user and timestamp information
                    if (spare.getMaterialNo() == null) {
                        // New spare
                        spare.setCreatedBy(user);
                        spare.setCreatedAt(LocalDateTime.now());
                    } else {
                        // Existing spare, update the fields
                        spare.setUpdatedBy(user);
                        spare.setUpdatedAt(LocalDateTime.now());
                    }

                    // Check if spare already exists
                    Spares existingSpare = sparesRepository.findByMaterialNo(spare.getMaterialNo()).orElse(null);

                    if (existingSpare != null) {
                        // Update the existing spare (cache and database sync)
                        updateSpares(spare.getMaterialNo(), spare);
                    } else {
                        // Create new spare (cache and database sync)
                        createSpares(spare);
                    }
                }
            }
        } catch (IOException e) {
            logger.error("Error while importing spares: {}", e.getMessage(), e);
            throw new Exception("Error while importing spares: " + e.getMessage(), e);
        }
    }

    /**
     * Export spares to Excel.
     */
    public byte[] exportSparesToExcel() {
        logger.info("Exporting spares to Excel.");
        List<Spares> sparesList = sparesRepository.findAll();

        try (Workbook workbook = new XSSFWorkbook(); ByteArrayOutputStream outputStream = new ByteArrayOutputStream()) {
            Sheet sheet = workbook.createSheet("Spares");
            Row headerRow = sheet.createRow(0);
            String[] headers = {"Material Group", "Material Sub Group", "Material Name", "Length", "Breadth", "Height",
                    "Original OEM", "Part No", "Drawing No", "Unit of Measure", "Minimum Order Level", "Lead Time"};
            for (int i = 0; i < headers.length; i++) {
                Cell cell = headerRow.createCell(i);
                cell.setCellValue(headers[i]);
            }
            int rowNum = 1;
            for (Spares spare : sparesList) {
                Row row = sheet.createRow(rowNum++);
                row.createCell(0).setCellValue(spare.getMaterialGroup());
                // Add other fields here
            }
            workbook.write(outputStream);
            return outputStream.toByteArray();
        } catch (IOException e) {
            logger.error("Error while exporting spares to Excel: {}", e.getMessage(), e);
            return null;
        }
    }

    /**
     * Cache auto-refresh (refresh cache periodically from DB).
     */
//    @CacheEvict(value = "spares", allEntries = true) // Clear all spares from cache
//    @Cacheable(value = "spares", unless = "#result.isEmpty()") // Re-cache data after fetching from database
//    public List<Spares> refreshCache() {
//        logger.info("Refreshing cache from database.");
//        // Fetch all spares from the database to reload the cache
//        List<Spares> sparesList = sparesRepository.findAll();
//        
//        // Return the list (this will trigger the @Cacheable and cache the result)
//        return sparesList;
//    }
 // Schedule this method to run every 5 minutes (for example)
    
    /**
     * Automatically preload recent spares into the cache at application startup.
     */
    @PostConstruct
    public void preloadRecentSpares() {
        logger.info("Application startup detected. Preloading recent spares into the cache.");
        cacheRecentSpares1();
    }

    /**
     * Automatically refresh the cache every 5 minutes.
     */
 // Method to check if the database is available
    private boolean isDatabaseAvailable() {
        try {
            // Get a connection from the data source
            Connection connection = dataSource.getConnection();
            if (connection != null && !connection.isClosed()) {
                connection.close(); // Close the connection after checking
                return true; // Database is available
            }
        } catch (SQLException e) {
            logger.error("Database is not available: " + e.getMessage());
        }
        return false; // Database is not available
    }

    // Method to refresh the cache, scheduled to run every 5 minutes
    @Scheduled(fixedRate = 300000) // 300,000 ms = 5 minutes
    public void refreshCache() {
        if (isDatabaseAvailable()) {
            logger.info("Database is running. Refreshing cache...");
            cacheRecentSpares1(); // Method to refresh the cache
        } else {
            logger.info("Database is not available. Cache refresh skipped.");
        }
    }

    // Your cache refresh logic here (example)
    public void cacheRecentSpares1() {
        // Logic for refreshing the cache
        logger.info("Cache has been refreshed with recent spares.");
    }

    /**
     * Preload or refresh recent spares (last 60 days) into the cache.
     */
    private void cacheRecentSpares() {
        LocalDateTime cutoffDate = LocalDateTime.now().minusDays(60);

        try {
            // Fetch spares updated within the last 60 days
            List<Spares> recentSpares = sparesRepository.findByLastUpdatedAfter(cutoffDate);

            // Access the "sparesList" cache
            Cache cache = cacheManager.getCache("sparesList");
            if (cache != null) {
                // Evict old cache data
                cache.evict("recent");

                // Populate the cache with the recent spares
                if (!recentSpares.isEmpty()) {
                    cache.put("recent", recentSpares);
                    logger.info("Cache successfully updated with {} spares from the last 60 days.", recentSpares.size());
                } else {
                    logger.warn("No spares found within the last 60 days. Cache remains empty.");
                }
            } else {
                logger.error("Failed to update cache. CacheManager did not return a valid cache for 'sparesList'.");
            }
        } catch (Exception e) {
            logger.error("Error while updating the cache: {}", e.getMessage(), e);
        }
    }
    
    
    public void onMessage(Message message, byte[] pattern) {
        String materialNo = new String(message.getBody());
        handleCacheUpdate(materialNo);
    }

    public void handleCacheUpdate(String materialNo) {
        try {
            // Fetch the latest data from the database
            Spares spares = sparesRepository.findByMaterialNo(Long.parseLong(materialNo))
                    .orElseThrow(() -> new RuntimeException("Spares not found with Material No: " + materialNo));

            // Update the cache with the new data
            String spareJson = objectMapper.writeValueAsString(spares);
            redisTemplate.opsForValue().set("spares:" + materialNo, spareJson);
            System.out.println("Cache updated for Material No: " + materialNo);
        } catch (JsonProcessingException e) {
            System.err.println("Error serializing spare to cache: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.err.println("Invalid material number format: " + materialNo);
        } catch (RuntimeException e) {
            System.err.println("Error updating cache: " + e.getMessage());
        }
    }
}
