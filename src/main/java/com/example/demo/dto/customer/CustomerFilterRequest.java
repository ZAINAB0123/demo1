package com.example.demo.dto.customer;
import com.example.demo.entity.customer.CustomerStatus;


public record CustomerFilterRequest(
        String name,
        CustomerStatus status,
        String title
) {
}
