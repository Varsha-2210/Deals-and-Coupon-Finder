package com.dealservice.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.dealservice.model.Deal;
import com.dealservice.service.DealService;

class DealControllerTest {

    @Mock
    private DealService dealService;

    @InjectMocks
    private DealController dealController;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testCreateDeal() {
        Deal deal = new Deal("Test Deal", 100, "Test Company", "test.jpg");
        when(dealService.save(any(Deal.class))).thenReturn(deal);

        Deal createdDeal = dealController.createDeal(deal);

        assertEquals(deal, createdDeal);
        verify(dealService, times(1)).save(any(Deal.class));
    }

    @Test
    public void testGetAllDeals() {
        List<Deal> deals = Arrays.asList(
                new Deal("Test Deal 1", 100, "Test Company", "test1.jpg"),
                new Deal("Test Deal 2", 200, "Test Company", "test2.jpg"));

        when(dealService.findAll()).thenReturn(deals);

        List<Deal> result = dealController.getAllDeals();

        assertEquals(deals.size(), result.size());
        assertEquals(deals, result);
        verify(dealService, times(1)).findAll();
    }

    @Test
    public void testGetDealById() {
        Deal deal = new Deal("Test Deal", 100, "Test Company", "test.jpg");
        when(dealService.findById(1L)).thenReturn(deal);

        Deal result = dealController.getDealById(1L);

        assertEquals(deal, result);
        verify(dealService, times(1)).findById(1L);
    }

    @Test
    public void testUpdateDealById() {
        Deal existingDeal = new Deal("Test Deal", 100, "Test Company", "test.jpg");
        Deal updatedDeal = new Deal("Updated Deal", 150, "Updated Company", "test.jpg");

        when(dealService.updateById(1L, updatedDeal)).thenReturn(updatedDeal);

        Deal result = dealController.updateDealById(1L, updatedDeal);

        assertEquals(updatedDeal, result);
        verify(dealService, times(1)).updateById(1L, updatedDeal);
    }
}