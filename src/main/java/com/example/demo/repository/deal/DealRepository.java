package com.example.demo.repository.deal;
import com.example.demo.dto.deal.DealListResponse;
import com.example.demo.entity.Deal;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.List;
import java.util.Optional;

public interface DealRepository {
    List<Deal> findAll();
    Deal save(Deal deal);
    Optional<Deal> findById(Long id);
    void delete(Deal deal);
    List<Deal> findAllWithCustomer();
    List<Deal>findAllWithCustomerByGraph();
    Page<DealListResponse> dealListResponseWithCustomer(Pageable pageable);

}
