package com.tegarardirohman.usermanagement.service.impl;

import com.tegarardirohman.usermanagement.model.entity.User;
import com.tegarardirohman.usermanagement.repository.UserRepository;
import com.tegarardirohman.usermanagement.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.UUID;

@Service
@AllArgsConstructor
@Transactional
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;


    public void register(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword())); // Enkripsi password
        user.setId(UUID.randomUUID());
        user.setStatus("ACTIVE");
        user.setCreatedAt(System.currentTimeMillis());
        user.setUpdatedAt(System.currentTimeMillis());
        userRepository.save(user);
    }

    public void update(User user) {
        user.setUpdatedAt(System.currentTimeMillis());
        userRepository.update(user);
    }

    public void delete(UUID userId) {
        userRepository.softDelete(userId);
    }

    public User findById(UUID id) {
        return userRepository.findById(String.valueOf(id));
    }

    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }


}
