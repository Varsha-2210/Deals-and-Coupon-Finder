package com.cartservice.service;

import java.util.List;

import com.cartservice.model.CartDeal;

//Interface for CartDeal service operations
public interface CartDealService {

 // Add a new cart deal
 public CartDeal add(CartDeal cartDeal);
 
 // Delete a cart deal by ID
 public void deleteById(String id);
 
 // Retrieve all cart deals
 public List<CartDeal> findAll();
 
 // Find a cart deal by ID
 public CartDeal findById(String id);
 
 // Update a cart deal by ID
 public CartDeal updateCoupon(String id, CartDeal cartDeal);
 
 // Change quantity of a cart deal by ID
 public void changequantity(String id, int qty);
 
 
 public void deleteAll();
}