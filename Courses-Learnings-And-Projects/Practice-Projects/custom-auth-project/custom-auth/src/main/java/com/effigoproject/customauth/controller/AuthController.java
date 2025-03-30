package com.effigoproject.customauth.controller;

import com.effigoproject.customauth.entity.User;
import com.effigoproject.customauth.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.Map;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "http://localhost:3000")
public class AuthController {

    private final UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    // ✅ Register a new user
    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody User user) {
        String result = userService.registerUser(user);
        return ResponseEntity.ok(result);
    }

    // ✅ Login and authenticate user
    @PostMapping("/login")
    public ResponseEntity<String> loginUser(@RequestBody Map<String, String> loginRequest) {
        String username = loginRequest.get("username");
        String password = loginRequest.get("password");

        boolean isAuthenticated = userService.authenticate(username, password);

        if (isAuthenticated) {
            return ResponseEntity.ok("Login successful! Redirecting to home page...");
        } else {
            return ResponseEntity.badRequest().body("Invalid username or password!");
        }
    }

    // ✅ Home page (Accessible after successful login)
    @GetMapping("/home")
    public ResponseEntity<String> home() {
        return ResponseEntity.ok("Welcome to the Home Page!");
    }

//    @GetMapping("/user")
//    public User getLoggedInUserDetails() {
//        return userService.getLoggedInUser();
//    }
    @GetMapping("/user")
    public ResponseEntity<User> getUserDetails(Principal principal) {
        User user = userService.findByUsername(principal.getName());
        return ResponseEntity.ok(user);
    }

}