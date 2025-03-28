package com.dealservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dealservice.model.Deal;

// Repository interface for dealing with Deal entities in a SQL database
@Repository
public interface DealRepository extends JpaRepository<Deal, Long> {
    // You can add custom query methods if needed. For example:
    // List<Deal> findByCompanyName(String companyName);
}
