package com.effigoproject.datareceiver.controller;

import com.effigoproject.datareceiver.entity.User;
import com.effigoproject.datareceiver.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/data")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/receive")
    public ResponseEntity<String> receiveUsers(@RequestBody List<User> users) {
        return ResponseEntity.ok(userService.saveUser(users));
    }

    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

}
