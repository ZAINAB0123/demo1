package com.example.demo;

import com.example.demo.dto.customer.CustomerFilterRequest;
import com.example.demo.entity.Customer;
import com.example.demo.repository.CustomerJpaRepository;
import com.example.demo.repository.CustomerRepository;
import com.example.demo.specification.CustomerSpecification;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class CustomerRepositoryAdapter
        implements CustomerRepository {

    private final CustomerJpaRepository repository;

    @Override
    public Customer save(Customer customer) {
        return repository.save(customer);
    }

    @Override
    public Optional<Customer> findById(Long id) {
        return repository.findById(id);
    }
    @Override
    public List<Customer> findAll() {
        return repository.findAll();
    }
    @Override
    public void delete(Customer customer) {
        repository.delete(customer);
    }
    @Override
    public List<Customer> findAllWithDeals() {
        return repository.findAllWithDeals();
    }

    @Override
    public List<Customer> findAllWithDealsByGraph() {
        return repository.findAllWithDealsByGraph();
    }

    @Override
    public Page<Long> findCustomerIds(Pageable pageable) {
        return repository.findCustomerIds(pageable);
    }

    @Override
    public List<Customer> findAllWithDealsByIds(List<Long> ids) {
        return repository.findAllWithDealsByIds(ids);
    }

    @Override
    public Page<Customer> findAll(
            CustomerFilterRequest filter,
            Pageable pageable
    ) {
        Specification<Customer> specification =
                CustomerSpecification.getSpecification(filter);

        return repository.findAll(specification, pageable);
    }
}
