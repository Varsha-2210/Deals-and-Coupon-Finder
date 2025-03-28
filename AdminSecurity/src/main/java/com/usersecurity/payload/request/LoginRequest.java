package com.usersecurity.payload.request;
 
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
public class LoginRequest 
{
	@NotBlank(message = "Username is required")
	@Size(max = 20, message = "Username must be at most 20 characters")
	private String username;
	@NotBlank(message = "Password is required")
    @Size(min = 8, message = "Password must be at least 8 characters")
	private String password;

	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
        this.username = username != null ? username.toLowerCase() : null;
    }
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
 
}