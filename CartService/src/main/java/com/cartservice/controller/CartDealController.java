package com.cartservice.controller;

import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.cartservice.model.CartDeal;
import com.cartservice.service.CartDealServiceImpl;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/cart/cartdeal")
@CrossOrigin(origins="*")
public class CartDealController {

    @Autowired
    CartDealServiceImpl cartDealService;

    // Add a new cart deal
    @PostMapping(value = "/add")
    public ResponseEntity<String> addDetails(@Valid @RequestBody CartDeal cartDeal) {
        if (cartDeal.getDealId() == null) {
            cartDeal.setDealId(generateId());  // Generate and set the id
        }
        cartDeal.setQuantity(1);  // Set default quantity to 1
        cartDealService.add(cartDeal);
        return ResponseEntity.status(HttpStatus.CREATED).body("Deal added successfully");
    }

    private String generateId() {
        return String.valueOf(new Random().nextInt(1000000)); // Generate a random numeric ID
    }

    // Get all cart deals
    @GetMapping(value = "/list")
    public ResponseEntity<List<CartDeal>> getCartDealDetails() {
        List<CartDeal> list = cartDealService.findAll();
        return ResponseEntity.ok(list);
    }

    // Find a cart deal by ID
    @GetMapping(value = "/findById/{dealId}")
    public ResponseEntity<CartDeal> findById(@PathVariable("dealId") String dealId) {
        CartDeal cartDeal = cartDealService.findById(dealId);
        if (cartDeal == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        return ResponseEntity.ok(cartDeal);
    }

    // Delete a cart deal by ID
    @DeleteMapping(value = "/deleteById/{dealId}")
    public ResponseEntity<String> deleteById(@PathVariable("dealId") String dealId) {
        cartDealService.deleteById(dealId);
        return ResponseEntity.ok("Deleted Successfully");
    }

    // Update a cart deal by ID
    @PutMapping("/updateById/{dealId}")
    public ResponseEntity<CartDeal> updateDeal(@PathVariable String dealId, @RequestBody CartDeal cartDeal) {
        CartDeal updatedDeal = cartDealService.updateCoupon(dealId, cartDeal);
        if (updatedDeal == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        return ResponseEntity.ok(updatedDeal);
    }

    // Change quantity of a cart deal
    @PutMapping("changeQuantity/{dealId}/{quantity}")
    public ResponseEntity<String> changeQuantityDeal(@PathVariable("dealId") String dealId, @PathVariable("quantity") int quantity) {
        if (quantity <= 0) {
            return ResponseEntity.badRequest().body("Quantity must be greater than 0");
        }
        cartDealService.changequantity(dealId, quantity);
        return ResponseEntity.ok("Quantity updated successfully");
    }

    // Delete all cart deals
    @DeleteMapping("/deleteAll")
    public ResponseEntity<String> deleteAll() {
        cartDealService.deleteAll();
        return ResponseEntity.ok("All Deals Deleted Successfully");
    }
}