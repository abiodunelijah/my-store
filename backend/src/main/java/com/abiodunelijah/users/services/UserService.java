package com.abiodunelijah.users.services;

import com.abiodunelijah.users.dtos.CreateUserRequest;
import com.abiodunelijah.users.dtos.UserUpdateRequest;
import com.abiodunelijah.users.entities.User;

public interface UserService {
    User createUser(CreateUserRequest request);

    User updateUser(UserUpdateRequest request, Long userId);

    User getUserById(Long userId);

    void deleteUser(Long userId);

}
