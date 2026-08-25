package com.example.demo.controller;

import com.example.demo.dto.customer.*;
import com.example.demo.entity.CustomerStatus;
import com.example.demo.service.CustomerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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
    @Operation(
            summary = "Create customer",
            description = "Creates a new customer"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Customer created"),
            @ApiResponse(responseCode = "400", description = "Invalid request")
    })
    public ResponseEntity<CustomerResponse> createCustomer(
            @Valid @RequestBody CustomerCreateRequest request) {
        CustomerResponse customerSave = customerService.createCustomer(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(customerSave);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CustomerResponse> updateCustomer(
            @Valid @RequestBody CustomerUpdateRequest request
    ) {
        CustomerResponse customerUpdate = customerService.updateCustomer(request);
        return ResponseEntity.ok().body(customerUpdate);
    }

    @GetMapping("/{id}")
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
    public Page<CustomerResponse> findAll(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) CustomerStatus status,
            @RequestParam(required = false) String title,

            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size,
            @RequestParam(defaultValue = "id,asc") String sort
    ) {
        CustomerFilterRequest filter =
                new CustomerFilterRequest(name, status, title);

        String[] sortParts = sort.split(",");

        Sort.Direction direction =
                sortParts.length > 1
                        ? Sort.Direction.fromString(sortParts[1])
                        : Sort.Direction.ASC;

        Pageable pageable = PageRequest.of(
                page,
                size,
                Sort.by(direction, sortParts[0])
        );

        return customerService.findAllFilter(filter, pageable);
    }
@GetMapping("/page3")
public ResponseEntity<List<CustomerWithDealsResponse>> findAllPage2(
        CustomerFilterRequest request,
        Pageable pageable){
        return ResponseEntity.ok(customerService.findAll(pageable));
}
    @DeleteMapping("/{id}")
    public ResponseEntity<CustomerResponse> deleteCustomer(
            @PathVariable Long id
    ) {
        customerService.deleteCustomer(id);
        return ResponseEntity.noContent().build();
    }
}

