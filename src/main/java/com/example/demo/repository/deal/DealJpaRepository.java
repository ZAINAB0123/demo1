package com.example.demo.repository.deal;

import com.example.demo.dto.deal.DealListResponse;
import com.example.demo.entity.Deal;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface DealJpaRepository extends JpaRepository<Deal, Long>, JpaSpecificationExecutor<Deal> {
    @Query("""
select d
from Deal d
left join fetch d.customer
""")
    List<Deal> findAllWithCustomer();
    @EntityGraph(attributePaths = {"customer"})
    @Query("select d from Deal  d")
    List<Deal>findAllWithCustomerByGraph();

    //  Page<Deal> findAllWithCustomer(Pageable pageable);
    @Query("""
SELECT new com.example.demo.dto.deal.DealListResponse(
d.title,
d.amount,
c.name
)
from Deal d
join d.customer c
""")
    Page<DealListResponse> dealListResponseWithCustomer(Pageable pageable);

}
