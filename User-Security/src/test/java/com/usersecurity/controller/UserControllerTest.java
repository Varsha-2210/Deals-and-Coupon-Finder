package com.usersecurity.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.usersecurity.exception.UserAlreadyPresentException;
import com.usersecurity.exception.UserNotFoundException;
import com.usersecurity.model.User;
import com.usersecurity.service.UserServiceImpl;

@ExtendWith(MockitoExtension.class)
public class UserControllerTest {

    @Mock
    private UserServiceImpl userService;

    @InjectMocks
    private UserController userController;

    @Test
    public void testCreateUser_Success() throws UserAlreadyPresentException {
        // Arrange
        User user = new User();
        when(userService.createUser(any(User.class))).thenReturn(user);

        // Act
        ResponseEntity<?> responseEntity = userController.createUser(user);

        // Assert
        assert responseEntity.getStatusCode() == HttpStatus.CREATED;
    }

    @Test
    public void testCreateUser_UserAlreadyPresent() throws UserAlreadyPresentException {
        // Arrange
        User user = new User();
        when(userService.createUser(any(User.class))).thenThrow(new UserAlreadyPresentException("User already present"));

        // Act
        ResponseEntity<?> responseEntity = userController.createUser(user);

        // Assert
        assert responseEntity.getStatusCode() == HttpStatus.CONFLICT;
    }

    @Test
    public void testFindUserByMail_Success() throws UserNotFoundException {
        // Arrange
        String email = "test@example.com";
        User user = new User();
        when(userService.getByEmailId(email)).thenReturn(user);

        // Act
        ResponseEntity<?> responseEntity = userController.findUserByMail(email);

        // Assert
        assert responseEntity.getStatusCode() == HttpStatus.OK;
    }

    @Test
    public void testFindUserByMail_UserNotFound() throws UserNotFoundException {
        // Arrange
        String email = "test@example.com";
        when(userService.getByEmailId(email)).thenThrow(new UserNotFoundException("User not found"));

        // Act
        ResponseEntity<?> responseEntity = userController.findUserByMail(email);

        // Assert
        assert responseEntity.getStatusCode() == HttpStatus.NOT_FOUND;
    }

    @Test
    public void testUpdateUserByMail_Success() throws UserNotFoundException {
        // Arrange
        String email = "test@example.com";
        User user = new User();
        when(userService.updateByEmail(email, user)).thenReturn(user);

        // Act
        ResponseEntity<?> responseEntity = userController.updateUserByMail(email, user);

        // Assert
        assert responseEntity.getStatusCode() == HttpStatus.OK;
    }

    @Test
    public void testUpdateUserByMail_UserNotFound() throws UserNotFoundException {
        // Arrange
        String email = "test@example.com";
        User user = new User();
        when(userService.updateByEmail(email, user)).thenThrow(new UserNotFoundException("User not found"));

        // Act
        ResponseEntity<?> responseEntity = userController.updateUserByMail(email, user);

        // Assert
        assert responseEntity.getStatusCode() == HttpStatus.NOT_FOUND;
    }

    @Test
    public void testDeleteUser_Success() throws UserNotFoundException {
        // Arrange
        String email = "test@example.com";
        when(userService.deleteByEmailId(email)).thenReturn("User deleted successfully");

        // Act
        ResponseEntity<?> responseEntity = userController.deleteUser(email);

        // Assert
        assert responseEntity.getStatusCode() == HttpStatus.OK;
    }

    @Test
    public void testDeleteUser_UserNotFound() throws UserNotFoundException {
        // Arrange
        String email = "test@example.com";
        when(userService.deleteByEmailId(email)).thenThrow(new UserNotFoundException("User not found"));

        // Act
        ResponseEntity<?> responseEntity = userController.deleteUser(email);

        // Assert
        assert responseEntity.getStatusCode() == HttpStatus.NOT_FOUND;
    }
}

