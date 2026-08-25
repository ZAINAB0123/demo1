package com.example.demo.controller;

import com.example.demo.dto.deal.*;
import com.example.demo.service.DealService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/deals")
@RequiredArgsConstructor
public class DealController {
    private final DealService dealService;

    @PostMapping
    public ResponseEntity<DealResponse> createDeal(@Valid @RequestBody DealCreateRequest request) {
        DealResponse response = dealService.createDeal(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DealResponse> updateDeal(
            @PathVariable Long id,
            @Valid @RequestBody DealUpdateRequest request
    ) {
        DealResponse response = dealService.updateDeal(id, request);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DealResponse> getDealId(@PathVariable Long id) {
        DealResponse response = dealService.getDeal(id);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<List<DealResponse>> getAllDeals() {
        return ResponseEntity.ok(dealService.findAllDeals());
    }

    @GetMapping("/page")
    public ResponseEntity<Page<DealListResponse>> getDealsAll(Pageable pageable) {
        Page<DealListResponse> responsePage = dealService.getDealsPage(pageable);
        return ResponseEntity.ok(responsePage);
    }

    @GetMapping("/fetch")
    public ResponseEntity<List<DealWithCustomerResponse>> getAllDealsFetch() {
        return ResponseEntity.ok(dealService.findAllDealsList());
    }

    @GetMapping("/graph")
    public ResponseEntity<List<DealWithCustomerResponse>> getAllDealsGraph() {
        return ResponseEntity.ok(dealService.getAllDealsGraph());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<DealResponse> deleteDeal(@PathVariable Long id) {
        dealService.deleteDeal(id);
        return ResponseEntity.noContent().build();
    }
}
