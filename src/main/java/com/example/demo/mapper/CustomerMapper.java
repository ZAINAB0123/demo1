package com.example.demo.mapper;

import com.example.demo.dto.customer.*;
import com.example.demo.entity.Customer;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CustomerMapper {
    private final DealMapper dealMapper;

    public CustomerMapper(DealMapper dealMapper) {
        this.dealMapper = dealMapper;
    }

    public Customer toEntity(CustomerCreateRequest request) {
        return new Customer(
                request.name(),
                request.email(),
                request.phone()
        );

    }

    public CustomerResponse toResponse(Customer customer) {
        return new CustomerResponse(
                customer.getId(),
                customer.getName(),
                customer.getEmail(),
                customer.getPhone(),
                customer.getStatus(),
                customer.getCreatedAt(),
                customer.getVersion()
        );

    }

    public List<CustomerResponse> toResponseList(List<Customer> customers) {

        List<CustomerResponse> result = new ArrayList<>();

        for (Customer customer : customers) {

            CustomerResponse response = toResponse(customer);

            result.add(response);
        }

        return result;
    }

    public CustomerWithDealsResponse toResponseWithDeals(Customer customer) {
        return new CustomerWithDealsResponse(
                customer.getId(),
                customer.getName(),
                customer.getEmail(),
                customer.getDeals()
                        .stream()
                        .map(dealMapper::toResponse)
                        .toList()
        );
    }

    public List<CustomerWithDealsResponse> toCustomerWithDealsResponse(List<Customer> customers) {
        List<CustomerWithDealsResponse> result = new ArrayList<>();
        for (Customer customer : customers) {
            CustomerWithDealsResponse response = toResponseWithDeals(customer);
            result.add(response);
        }
        return result;
    }

}
