package com.usersecurity.service;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;

public interface UserDetails {
	// Method to retrieve the authorities granted to the user
    Collection<? extends GrantedAuthority> getAuthorities();

    // Method to retrieve the password used to authenticate the user
    String getPassword();

    // Method to retrieve the username used to authenticate the user
    String getUsername();

    // Method to determine whether the user's account has expired
    boolean isAccountNonExpired();

    // Method to determine whether the user is locked or unlocked
    boolean isAccountNonLocked();

    // Method to determine whether the user's credentials (password) has expired
    boolean isCredentialsNonExpired();

    // Method to determine whether the user is enabled or disabled
    boolean isEnabled();
}
