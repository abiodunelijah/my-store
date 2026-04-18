package com.abiodunelijah.users.controllers;

import com.abiodunelijah.mappers.UserMapper;
import com.abiodunelijah.response.ApiResponse;
import com.abiodunelijah.users.dtos.CreateUserRequest;
import com.abiodunelijah.users.dtos.UserDto;
import com.abiodunelijah.users.dtos.UserUpdateRequest;
import com.abiodunelijah.users.entities.User;
import com.abiodunelijah.users.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/users")
public class UserController {

    private final UserService userService;
    private final UserMapper userMapper;

    @GetMapping("/{userId}")
    public ResponseEntity<ApiResponse> getUserById(@PathVariable Long userId) {
        User user = userService.getUserById(userId);
        UserDto userDto = userMapper.convertUserToDto(user);
        return ResponseEntity.ok(new ApiResponse("user found.", userDto));
    }

    @PostMapping("/create")
    public ResponseEntity<ApiResponse> createUser(@RequestBody CreateUserRequest request) {
        User user = userService.createUser(request);
        UserDto userDto = userMapper.convertUserToDto(user);
        return ResponseEntity.ok(new ApiResponse("User created.", userDto));
    }

    @PutMapping("/{userId}/update")
    public ResponseEntity<ApiResponse> updateUser(@RequestBody UserUpdateRequest request, @PathVariable Long userId) {
        User user = userService.updateUser(request, userId);
        UserDto userDto = userMapper.convertUserToDto(user);
        return ResponseEntity.ok(new ApiResponse("user updated successfully.", userDto));
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<ApiResponse> deleteUser(@PathVariable Long userId) {

        userService.deleteUser(userId);
        return ResponseEntity.ok(new ApiResponse("user successfully deleted.", null));
    }
}
