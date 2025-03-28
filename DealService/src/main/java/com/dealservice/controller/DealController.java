package com.dealservice.controller;

import java.util.List;




//import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dealservice.exception.DealAlreadyExistsException;
import com.dealservice.exception.DealNotFoundException;
import com.dealservice.model.Deal;
import com.dealservice.service.DealService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/deals")
@CrossOrigin(origins="*")
public class DealController {

    @Autowired
    private DealService dealService;

    // Endpoint to add a new deal
    @PostMapping("/add")
    public Deal createDeal(@RequestBody @Valid Deal deal) {
        try {
            return dealService.save(deal);
        } catch (DealAlreadyExistsException ex) {
            throw new DealAlreadyExistsException("Deal with ID " + deal.getDealId() + " already exists");
        }
    }

    // Endpoint to get all deals
    @GetMapping("/findAll")
    public List<Deal> getAllDeals() {
        return dealService.findAll();
    }

    // Endpoint to find a deal by its ID
    @GetMapping("/findById/{id}")
    public Deal getDealById(@PathVariable Long id) {
        try {
            return dealService.findById(id);
        } catch (DealNotFoundException ex) {
            throw new DealNotFoundException("Deal with ID " + id + " not found");
        }
    }

    // Endpoint to update a deal by its ID
    @PutMapping("/updateById/{id}")
    public Deal updateDealById(@PathVariable Long id, @RequestBody @Valid Deal deal) {
        try {
            return dealService.updateById(id, deal);
        } catch (DealNotFoundException ex) {
            throw new DealNotFoundException("Deal with ID " + id + " not found");
        }
    }

    // Endpoint to delete a deal by its ID
    @DeleteMapping("/deleteById/{id}")
    public String deleteDeal(@PathVariable Long id) {
        try {
            dealService.deleteById(id);
            return "Deal with ID " + id + " has been deleted";
        } catch (DealNotFoundException ex) {
            throw new DealNotFoundException("Deal with ID " + id + " not found");
        }
    }
}