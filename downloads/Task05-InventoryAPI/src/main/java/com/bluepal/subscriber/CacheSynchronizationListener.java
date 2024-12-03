//package com.bluepal.subscriber;
//
//import java.util.List;
//
//import org.apache.logging.log4j.message.Message;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.cache.CacheManager;
//import org.springframework.data.redis.connection.MessageListener;
//import org.springframework.stereotype.Component;
//
//import com.bluepal.entity.Spares;
//import com.bluepal.repository.SparesRepository;
//
//@Component
//public class CacheSynchronizationListener implements MessageListener {
//
//	private static final Logger logger = LoggerFactory.getLogger(CacheSynchronizationListener.class);
//	
//    @Autowired
//    private CacheManager cacheManager; // To interact with the cache
//
//    @Autowired
//    private SparesRepository sparesRepository; // Assuming this repository is used to preload the cache
//
//    public void onMessage(Message message, byte[] pattern) {
//        String messageBody = (String) ((Object) message).getBody();
//        logger.info("Received cache synchronization event: {}", messageBody);
//
//        // Check the content of the message, and trigger appropriate cache sync logic
//        if ("Cache Updated".equals(messageBody)) {
//            // Cache update event, clear cache and reload from DB
//            cacheManager.getCache("spares").clear(); // Example: Clear the cache
//            logger.info("Cache cleared due to synchronization event.");
//
//            // Alternatively, reload the cache with fresh data
//            preloadCache(); // A method to reload cache from the database
//        }
//    }
//
//    public void preloadCache() {
//        // Fetch all spares from the database and load them into the cache
//        List<Spares> sparesList = sparesRepository.findAll();
//        sparesList.forEach(spare -> cacheManager.getCache("spares").put(spare.getMaterialNo(), spare));
//        logger.info("Cache reloaded from database after synchronization.");
//    }
//}
//
//
//
//
//
////@Component
////public class CacheSynchronizationListener implements MessageListener {
////	
////    private static final Logger logger = LoggerFactory.getLogger(CacheSynchronizationListener.class);
////
////    @Autowired
////    private CacheManager cacheManager; // CacheManager to interact with the cache
////    @Autowired
////    private SparesRepository repository;
////
////    @Override
////	public void onMessage(org.springframework.data.redis.connection.Message message, byte[] pattern) {
////        String messageBody = (String) message.getBody();
////        logger.info("Received cache synchronization event: {}", messageBody);
////        
////        if ("Cache Updated".equals(messageBody)) {
////            // Implement cache refresh logic (e.g., refresh or clear the cache)
////            cacheManager.getCache("spares").clear(); // Example: Clearing the cache
////            logger.info("Cache cleared due to synchronization event.");
////            
////            // Alternatively, you can refresh the cache by fetching the latest data from the database
////            preloadCache(); // A method to preload the cache from the database
////        }
////    }
////
////    // Method to preload cache (fetch from DB and populate cache)
////    public void preloadCache() {
////        // Fetch data from the database and reload into the cache
////        List<Spares> sparesList = repository.findAll();
////        sparesList.forEach(spare -> cacheManager.getCache("spares").put(spare.getMaterialNo(), spare));
////        logger.info("Cache reloaded from database after synchronization.");
////    }
////
////	
////}
