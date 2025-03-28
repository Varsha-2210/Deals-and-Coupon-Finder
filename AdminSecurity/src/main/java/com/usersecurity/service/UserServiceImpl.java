package com.usersecurity.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.usersecurity.exception.UserAlreadyPresentException;
import com.usersecurity.exception.UserNotFoundException;
import com.usersecurity.model.User;
import com.usersecurity.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {

    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private UserRepository userRepository;
    
    // Creates a new user.
    @Transactional
    public User createUser(User newUser) {
        logger.info("Creating a new user");

        // Check if user with the provided email already exists
        if (userRepository.existsByEmail(newUser.getEmail())) {
            String errorMessage = "User with email " + newUser.getEmail() + " already exists.";
            logger.error(errorMessage);
            throw new UserAlreadyPresentException(errorMessage);
        }

        // Save the new user
        return userRepository.save(newUser);
    }
    
    // Retrieves a user by email.
    public User getByEmailId(String email) {
        logger.info("Fetching user by email: {}", email);
        User user = userRepository.getByEmail(email);
        if (user == null) {
            String errorMessage = "User with email " + email + " not found.";
            logger.error(errorMessage);
            throw new UserNotFoundException(errorMessage);
        }
        return user;
    }
    
    // Updates a user with the specified email.
    @Transactional
    public User updateByEmail(String email, User updatedUser) {
        logger.info("Updating user by email: {}", email);
        User existingUser = getByEmailId(email);
        existingUser.setUsername(updatedUser.getUsername());
        existingUser.setPassword(updatedUser.getPassword());
        existingUser.setPhone(updatedUser.getPhone());
        return userRepository.save(existingUser);
    }
    
    // Deletes a user with the specified email.
    @Transactional
    public String deleteByEmailId(String email) {
        logger.info("Deleting user by email: {}", email);
        if (!userRepository.existsByEmail(email)) {
            String errorMessage = "User with email " + email + " not found.";
            logger.error(errorMessage);
            throw new UserNotFoundException(errorMessage);
        }
        userRepository.deleteByEmail(email);
        return "Deleted Successfully";
    }
}
