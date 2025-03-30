package com.effigoproject.spring_batch.config;

import com.effigoproject.spring_batch.entity.Users;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.support.ListItemReader;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.File;
import java.io.IOException;
import java.util.List;

@Configuration
public class JsonFileUserReader {

    private static final Logger logger = LoggerFactory.getLogger(JsonFileUserReader.class);

    @Bean
    public ItemReader<Users> reader() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        List<Users> users = objectMapper.readValue(
                new File("src/main/resources/users-data.json"),
                new TypeReference<List<Users>>() {}
        );

        logger.info("✅ Loaded {} users from JSON at {}", users.size(), System.currentTimeMillis());

        return new ListItemReader<>(users);
    }
}
