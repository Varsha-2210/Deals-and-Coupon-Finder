package com.usersecurity.service;

import com.usersecurity.model.User;

public interface UserService {

    // Method to create a user
    User createUser(User newUser);
    
    // Method to get a user by email
    User getByEmailId(String email);
    
    // Method to update a user by email
    User updateByEmail(String email, User updatedUser);

    // Method to delete a user by email
    String deleteByEmailId(String email);
}
