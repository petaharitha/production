package com.bluepal.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Component;

@Component
public class CacheSubscriber {

    @Autowired
    private CacheManager cacheManager;

    // Handle cache invalidation message
    public void handleCacheSync(String cacheUpdateMessage) {
        System.out.println("Received cache invalidation message for: " + cacheUpdateMessage);

        try {
            // Split the message into action, cacheName, and cacheKey
            String[] parts = cacheUpdateMessage.split("::");
            String action = parts[0];  // Cache action (e.g., CACHE_DELETE or CACHE_UPDATE)
            String cacheName = parts[1];  // Cache name (e.g., sparesCache)
            String cacheKey = (parts.length > 2) ? parts[2] : null;  // Cache key

            // Eviction handling for DELETE action
            if ("CACHE_DELETE".equals(action) && cacheKey != null) {
                Cache cache = cacheManager.getCache(cacheName);
                if (cache != null) {
                    cache.evict(cacheKey);  // Evict specific cache entry
                    System.out.println("Evicted cache for key: " + cacheKey);
                } else {
                    System.err.println("Cache not found: " + cacheName);
                }
            }

            // Additional actions like CACHE_UPDATE could be handled here if necessary
            if ("CACHE_UPDATE".equals(action) && cacheKey != null) {
                // Handle the update action if needed (could be used for more advanced cache handling)
                Cache cache = cacheManager.getCache(cacheName);
                if (cache != null) {
                    // You can also add logic here to update cache or perform other tasks
                    System.out.println("Cache update handled for key: " + cacheKey);
                }
            }
        } catch (Exception e) {
            System.err.println("Error handling cache synchronization: " + e.getMessage());
        }
    }
}
