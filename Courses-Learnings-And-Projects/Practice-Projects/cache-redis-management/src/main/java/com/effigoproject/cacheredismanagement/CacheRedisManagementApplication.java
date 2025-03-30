package com.effigoproject.cacheredismanagement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class CacheRedisManagementApplication {

	public static void main(String[] args) {
		SpringApplication.run(CacheRedisManagementApplication.class, args);
	}

}
