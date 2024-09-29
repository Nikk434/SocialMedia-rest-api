package com.example.rest.webserivces.restful_web_services.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import static org.springframework.security.config.Customizer.withDefaults;

@Configuration

public class apiSecurityConfig {
    // method to customize spring security filter chain
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
        // authenticating all requests
        http.authorizeHttpRequests(
            auth -> auth.anyRequest().authenticated()
            );  

        // Configuring basic HTTP authentication
        http.httpBasic(withDefaults()); 
        
        // Disabling CSRF
        http.csrf(csrf -> csrf.disable());
        return http.build();
    }
}
