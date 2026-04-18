package com.abiodunelijah.users.services.impl;

import com.abiodunelijah.users.dtos.CreateUserRequest;
import com.abiodunelijah.users.dtos.UserUpdateRequest;
import com.abiodunelijah.users.entities.User;
import com.abiodunelijah.users.repositories.UserRepository;
import com.abiodunelijah.users.services.UserService;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public User createUser(CreateUserRequest request) {

        return Optional.of(request)
                .filter(user -> !userRepository.existsByEmail((request.getEmail())))
                .map(req -> {
                    User user = new User();
                    user.setFirstName(request.getFirstName());
                    user.setLastName(request.getLastName());
                    user.setEmail(request.getEmail());
                    user.setPassword(request.getPassword());

                    return userRepository.save(user);

                }).orElseThrow(() -> new EntityExistsException("User with " + request.getEmail() + " already exists."));
    }

    @Override
    public User updateUser(UserUpdateRequest request, Long userId) {
        return userRepository.findById(userId)
                .map(existingUser -> {
                    existingUser.setFirstName(request.getFirstName());
                    existingUser.setLastName(request.getLastName());
                    return userRepository.save(existingUser);
                }).orElseThrow(() -> new EntityNotFoundException("User not found."));
    }

    @Override
    public User getUserById(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("User not found."));
    }

    @Override
    public void deleteUser(Long userId) {
        userRepository.findById(userId).ifPresentOrElse(userRepository::delete, () -> {
            throw new EntityNotFoundException("User not found.");
        });
    }
}
