package com.usersecurity.service;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.UserDetails;

import com.usersecurity.model.User;
import com.usersecurity.repository.UserRepository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@SpringBootTest
public class UserDetailsServiceImplTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserDetailsServiceImpl userDetailsService;

    @Test
    void testLoadUserByUsername() {
        // Arrange
        String username = "testUser";
        User user = new User(username, "test@example.com", "password", "1234567890");
        when(userRepository.findByUsername(username)).thenReturn(user);

        // Act
        UserDetails userDetails = userDetailsService.loadUserByUsername(username);

        // Assert
        assertEquals(username, userDetails.getUsername());
        // Add more assertions based on your UserDetailsImpl implementation
    }

    @Test
    void testLoadUserByUsername_UserNotFound() {
        // Arrange
        String username = "nonexistent_user";
        // Mocking behavior of findByUsername method to return null
        when(userRepository.findByUsername(username)).thenReturn(null);
    }


}
