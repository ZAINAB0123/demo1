package com.example.demo.dto.deal;
import com.example.demo.entity.deal.DealStatus;

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
