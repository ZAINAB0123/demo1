package com.example.demo.service;

import com.example.demo.dto.deal.*;
import com.example.demo.entity.Deal;
import com.example.demo.exception.DealNotFoundException;
import com.example.demo.mapper.DealMapper;
import com.example.demo.repository.DealRepository;
import lombok.RequiredArgsConstructor;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@RequiredArgsConstructor
public class DealService {
    private final DealRepository dealRepository;
    private final DealMapper dealMapper;

    @Transactional
    public DealResponse createDeal(DealCreateRequest request) {
        Deal deal = dealMapper.toEntity(request);
        Deal savedDeal = dealRepository.save(deal);
        return dealMapper.toResponse(savedDeal);
    }

    @Transactional
    public DealResponse updateDeal(Long id, DealUpdateRequest request) {
        Deal deal = dealRepository.findById(id).orElseThrow(() ->
                new DealNotFoundException("Deal not found"));
        deal.setTitle(request.title());
        deal.setDescription(request.description());
        deal.setAmount(request.amount());
        return dealMapper.toResponse(deal);
    }

    @Transactional(readOnly = true)
    public DealResponse getDeal(Long id) {
        Deal deal = dealRepository.findById(id).orElseThrow(() ->
                new DealNotFoundException("Deal not found"));
        return dealMapper.toResponse(deal);
    }
@Transactional(readOnly = true)
public List<DealResponse> findAllDeals() {
return dealMapper.toResponseList(dealRepository.findAll());
}
@Transactional(readOnly = true)
public List<DealWithCustomerResponse> findAllDealsList() {
return dealMapper.toResponseFetchList(dealRepository.findAllWithCustomer());
}
@Transactional(readOnly = true)
public List<DealWithCustomerResponse>getAllDealsGraph() {
        return dealMapper.toResponseFetchList(dealRepository.findAllWithCustomerByGraph());
}
    @Transactional(readOnly = true)
    public Page<DealListResponse> getDealsPage(Pageable pageable) {
        return dealRepository.dealListResponseWithCustomer(pageable);
    }

    @Transactional
    public void deleteDeal(Long id) {
        Deal deal = dealRepository.findById(id).orElseThrow(() ->
                new DealNotFoundException("Deal not found"));
        dealRepository.delete(deal);
    }
}
