package com.cartservice.controller;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.Arrays;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.cartservice.model.CartCoupon;
import com.cartservice.service.CartCouponServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
@AutoConfigureMockMvc
public class CartCouponControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CartCouponServiceImpl cartCouponService;

    @Autowired
    private ObjectMapper objectMapper;

 // Test case for adding a new cart coupon
    @Test
    public void insertDetails() throws Exception {
        CartCoupon cartCoupon = new CartCoupon();
        cartCoupon.setCouponId("COU123");
        cartCoupon.setCategory("Electronics");
        cartCoupon.setPrice(100);
        cartCoupon.setOffer("10% off");
        cartCoupon.setCompanyName("ABC Inc.");
        cartCoupon.setExpiryDate("2025-12-31");
        cartCoupon.setQuantity(10);

        // Mocking the service method
        when(cartCouponService.add(any(CartCoupon.class))).thenReturn(cartCoupon);

        // Performing the POST request and validating the response
        mockMvc.perform(post("/cart/cartcoupon/add")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(cartCoupon)))
                .andExpect(status().isCreated());

        // Verifying that the service method is invoked
        verify(cartCouponService, Mockito.times(1)).add(any(CartCoupon.class));
    }

    // Test case for getting all cart coupons
    @Test
    public void getCartCouponDetails() throws Exception {
        CartCoupon cartCoupon = new CartCoupon();
        cartCoupon.setCouponId("coupon123");
        cartCoupon.setCategory("Electronics");
        cartCoupon.setPrice(100);
        cartCoupon.setOffer("10% off");
        cartCoupon.setCompanyName("ABC Inc.");
        cartCoupon.setExpiryDate("2025-12-31");
        cartCoupon.setQuantity(10);

        // Mocking the service method
        when(cartCouponService.findAll()).thenReturn(Arrays.asList(cartCoupon));

        // Performing the GET request and validating the response
        mockMvc.perform(get("/cart/cartcoupon/list"))
                .andExpect(status().isOk());

        // Verifying that the service method is invoked
        verify(cartCouponService, Mockito.times(1)).findAll();
    }

    // Test case for finding a cart coupon by ID
    @Test
    public void findById() throws Exception {
        CartCoupon cartCoupon = new CartCoupon();
        cartCoupon.setCouponId("coupon123");
        cartCoupon.setCategory("Electronics");
        cartCoupon.setPrice(100);
        cartCoupon.setOffer("10% off");
        cartCoupon.setCompanyName("ABC Inc.");
        cartCoupon.setExpiryDate("2025-12-31");
        cartCoupon.setQuantity(10);

        // Mocking the service method
        when(cartCouponService.findById(anyString())).thenReturn(cartCoupon);

        // Performing the GET request and validating the response
        mockMvc.perform(get("/cart/cartcoupon/findById/coupon123"))
                .andExpect(status().isOk());

        // Verifying that the service method is invoked
        verify(cartCouponService, Mockito.times(1)).findById(anyString());
    }

    // Test case for deleting a cart coupon by ID
    @Test
    public void deleteById() throws Exception {
        // Performing the DELETE request and validating the response
        mockMvc.perform(delete("/cart/cartcoupon/deleteById/coupon123"))
                .andExpect(status().isOk());

        // Verifying that the service method is invoked
        verify(cartCouponService, Mockito.times(1)).deleteById(anyString());
    }

    // Test case for updating a cart coupon by ID
    @Test
    public void updateCoupon() throws Exception {
        CartCoupon cartCoupon = new CartCoupon();
        cartCoupon.setCouponId("coupon123");
        cartCoupon.setCategory("Electronics");
        cartCoupon.setPrice(100);
        cartCoupon.setOffer("10% off");
        cartCoupon.setCompanyName("ABC Inc.");
        cartCoupon.setExpiryDate("2025-12-31");
        cartCoupon.setQuantity(10);

        // Mocking the service method
        when(cartCouponService.updateCoupon(anyString(), any(CartCoupon.class))).thenReturn(cartCoupon);

        // Performing the PUT request and validating the response
        mockMvc.perform(put("/cart/cartcoupon/updateById/coupon123")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(cartCoupon)))
                .andExpect(status().isOk());

        // Verifying that the service method is invoked
        verify(cartCouponService, Mockito.times(1)).updateCoupon(anyString(), any(CartCoupon.class));
    }
    
    // Test case for changing the quantity of a cart coupon
    @Test
    public void changeQuantityCoupon() throws Exception {
        mockMvc.perform(put("/cart/cartcoupon/changeQuantity/coupon123/5"))
                .andExpect(status().isOk());

        // Verifying that the service method was called with the correct parameters
        verify(cartCouponService, Mockito.times(1)).changequantity(anyString(), anyInt());
    }
}
