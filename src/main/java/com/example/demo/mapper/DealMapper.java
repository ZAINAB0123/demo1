package com.example.demo.mapper;

import com.example.demo.dto.deal.DealCreateRequest;
import com.example.demo.dto.deal.DealWithCustomerResponse;
import com.example.demo.dto.deal.DealResponse;
import com.example.demo.entity.Deal;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DealMapper {

    public Deal toEntity(DealCreateRequest request) {
        return new Deal(
                request.title(),
                request.description(),
                request.amount()
        );
    }

    public DealResponse toResponse(Deal deal) {
        return new DealResponse(
                deal.getId(),
                deal.getTitle(),
                deal.getDescription(),
                deal.getAmount(),
                deal.getStatus(),
                deal.getCreatedAt(),
                deal.getVersion()
        );
    }

    public List<DealResponse> toResponseList(List<Deal> deals) {
        List<DealResponse> dealList = new ArrayList<>();
        for (Deal deal1 : deals) {
            dealList.add(toResponse(deal1));
        }
        return dealList;
    }

    public DealWithCustomerResponse toDealWithCustomerResponse(Deal deal) {
        return new DealWithCustomerResponse(
                deal.getId(),
                deal.getTitle(),
                deal.getCustomer().getName()
        );
    }

    public List<DealWithCustomerResponse> toResponseFetchList(List<Deal> deals) {
        List<DealWithCustomerResponse> dealList = new ArrayList<>();
        for (Deal deal : deals) {
            dealList.add(toDealWithCustomerResponse(deal));
        }
        return dealList;
    }
}
