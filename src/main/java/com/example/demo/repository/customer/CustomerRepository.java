package com.example.demo.repository.customer;

import com.example.demo.dto.customer.CustomerFilterRequest;
import com.example.demo.entity.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


import java.util.List;
import java.util.Optional;

public interface CustomerRepository {
    // Вариант 1: обычное получение Customer
    List<Customer> findAll();

    Customer save(Customer customer);

    Optional<Customer> findById(Long id);

    void delete(Customer customer);

    List<Customer> findAllWithDeals();

    List<Customer> findAllWithDealsByGraph();

    Page<Long> findCustomerIds(Pageable pageable);

    List<Customer> findAllWithDealsByIds(List<Long> ids);

    Page<Customer> findAll(CustomerFilterRequest filter, Pageable pageable);
}
