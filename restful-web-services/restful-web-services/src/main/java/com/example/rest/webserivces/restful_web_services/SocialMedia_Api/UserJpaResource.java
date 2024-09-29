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

    private postRepo postRepo;

    public UserJpaResource(UserRepo userRepo, postRepo postRepo) {
        super();
        this.userRepo = userRepo;
        this.postRepo = postRepo;
        
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
    
    @GetMapping("/jpa/users/{id}/posts")
    public List<post> retrivePostForUser(@PathVariable int id){
        Optional<User> user = userRepo.findById(id); 
        
        if (user.isEmpty()) {
            throw new UserNotFoundException("id = "+id);
        }
        return user.get().getPosts();
    }

    @PostMapping("/jpa/users/{id}/posts")
    public ResponseEntity<Object> creatpostForuser(@PathVariable int id, @Valid @RequestBody post post){
        Optional<User> user = userRepo.findById(id); 
        
        if (user.isEmpty()) {
            throw new UserNotFoundException("id = "+id);
        }

        post.setUser(user.get());
        post saved_post = postRepo.save(post);
        URI location = ServletUriComponentsBuilder
                        .fromCurrentRequest()
                        .path("/{id}")
                        .buildAndExpand
                        (saved_post.getId())
                        .toUri();
        return ResponseEntity.created(location).build();
    
    }
}
