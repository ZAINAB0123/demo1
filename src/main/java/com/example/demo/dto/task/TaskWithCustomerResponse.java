package com.example.demo.dto.task;

public record TaskWithCustomerResponse(
    Long id,
    String title,
    String customerName
    )
{
}
