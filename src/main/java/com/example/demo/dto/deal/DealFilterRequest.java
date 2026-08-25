package com.example.demo.dto.deal;
import com.example.demo.entity.DealStatus;

import java.math.BigDecimal;


public record DealFilterRequest(
    String title,
    DealStatus status,
    BigDecimal minAmount,
    BigDecimal maxAmount,
    String customerName
)
{
}
