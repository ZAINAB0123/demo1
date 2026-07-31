package com.example.demo.service;

import com.example.demo.dto.customer.CustomerCreateRequest;
import com.example.demo.dto.customer.CustomerResponse;
import com.example.demo.dto.customer.CustomerUpdateRequest;
import com.example.demo.entity.Customer;
import com.example.demo.exception.CustomerNotFoundException;
import com.example.demo.mapper.CustomerMapper;
import com.example.demo.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerService {
    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;

    @Transactional
    public CustomerResponse createCustomer(CustomerCreateRequest request) {
        Customer customer = customerMapper.toEntity(request);
       Customer customerSaved = customerRepository.save(customer);
       return customerMapper.toResponse(customerSaved);
    }
    @Transactional
    public CustomerResponse updateCustomer(Long id, CustomerUpdateRequest request){
        Customer customer = customerRepository.findById(id).orElseThrow(() ->
                new CustomerNotFoundException("Customer not found"));
        customer.setName(request.name());
        return customerMapper.toResponse(customer);
    }
    @Transactional(readOnly = true)
    public CustomerResponse getCustomerById(Long id){
        return customerMapper.toResponse(customerRepository.findById(id).orElseThrow(() ->
                new CustomerNotFoundException("Customer not found")));
    }
    @Transactional(readOnly = true)
    public List<CustomerResponse> getAllCustomers(){
        return customerMapper.toResponseList(customerRepository.findAll());
    }
    @Transactional
    public void deleteCustomer(Long id){
       Customer customer = customerRepository.findById(id).orElseThrow(() ->
                new CustomerNotFoundException("Customer not found"));
       customerRepository.delete(customer);
    }
}
