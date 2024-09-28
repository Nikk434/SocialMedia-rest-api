package com.example.rest.webserivces.restful_web_services.SocialMedia_Api;

import org.springframework.data.jpa.repository.JpaRepository;
// import org.springframework.data.jpa.repository.support.JpaRepositoryFactory;

public interface UserRepo extends JpaRepository <User, Integer>{
    
}
