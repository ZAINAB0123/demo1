package com.example.demo.dto.customer;

import com.example.demo.entity.CustomerStatus;


import java.time.LocalDateTime;

public record CustomerResponse(
     Long id,
     String name,
     String email,
     String phone,
     CustomerStatus status,
     LocalDateTime createdAt,
     Long version
) {
}
