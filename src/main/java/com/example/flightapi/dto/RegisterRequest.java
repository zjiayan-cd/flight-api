package com.example.flightapi.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class RegisterRequest {
	@NotBlank(message = "Password is required")
    private String password;
    
	@Email(message = "Email format is invalid")
    @NotBlank(message = "Email is required")
    private String email;
    
    @NotBlank(message="User name is required")
    private String username;
    private String phone;
    private String country;
}
