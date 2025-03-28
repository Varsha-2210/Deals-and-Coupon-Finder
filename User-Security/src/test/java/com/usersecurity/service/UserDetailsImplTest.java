package com.usersecurity.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Collection;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import com.usersecurity.model.User;
import com.usersecurity.service.UserDetailsImpl;

@ExtendWith(MockitoExtension.class)
public class UserDetailsImplTest {

    @InjectMocks
    private UserDetailsImpl userDetails;

    @Test
    void testGetAuthorities() {
        // Arrange
        Long userId = 1L;  // Use Long as user_id is a Long
        String username = "testUser";
        String email = "test@example.com";
        String password = "testPassword";

        // Create a UserDetailsImpl object using the correct constructor
        userDetails = new UserDetailsImpl(userId, username, email, password);

        // Act
        Collection<? extends GrantedAuthority> authorities = userDetails.getAuthorities();

        // Assert
        assertEquals(1, authorities.size()); // Ensure only one authority is returned
        assertTrue(authorities.contains(new SimpleGrantedAuthority("ROLE_USER"))); // Ensure the authority ROLE_USER is present
    }

    @Test
    void testEqualsWithSameObject() {
        // Arrange
        UserDetailsImpl user1 = new UserDetailsImpl(1L, "username", "email@example.com", "password");

        // Act
        boolean result = user1.equals(user1);

        // Assert
        assertTrue(result); // Same object should be equal
    }

    @Test
    void testEqualsWithEqualObjects() {
        // Arrange
        UserDetailsImpl user1 = new UserDetailsImpl(1L, "username", "email@example.com", "password");
        UserDetailsImpl user2 = new UserDetailsImpl(1L, "username", "email@example.com", "password");

        // Act
        boolean result = user1.equals(user2);

        // Assert
        assertTrue(result); // Equal objects should be equal
    }

    @Test
    void testEqualsWithDifferentObjects() {
        // Arrange
        UserDetailsImpl user1 = new UserDetailsImpl(1L, "username", "email@example.com", "password");
        UserDetailsImpl user2 = new UserDetailsImpl(2L, "username", "email@example.com", "password");

        // Act
        boolean result = user1.equals(user2);

        // Assert
        assertFalse(result); // Different objects should not be equal
    }

    @Test
    void testHashCodeWithEqualObjects() {
        // Arrange
        UserDetailsImpl user1 = new UserDetailsImpl(1L, "username", "email@example.com", "password");
        UserDetailsImpl user2 = new UserDetailsImpl(1L, "username", "email@example.com", "password");

        // Act
        int hashCode1 = user1.hashCode();
        int hashCode2 = user2.hashCode();

        // Assert
        assertEquals(hashCode1, hashCode2); // Equal objects should have the same hash code
    }

    @Test
    void testHashCodeWithDifferentObjects() {
        // Arrange
        UserDetailsImpl user1 = new UserDetailsImpl(1L, "username", "email@example.com", "password");
        UserDetailsImpl user2 = new UserDetailsImpl(2L, "username", "email@example.com", "password");

        // Act
        int hashCode1 = user1.hashCode();
        int hashCode2 = user2.hashCode();

        // Assert
        assertNotEquals(hashCode1, hashCode2); // Different objects should have different hash codes
    }
}
