package com.usersecurity.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long user_id;  // Use Long for BIGINT type in the database

    @NotBlank(message = "Username is required")
    @Size(max = 20, message = "Username must be at most 20 characters")
    private String username;

    @NotBlank(message = "Email is required")
    @Size(max = 30, message = "Email must be at most 30 characters")
    @Email(message = "Invalid email format")
    @Pattern(regexp = "^[a-zA-Z0-9._%+-]+@(gmail\\.com|yahoo\\.com|hotmail\\.com)$", message = "Email must be a Gmail, Yahoo, or Hotmail address")
    private String email;

    @NotBlank(message = "Password is required")
    @Size(min = 8, message = "Password must be at least 8 characters")
    private String password;

    @Pattern(regexp = "[6-9]\\d{9}", message = "Phone number must start with a number between 6 to 9 and be 10 digits long")
    private String phone;

    // Default constructor
    public User() {
        super();
    }

    // Parameterized constructor
    public User(String username, String email, String password, String phone) {
        super();
        this.username = username;
        this.email = email;
        this.password = password;
        this.phone = phone;
    }

    // Getters and setters
    public Long getUser_id() {
        return user_id;
    }

    public void setUser_id(Long user_id) {
        this.user_id = user_id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    // toString method
    @Override
    public String toString() {
        return "User [user_id=" + user_id + ", username=" + username + ", email=" + email + ", password=" + password
                + ", phone=" + phone + "]";
    }
}
