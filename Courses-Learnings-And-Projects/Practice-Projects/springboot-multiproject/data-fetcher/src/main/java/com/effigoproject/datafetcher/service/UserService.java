package com.effigoproject.datafetcher.service;

import com.effigoproject.datafetcher.entity.User;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@Service
public class UserService {

    private final ObjectMapper objectMapper = new ObjectMapper();

    public List<User> readUsersFromFile() throws IOException {
        ClassPathResource resource = new ClassPathResource("data.json");
        try (InputStream inputStream = resource.getInputStream()) {
            return objectMapper.readValue(inputStream, new TypeReference<List<User>>() {});
        }
    }
}