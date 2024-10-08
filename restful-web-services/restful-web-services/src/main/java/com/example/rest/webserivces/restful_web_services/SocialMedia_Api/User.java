package com.example.rest.webserivces.restful_web_services.SocialMedia_Api;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

// import jakarta.validation.OverridesAttribute.List;
import java.util.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;
@Entity(name = "user_details")
public class User {

    protected User(){

    }
    @Id
    @GeneratedValue
    private Integer id;

    @Size(min = 2, message = "name should have alteast 2 characters")
    private String name;
    
    @Past(message = "Birth date must be in the past")
    private LocalDate birthDate;
    
    @OneToMany(mappedBy = "user")
    @JsonIgnore
    private List<post> posts;

    public List<post> getPosts() {
        return posts;
    }
    public void setPosts(List<post> posts) {
        this.posts = posts;
    }


    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public LocalDate getBirthDate() {
        return birthDate;
    }
    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }
    public User(Integer id, String name, LocalDate birthDate) {
        this.id = id;
        this.name = name;
        this.birthDate = birthDate;
    }
    @Override
    public String toString() {
        return "User [id=" + id + ", name=" + name + ", birthDate=" + birthDate + "]";
    }

    
}
