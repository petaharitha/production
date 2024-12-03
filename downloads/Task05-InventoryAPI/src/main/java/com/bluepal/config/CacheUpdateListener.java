package com.bluepal.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Service;

@Service
public class CacheUpdateListener {

    @Autowired
    private CacheManager cacheManager;

    public void handleMessage(String message) {
        System.out.println("Received cache sync message: " + message);
        
        // Split the message into action, cache name, and cache key
        String[] parts = message.split("::");
        if (parts.length < 3) {
            System.err.println("Invalid message format. Expected format: <action>::<cacheName>::<cacheKey>");
            return;
        }

        String action = parts[0];
        String cacheName = parts[1];
        String cacheKey = parts[2];

        // Get the cache from the CacheManager
        Cache cache = cacheManager.getCache(cacheName);
        if (cache != null) {
            switch (action) {
                case "CACHE_UPDATE":
                    // If action is CACHE_UPDATE, evict the cache (it could be extended to actually update the cache)
                    cache.evict(cacheKey);
                    System.out.println("Cache updated, evicted cache key: " + cacheKey);
                    break;
                case "CACHE_DELETE":
                    // If action is CACHE_DELETE, evict the cache
                    cache.evict(cacheKey);
                    System.out.println("Cache deleted, evicted cache key: " + cacheKey);
                    break;
                default:
                    System.err.println("Unknown cache action: " + action);
                    break;
            }
        } else {
            System.err.println("Cache not found for name: " + cacheName);
        }
    }
}
