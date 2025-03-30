package com.effigoproject.ehcache.service;

import com.effigoproject.ehcache.entity.Restaurant;
import com.effigoproject.ehcache.repository.RestaurantRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RestaurantService {

    private static final Logger logger = LoggerFactory.getLogger(RestaurantService.class);

    private final RestaurantRepository repository;
    private final CacheManager cacheManager;

    public RestaurantService(RestaurantRepository repository, CacheManager cacheManager) {
        this.repository = repository;
        this.cacheManager = cacheManager;
    }

    public Restaurant getRestaurantById(Long id) {
        Cache cache = cacheManager.getCache("restaurantCache");
        if (cache != null) {
            Restaurant cachedRestaurant = cache.get(id, Restaurant.class);
            if (cachedRestaurant != null) {
                logger.info("Fetching restaurant with ID: {} from Cache...", id);
                return cachedRestaurant;
            }
        }

        logger.info("Fetching restaurant with ID: {} from Database...", id);
        Restaurant restaurant = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Restaurant not found!"));

        if (cache != null) {
            cache.put(id, restaurant);
            logger.info("Caching restaurant with ID: {}", id);
        }

        return restaurant;
    }

    public List<Restaurant> getAllRestaurants() {
        logger.info("Fetching all restaurants from Database...");
        List<Restaurant> restaurants = repository.findAll();

        Cache cache = cacheManager.getCache("restaurantCache");
        if (cache != null) {
            restaurants.forEach(restaurant -> {
                cache.put(restaurant.getId(), restaurant);
                logger.info("Caching restaurant with ID: {}", restaurant.getId());
            });
        }

        return restaurants;
    }

    @CachePut(value = "restaurantCache", key = "#restaurant.id")
    public Restaurant saveRestaurant(Restaurant restaurant) {
        logger.info("Saving restaurant with ID: {} into the Database...", restaurant.getId());
        return repository.save(restaurant);
    }

    @CacheEvict(value = "restaurantCache", key = "#id")
    public void deleteRestaurant(Long id) {
        logger.info("Deleting restaurant with ID: {} from Database and Cache...", id);
        repository.deleteById(id);
    }

    @CacheEvict(value = "restaurantCache", allEntries = true)
    public void clearCache() {
        logger.info("Clearing all entries from restaurantCache...");
    }
}
