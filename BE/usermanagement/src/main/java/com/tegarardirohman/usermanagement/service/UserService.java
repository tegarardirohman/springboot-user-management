package com.tegarardirohman.usermanagement.service;


import com.tegarardirohman.usermanagement.model.entity.User;

import java.util.UUID;

public interface UserService {

    void register(User user);

    void update(User user);

    void delete(UUID id);

    User findById(UUID id);
}
