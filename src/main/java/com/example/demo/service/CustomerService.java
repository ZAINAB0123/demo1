package com.example.demo.service;

import com.example.demo.dto.customer.*;
import com.example.demo.entity.Customer;
import com.example.demo.exception.CustomerNotFoundException;
import com.example.demo.mapper.CustomerMapper;
import com.example.demo.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
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
    public CustomerResponse updateCustomer(CustomerUpdateRequest request){
        Customer customer = customerRepository.findById(request.id()).orElseThrow(() ->
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
    public List<CustomerResponse> findAll(){
      return customerMapper.toResponseList(customerRepository.findAll());
    }

    @Transactional(readOnly = true)
    public List<CustomerWithDealsResponse> findAllFetch(){
        return customerMapper.toCustomerWithDealsResponse(customerRepository.findAllWithDeals());
    }

    @Transactional(readOnly = true)
    public List<CustomerWithDealsResponse>findAllGraph(){
      return customerMapper.toCustomerWithDealsResponse(customerRepository.findAllWithDealsByGraph());
    }

    @Transactional(readOnly = true, propagation = Propagation.MANDATORY )  // MANDATORY- требует существующую транзакцию
    public List<CustomerWithDealsResponse> findAll(Pageable pageable) {
        Page<Long> page = customerRepository.findCustomerIds(pageable);
        List<Customer> customers =
                customerRepository.findAllWithDealsByIds(page.getContent());
        return customerMapper.toCustomerWithDealsResponse(customers);
    }

    @Transactional(readOnly = true)
    public Page<CustomerResponse> findAllFilter(
            CustomerFilterRequest filter,
            Pageable pageable
    ){
Page<Customer> page = customerRepository.findAll(filter, pageable);
return  page.map(customerMapper::toResponse);
    }

    @Transactional
    public void deleteCustomer(Long id){
       Customer customer = customerRepository.findById(id).orElseThrow(() ->
                new CustomerNotFoundException("Customer not found"));
       customerRepository.delete(customer);
    }
}
