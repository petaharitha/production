
package com.supplier.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.supplier.entity.PurchasingRFQ;
import com.supplier.entity.Vendor;
import com.supplier.repository.PurchasingRFQRepository;
import com.supplier.repository.VendorRepository;

import jakarta.annotation.PostConstruct;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;


//@Service
//
//public class SupplierService {
//
//	private static final Logger logger = LoggerFactory.getLogger(SupplierService.class);
//    
//	@Autowired
//
//	private RedisTemplate<String, String> redisTemplate; // Inject RedisTemplate for publishing
//	
//	@Autowired
//
//	private ObjectMapper objectMapper; // Jackson ObjectMapper for serializing and deserializing
//	
//	
//
//	@Qualifier("redisCacheManager")
//	@Autowired
//    private CacheManager cacheManager;
//	
//	private static boolean isCachePreloaded;
//    @Autowired
//    private VendorRepository vendorRepository;
//
//    @PostConstruct
//    public void preloadCache() {
//        try {
//            // Fetch all vendors from the database and load them into the cache
//            List<Vendor> vendorList = vendorRepository.findAll();
//            for (Vendor vendor : vendorList) {
//                // Serialize the Vendor object to JSON string
//                String vendorJson = objectMapper.writeValueAsString(vendor);
//                // Manually put the vendors into Redis cache as a JSON string
//                redisTemplate.opsForValue().set("vendor:" + vendor.getId(), vendorJson);
//            }
//
//            isCachePreloaded = true;
//            logger.info("Cache preloaded with {} vendors.", vendorList.size());
//        } catch (Exception e) {
//            logger.error("Error preloading cache from database: {}", e.getMessage(), e);
//        }
//    }
//
//    /**
//     * Method to check if the cache is preloaded.
//     */
//    public boolean isCachePreloaded() {
//        return isCachePreloaded;
//    }
//
//    /**
//     * Fetch all vendors, return from cache if preloaded, otherwise return from database.
//     */
//    public List<Vendor> getAllVendors() {
//        if (isCachePreloaded) {
//            logger.info("Fetching all vendors from cache.");
//
//            // Retrieve vendors from Redis using RedisTemplate by scanning keys
//            Set<String> keys = redisTemplate.keys("vendor:*");
//            if (keys == null || keys.isEmpty()) {
//                logger.info("No cached vendors found. Returning vendors from the database.");
//                return vendorRepository.findAll();
//            }
//
//            List<String> cachedJsonVendors = redisTemplate.opsForValue().multiGet(keys);
//            List<Vendor> cachedVendors = new ArrayList<>();
//
//            if (cachedJsonVendors != null) {
//                for (String vendorJson : cachedJsonVendors) {
//                    try {
//                        // Deserialize JSON string back to Vendor object
//                        Vendor vendor = objectMapper.readValue(vendorJson, Vendor.class);
//                        cachedVendors.add(vendor);
//                    } catch (JsonProcessingException e) {
//                        logger.error("Error deserializing vendor: {}", e.getMessage(), e);
//                    }
//                }
//            }
//
//            // Return cached vendors if available
//            if (!cachedVendors.isEmpty()) {
//                logger.info("Returning vendors from cache.");
//                return cachedVendors;
//            } else {
//                logger.info("Cache is empty. Returning vendors from the database.");
//                return vendorRepository.findAll();
//            }
//        } else {
//            logger.info("Cache not preloaded. Returning all vendors from the database.");
//            return vendorRepository.findAll();
//        }
//    }
//
//        
// 
//    @PostConstruct
//    public void preloadCache() {
//        try {
//            // Fetch all vendors from the database and load them into the cache
//            List<Vendor> vendorList = vendorRepository.findAll();
//            for (Vendor vendor : vendorList) {
//                // Serialize the Vendor object to JSON string
//                String vendorJson = objectMapper.writeValueAsString(vendor);
//                // Manually put the vendors into Redis cache as a JSON string
//                redisTemplate.opsForValue().set("vendor:" + vendor.getId(), vendorJson);
//            }
//
//            isCachePreloaded = true;
//            logger.info("Cache preloaded with {} vendors.", vendorList.size());
//        } catch (Exception e) {
//            logger.error("Error preloading cache from database: {}", e.getMessage(), e);
//        }
//    }
//
//    /**
//     * Method to check if the cache is preloaded.
//     */
//    public boolean isCachePreloaded() {
//        return isCachePreloaded;
//    }
//
//    
//       
//  // Method to delete a supplier and evict from the cache
//  @CacheEvict(value = "supplier", key = "#id")
//  public void deleteSupplier(Long id) {
//      logger.info("Deleting supplier with id: {}", id);
//      vendorRepository.deleteById(id);
//      // Ensure that the cache is also cleared when a supplier is deleted
//      String key = "supplier::" + id;
//      
//  }
//    
////Method to update a supplier and synchronize the cache
//@CacheEvict(value = "suppliers", key = "#id", beforeInvocation = true) // Ensure eviction happens before the method is executed
//@CachePut(value = "suppliers", key = "#id") // Update the cache with the new supplier data
//public Vendor updateSupplier1(Long id, Vendor vendorDetails) {
//   // Retrieve the supplier from the database
//   Vendor vendor = vendorRepository.findById(id)
//           .orElseThrow(() -> new RuntimeException("Supplier not found"));
//
//   // Update vendor fields
//   vendor.setVendorIdNo(vendorDetails.getVendorIdNo());
//   vendor.setVendorName(vendorDetails.getVendorName());
//   vendor.setAddressLine1(vendorDetails.getAddressLine1());
//   vendor.setAddressLine2(vendorDetails.getAddressLine2());
//   vendor.setAddressLine3(vendorDetails.getAddressLine3());
//   vendor.setCity(vendorDetails.getCity());
//   vendor.setDistrict(vendorDetails.getDistrict());
//   vendor.setState(vendorDetails.getState());
//   vendor.setCountry(vendorDetails.getCountry());
//   vendor.setPinCode(vendorDetails.getPinCode());
//   vendor.setPanNumber(vendorDetails.getPanNumber());
//   vendor.setCinNumber(vendorDetails.getCinNumber());
//   vendor.setGstNumber(vendorDetails.getGstNumber());
//   vendor.setMsmE(vendorDetails.getMsmE());
//   vendor.setSpareField(vendorDetails.getSpareField());
//
//   vendor.setSalesRepFirstName(vendorDetails.getSalesRepFirstName());
//   vendor.setSalesRepMiddleName(vendorDetails.getSalesRepMiddleName());
//   vendor.setSalesRepLastName(vendorDetails.getSalesRepLastName());
//   vendor.setSalesRepMobileNo1(vendorDetails.getSalesRepMobileNo1());
//   vendor.setSalesMobileNo2(vendorDetails.getSalesMobileNo2());
//   vendor.setSalesRepEmail1(vendorDetails.getSalesRepEmail1());
//   vendor.setSalesRepEmail2(vendorDetails.getSalesRepEmail2());
//
//   vendor.setAccountsRepFirstName(vendorDetails.getAccountsRepFirstName());
//   vendor.setAccountsRepMiddleName(vendorDetails.getAccountsRepMiddleName());
//   vendor.setAccountsRepLastName(vendorDetails.getAccountsRepLastName());
//   vendor.setAccountsRepMobileNo1(vendorDetails.getAccountsRepMobileNo1());
//   vendor.setAccountsMobileNo2(vendorDetails.getAccountsMobileNo2());
//   vendor.setAccountsRepEmail1(vendorDetails.getAccountsRepEmail1());
//   vendor.setAccountsRepEmail2(vendorDetails.getAccountsRepEmail2());
//
//   vendor.setLogisticsRepFirstName(vendorDetails.getLogisticsRepFirstName());
//   vendor.setLogisticsRepMiddleName(vendorDetails.getLogisticsRepMiddleName());
//   vendor.setLogisticsRepLastName(vendorDetails.getLogisticsRepLastName());
//   vendor.setLogisticsRepMobileNo1(vendorDetails.getLogisticsRepMobileNo1());
//   vendor.setLogisticsMobileNo2(vendorDetails.getLogisticsMobileNo2());
//   vendor.setLogisticsRepEmail1(vendorDetails.getLogisticsRepEmail1());
//   vendor.setLogisticsRepEmail2(vendorDetails.getLogisticsRepEmail2());
//
//   // Update audit fields
//   vendor.setUpdatedBy(vendorDetails.getUpdatedBy());
//
//   // Save the updated vendor to the database
//   Vendor updatedVendor = vendorRepository.save(vendor);
//
//   // Update Redis cache
//   try {
//       String vendorJson = objectMapper.writeValueAsString(updatedVendor);
//       redisTemplate.opsForValue().set("vendor:" + id, vendorJson);
//       logger.info("Updated vendor with id: {} synchronized with cache.", id);
//   } catch (JsonProcessingException e) {
//       logger.error("Error serializing updated vendor to cache: {}", e.getMessage());
//   }
//
//   // Publish cache update
//   try {
//       cachePublisher cachePublisher = new cachePublisher();
//	cachePublisher.publishCacheUpdate("CACHE_UPDATE", "vendorCache", String.valueOf(updatedVendor.getId()));
//       logger.info("Published cache update for vendor with id: {}", id);
//   } catch (Exception e) {
//       logger.error("Error publishing cache update for vendor with id: {}", id, e);
//   }
//
//   // Return the updated vendor
//   return updatedVendor;
//}
//
//
//}
@Service
public class SupplierService {

    private static final Logger logger = LoggerFactory.getLogger(SupplierService.class);

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @Autowired
    private ObjectMapper objectMapper;

    @Qualifier("redisCacheManager")
    @Autowired
    private CacheManager cacheManager;

    @Autowired
    private VendorRepository vendorRepository;

    private static boolean isCachePreloaded;
    
    @Autowired
    private PurchasingRFQRepository purchaseRFQRepository;
 

    @Autowired
    private EntityManager entityManager;
    @Autowired
    private CacheService cacheService;
    @Autowired

    @PostConstruct
    public void preloadCache() {
        try {
            // Fetch all vendors from the database and load them into the cache
            List<Vendor> vendorList = vendorRepository.findAll();
            for (Vendor vendor : vendorList) {
                // Serialize the Vendor object to JSON string
                String vendorJson = objectMapper.writeValueAsString(vendor);
                // Manually put the vendors into Redis cache as a JSON string
                redisTemplate.opsForValue().set("vendor:" + vendor.getId(), vendorJson);
            }
            isCachePreloaded = true;
            logger.info("Cache preloaded with {} vendors.", vendorList.size());
        } catch (Exception e) {
            logger.error("Error preloading cache from database: {}", e.getMessage(), e);
        }
    }

    public boolean isCachePreloaded() {
        return isCachePreloaded;
    }

    public List<Vendor> getAllVendors() {
        if (isCachePreloaded) {
            logger.info("Fetching all vendors from cache.");

            // Retrieve vendors from Redis using RedisTemplate by scanning keys
            Set<String> keys = redisTemplate.keys("vendor:*");
            if (keys == null || keys.isEmpty()) {
                logger.info("No cached vendors found. Returning vendors from the database.");
                return vendorRepository.findAll();
            }

            List<String> cachedJsonVendors = redisTemplate.opsForValue().multiGet(keys);
            List<Vendor> cachedVendors = new ArrayList<>();

            if (cachedJsonVendors != null) {
                for (String vendorJson : cachedJsonVendors) {
                    try {
                        // Deserialize JSON string back to Vendor object
                        Vendor vendor = objectMapper.readValue(vendorJson, Vendor.class);
                        cachedVendors.add(vendor);
                    } catch (JsonProcessingException e) {
                        logger.error("Error deserializing vendor: {}", e.getMessage(), e);
                    }
                }
            }

            // Return cached vendors if available
            if (!cachedVendors.isEmpty()) {
                logger.info("Returning vendors from cache.");
                return cachedVendors;
            } else {
                logger.info("Cache is empty. Returning vendors from the database.");
                return vendorRepository.findAll();
            }
        } else {
            logger.info("Cache not preloaded. Returning all vendors from the database.");
            return vendorRepository.findAll();
        }
    }

    // Method to create a new supplier and add it to the cache
    @CachePut(value = "vendorCache", key = "#vendor.id")
    public Vendor createSupplier(Vendor vendor) {
        // Log the creation of a new vendor
        logger.info("Creating a new supplier: {}", vendor);

        // Save the vendor object to the database
        Vendor savedVendor = vendorRepository.save(vendor);

        // Log the successful saving of the vendor to the database
        logger.info("Vendor with ID: {} saved to the database.", savedVendor.getId());

        // Return the saved vendor, Spring will handle saving it to the cache
        return savedVendor;
    }

//    // Method to update a supplier and synchronize the cache
//    @CacheEvict(value = "vendorCache", key = "#id", beforeInvocation = true) // Evict before execution
//    @CachePut(value = "vendorCache", key = "#id") // Update the cache with the new vendor data
//    public Vendor updateSupplier(Long id, Vendor vendorDetails) {
//        // Retrieve the supplier from the database
//        Vendor vendor = vendorRepository.findById(id)
//                .orElseThrow(() -> new RuntimeException("Supplier not found"));
//
//        // Update vendor fields
//        vendor.setVendorIdNo(vendorDetails.getVendorIdNo());
//        vendor.setVendorName(vendorDetails.getVendorName());
//        vendor.setAddressLine1(vendorDetails.getAddressLine1());
//        vendor.setAddressLine2(vendorDetails.getAddressLine2());
//        vendor.setAddressLine3(vendorDetails.getAddressLine3());
//        vendor.setCity(vendorDetails.getCity());
//        vendor.setDistrict(vendorDetails.getDistrict());
//        vendor.setState(vendorDetails.getState());
//        vendor.setCountry(vendorDetails.getCountry());
//        vendor.setPinCode(vendorDetails.getPinCode());
//        vendor.setPanNumber(vendorDetails.getPanNumber());
//        vendor.setCinNumber(vendorDetails.getCinNumber());
//        vendor.setGstNumber(vendorDetails.getGstNumber());
//        vendor.setMsmE(vendorDetails.getMsmE());
//        vendor.setSpareField(vendorDetails.getSpareField());
//        vendor.setSalesRepFirstName(vendorDetails.getSalesRepFirstName());
//        vendor.setSalesRepMiddleName(vendorDetails.getSalesRepMiddleName());
//        vendor.setSalesRepLastName(vendorDetails.getSalesRepLastName());
//        vendor.setSalesRepMobileNo1(vendorDetails.getSalesRepMobileNo1());
//        vendor.setSalesMobileNo2(vendorDetails.getSalesMobileNo2());
//        vendor.setSalesRepEmail1(vendorDetails.getSalesRepEmail1());
//        vendor.setSalesRepEmail2(vendorDetails.getSalesRepEmail2());
//        vendor.setAccountsRepFirstName(vendorDetails.getAccountsRepFirstName());
//        vendor.setAccountsRepMiddleName(vendorDetails.getAccountsRepMiddleName());
//        vendor.setAccountsRepLastName(vendorDetails.getAccountsRepLastName());
//        vendor.setAccountsRepMobileNo1(vendorDetails.getAccountsRepMobileNo1());
//        vendor.setAccountsMobileNo2(vendorDetails.getAccountsMobileNo2());
//        vendor.setAccountsRepEmail1(vendorDetails.getAccountsRepEmail1());
//        vendor.setAccountsRepEmail2(vendorDetails.getAccountsRepEmail2());
//        vendor.setLogisticsRepFirstName(vendorDetails.getLogisticsRepFirstName());
//        vendor.setLogisticsRepMiddleName(vendorDetails.getLogisticsRepMiddleName());
//        vendor.setLogisticsRepLastName(vendorDetails.getLogisticsRepLastName());
//        vendor.setLogisticsRepMobileNo1(vendorDetails.getLogisticsRepMobileNo1());
//        vendor.setLogisticsMobileNo2(vendorDetails.getLogisticsMobileNo2());
//        vendor.setLogisticsRepEmail1(vendorDetails.getLogisticsRepEmail1());
//        vendor.setLogisticsRepEmail2(vendorDetails.getLogisticsRepEmail2());
//
//        // Update audit fields
//        vendor.setUpdatedBy(vendorDetails.getUpdatedBy());
//
//        // Save the updated vendor to the database
//        Vendor updatedVendor = vendorRepository.save(vendor);
//        	
//     // Update the related PurchaseRFQ in cache as well
//        updatePurchaseRFQsCache(updatedVendor);
//        // Return the updated vendor
//        return updatedVendor;
//    }
//    	
//    
//    // Manually update PurchaseRFQ cache when Vendor changes
//    private void updatePurchaseRFQsCache(Vendor updatedVendor) {
//    	// Convert Set<PurchaseRFQ> to List<PurchaseRFQ>
//    	Set<PurchasingRFQ> purchaseRFQs = new CopyOnWriteArraySet<>(updatedVendor.getPurchaseRFQs());
//
//        for (PurchasingRFQ rfq : purchaseRFQs) {
//            try {
//                // Serialize the PurchaseRFQ object to a JSON string
//                String rfqJson = objectMapper.writeValueAsString(rfq);
//                redisTemplate.opsForValue().set("purchaseRFQ:" + rfq.getId(), rfqJson); // Store the JSON string in Redis
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        }
//    }
 // Method to update a supplier and synchronize the cache
//    @CacheEvict(value = "vendorCache", key = "#id", beforeInvocation = true) // Evict before execution
//    @CachePut(value = "vendorCache", key = "#id") // Update the cache with the new vendor data
//    public Vendor updateSupplier(Long id, Vendor vendorDetails) {
//        // Retrieve the supplier from the database
//        Vendor vendor = vendorRepository.findById(id)
//                .orElseThrow(() -> new RuntimeException("Supplier not found"));
//
//        // Directly update the vendor without using setters
//        vendor = updateVendorFields(vendor, vendorDetails);
//
//        // Save the updated vendor to the database
//        Vendor updatedVendor = vendorRepository.save(vendor);
//
//        // Manually refresh related PurchaseRFQs in the repository
//        updatePurchaseRFQsRepository(updatedVendor);
//
//        // Return the updated vendor
//        return updatedVendor;
//    }
//
//    // Helper method to update the vendor fields without using setters
//    private Vendor updateVendorFields(Vendor vendor, Vendor vendorDetails) {
//        // Directly update fields (avoiding setter methods)
//        vendor.setVendorIdNo(vendorDetails.getVendorIdNo());
//        vendor.setVendorName(vendorDetails.getVendorName());
//        vendor.setAddressLine1(vendorDetails.getAddressLine1());
//        vendor.setAddressLine2(vendorDetails.getAddressLine2());
//        vendor.setAddressLine3(vendorDetails.getAddressLine3());
//        vendor.setCity(vendorDetails.getCity());
//        vendor.setDistrict(vendorDetails.getDistrict());
//        vendor.setState(vendorDetails.getState());
//        vendor.setCountry(vendorDetails.getCountry());
//        vendor.setPinCode(vendorDetails.getPinCode());
//        vendor.setPanNumber(vendorDetails.getPanNumber());
//        vendor.setCinNumber(vendorDetails.getCinNumber());
//        vendor.setGstNumber(vendorDetails.getGstNumber());
//        vendor.setMsmE(vendorDetails.getMsmE());
//        vendor.setSpareField(vendorDetails.getSpareField());
//        vendor.setSalesRepFirstName(vendorDetails.getSalesRepFirstName());
//        vendor.setSalesRepMiddleName(vendorDetails.getSalesRepMiddleName());
//        vendor.setSalesRepLastName(vendorDetails.getSalesRepLastName());
//        vendor.setSalesRepMobileNo1(vendorDetails.getSalesRepMobileNo1());
//        vendor.setSalesMobileNo2(vendorDetails.getSalesMobileNo2());
//        vendor.setSalesRepEmail1(vendorDetails.getSalesRepEmail1());
//        vendor.setSalesRepEmail2(vendorDetails.getSalesRepEmail2());
//        vendor.setAccountsRepFirstName(vendorDetails.getAccountsRepFirstName());
//        vendor.setAccountsRepMiddleName(vendorDetails.getAccountsRepMiddleName());
//        vendor.setAccountsRepLastName(vendorDetails.getAccountsRepLastName());
//        vendor.setAccountsRepMobileNo1(vendorDetails.getAccountsRepMobileNo1());
//        vendor.setAccountsMobileNo2(vendorDetails.getAccountsMobileNo2());
//        vendor.setAccountsRepEmail1(vendorDetails.getAccountsRepEmail1());
//        vendor.setAccountsRepEmail2(vendorDetails.getAccountsRepEmail2());
//        vendor.setLogisticsRepFirstName(vendorDetails.getLogisticsRepFirstName());
//        vendor.setLogisticsRepMiddleName(vendorDetails.getLogisticsRepMiddleName());
//        vendor.setLogisticsRepLastName(vendorDetails.getLogisticsRepLastName());
//        vendor.setLogisticsRepMobileNo1(vendorDetails.getLogisticsRepMobileNo1());
//        vendor.setLogisticsMobileNo2(vendorDetails.getLogisticsMobileNo2());
//        vendor.setLogisticsRepEmail1(vendorDetails.getLogisticsRepEmail1());
//        vendor.setLogisticsRepEmail2(vendorDetails.getLogisticsRepEmail2());
//        vendor.setUpdatedBy(vendorDetails.getUpdatedBy());  // Update audit fields
//
//        return vendor;
//    }
//
//    // Method to refresh PurchaseRFQ repository based on updated vendor
//    private void updatePurchaseRFQsRepository(Vendor updatedVendor) {
//        // Fetch all PurchaseRFQs related to the vendor directly from the repository
//        List<PurchasingRFQ> purchaseRFQs = purchaseRFQRepository.findByVendor(updatedVendor);
//
//        // Iterate through each PurchaseRFQ and update the cache or perform any necessary updates
//        for (PurchasingRFQ rfq : purchaseRFQs) {
//            // Update the RFQ repository or perform necessary cache operations
//        	purchaseRFQRepository.save(rfq);  // Refresh the RFQ in the repository
//        }
//    }

    
//    // Method to delete a supplier and evict from the cache
//    @CacheEvict(value = "vendorCache", key = "#id")
//    public void deleteP(Long id) {
//        logger.info("Deleting supplier with id: {}", id);
//        vendorRepository.deleteById(id);
//    }
//    
// // Method to fetch a supplier by ID, with caching mechanism
//    @Cacheable(value = "vendorCache", key = "#id")
//    public Vendor getSupplierById(Long id) {
//        logger.info("Fetching supplier with ID: {}", id);
//
//        // Retrieve the supplier from the database
//        return vendorRepository.findById(id)
//                .orElseThrow(() -> new RuntimeException("Supplier not found"));
//    }
    @Transactional
    public void deleteVendor(Long vendorId) {
        logger.info("Deleting vendor with ID: {}", vendorId);
        vendorRepository.deleteById(vendorId); // JPA will handle associated RFQs
    }

    /**
     * Updates a Vendor and refreshes the associated PurchasingRFQ entity,
     * while ensuring changes are updated in the Redis cache.
     *
     * @param vendorId          ID of the Vendor to update
     * @param updatedVendorData Updated Vendor details
     * @param purchasingRFQId   ID of the associated PurchasingRFQ
     */
    

    @Transactional
    public void updateVendorAndRefreshRFQ(Long vendorId, Vendor updatedVendorData, Long purchasingRFQId) {
        Logger log = LoggerFactory.getLogger(getClass());
        
        // Step 1: Update Vendor
        Vendor vendor = vendorRepository.findById(vendorId)
                .orElseThrow(() -> new EntityNotFoundException("Vendor not found"));

        log.info("Found existing vendor with ID: {}", vendorId);

        vendor = updateVendorFields(vendor, updatedVendorData);
        vendorRepository.save(vendor);

        log.info("Updated vendor details: {}", vendor);

        // Step 2: Fetch and refresh PurchasingRFQ with the custom repository method
        PurchasingRFQ purchasingRFQ = purchaseRFQRepository.findByIdWithVendor(purchasingRFQId)
                .orElseThrow(() -> new EntityNotFoundException("PurchasingRFQ not found"));

        log.info("Found existing PurchasingRFQ with ID: {}", purchasingRFQId);

        // Ensure synchronization
        entityManager.refresh(vendor);
        entityManager.refresh(purchasingRFQ);

        log.info("Synchronized vendor and PurchasingRFQ with the database");

        // Step 3: Update Cache
        refreshCache(vendorId, vendor, purchasingRFQId, purchasingRFQ);
        log.info("Cache refreshed for vendor ID: {} and PurchasingRFQ ID: {}", vendorId, purchasingRFQId);

        log.info("New vendor details in cache: {}", vendor);
        log.info("New PurchasingRFQ details in cache: {}", purchasingRFQ);
    }

    // Helper method to refresh cache
    private void refreshCache(Long vendorId, Vendor vendor, Long purchasingRFQId, PurchasingRFQ purchasingRFQ) {
        Logger log = LoggerFactory.getLogger(getClass());

        // Example: Use a custom cache service to refresh the cache
        cacheService.invalidateVendorCache(vendorId);
        cacheService.invalidateRFQCache(purchasingRFQId);

        log.info("Invalidated cache for vendor ID: {} and PurchasingRFQ ID: {}", vendorId, purchasingRFQId);

        cacheService.putVendor(vendorId, vendor);
        cacheService.putRFQ(purchasingRFQId, purchasingRFQ);

        log.info("Updated cache with new vendor details: {}", vendor);
        log.info("Updated cache with new PurchasingRFQ details: {}", purchasingRFQ);
    }



    /**
     * Repository method to fetch PurchasingRFQ with Vendor eagerly.
     */
    

    /**
     * Helper method to update Vendor fields.
     */
    
//    private Vendor updateVendorFields(Vendor vendor, Vendor vendorDetails) {
//        // Directly update all vendor fields
//        vendor.setVendorIdNo(vendorDetails.getVendorIdNo());
//        vendor.setVendorName(vendorDetails.getVendorName());
//        vendor.setAddressLine1(vendorDetails.getAddressLine1());
//        vendor.setAddressLine2(vendorDetails.getAddressLine2());
//        vendor.setAddressLine3(vendorDetails.getAddressLine3());
//        vendor.setCity(vendorDetails.getCity());
//        vendor.setDistrict(vendorDetails.getDistrict());
//        vendor.setState(vendorDetails.getState());
//        vendor.setCountry(vendorDetails.getCountry());
//        vendor.setPinCode(vendorDetails.getPinCode());
//        vendor.setPanNumber(vendorDetails.getPanNumber());
//        vendor.setCinNumber(vendorDetails.getCinNumber());
//        vendor.setGstNumber(vendorDetails.getGstNumber());
//        vendor.setMsmE(vendorDetails.getMsmE());
//        vendor.setUpdatedBy(vendorDetails.getUpdatedBy()); // Audit field
//
//        return vendor;
//    }
//
//    /**
//     * Updates the Redis cache for Vendor and PurchasingRFQ entities.
//     */
//    private void updateCache(Long vendorId, Vendor vendor, Long purchasingRFQId, PurchasingRFQ purchasingRFQ) {
//        try {
//            // Convert objects to JSON strings
//            String vendorJson = objectMapper.writeValueAsString(vendor);
//            String purchasingRFQJson = objectMapper.writeValueAsString(purchasingRFQ);
//
//            // Evict old cache entries
//            redisTemplate.delete("Vendor:" + vendorId);
//            redisTemplate.delete("PurchasingRFQ:" + purchasingRFQId);
//
//            // Add updated entities back to cache
//            redisTemplate.opsForValue().set("Vendor:" + vendorId, vendorJson);
//            redisTemplate.opsForValue().set("PurchasingRFQ:" + purchasingRFQId, purchasingRFQJson);
//        } catch (JsonProcessingException e) {
//            throw new RuntimeException("Error serializing objects to JSON", e);
//        }
//    }
    
    @Transactional
    public void updateVendorAndRefreshRFQ(Long vendorId, Vendor updatedVendorData) {
        // Step 1: Update the Vendor
        Vendor vendor = vendorRepository.findById(vendorId)
                .orElseThrow(() -> new EntityNotFoundException("Vendor not found"));

        // Update the vendor fields using a helper method or directly
        vendor = updateVendorFields(vendor, updatedVendorData);
        vendorRepository.save(vendor);

        // Step 2: Find all PurchasingRFQ entries associated with this vendor
        List<PurchasingRFQ> purchasingRFQs = purchaseRFQRepository.findAllByVendorId(vendorId);
        if (purchasingRFQs.isEmpty()) {
            throw new EntityNotFoundException("No PurchasingRFQ entries found for the given Vendor ID");
        }

        // Step 3: Refresh the cache for each PurchasingRFQ
        for (PurchasingRFQ purchasingRFQ : purchasingRFQs) {
            // Refresh the cache for this PurchasingRFQ entry
            updateCache(vendorId, vendor, purchasingRFQ.getId(), purchasingRFQ);
        }
    }

    // Helper method to update the cache with new data
    private void updateCache(Long vendorId, Vendor vendor, Long purchasingRFQId, PurchasingRFQ purchasingRFQ) {
        try {
            // Convert objects to JSON strings
            String vendorJson = objectMapper.writeValueAsString(vendor);
            String purchasingRFQJson = objectMapper.writeValueAsString(purchasingRFQ);

            // Evict old cache entries and set new ones
            redisTemplate.delete("Vendor:" + vendorId);
            redisTemplate.delete("PurchasingRFQ:" + purchasingRFQId);

            // Add updated entities back to cache
            redisTemplate.opsForValue().set("Vendor:" + vendorId, vendorJson);
            redisTemplate.opsForValue().set("PurchasingRFQ:" + purchasingRFQId, purchasingRFQJson);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Error serializing objects to JSON", e);
        }
    }
    private Vendor updateVendorFields(Vendor vendor, Vendor vendorDetails) {
        vendor.setVendorIdNo(vendorDetails.getVendorIdNo());
        vendor.setVendorName(vendorDetails.getVendorName());
        vendor.setAddressLine1(vendorDetails.getAddressLine1());
        vendor.setAddressLine2(vendorDetails.getAddressLine2());
        vendor.setAddressLine3(vendorDetails.getAddressLine3());
        vendor.setCity(vendorDetails.getCity());
        vendor.setDistrict(vendorDetails.getDistrict());
        vendor.setState(vendorDetails.getState());
        vendor.setCountry(vendorDetails.getCountry());
        vendor.setPinCode(vendorDetails.getPinCode());
        vendor.setPanNumber(vendorDetails.getPanNumber());
        vendor.setCinNumber(vendorDetails.getCinNumber());
        vendor.setGstNumber(vendorDetails.getGstNumber());
        vendor.setMsmE(vendorDetails.getMsmE());
        vendor.setUpdatedBy(vendorDetails.getUpdatedBy()); // Audit field

        return vendor;
    }


}


 
