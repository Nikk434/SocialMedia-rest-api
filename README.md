# SocialMedia REST API
This is a RESTful API for a Social Media application built using Spring Boot. It supports basic user management and post functionality, including user registration, login, and CRUD operations for posts. This project is designed as a backend service for a social media platform.

## Features
- User Registration & Login
- Role-based Access Control using Spring Security
- CRUD Operations for User Posts
- JWT Authentication for Secure Access
- Error Handling & Validation

## Prerequisites
Before you begin, ensure you have the following installed:
- Java 17 or higher
- Maven 3.6+ for dependency management and building
- MySQL or any other relational database
- An IDE like IntelliJ IDEA or Eclipse

## API Endpoints
### Authentication
- POST /auth/register
    - Register a new user.
    - Request Body: { "username": "example", "password": "pass123", "email": "example@example.com" }
- POST /auth/login
    - Login and retrieve a JWT token.
    - Request Body: { "username": "example", "password": "pass123" }
- User Management
    - GET /users/{id}
        - Retrieve user details by ID.
    - PUT /users/{id}
        - Update user details.
    - DELETE /users/{id}
        - Delete a user by ID.
- Posts Management
    - GET /posts
        - Retrieve all posts.
    - GET /posts/{id}
        - Retrieve a post by ID.
    - POST /posts
        - Create a new post.
            - Request Body: { "title": "New Post", "content": "This is a new post." }
    - DELETE /posts/{id}
        - Delete a post by 

## Technologies Used
- Spring Boot - Backend framework
- Spring Security - Security framework for role-based authentication
- JWT - JSON Web Tokens for secure access
- JPA & Hibernate - ORM for database access
- Maven - Build and dependency management
- MySQL - Relational database for data persistence