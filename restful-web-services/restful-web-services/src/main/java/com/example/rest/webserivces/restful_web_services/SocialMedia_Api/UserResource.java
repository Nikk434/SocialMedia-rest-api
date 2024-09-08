package com.example.rest.webserivces.restful_web_services.SocialMedia_Api;

import java.net.URI;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
// import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import jakarta.validation.Valid;

@RestController
public class UserResource {
    
    private UserDAO_Services userDAO_Services;

    

    public UserResource(UserDAO_Services userDAO_Services) {
        super();
        this.userDAO_Services = userDAO_Services;
    }
    // get all users
    @GetMapping("/users")
    public List<User> retriveAllUsers(){
        return userDAO_Services.findAll();
    }
    //get user with an id
    @GetMapping("/users/{id}")
    public User retriveUser(@PathVariable int id){
        User user = userDAO_Services.findOne(id); 
        if (user == null) {
            throw new UserNotFoundException("id = "+id);
        }
        return user;
        
    }
    // To save a user 
    @PostMapping("/users")
    public ResponseEntity<Object> createUser(@Valid @RequestBody User user){
        User savedUser = userDAO_Services.save(user);
        URI location = ServletUriComponentsBuilder
                        .fromCurrentRequest()
                        .path("/{id}")
                        .buildAndExpand
                        (savedUser.getId())
                        .toUri();
        return ResponseEntity.created(location).build();
    }

    @DeleteMapping("/users/{id}")
    public void deleteUser(@PathVariable int id){
        userDAO_Services.deleteOneById(id); 
    }
}
