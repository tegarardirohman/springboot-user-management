package com.tegarardirohman.usermanagement.service;

import com.tegarardirohman.usermanagement.model.dto.request.LoginRequest;
import com.tegarardirohman.usermanagement.model.dto.request.RegisterRequest;
import com.tegarardirohman.usermanagement.model.dto.response.UserResponse;
import org.springframework.http.ResponseEntity;

public interface AuthService {

    ResponseEntity<UserResponse> login(LoginRequest request);
    ResponseEntity<UserResponse> register(RegisterRequest request);

}
