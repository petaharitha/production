package com.supplier.service;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Service
public class cachePublisher {
	private static final Logger logger = LoggerFactory.getLogger(cachePublisher.class);
    @Autowired
    private RedisTemplate<String, String> redisTemplate;  // Instance variable for RedisTemplate

    private static final String CHANNEL_NAME = "cacheSyncChannel";

    public void publishCacheUpdate(String action, String cacheName, String cacheKey) {
        try {
            // Construct the message in the desired format
            String message = String.format("%s::%s::%s", action, cacheName, cacheKey);

            // Publish the message to the Redis channel
            redisTemplate.convertAndSend(CHANNEL_NAME, message);

            // Log the published message
            logger.info("Published cache update message: {}", message);
        } catch (Exception e) {
            // Log any exceptions encountered during publishing
            logger.error("Error publishing cache update message: {}", e.getMessage(), e);
        }
    }

}