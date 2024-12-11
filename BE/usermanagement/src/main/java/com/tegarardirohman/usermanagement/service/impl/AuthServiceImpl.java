package com.tegarardirohman.usermanagement.service.impl;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.tegarardirohman.usermanagement.model.dto.request.LoginRequest;
import com.tegarardirohman.usermanagement.model.dto.request.RegisterRequest;
import com.tegarardirohman.usermanagement.model.dto.response.UserResponse;
import com.tegarardirohman.usermanagement.model.entity.User;
import com.tegarardirohman.usermanagement.repository.UserRepository;
import com.tegarardirohman.usermanagement.service.AuthService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.UUID;

@Service
public class AuthServiceImpl implements AuthService {

    private UserRepository userRepository;
    private BCryptPasswordEncoder passwordEncoder;

    @Value("${jwt.secret}")
    private String secret;

    public void AuthService(UserRepository userRepository, BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public AuthServiceImpl(UserRepository userRepository, BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public String register(User user) {
        // Hash the password
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setId(UUID.randomUUID());
        user.setStatus("ACTIVE");
        user.setCreatedAt(System.currentTimeMillis());
        user.setUpdatedAt(System.currentTimeMillis());

        userRepository.save(user);
        return "User registered successfully";
    }

    public String login(String email, String password) {
        User user = userRepository.findByEmail(email);

        if (user != null && passwordEncoder.matches(password, user.getPassword())) {
            return generateToken(user);
        } else {
            throw new RuntimeException("Invalid credentials");
        }
    }

    private String generateToken(User user) {
        return JWT.create()
                .withSubject(user.getEmail())
                .withExpiresAt(new Date(System.currentTimeMillis() + 1000 * 60 * 60)) // 1 hour
                .withClaim("role", user.getRole())
                .sign(Algorithm.HMAC512(secret.getBytes()));
    }

    @Override
    public ResponseEntity<UserResponse> login(LoginRequest request) {
        return null;
    }

    @Override
    public ResponseEntity<UserResponse> register(RegisterRequest request) {
        return null;
    }
}
