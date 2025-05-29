package com.example.flightapi.service;

import com.example.flightapi.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Service;

/*Spring Security 自定义用户加载逻辑*/
@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username)
        		.map(user -> org.springframework.security.core.userdetails.User.builder()
        				.username(user.getUsername())
        				.password(user.getPassword())
        				.roles("USER")
        				.build())
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
        
    }
}
