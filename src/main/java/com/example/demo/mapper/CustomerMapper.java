package com.example.demo.mapper;

import com.example.demo.dto.customer.CustomerCreateRequest;
import com.example.demo.dto.customer.CustomerResponse;
import com.example.demo.entity.Customer;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CustomerMapper {
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
    public List<CustomerResponse> toResponseList(List<Customer> customers){

        List<CustomerResponse> result = new ArrayList<>();

        for(Customer customer : customers){

            CustomerResponse response = toResponse(customer);

            result.add(response);
        }

        return result;
    }
}
