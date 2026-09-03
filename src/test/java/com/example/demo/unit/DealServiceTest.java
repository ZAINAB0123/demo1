package com.example.demo.unit;

import com.example.demo.dto.deal.DealCreateRequest;
import com.example.demo.dto.deal.DealResponse;
import com.example.demo.entity.Deal;
import com.example.demo.mapper.DealMapper;
import com.example.demo.repository.deal.DealRepository;
import com.example.demo.service.DealService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class DealServiceTest {
    @Mock
    private DealRepository dealRepository;
    @Mock
    private DealMapper dealMapper;
    @InjectMocks
    private DealService dealService;

    @Test
    void createDeal_shouldBeCreateAndReturnDeal() {
        DealCreateRequest request = mock(DealCreateRequest.class);
        Deal deal = new Deal();
        Deal dealSaved = new Deal();
        DealResponse response = mock(DealResponse.class);
        when(dealMapper.toEntity(request)).thenReturn(deal);
        when(dealRepository.save(deal)).thenReturn(dealSaved);
        when(dealMapper.toResponse(dealSaved)).thenReturn(response);
        DealResponse result = dealService.createDeal(request);
        assertNotNull(result);
        assertSame(response, result);
    }
}
