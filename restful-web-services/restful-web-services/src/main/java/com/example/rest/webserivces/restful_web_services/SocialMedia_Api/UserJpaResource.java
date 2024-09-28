package com.example.rest.webserivces.restful_web_services.SocialMedia_Api;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
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
public class UserJpaResource {
    
    private UserRepo userRepo;
    

    

    public UserJpaResource(UserRepo userRepo, UserDAO_Services userDAO_Services) {
        super();
        this.userRepo = userRepo;
    }
    // get all users
    @GetMapping("/jpa/users")
    public List<User> retriveAllUsers(){
        return userRepo.findAll();
    }
    //get user with an id
    @GetMapping("/jpa/users/{id}")
    public EntityModel <User> retriveUser(@PathVariable int id){
        Optional<User> user = userRepo.findById(id); 
        
        if (user.isEmpty()) {
            throw new UserNotFoundException("id = "+id);
        }

        EntityModel<User> entityModel = EntityModel.of(user.get());
        WebMvcLinkBuilder link = linkTo(methodOn(this.getClass()).retriveAllUsers());
        entityModel.add(link.withRel("all-users"));
        return entityModel;
        
    }
    // To save a user 
    @PostMapping("/jpa/users")
    public ResponseEntity<Object> createUser(@Valid @RequestBody User user){
        User savedUser = userRepo.save(user);
        URI location = ServletUriComponentsBuilder
                        .fromCurrentRequest()
                        .path("/{id}")
                        .buildAndExpand
                        (savedUser.getId())
                        .toUri();
        return ResponseEntity.created(location).build();
    }

    @DeleteMapping("/jpa/users/{id}")
    public void deleteUser(@PathVariable int id){
        userRepo.deleteById(id); 
    }
}
