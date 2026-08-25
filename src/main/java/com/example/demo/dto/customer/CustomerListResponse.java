package com.example.demo.dto.customer;

import com.example.demo.entity.Deal;

import java.util.List;

public record CustomerListResponse(
        String name,
        List<Deal> deals
) {
}
