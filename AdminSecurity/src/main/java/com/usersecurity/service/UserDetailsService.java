package com.usersecurity.service;

public interface UserDetailsService {
	 // Method to load user details by username
    UserDetails loadUserByUsername(String username);
}
