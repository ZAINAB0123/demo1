package com.example.demo.controller;

import com.example.demo.dto.customer.CustomerCreateRequest;
import com.example.demo.dto.customer.CustomerResponse;
import com.example.demo.dto.customer.CustomerUpdateRequest;
import com.example.demo.service.CustomerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
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
    ){
        CustomerResponse response = customerService.getCustomerById(id);
        return ResponseEntity.ok().body(response);
    }
    @GetMapping
    public ResponseEntity<List<CustomerResponse>> getAllCustomers(){
        List<CustomerResponse> response = customerService.getAllCustomers();
        return ResponseEntity.ok().body(response);
    }
    @DeleteMapping("{/id}")
    public ResponseEntity<CustomerResponse> deleteCustomer(
            @PathVariable Long id
    ){
        customerService.deleteCustomer(id);
        return ResponseEntity.noContent().build();
    }

}
