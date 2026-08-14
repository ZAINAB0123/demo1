package com.example.demo.dto.deal;
import com.example.demo.entity.DealStatus;
import java.time.LocalDateTime;

public record DealResponse(
        Long id,
        String title,
        String description,
        Double amount,
        DealStatus status,
        LocalDateTime createdAt,
        Long version
) {
}
