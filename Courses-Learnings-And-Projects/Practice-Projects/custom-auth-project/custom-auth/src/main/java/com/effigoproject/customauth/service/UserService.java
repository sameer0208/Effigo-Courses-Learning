package com.effigoproject.customauth.service;

import com.effigoproject.customauth.entity.User;
import com.effigoproject.customauth.repository.UserRepository;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder = new BCryptPasswordEncoder();
    }

    // ✅ Register a new user (with password encryption)
    public String registerUser(User user) {
        // Check if username or email already exists
        if (userRepository.existsByUsername(user.getUsername())) {
            return "Username is already taken!";
        }
        if (userRepository.existsByEmail(user.getEmail())) {
            return "Email is already registered!";
        }

        // Encrypt password using BCrypt
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        // Save user to the database
        userRepository.save(user);
        return "User registered successfully!";
    }

    // ✅ Authenticate user during login
    public boolean authenticate(String username, String password) {
        Optional<User> optionalUser = userRepository.findByUsername(username);

        if (optionalUser.isEmpty()) {
            return false; // User not found
        }

        User user = optionalUser.get();

        // Compare entered password with stored encrypted password
        return passwordEncoder.matches(password, user.getPassword());
    }

    public User getLoggedInUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

    public User findByUsername(String username) {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }
}