package com.tegarardirohman.usermanagement.model.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserResponse {
    private UUID id;
    private String email;
    private String name;
    private String surname;
    private String phone;
    private String role;
    private String gender;
    private Long birthday;
}
