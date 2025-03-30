package com.effigoproject.ehcache.config;

import org.ehcache.config.builders.CacheConfigurationBuilder;
import org.ehcache.config.builders.ExpiryPolicyBuilder;
import org.ehcache.config.builders.ResourcePoolsBuilder;
import org.ehcache.config.units.EntryUnit;
import org.ehcache.jsr107.Eh107Configuration;
import org.springframework.cache.jcache.JCacheCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.cache.Caching;
import javax.cache.spi.CachingProvider;
import java.time.Duration;

@Configuration
public class EhcacheConfig {

    @Bean
    public JCacheCacheManager cacheManager() {
        CachingProvider provider = Caching.getCachingProvider();
        javax.cache.CacheManager cacheManager = provider.getCacheManager();

        cacheManager.createCache("restaurantCache", Eh107Configuration.fromEhcacheCacheConfiguration(
                CacheConfigurationBuilder.newCacheConfigurationBuilder(
                        Long.class, // Key type
                        com.effigoproject.ehcache.entity.Restaurant.class,
                        ResourcePoolsBuilder.newResourcePoolsBuilder()
                                .heap(1000, EntryUnit.ENTRIES)
                ).withExpiry(ExpiryPolicyBuilder.timeToLiveExpiration(Duration.ofMinutes(10)))
        ));

        return new JCacheCacheManager(cacheManager);
    }
}