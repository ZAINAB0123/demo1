package com.example.demo.dto.customer;

import com.example.demo.dto.deal.DealResponse;

import java.util.List;

public record CustomerWithDealsResponse(
        Long id,
        String name,
        String email,
        List<DealResponse> deals
) {

}
