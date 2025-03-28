package com.dealservice.service;

import java.util.List;

import com.dealservice.model.Deal;

public interface DealService {
    Deal save(Deal deal);
    String deleteById(Long id);
    List<Deal> findAll();
    Deal findById(Long id);
    Deal updateById(Long id, Deal deal);
}