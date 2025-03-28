package com.dealservice.service;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dealservice.exception.DealAlreadyExistsException;
import com.dealservice.exception.DealNotFoundException;
import com.dealservice.model.Deal;
import com.dealservice.repository.DealRepository;

@Service
public class DealServiceImpl implements DealService {
    private static final Logger logger = LoggerFactory.getLogger(DealServiceImpl.class);

    @Autowired
    private DealRepository dealRepository;

    @Override
    public Deal save(Deal deal) {
        Long dealId = deal.getDealId();
        if (dealId != null && dealRepository.existsById(dealId)) {
            String errorMessage = "Deal with ID " + dealId + " already exists";
            logger.error(errorMessage);
            throw new DealAlreadyExistsException(errorMessage);
        }
        logger.info("Saving new deal with ID: {}", dealId);
        return dealRepository.save(deal);
    }

    @Override
    public String deleteById(Long id) {
        Optional<Deal> optionalDeal = dealRepository.findById(id);
        if (!optionalDeal.isPresent()) {
            String errorMessage = "Deal with ID: " + id + " not found";
            logger.error(errorMessage);
            throw new DealNotFoundException(errorMessage);
        }
        try {
            dealRepository.deleteById(id);
            logger.info("Deal with ID {} has been deleted", id);
            return "Deal with ID " + id + " has been deleted";
        } catch (Exception e) {
            String errorMessage = "Failed to delete deal with ID: " + id;
            logger.error(errorMessage, e);
            throw new RuntimeException(errorMessage, e);
        }
    }

    @Override
    public List<Deal> findAll() {
        logger.info("Retrieving all deals");
        return dealRepository.findAll();
    }

    @Override
    public Deal findById(Long id) {
        logger.info("Finding deal with ID: {}", id);
        Optional<Deal> deal = dealRepository.findById(id);
        if (deal.isPresent()) {
            return deal.get();
        } else {
            String errorMessage = "Deal with ID " + id + " not found";
            logger.error(errorMessage);
            throw new DealNotFoundException(errorMessage);
        }
    }

    @Override
    public Deal updateById(Long id, Deal deal) {
        Deal existingDeal = findById(id);
        if (existingDeal == null) {
            String errorMessage = "Deal with ID " + id + " not found";
            logger.error(errorMessage);
            throw new DealNotFoundException(errorMessage);
        }
        existingDeal.setName(deal.getName());
        existingDeal.setPrice(deal.getPrice());
        existingDeal.setCompanyName(deal.getCompanyName());
        existingDeal.setImgUrl(deal.getImgUrl());
        logger.info("Updating deal with ID: {}", id);
        return dealRepository.save(existingDeal);
    }


}