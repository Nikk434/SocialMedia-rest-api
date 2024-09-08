package com.example.rest.webserivces.restful_web_services.SocialMedia_Api;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import org.springframework.stereotype.Component;

// import jakarta.persistence.criteria.Predicate;
@Component
public class UserDAO_Services {
    private static List<User> users = new ArrayList<>();
    private static int idCounter = 0;
    static {
        users.add(new User(++idCounter, "nik", LocalDate.now()));
        users.add(new User(++idCounter, "nik 2", LocalDate.now()));
        users.add(new User(++idCounter, "nik 3", LocalDate.now()));
    }

    public List<User> findAll() {
        return users;
    }

    public User findOne(int id) {
        Predicate<? super User> predicate = user -> user.getId().equals(id); 
        return users.stream().filter(predicate).findFirst().orElse(null);
    }

    public User save(User user) {
        user.setId(++idCounter);
        users.add(user);
        return user;
    }

    public void deleteOneById(int id) {
        Predicate<? super User> predicate = user -> user.getId().equals(id); 
        users.removeIf(predicate);
    }
}
