package com.supplier.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.Optional;
import java.util.Set;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.supplier.entity.PurchasingRFQ;
import com.supplier.entity.Vendor;
import com.supplier.exception.ResourceNotFoundException;
import com.supplier.repository.PurchasingRFQRepository;
import com.supplier.repository.VendorRepository;

import jakarta.annotation.PostConstruct;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

import com.supplier.exception.ResourceNotFoundException;
@Service
public class PurchasingRFQService {

	private static final Logger logger = LoggerFactory.getLogger(PurchasingRFQService.class);

	@Autowired
	private VendorRepository vendorRepository;

	@Autowired
	private PurchasingRFQRepository purchasingRFQRepository;
	
	@Qualifier("redisCacheManager")
	@Autowired
    private CacheManager cacheManager;
	private static boolean isCachePreloaded;
	@Autowired
	private ObjectMapper objectMapper;
	@Autowired

	private RedisTemplate<String, String> redisTemplate;
	
	  @PersistenceContext
	    private EntityManager entityManager;
	

	 @PostConstruct
	    public void preloadCache() {
	        try {
	            // Fetch all vendors from the database and load them into the cache
	            List<PurchasingRFQ> purchasingRFQList = purchasingRFQRepository.findAll();
	            for (PurchasingRFQ purchasingRFQ: purchasingRFQList) {
	                // Serialize the Vendor object to JSON string
	                String purchasingRFQJson = objectMapper.writeValueAsString(purchasingRFQ);
	                // Manually put the vendors into Redis cache as a JSON string
	                redisTemplate.opsForValue().set("purchasingRFQ:" + purchasingRFQ.getId(), purchasingRFQJson);
	            }

	            isCachePreloaded = true;
	            logger.info("Cache preloaded with {} purchasingRFQ.", purchasingRFQList.size());
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
	     * Fetch all vendors, return from cache if preloaded, otherwise return from database.
	     */
	    public List<PurchasingRFQ> getAllPurchasingRFQ() {
	        if (isCachePreloaded) {
	            logger.info("Fetching all PurchasingRFQfrom cache.");

	            // Retrieve vendors from Redis using RedisTemplate by scanning keys
	            Set<String> keys = redisTemplate.keys("purchasingRFQ:*");
	            if (keys == null || keys.isEmpty()) {
	                logger.info("No cached vendors found. Returning purchasingRFQfrom the database.");
	                return purchasingRFQRepository.findAll();
	            }

	            List<String> cachedJsonPurchasingRFQ = redisTemplate.opsForValue().multiGet(keys);
	            List<PurchasingRFQ> cachedPurchasingRFQ = new ArrayList<>();

	            if (cachedJsonPurchasingRFQ != null) {
	                for (String purchasingRFQJson : cachedJsonPurchasingRFQ) {
	                    try {
	                        // Deserialize JSON string back to Vendor object
	                        PurchasingRFQ purchasingRFQ= objectMapper.readValue(purchasingRFQJson, PurchasingRFQ.class);
	                        cachedPurchasingRFQ.add(purchasingRFQ);
	                    } catch (JsonProcessingException e) {
	                        logger.error("Error deserializing vendor: {}", e.getMessage(), e);
	                    }
	                }
	            }

	            // Return cached vendors if available
	            if (!cachedPurchasingRFQ.isEmpty()) {
	                logger.info("Returning PurchasingRFQfrom cache.");
	                return cachedPurchasingRFQ;
	            } else {
	                logger.info("Cache is empty. Returning vendors from the database.");
	                return purchasingRFQRepository.findAll();
	            }
	        } else {
	            logger.info("Cache not preloaded. Returning all vendors from the database.");
	            return purchasingRFQRepository.findAll();
	        }
	    }


	    public Optional<PurchasingRFQ> getPurchasingRFQById(Long id) {
	        try {
	            // Check if the PurchasingRFQ is in the cache
	            String purchasingRFQJson = redisTemplate.opsForValue().get("purchasingRFQ:" + id);

	            if (purchasingRFQJson != null) {
	                try {
	                    // Deserialize the cached JSON string to a PurchasingRFQ object
	                    PurchasingRFQ purchasingRFQ = objectMapper.readValue(purchasingRFQJson, PurchasingRFQ.class);
	                    logger.info("PurchasingRFQ with id: {} found in cache.", id);
	                    return Optional.of(purchasingRFQ);
	                } catch (JsonProcessingException e) {
	                    logger.error("Error deserializing PurchasingRFQ from cache: {}", e.getMessage(), e);
	                }
	            }

	            // If not found in cache, fetch from the database
	            Optional<PurchasingRFQ> purchasingRFQFromDatabase = purchasingRFQRepository.findById(id);

	            if (purchasingRFQFromDatabase.isPresent()) {
	                try {
	                    // Serialize the PurchasingRFQ object and cache it for future use
	                    String purchasingRFQJsonToCache = objectMapper.writeValueAsString(purchasingRFQFromDatabase.get());
	                    redisTemplate.opsForValue().set("purchasingRFQ:" + id, purchasingRFQJsonToCache);
	                    logger.info("PurchasingRFQ with id: {} found in database. Added to cache.", id);
	                } catch (JsonProcessingException e) {
	                    logger.error("Error serializing PurchasingRFQ to cache: {}", e.getMessage(), e);
	                }
	            } else {
	                logger.warn("PurchasingRFQ with id: {} not found in the database.", id);
	            }

	            // Return the result from the database (or empty if not found)
	            return purchasingRFQFromDatabase;

	        } catch (Exception e) {
	            logger.error("Error occurred while fetching PurchasingRFQ with id: {}: {}", id, e.getMessage(), e);
	            return Optional.empty();
	        }
	    } 
	 
	
	
	// Cache put method to save PurchasingRFQ to Redis
    @CachePut(value = "purchasingRFQs", key = "#key")
    public PurchasingRFQ savePurchasingRFQToCache(String key, PurchasingRFQ purchasingRFQ) {
        logger.info("Saving PurchasingRFQ with id: {} to cache", purchasingRFQ.getId());
        return purchasingRFQ;  // Spring will automatically save to cache
    }

    // Method to create a new PurchasingRFQ
    public PurchasingRFQ createPurchasingRFQ(PurchasingRFQ purchasingRFQ) {
        PurchasingRFQ savedPurchasingRFQ = purchasingRFQRepository.save(purchasingRFQ);
        logger.info("Saved PurchasingRFQ to DB: {}", savedPurchasingRFQ);
        
        // Preload the newly created PurchasingRFQ into Redis cache
        String key = "purchasingRFQ::" + savedPurchasingRFQ.getId();
        savePurchasingRFQToCache(key, savedPurchasingRFQ);
        
        return savedPurchasingRFQ;
    }

   

  
    // Cache put method for updating PurchasingRFQ and syncing cache
    @CachePut(value = "purchasingRFQs", key = "#purchasingRFQ.id")
    public PurchasingRFQ updatePurchasingRFQ(PurchasingRFQ purchasingRFQ) {
        logger.info("Updating PurchasingRFQ: {}", purchasingRFQ);
        
        // Update the PurchasingRFQ in the database
        PurchasingRFQ updatedPurchasingRFQ = purchasingRFQRepository.save(purchasingRFQ);
        
        // Update the cache with the updated PurchasingRFQ
        String key = "purchasingRFQ::" + updatedPurchasingRFQ.getId();
        savePurchasingRFQToCache(key, updatedPurchasingRFQ);
        
        return updatedPurchasingRFQ;
    }

    @Transactional
    public void deletePurchasingRFQ(Long id) {
        logger.info("Attempting to delete PurchasingRFQ with id: {}", id);
        try {
            // Check if the entity exists in the database
            Optional<PurchasingRFQ> purchasingRFQOptional = purchasingRFQRepository.findById(id);

            if (purchasingRFQOptional.isPresent()) {
                // Delete the entity from the database
                purchasingRFQRepository.deleteById(id);
                logger.info("Deleted PurchasingRFQ with id: {} from the database.", id);

                // Remove the entity from the Redis cache
                String cacheKey = "purchasingRFQ:" + id;
                redisTemplate.delete(cacheKey);
                logger.info("Deleted PurchasingRFQ with id: {} from the cache.", id);
            } else {
                logger.warn("PurchasingRFQ with id: {} not found in the database.", id);
            }
        } catch (Exception e) {
            logger.error("Error occurred while deleting PurchasingRFQ with id: {}: {}", id, e.getMessage(), e);
            throw new RuntimeException("Failed to delete PurchasingRFQ with id: " + id, e);
        }
    }

}
