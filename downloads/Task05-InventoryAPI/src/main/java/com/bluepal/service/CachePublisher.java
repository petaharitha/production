package com.bluepal.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Service
public class CachePublisher {

    @Autowired
    private RedisTemplate<String, String> redisTemplate;  // Instance variable for RedisTemplate

    private static final String CHANNEL_NAME = "cacheSyncChannel";

    // Publish cache update message to Redis
    public void publishCacheUpdate(String action, String cacheName, String cacheKey) {
        String message = String.format("%s::%s::%s", action, cacheName, cacheKey);
        redisTemplate.convertAndSend(CHANNEL_NAME, message); // Publish the message to Redis
        System.out.println("Published cache update message: " + message);
    }
}
