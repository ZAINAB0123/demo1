package com.example.demo.dto.deal;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

public record DealCreateRequest(
        @NotBlank(message = "Title is required")
        String title,
        @NotBlank(message = "Description is required")
        @Size(min = 5, max = 100)
        String description,
        @NotNull
        @Positive(message = "Amount is required")
        Double amount) {
}
