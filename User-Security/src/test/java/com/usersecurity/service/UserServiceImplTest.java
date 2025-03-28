package com.usersecurity.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.usersecurity.exception.UserAlreadyPresentException;
import com.usersecurity.exception.UserNotFoundException;
import com.usersecurity.model.User;
import com.usersecurity.repository.UserRepository;

@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserServiceImpl userServiceImpl;

    @Test
    void testCreateUser_UserNotAlreadyPresent() {
        // Arrange
        User newUser = new User("testuser", "test@example.com", "password", "1234567890");
        when(userRepository.existsByEmail(newUser.getEmail())).thenReturn(false);
        when(userRepository.save(newUser)).thenReturn(newUser);

        // Act
        User createdUser = userServiceImpl.createUser(newUser);

        // Assert
        assertNotNull(createdUser);
        assertEquals(newUser, createdUser);
    }

    @Test
    void testCreateUser_UserAlreadyPresent() {
        // Arrange
        User existingUser = new User("testuser", "test@example.com", "password", "1234567890");
        when(userRepository.existsByEmail(existingUser.getEmail())).thenReturn(true);

        // Act and Assert
        UserAlreadyPresentException exception = assertThrows(UserAlreadyPresentException.class,
                () -> userServiceImpl.createUser(existingUser));
        assertEquals("User with email " + existingUser.getEmail() + " already exists.", exception.getMessage());
    }

    @Test
    void testGetByEmailId_UserFound() {
        // Arrange
        String email = "test@example.com";
        User expectedUser = new User("testuser", email, "password", "1234567890");
        when(userRepository.getByEmail(email)).thenReturn(expectedUser);

        // Act
        User retrievedUser = userServiceImpl.getByEmailId(email);

        // Assert
        assertEquals(expectedUser, retrievedUser);
    }

    @Test
    void testGetByEmailId_UserNotFound() {
        // Arrange
        String email = "nonexistent@example.com";
        when(userRepository.getByEmail(email)).thenReturn(null);

        // Act and Assert
        UserNotFoundException exception = assertThrows(UserNotFoundException.class,
                () -> userServiceImpl.getByEmailId(email));
        assertEquals("User with email " + email + " not found.", exception.getMessage());
    }

    @Test
    void testUpdateByEmail_UserFound() {
        // Arrange
        String email = "test@example.com";
        User existingUser = new User("testuser", email, "password", "1234567890");
        User updatedUser = new User("updateduser", email, "newpassword", "9876543210");
        when(userRepository.getByEmail(email)).thenReturn(existingUser);
        when(userRepository.save(existingUser)).thenReturn(existingUser);

        // Act
        User result = userServiceImpl.updateByEmail(email, updatedUser);

        // Assert
        assertEquals(updatedUser.getUsername(), result.getUsername());
        assertEquals(updatedUser.getPassword(), result.getPassword());
        assertEquals(updatedUser.getPhone(), result.getPhone());
    }

    @Test
    void testUpdateByEmail_UserNotFound() {
        // Arrange
        String email = "nonexistent@example.com";
        User updatedUser = new User("updateduser", email, "newpassword", "9876543210");
        when(userRepository.getByEmail(email)).thenReturn(null);

        // Act and Assert
        UserNotFoundException exception = assertThrows(UserNotFoundException.class,
                () -> userServiceImpl.updateByEmail(email, updatedUser));
        assertEquals("User with email " + email + " not found.", exception.getMessage());
    }

    @Test
    void testDeleteByEmailId_UserFound() {
        // Arrange
        String email = "test@example.com";
        when(userRepository.existsByEmail(email)).thenReturn(true);

        // Act
        String result = userServiceImpl.deleteByEmailId(email);

        // Assert
        assertEquals("Deleted Successfully", result);
        verify(userRepository).deleteByEmail(email);
    }

    @Test
    void testDeleteByEmailId_UserNotFound() {
        // Arrange
        String email = "nonexistent@example.com";
        when(userRepository.existsByEmail(email)).thenReturn(false);

        // Act and Assert
        UserNotFoundException exception = assertThrows(UserNotFoundException.class,
                () -> userServiceImpl.deleteByEmailId(email));
        assertEquals("User with email " + email + " not found.", exception.getMessage());
    }
}
