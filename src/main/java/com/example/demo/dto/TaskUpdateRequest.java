package com.example.demo.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record TaskUpdateRequest(
        @NotBlank(message = "Title is required")
        @Size(max = 50, message = "Title must be less than 50 characters")
        String title,
        @NotBlank(message = "Description is required")
        String description
) {
}
