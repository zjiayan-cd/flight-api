package com.example.flightapi.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Schema(description = "用户注册请求")
@Data
public class RegisterRequest {
	@Schema(description = "密码", example = "P@ssw0rd")
	@NotBlank(message = "Password is required")
    private String password;
    
	@Email(message = "Email format is invalid")
    @NotBlank(message = "Email is required")
    private String email;
    
	@Schema(description = "用户名", example = "testuser")
    @NotBlank(message="User name is required")
    private String username;
    private String phone;
    private String country;
}
