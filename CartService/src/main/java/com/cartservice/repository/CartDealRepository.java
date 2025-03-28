package com.cartservice.repository;

import com.cartservice.model.CartDeal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/*
    This creates an interface CartDealRepository which extends the JpaRepository 
    which is provided by Spring Data JPA.
    JpaRepository provides all the necessary methods which help to create a
    CRUD application and it also supports custom query methods.
*/
@Repository
public interface CartDealRepository extends JpaRepository<CartDeal, String> {
    // Custom query methods can be added here if needed
}

