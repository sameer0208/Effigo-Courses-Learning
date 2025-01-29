package com.example.sameerkart.service;

import com.example.sameerkart.dto.UserDTO;
import com.example.sameerkart.entity.User;
import com.example.sameerkart.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<UserDTO> getAllUsers() {
        return userRepository.findAll().stream()
                .map(user -> new UserDTO(user.getUserId(), user.getUsername(), user.getEmail(), user.getRole()))
                .collect(Collectors.toList());
    }

    public UserDTO getUserById(String userId) {
        Optional<User> user = userRepository.findById(userId);
        return user.map(u -> new UserDTO(u.getUserId(), u.getUsername(), u.getEmail(), u.getRole())).orElse(null);
    }

    public UserDTO createUser(User user) {
        User savedUser = userRepository.save(user);
        return new UserDTO(savedUser.getUserId(), savedUser.getUsername(), savedUser.getEmail(), savedUser.getRole());
    }

    public void deleteUser(String userId) {
        userRepository.deleteById(userId);
    }
}
