//package com.effigoproject.datafetcher.service;
//
//import jakarta.annotation.PostConstruct;
//import org.springframework.http.HttpEntity;
//import org.springframework.http.HttpHeaders;
//import org.springframework.http.MediaType;
//import org.springframework.stereotype.Service;
//import org.springframework.web.client.RestTemplate;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//
//
//@Service
//public class DataSenderService {
//
//    private final UserService userService;
//    private final RestTemplate restTemplate;
//    private static final Logger logger = LoggerFactory.getLogger(DataSenderService.class);
//    public DataSenderService(UserService userService, RestTemplate restTemplate) {
//        this.userService = userService;
//        this.restTemplate = restTemplate;
//    }
//
//    @PostConstruct
//    public void sendDataOnStartup() {
//        logger.info("✅ PostConstruct method is triggered.");
//        try {
//            logger.info("🚀 Sending data to receiver on startup...");
//            String response = sendDataToReceiver();
//            logger.info("✅ Data sent successfully: {}", response);
//        } catch (Exception e) {
//            logger.error("❌ Error sending data to receiver: {}", e.getMessage(), e);
//        }
//    }
//
//
//    public String sendDataToReceiver() {
//        String jsonData = userService.readJsonData();
//
//        // Set Content-Type to application/json
//        HttpHeaders headers = new HttpHeaders();
//        headers.setContentType(MediaType.APPLICATION_JSON);
//
//        // Create a request entity with headers and body
//        HttpEntity<String> request = new HttpEntity<>(jsonData, headers);
//
//        // Replace with your actual receiver URL
//        String receiverUrl = "http://data-receiver-service:8081/api/data/receive";
////        String receiverUrl = "http://localhost:8081/api/data/receive";
//
//        return restTemplate.postForObject(receiverUrl, request, String.class);
//    }
//
//}