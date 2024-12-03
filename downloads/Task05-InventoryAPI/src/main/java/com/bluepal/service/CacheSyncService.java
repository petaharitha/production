package com.bluepal.service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class CacheSyncService {

    private static final Logger logger = LoggerFactory.getLogger(CacheSyncService.class);

    //@RedisListener(topic = "sparesCache")
    public void handleCacheUpdate(String materialNo) {
        logger.info("Cache update event received for Material No: {}", materialNo);

        try {
            // Your logic to fetch data and update the cache
        } catch (Exception e) {
            logger.error("Error processing cache update: {}", e.getMessage(), e);
        }
    }
}
