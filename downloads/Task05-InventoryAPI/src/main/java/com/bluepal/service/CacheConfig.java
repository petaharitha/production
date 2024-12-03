package com.bluepal.service;

import java.time.Duration;

import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;

@Configuration
@EnableCaching
public class CacheConfig {

    @Bean(name = "redisCacheManager")
    public CacheManager redisCacheManager(RedisConnectionFactory redisConnectionFactory) {
        RedisCacheConfiguration defaultConfig = RedisCacheConfiguration.defaultCacheConfig()
            .entryTtl(Duration.ofHours(1))
            .disableCachingNullValues();
        
        return RedisCacheManager.builder(redisConnectionFactory)
            .cacheDefaults(defaultConfig)
            .build();
    }

    @Bean(name = "concurrentMapCacheManager")
    @Primary
    public CacheManager concurrentMapCacheManager() {
        return new ConcurrentMapCacheManager("spares");
    }
}



//package com.bluepal.service;
//
//import java.time.Duration;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.cache.CacheManager;
//import org.springframework.cache.annotation.EnableCaching;
//import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.Primary;
//import org.springframework.data.redis.cache.RedisCacheConfiguration;
//import org.springframework.data.redis.cache.RedisCacheManager;
//import org.springframework.data.redis.connection.RedisConnectionFactory;
//
//@Configuration
//@EnableCaching
//public class CacheConfig {
//
//    private static final Logger logger = LoggerFactory.getLogger(CacheConfig.class);
//
//    /**
//     * Redis Cache Manager with no expiry.
//     */
//    @Bean(name = "redisCacheManager")
//    public CacheManager redisCacheManager(RedisConnectionFactory redisConnectionFactory) {
//        logger.info("Initializing Redis Cache Manager with no expiry...");
//        
//        // Configuring Redis cache with no expiry and disabled null caching
//        RedisCacheConfiguration cacheConfig = RedisCacheConfiguration.defaultCacheConfig()
//            .entryTtl(Duration.ZERO) // No expiry
//            .disableCachingNullValues(); // Do not cache null values
//
//        RedisCacheManager redisCacheManager = RedisCacheManager.builder(redisConnectionFactory)
//            .cacheDefaults(cacheConfig)
//            .build();
//
//        logger.info("Redis Cache Manager initialized.");
//        return redisCacheManager;
//    }
//
//    /**
//     * ConcurrentMapCacheManager for in-memory caching (default fallback).
//     */
//    @Bean(name = "concurrentMapCacheManager")
//    @Primary
//    public CacheManager concurrentMapCacheManager() {
//        logger.info("Initializing ConcurrentMapCacheManager for in-memory caching...");
//        
//        // In-memory cache with no expiry
//        ConcurrentMapCacheManager cacheManager = new ConcurrentMapCacheManager("sparesList");
//        logger.info("ConcurrentMapCacheManager initialized.");
//        return cacheManager;
//    }
//
//    /**
//     * Custom logic to decide which CacheManager to use.
//     */
//    @Bean
//    public CacheManager cacheManager(RedisConnectionFactory redisConnectionFactory) {
//        boolean useRedis = redisConnectionFactory != null;
//        logger.info("Determining which CacheManager to use... Redis available: {}", useRedis);
//
//        if (useRedis) {
//            return redisCacheManager(redisConnectionFactory);
//        } else {
//            logger.warn("Redis connection factory not available. Falling back to ConcurrentMapCacheManager.");
//            return concurrentMapCacheManager();
//        }
//    }
//}
