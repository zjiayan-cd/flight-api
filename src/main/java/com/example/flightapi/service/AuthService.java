package com.example.flightapi.service;

import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.example.flightapi.dto.LoginRequest;
import com.example.flightapi.dto.LoginResponse;
import com.example.flightapi.dto.RegisterRequest;
import com.example.flightapi.entity.User;
import com.example.flightapi.repository.UserRepository;
import com.example.flightapi.security.JwtTokenProvider;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;

    public void register(RegisterRequest request) {
        // 检查用户名冲突
        if (userRepository.findByUsername(request.getUsername()).isPresent()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "username.taken");
        }

        // 检查邮箱冲突
        if (userRepository.findByEmail(request.getEmail()).isPresent()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Email is already registered");
        }

        // 构建新用户对象
        User user = User.builder()
                .username(request.getUsername())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword())) // 加密后存储
                .country(request.getCountry())
                .phone(request.getPhone())
                .build();

        // 保存用户
        userRepository.save(user);
    }
    
    public LoginResponse login(LoginRequest request) {
    	User user = userRepository.findByUsername(request.getUsername())
    			.orElseThrow(() -> new ResponseStatusException(HttpStatus.UNAUTHORIZED, "login.invalid"));
    	
    	if(!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
    		throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "login.invalid");
    	}
    	
    	String token = jwtTokenProvider.generateToken(user);
    	return new LoginResponse(token, user.getUsername(), user.getEmail());
    }
}
