package com.effigoproject.datareceiver.service;

import com.effigoproject.datareceiver.entity.User;
import com.effigoproject.datareceiver.repository.UserRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public String saveUser(List<User> user) {
        userRepository.saveAll(user);
        return "Users data saved";
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
}
