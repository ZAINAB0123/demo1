package com.example.demo.controller;

import com.example.demo.dto.user.ChangePasswordRequest;
import com.example.demo.dto.user.UserCreateRequest;
import com.example.demo.dto.user.UserResponse;
import com.example.demo.dto.user.UserUpdateRequest;
import com.example.demo.service.UserService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PutMapping("/{id}")
    public ResponseEntity<UserResponse> updateUser(
            @PathVariable Long id,
            @Valid @RequestBody UserUpdateRequest request
    ) {
        UserResponse response = userService.updateUser(id, request);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponse> getUser(@PathVariable Long id) {
        UserResponse response = userService.getUser(id);
        return ResponseEntity.ok(response);
    }
    @GetMapping
    public ResponseEntity<List<UserResponse>> getAllUsers() {
        List<UserResponse> responseList = userService.getAll();
        return ResponseEntity.ok(responseList);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }
    @GetMapping("/name/{name}")
    public ResponseEntity<UserResponse> getUserByName(@PathVariable String name) {
        UserResponse response = userService.getUserByName(name);
        return ResponseEntity.ok(response);
    }
    @GetMapping("/with-deals")
    public ResponseEntity<List<UserResponse>> getAllDeals() {
        List<UserResponse> userResponseList = userService.getAllByDeals();
        return  ResponseEntity.ok(userResponseList);
    }
    @SecurityRequirement(name = "bearerAuth")
    @PutMapping("/password")
    public ResponseEntity<Void> changePassword(
            @Valid @RequestBody ChangePasswordRequest request) {
        userService.changePassword(request);
        return ResponseEntity.noContent().build();
    }
}
