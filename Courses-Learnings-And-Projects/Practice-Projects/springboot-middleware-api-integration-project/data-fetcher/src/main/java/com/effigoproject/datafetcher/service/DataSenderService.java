package com.effigoproject.datafetcher.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import jakarta.annotation.PostConstruct;
import java.io.File;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

@Service
public class DataSenderService {

    private static final Logger logger = LoggerFactory.getLogger(DataSenderService.class);
    private final RestTemplate restTemplate = new RestTemplate();
    private final String receiverUrl = "http://data-receiver:8080/api/data/receive";

    @PostConstruct
    public void sendData() {
        try {
            // Load JSON from resources folder (src/main/resources/data.json)
            ObjectMapper objectMapper = new ObjectMapper();
            InputStream inputStream = getClass().getResourceAsStream("/data.json");

            // Deserialize to List of Maps (Array of JSON objects)
            List<Map<String, Object>> jsonData = objectMapper.readValue(inputStream, new TypeReference<List<Map<String, Object>>>() {});


            // Send JSON to data-receiver
            String response = restTemplate.postForObject(receiverUrl, jsonData, String.class);
            logger.info("✅ Response from data-receiver: {}", response);
        } catch (Exception e) {
            logger.error("❌ Error sending data: {}", e.getMessage(), e);
        }
    }
}