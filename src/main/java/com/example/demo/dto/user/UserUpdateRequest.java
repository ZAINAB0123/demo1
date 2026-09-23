package com.example.demo.dto.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record UserUpdateRequest(
        @NotBlank(message = "Name is required")
        @Size(max = 50, message = "Name must be less than 50 characters")
        String name,


        @NotBlank(message = "Email is required")
        @Email(message = "Invalid email format")
        String email,


        @NotBlank(message = "Phone is required")
        String phone
) {
}
