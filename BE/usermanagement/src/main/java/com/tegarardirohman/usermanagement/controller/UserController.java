package com.tegarardirohman.usermanagement.controller;

import com.tegarardirohman.usermanagement.model.entity.User;
import com.tegarardirohman.usermanagement.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody User user) {
        userService.register(user);
        return ResponseEntity.ok("User registered successfully!");
    }

    @PutMapping("/users/{id}")
    public ResponseEntity<String> update(@PathVariable UUID id, @RequestBody User user) {
        user.setId(id);
        userService.update(user);
        return ResponseEntity.ok("User updated successfully!");
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<String> delete(@PathVariable UUID id) {
        userService.delete(id);
        return ResponseEntity.ok("User deleted successfully!");
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<User> getUser(@PathVariable UUID id) {
        User user = userService.findById(id);
        return ResponseEntity.ok(user);
    }
}
