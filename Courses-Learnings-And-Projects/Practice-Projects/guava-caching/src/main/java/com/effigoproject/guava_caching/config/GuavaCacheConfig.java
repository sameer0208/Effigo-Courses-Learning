package com.effigoproject.guava_caching.config;
import com.effigoproject.guava_caching.entity.BankDetails;
import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.TimeUnit;

@Configuration
public class GuavaCacheConfig {

    @Bean
    public Cache<String, BankDetails> bankDetailsCache() {
        return CacheBuilder.newBuilder()
                .maximumSize(100) // Maximum cache size
                .expireAfterAccess(10, TimeUnit.MINUTES) // Cache expiration time
                .build();
    }
}
