package com.example.demo.specification;

import com.example.demo.dto.customer.CustomerFilterRequest;
import com.example.demo.entity.Customer;
import com.example.demo.entity.Deal;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;


public class CustomerSpecification {
    public static Specification<Customer> getSpecification(
            CustomerFilterRequest request) {
        return (root, query, cb) -> {

            // убираем дубли из-за OneToMany
            query.distinct(true);
            Predicate predicate = cb.conjunction();

            // фильтр по имени Customer
            if (StringUtils.hasText(request.name())) {
                predicate = cb.and(
                        predicate,
                        cb.like(cb.lower(
                                        root.get("name")),
                                "%" + request.name().toLowerCase() + "%"
                        )
                );
            }
            // фильтр по статусу Customer
            if (request.status() != null) {
                predicate = cb.and(
                        predicate,
                        cb.equal(
                                root.get("status"),
                                request.status()
                        )
                );
            }
            // фильтр по названию Deal

            if (StringUtils.hasText(request.title())) {
                query.distinct(true);
                Join<Customer, Deal> deal =
                        root.join("deals");
                predicate = cb.and(
                        predicate,
                        cb.like(
                                cb.lower(
                                        deal.get("title")),
                                "%" + request.title().toLowerCase() + "%"
                        )
                );
            }
            return predicate;
        };
    }
}




