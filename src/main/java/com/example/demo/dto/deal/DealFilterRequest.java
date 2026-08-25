package com.example.demo.dto.deal;
import com.example.demo.entity.DealStatus;


public record DealFilterRequest(
    String title,
    DealStatus status,
    Double minAmount,
    Double maxAmount,
    String customerName
)
{
}
