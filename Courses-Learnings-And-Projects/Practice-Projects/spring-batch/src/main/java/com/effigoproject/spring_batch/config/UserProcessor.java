    package com.effigoproject.spring_batch.config;

    import com.effigoproject.spring_batch.entity.Users;
    import org.springframework.batch.item.ItemProcessor;
    import org.springframework.stereotype.Component;

    @Component
    public class UserProcessor implements ItemProcessor<Users, Users> {

        @Override
        public Users process(Users users) {
            users.setName(users.getName().toUpperCase());
            return users;
        }
    }
