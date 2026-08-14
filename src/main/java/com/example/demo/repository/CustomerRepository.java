package com.example.demo.repository;

import com.example.demo.dto.customer.CustomerListResponse;
import com.example.demo.entity.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CustomerRepository extends JpaRepository<Customer, Long>, JpaSpecificationExecutor<Customer> {
    // Вариант 1: обычное получение Customer
    List<Customer> findAll();

    // Вариант 2: JOIN FETCH - Customer + Deal
    @Query("""
            select distinct c
            from Customer c 
            left join fetch c.deals
            """)
    List<Customer> findAllWithDeals();

    // Вариант 3: EntityGraph - Customer + Deal
    @EntityGraph(attributePaths = {"deals"})
    @Query("select c from Customer c")
    List<Customer> findAllWithDealsByGraph();

    @Query("""
            select new com.example.demo.dto.customer.CustomerListResponse(
            c.name,
            c.deals
            )from Customer c
            join c.deals d
            """)
    Page<CustomerListResponse> findAllWithDealsByCustomerPage(Pageable pageable);

    @Query("""
            select c.id
            from Customer c
            order by c.id
            """)
    Page<Long> findCustomerIds(Pageable pageable);

    @Query("""
            select distinct c
            from Customer c
            left join fetch c.deals
            where c.id in :ids
            """)
    List<Customer> findAllWithDealsByIds(
            @Param("ids") List<Long> ids
    );
// встроен в JpaSpecificationExecutor(не нужно писать)
    Page<Customer> findAll(
            Specification<Customer> spec,
            Pageable pageable
    );

}
