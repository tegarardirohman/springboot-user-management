package com.tegarardirohman.usermanagement.controller;

import com.tegarardirohman.usermanagement.model.entity.User;
import com.tegarardirohman.usermanagement.repository.UserRepository;
import com.tegarardirohman.usermanagement.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/register")
    public String register(@RequestBody User user) {
        user.setDeleted(false);
        userRepository.save(user);
        return "User  registered successfully!";
    }

    @PostMapping("/login")
    public String login(@RequestBody User user) {
        User foundUser  = userRepository.findByUsername(user.getUsername());
        if (foundUser  != null && foundUser .getPassword().equals(user.getPassword())) {
            return jwtUtil.generateToken(foundUser .getUsername());
        }
        throw new RuntimeException("Invalid credentials");
    }

    @GetMapping("/users")
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
}