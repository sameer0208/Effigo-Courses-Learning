package com.effigoproject.spring_batch.config;

import com.effigoproject.spring_batch.entity.Users;
import com.effigoproject.spring_batch.repository.UserRepository;
import org.apache.catalina.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.Chunk;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserWriter implements ItemWriter<Users> {

    private static final Logger logger = LoggerFactory.getLogger(UserWriter.class);

    public UserRepository userRepository;

    public UserWriter(UserRepository userRepository)
    {
        this.userRepository = userRepository;
    }

    @Override
    public void write(Chunk<? extends Users> users) {
        List<? extends Users> userList = users.getItems();

        logger.info("💾 Writing {} records to the database...", userList.size());

        userRepository.saveAll(userList);

        logger.info("✅ Successfully wrote {} records!", userList.size());
    }
}
