package com.usersecurity.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.usersecurity.exception.UserAlreadyPresentException;
import com.usersecurity.exception.UserNotFoundException;
import com.usersecurity.model.User;
import com.usersecurity.service.UserServiceImpl;

@RestController
@RequestMapping("/users")
@CrossOrigin(origins="*")
@Validated
public class UserController {

    @Autowired
    private UserServiceImpl userServiceImpl;
    
    @PostMapping("/createUser")
    public ResponseEntity<?> createUser(@RequestBody @Valid User user) {
        try {
            // Call the service to create a new user
            User createdUser = userServiceImpl.createUser(user);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdUser);
        } catch (UserAlreadyPresentException ex) {
            // Handle the case where user already exists
            return ResponseEntity.status(HttpStatus.CONFLICT).body(ex.getMessage());
        }
    }

    // Endpoint to find a user by email
    @GetMapping("/findUserByMail/{email}")
    public ResponseEntity<?> findUserByMail(@PathVariable("email") String email) {
        try {
            // Call the service to find user by email
            User user = userServiceImpl.getByEmailId(email);
            return ResponseEntity.ok(user);
        } catch (UserNotFoundException ex) {
            // Handle the case where user is not found
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
        }
    }

    // Endpoint to update a user by email
    @PutMapping("/updateUserByMail/{email}")
    public ResponseEntity<?> updateUserByMail(@PathVariable("email") String email, @RequestBody @Valid User user) {
        try {
            // Call the service to update user by email
            User updatedUser = userServiceImpl.updateByEmail(email, user);
            return ResponseEntity.ok(updatedUser);
        } catch (UserNotFoundException ex) {
            // Handle the case where user is not found
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
        }
    }

    // Endpoint to delete a user by email
    @DeleteMapping("/deleteUser/{email}")
    public ResponseEntity<?> deleteUser(@PathVariable("email") String email) {
        try {
            // Call the service to delete user by email
            String message = userServiceImpl.deleteByEmailId(email);
            return ResponseEntity.ok(message);
        } catch (UserNotFoundException ex) {
            // Handle the case where user is not found
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
        }
    }
}
