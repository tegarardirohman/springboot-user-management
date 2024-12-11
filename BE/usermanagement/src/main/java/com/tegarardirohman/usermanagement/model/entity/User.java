package com.tegarardirohman.usermanagement.model.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
public class User {

    private UUID id; // UUID sebagai primary key
    private String email;
    private String password;
    private String name;
    private String surname;
    private String phone;
    private String role; // ROLE_USER, ROLE_ADMIN
    private String status; // ACTIVE, INACTIVE (untuk soft delete)
    private Long createdAt;
    private Long updatedAt;
}
