package com.effigoproject.smart_inventory_system.controller;

import com.effigoproject.smart_inventory_system.entity.User;
import com.effigoproject.smart_inventory_system.service.CustomUserDetailsService;
import com.effigoproject.smart_inventory_system.utils.JwtUtil;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthController {
    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;
    private final UserDetailsService userDetailsService;
    private final CustomUserDetailsService userService;

    public AuthController(AuthenticationManager authenticationManager, JwtUtil jwtUtil, UserDetailsService userDetailsService, CustomUserDetailsService userService) {
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
        this.userDetailsService = userDetailsService;
        this.userService = userService;
    }

    @PostMapping("/login")
    public Map<String, String> login(@RequestBody Map<String, String> request) {
        String username = request.get("username");
        String password = request.get("password");

        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        UserDetails userDetails = userDetailsService.loadUserByUsername(username);
        String token = jwtUtil.generateToken(userDetails);

        return Map.of("token", token);
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody User user) {
        String result = userService.registerUser(user);
        return ResponseEntity.ok(result);
    }
}
