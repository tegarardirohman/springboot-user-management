package com.tegarardirohman.usermanagement.model.dto.request;

import com.tegarardirohman.usermanagement.model.entity.Gender;
import com.tegarardirohman.usermanagement.model.entity.Role;
import lombok.Data;

@Data
public class RegisterRequest {
    private String email;
    private String password;
    private String name;
    private String surname;
    private String phone;
    private Role role;
    private Gender gender;
    private Long birthday;
}
