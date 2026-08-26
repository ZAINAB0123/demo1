package com.example.demo.dto.deal;

import java.math.BigDecimal;

public record DealListResponse(
       String title,
       BigDecimal amount,
       String customerName
) {
}
