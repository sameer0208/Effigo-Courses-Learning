package com.effigocourse.rest.webservices.restful_web_services.user;

import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

@Component
public class UserDaoService {
    //JPA/Hibernate > Database
    //UserDaoService > Static List
    //private List<User> findAll()
    //public User save(User user)
    //public User findOne(int id)
    private static int usersCount =0;
    private static List<User> users = new ArrayList<>();
    static {
        users.add(new User(++usersCount,"Sameer", LocalDate.now().minusYears(22)));
        users.add(new User(++usersCount,"Jack", LocalDate.now().minusYears(18)));
        users.add(new User(++usersCount,"John", LocalDate.now().minusYears(15)));
    }

    public List<User> findAll()
    {
        return users;
    }

    public User findOne(int id)
    {
        Predicate<? super User> predicate = user -> user.getId()==id;
        return users.stream().filter(predicate).findFirst().orElse(null);
    }

    public void deleteById(int id)
    {
        Predicate<? super User> predicate = user -> user.getId()==id;
        users.removeIf(predicate);
    }

    public User save(User user)
    {
        user.setId(++usersCount);
        users.add(user);
        return user;
    }
}
