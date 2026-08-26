package com.example.demo.specification;

import com.example.demo.dto.deal.DealFilterRequest;
import com.example.demo.entity.Customer;
import com.example.demo.entity.Deal;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.Predicate;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;


public class DealSpecification {
    public static Specification<Deal> getSpecification(DealFilterRequest request) {

        return (root, query, criteriaBuilder) -> {

            Predicate predicate = criteriaBuilder.conjunction();
            if (StringUtils.hasText(request.title())) {
                predicate = criteriaBuilder.and(
                        predicate,
                        criteriaBuilder.like(criteriaBuilder.lower(root.get("title")),
                                "%" + request.title().toLowerCase() + "%")
                );
            }
            if (request.status() != null) {
                predicate = criteriaBuilder.and(
                        predicate,
                        criteriaBuilder.equal(root.get("status"),
                                request.status())
                );
            }
            if (request.minAmount() != null) {
                predicate = criteriaBuilder.and(
                        predicate,
                        criteriaBuilder.greaterThanOrEqualTo(root.get("amount"),
                                request.minAmount())
                );
            }
            if (request.maxAmount() != null) {
                predicate = criteriaBuilder.and(
                        predicate,
                        criteriaBuilder.lessThanOrEqualTo(root.get("amount"), request.maxAmount())
                );
            }
            if (StringUtils.hasText(request.customerName())) {
                Join<Deal, Customer> customerJoin = root.join("customer");
                predicate = criteriaBuilder.and(
                        predicate,
                        criteriaBuilder.like(criteriaBuilder.lower(customerJoin.get("name")),
                                "%" + request.customerName().toLowerCase() + "%"
                        )
                );
            }
            return predicate;
        };
    }
}
