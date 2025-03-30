package com.effigoproject.datafetcher.controller;


import com.effigoproject.datafetcher.entity.User;
import com.effigoproject.datafetcher.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
@RequestMapping("/api/data")
public class UserController {

    private final UserService userService;
    private final RestTemplate restTemplate;

    private static final String BASE_URL = "http://localhost:8081";

    public UserController(UserService userService, RestTemplate restTemplate) {
        this.userService = userService;
        this.restTemplate = restTemplate;
    }


    @PostMapping("/send")
    public ResponseEntity<String> sendJsonToReceiver() {
        try {
            List<User> users = userService.readUsersFromFile();
            ResponseEntity<String> response = restTemplate.postForEntity(BASE_URL + "/api/data/receive", users, String.class);
            return ResponseEntity.ok("Users read from file and sent to Data Receiver successfully.");
        } catch (Exception e) {
            System.err.println("Error reading and sending users: " + e.getMessage());
            return ResponseEntity.internalServerError().body("Failed to read and send users");
        }
    }
}