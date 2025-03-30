package com.effigoproject.multi_level_caching.config;

import com.effigoproject.multi_level_caching.service.PropertyService;
import com.github.benmanes.caffeine.cache.Caffeine;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.caffeine.CaffeineCacheManager;
import org.springframework.cache.interceptor.SimpleKeyGenerator;
import org.springframework.cache.support.CompositeCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;

import java.time.Duration;

@Configuration
@EnableCaching
public class CacheConfig {

    private static final Logger logger = LoggerFactory.getLogger(PropertyService.class);

    // Redis Connection Factory (default localhost:6379)
    @Bean
    public RedisConnectionFactory redisConnectionFactory() {
        return new LettuceConnectionFactory();
    }

    // Level 1: Caffeine Cache (In-Memory, holds only 20 records)
    @Bean
    public CaffeineCacheManager caffeineCacheManager() {
        CaffeineCacheManager cacheManager = new CaffeineCacheManager("properties", "allProperties");
        cacheManager.setCaffeine(Caffeine.newBuilder()
                .maximumSize(20)                      // Limit Caffeine to 20 records
                .expireAfterWrite(Duration.ofMinutes(5)) // Expiration time in Caffeine
        );
        return cacheManager;
    }

    // Level 2: Redis Cache (Distributed, stores all records)
    @Bean
    public RedisCacheManager redisCacheManager(RedisConnectionFactory redisConnectionFactory) {
        RedisCacheConfiguration cacheConfiguration = RedisCacheConfiguration.defaultCacheConfig()
                .entryTtl(Duration.ofHours(1)) // Redis cache expiration
                .disableCachingNullValues();   // Avoid caching null values

        return RedisCacheManager.builder(redisConnectionFactory)
                .cacheDefaults(cacheConfiguration)
                .build();
    }

    // Composite Cache Manager (Combining Caffeine + Redis)
    @Primary
    @Bean
    public CacheManager cacheManager(CaffeineCacheManager caffeineCacheManager, RedisCacheManager redisCacheManager) {
        logger.info("✅ Multi-Level Caching Initialized (Caffeine: 20 records + Redis)");
        return new CompositeCacheManager(caffeineCacheManager, redisCacheManager);
    }

    // Optional: Key Generator (for advanced cache keys)
    @Bean
    public SimpleKeyGenerator keyGenerator() {
        return new SimpleKeyGenerator();
    }
}