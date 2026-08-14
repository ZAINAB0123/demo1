package com.example.demo.specification;

import com.example.demo.dto.deal.DealFilterRequest;
import com.example.demo.entity.Deal;
import org.springframework.data.jpa.domain.Specification;

public class DealSpecification {
    public static Specification<Deal> getSpecification(DealFilterRequest request){
        if (request.status()!=null){
            return (root, query, criteriaBuilder) ->
                    criteriaBuilder.equal(root.get("status"), request.status());
        }
        else {
            return null;
        }
    }

}
