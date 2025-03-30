package com.effigoproject.multi_level_caching.service;

import com.effigoproject.multi_level_caching.entity.Property;
import com.effigoproject.multi_level_caching.repository.PropertyRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PropertyService {

    private static final Logger logger = LoggerFactory.getLogger(PropertyService.class);

    private final PropertyRepository propertyRepository;
    private final CacheManager cacheManager;
    private final RedisTemplate<String, Object> redisTemplate;

    public PropertyService(PropertyRepository propertyRepository, CacheManager cacheManager, RedisTemplate<String, Object> redisTemplate) {
        this.propertyRepository = propertyRepository;
        this.cacheManager = cacheManager;
        this.redisTemplate = redisTemplate;
    }

    @Cacheable(value = "properties", key = "#id")
    public Property getPropertyById(Long id) {
        logger.info("[CACHE MISS] Fetching property with ID: {} from the database", id);
        return propertyRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Property with ID " + id + " not found"));
    }

    @Cacheable(value = "allProperties")
    public List<Property> getAllProperties() {
        logger.info("[CACHE MISS] Fetching all properties from the database");

        List<Property> allProperties = propertyRepository.findAll();
        Cache caffeineCache = cacheManager.getCache("properties");
        if (caffeineCache == null) {
            throw new IllegalStateException("Caffeine cache 'properties' not found");
        }

        List<Property> caffeineProperties = allProperties.stream()
                .limit(20)
                .toList();

        List<Property> redisProperties = allProperties.stream()
                .skip(20)
                .toList();

        logger.info("[CACHE] Caching first 20 properties in Caffeine");
        caffeineProperties.forEach(property -> caffeineCache.put(property.getId(), property));

        logger.info("[CACHE] Caching remaining properties in Redis");
        redisProperties.forEach(property -> redisTemplate.opsForValue().set("property:" + property.getId(), property));

        return allProperties;
    }

    @CachePut(value = "properties", key = "#result.id")
    public Property saveProperty(Property property) {
        Property savedProperty = propertyRepository.save(property);
        logger.info("[CACHE UPDATE] Saved property with ID: {} and updated cache", savedProperty.getId());

        refreshAllPropertiesCache();
        return savedProperty;
    }

    @CacheEvict(value = "properties", key = "#id")
    public void deleteProperty(Long id) {
        if (!propertyRepository.existsById(id)) {
            throw new RuntimeException("Property with ID " + id + " does not exist");
        }
        propertyRepository.deleteById(id);
        logger.info("[CACHE EVICT] Deleted property with ID: {} and evicted from cache", id);

        evictAllPropertiesCache();
    }

    @CachePut(value = "allProperties")
    public List<Property> refreshAllPropertiesCache() {
        logger.info("[CACHE REFRESH] Refreshing 'allProperties' cache from the database");
        return propertyRepository.findAll();
    }

    @CacheEvict(value = "allProperties")
    public void evictAllPropertiesCache() {
        logger.info("[CACHE EVICT] Evicted 'allProperties' cache");
    }
}
