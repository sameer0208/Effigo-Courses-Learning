package com.effigoproject.datareceiver.config;

import com.effigoproject.datareceiver.entity.User;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

@Component
public class DataProcessor implements ItemProcessor<User, User> {
    @Override
    public User process(User user){
        return user;
    }
}
