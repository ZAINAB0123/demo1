package com.example.demo.dto.deal;
import com.example.demo.entity.deal.DealStatus;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record DealResponse(
        Long id,
        String title,
        String description,
        BigDecimal amount,
        DealStatus status,
        LocalDateTime createdAt,
        Long version
) {
}
