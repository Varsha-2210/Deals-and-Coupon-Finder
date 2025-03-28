package com.dealservice.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.dealservice.exception.DealAlreadyExistsException;
import com.dealservice.exception.DealNotFoundException;
import com.dealservice.model.Deal;
import com.dealservice.repository.DealRepository;

class DealServiceImplTest {
    @Mock
    private DealRepository dealRepository;

    @InjectMocks
    private DealServiceImpl dealService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    
    @Test
    public void testDeleteById_Success() {
        Deal deal = new Deal("Test Deal", 100, "Test Company", "test.jpg");
        when(dealRepository.findById(1L)).thenReturn(Optional.of(deal));

        String result = dealService.deleteById(1L);

        assertEquals("Deal with ID 1 has been deleted", result);
        verify(dealRepository, times(1)).findById(1L);
        verify(dealRepository, times(1)).deleteById(1L);
    }

    @Test
    public void testDeleteById_NotFound() {
        when(dealRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(DealNotFoundException.class, () -> {
            dealService.deleteById(1L);
        });

        verify(dealRepository, times(1)).findById(1L);
        verify(dealRepository, never()).deleteById(anyLong());
    }

    @Test
    public void testFindAll() {
        List<Deal> deals = new ArrayList<>();
        deals.add(new Deal("Test Deal 1", 100, "Test Company", "test1.jpg"));
        deals.add(new Deal("Test Deal 2", 200, "Test Company", "test2.jpg"));
        when(dealRepository.findAll()).thenReturn(deals);

        List<Deal> result = dealService.findAll();

        assertEquals(deals.size(), result.size());
        assertEquals(deals, result);
        verify(dealRepository, times(1)).findAll();
    }

    @Test
    public void testFindById_Success() {
        Deal deal = new Deal("Test Deal", 100, "Test Company", "test.jpg");
        when(dealRepository.findById(1L)).thenReturn(Optional.of(deal));

        Deal result = dealService.findById(1L);

        assertEquals(deal, result);
        verify(dealRepository, times(1)).findById(1L);
    }

    @Test
    public void testFindById_NotFound() {
        when(dealRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(DealNotFoundException.class, () -> {
            dealService.findById(1L);
        });

        verify(dealRepository, times(1)).findById(1L);
    }

    @Test
    public void testUpdateById_Success() {
        Deal existingDeal = new Deal("Test Deal", 100, "Test Company", "test.jpg");
        Deal updatedDeal = new Deal("Updated Deal", 150, "Updated Company", "test.jpg");
        when(dealRepository.findById(1L)).thenReturn(Optional.of(existingDeal));
        when(dealRepository.save(existingDeal)).thenReturn(updatedDeal);

        Deal result = dealService.updateById(1L, updatedDeal);

        assertEquals(updatedDeal, result);
        verify(dealRepository, times(1)).findById(1L);
        verify(dealRepository, times(1)).save(existingDeal);
    }

    @Test
    public void testUpdateById_NotFound() {
        Deal updatedDeal = new Deal("Updated Deal", 150, "Updated Company", "test.jpg");
        when(dealRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(DealNotFoundException.class, () -> {
            dealService.updateById(1L, updatedDeal);
        });

        verify(dealRepository, times(1)).findById(1L);
        verify(dealRepository, never()).save(any(Deal.class));
    }
}