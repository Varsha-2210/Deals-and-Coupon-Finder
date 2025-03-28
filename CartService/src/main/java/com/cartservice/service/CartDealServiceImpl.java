package com.cartservice.service;
 
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
 
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
 
import com.cartservice.exception.CartDealAlreadyExistsException;
import com.cartservice.exception.CartDealNotFoundException;
import com.cartservice.model.CartDeal;
import com.cartservice.repository.CartDealRepository;
 
 
@Service
public class CartDealServiceImpl implements CartDealService
{
	@Autowired
	private CartDealRepository cartDealRepository;
	// Logger for logging messages
	Logger logger = LoggerFactory.getLogger(CartDealServiceImpl.class);
	// Add a new cart deal
	public CartDeal add(CartDeal cartDeal) {
		if(cartDealRepository.existsById(cartDeal.getDealId())) {
			logger.error("Deal with ID {} already exists", cartDeal.getDealId());
			throw new CartDealAlreadyExistsException("Already Exists");
		}
		logger.info("Adding new deal with ID {}", cartDeal.getDealId());
		return cartDealRepository.save(cartDeal);
	}
	// Delete a cart deal by ID
	 public void deleteById(String dealId) {
		 logger.error("Deal with ID {} not found", dealId);
	        if (!cartDealRepository.existsById(dealId)) {
	            throw new CartDealNotFoundException("Deal with ID " + dealId + " not found");
	        }
	        logger.info("Deleting deal with ID {}", dealId);
	        cartDealRepository.deleteById(dealId);
	    }
 
	
	// Retrieve all cart deals
	public List<CartDeal> findAll() {
		List<CartDeal> list = cartDealRepository.findAll();
		List<CartDeal> list1=new ArrayList<>();
		for(int i =0 ; i<list.size();i++)
		{
			if(list.get(i).getName()!=null)
			{
				list1.add(list.get(i));
			}
		}
		logger.info("Found {} deals in the cart", list1.size());
		return list1;
	}
	 // Find a cart deal by ID
	public CartDeal findById(String dealId) {
        Optional<CartDeal> cartDealOptional = cartDealRepository.findById(dealId);
        if (cartDealOptional.isPresent()) {
        	 logger.info("Deal with ID {} found", dealId);
            return cartDealOptional.get();
        } else {
        	logger.error("Deal with ID {} not found", dealId);
            throw new CartDealNotFoundException("Deal with ID " + dealId + " not found");
        }
    }
	// Update a cart deal by ID
	public CartDeal updateCoupon(String dealId, CartDeal cartDeal) {
	    Optional<CartDeal> existingDealOptional = cartDealRepository.findById(dealId);
	    if (existingDealOptional.isPresent()) {
	        CartDeal existingDeal = existingDealOptional.get();
	        existingDeal.setName(cartDeal.getName());
	        existingDeal.setPrice(cartDeal.getPrice());
	        existingDeal.setCompanyName(cartDeal.getCompanyName());
	        existingDeal.setImgUrl(cartDeal.getImgUrl());
	        logger.info("Updating deal with ID {}", dealId);
	        return cartDealRepository.save(existingDeal);
	    } else {
	    	logger.error("Deal with ID {} not found", dealId);
	        throw new CartDealNotFoundException("Deal with ID " + dealId + " not found");
	    }
	}
	// Change quantity of a cart deal by ID
	 public void changequantity(String dealId, int quantity) {
	        Optional<CartDeal> optionalCartDeal = cartDealRepository.findById(dealId);
	        if (optionalCartDeal.isPresent()) {
	            CartDeal cartDeal = optionalCartDeal.get();
	            cartDeal.setQuantity(quantity);
	            cartDealRepository.save(cartDeal);
	            logger.info("Changed quantity of deal with ID {} to {}", dealId, quantity);
	        } else {
	        	logger.error("Deal with ID {} not found", dealId);
	            throw new CartDealNotFoundException("Deal with ID " + dealId + " not found");
	        }
	    }
 
 
	 public void deleteAll() {
	        cartDealRepository.deleteAll();
	    }
}