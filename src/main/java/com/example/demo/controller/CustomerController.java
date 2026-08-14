package com.example.demo.controller;

import com.example.demo.dto.customer.*;
import com.example.demo.exception.PageSizeException;
import com.example.demo.service.CustomerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customers")
@RequiredArgsConstructor
public class CustomerController {
    private final CustomerService customerService;

    @PostMapping
    public ResponseEntity<CustomerResponse> createCustomer(
            @Valid @RequestBody CustomerCreateRequest request) {
        CustomerResponse customerSave = customerService.createCustomer(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(customerSave);
    }

    @PutMapping("{/id}")
    public ResponseEntity<CustomerResponse> updateCustomer(
            @PathVariable Long id,
            @Valid @RequestBody CustomerUpdateRequest request
    ) {
        CustomerResponse customerUpdate = customerService.updateCustomer(id, request);
        return ResponseEntity.ok().body(customerUpdate);
    }

    @GetMapping("{/id}")
    public ResponseEntity<CustomerResponse> getCustomer(
            @PathVariable Long id
    ) {
        CustomerResponse response = customerService.getCustomerById(id);
        return ResponseEntity.ok().body(response);
    }

    @GetMapping
    public ResponseEntity<List<CustomerResponse>> getAllCustomers() {
        List<CustomerResponse> response = customerService.findAll();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/with-deals")
    public ResponseEntity<List<CustomerWithDealsResponse>> getAllCustomersWithDeals() {
        return ResponseEntity.ok(customerService.findAllFetch());
    }

    @GetMapping("/with-deals-graph")
    public ResponseEntity<List<CustomerWithDealsResponse>> getAllCustomersWithDealsGraph() {
        return ResponseEntity.ok(customerService.findAllGraph());
    }

    @GetMapping("/filter")
    @PageableDefault(size = 20)
    public Page<CustomerResponse> findAll(
            CustomerFilterRequest filter,
            Pageable pageable
    ) {
        if (pageable.getPageSize()>100){
            throw new PageSizeException("Page size is greater than 100");
        }
        return customerService.findAllFilter(
                filter,
                pageable
        );
    }
    @GetMapping("/page2")
    public ResponseEntity<Page<CustomerListResponse>> findAllPage(
            CustomerFilterRequest request,
            Pageable pageable) {
return ResponseEntity.ok(customerService.findAllWithCustomerByPage(pageable));
    }
@GetMapping("/page3")
public ResponseEntity<List<CustomerWithDealsResponse>> findAllPage2(
        CustomerFilterRequest request,
        Pageable pageable){
        return ResponseEntity.ok(customerService.findAll(pageable));
}
    @DeleteMapping("{/id}")
    public ResponseEntity<CustomerResponse> deleteCustomer(
            @PathVariable Long id
    ) {
        customerService.deleteCustomer(id);
        return ResponseEntity.noContent().build();
    }

}
