package com.cartservice.controller;

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

import com.cartservice.model.CartDeal;
import com.cartservice.service.CartDealServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
@AutoConfigureMockMvc
public class CartDealControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CartDealServiceImpl cartDealService;

    @Autowired
    private ObjectMapper objectMapper;

    // Test case for adding a new deal
    @Test
    public void addDetails() throws Exception {
        CartDeal cartDeal = new CartDeal();
        cartDeal.setDealId("DEAL123");
        cartDeal.setName("Electronics");
        cartDeal.setPrice(100);
        cartDeal.setCompanyName("ABC Inc.");

        mockMvc.perform(post("/cart/cartdeal/add")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(cartDeal)))
                .andExpect(status().isCreated());

        // Verifying that the service method was called with the correct parameters
        verify(cartDealService, Mockito.times(1)).add(any(CartDeal.class));
    }

    // Test case for getting all deals
    @Test
    public void getDealDetails() throws Exception {
        CartDeal cartDeal = new CartDeal();
        cartDeal.setDealId("deal123");
        cartDeal.setName("Electronics");
        cartDeal.setPrice(100);
        cartDeal.setCompanyName("ABC Inc.");

        when(cartDealService.findAll()).thenReturn(Arrays.asList(cartDeal));

        mockMvc.perform(get("/cart/cartdeal/list"))
                .andExpect(status().isOk());

        // Verifying that the service method was called to retrieve all deals
        verify(cartDealService, Mockito.times(1)).findAll();
    }

    // Test case for finding a deal by ID
    @Test
    public void findById() throws Exception {
        CartDeal cartDeal = new CartDeal();
        cartDeal.setDealId("deal123");
        cartDeal.setName("Electronics");
        cartDeal.setPrice(100);
        cartDeal.setCompanyName("ABC Inc.");

        when(cartDealService.findById(anyString())).thenReturn(cartDeal);

        mockMvc.perform(get("/cart/cartdeal/findById/deal123"))
                .andExpect(status().isOk());

        // Verifying that the service method was called to find a deal by ID
        verify(cartDealService, Mockito.times(1)).findById(anyString());
    }

   

    // Test case for updating a deal by ID
    @Test
    public void updateCoupon() throws Exception {
        CartDeal cartDeal = new CartDeal();
        cartDeal.setDealId("deal123");
        cartDeal.setName("Electronics");
        cartDeal.setPrice(100);
        cartDeal.setCompanyName("ABC Inc.");

        when(cartDealService.updateCoupon(anyString(), any(CartDeal.class))).thenReturn(cartDeal);

        mockMvc.perform(put("/cart/cartdeal/updateById/deal123")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(cartDeal)))
                .andExpect(status().isOk());

        // Verifying that the service method was called to update a deal by ID
        verify(cartDealService, Mockito.times(1)).updateCoupon(anyString(), any(CartDeal.class));
    }

    // Test case for changing the quantity of a deal
    @Test
    public void changeQuantityDeal() throws Exception {
        mockMvc.perform(put("/cart/cartdeal/changeQuantity/deal123/5"))
                .andExpect(status().isOk());

        // Verifying that the service method was called to change the quantity of a deal
        verify(cartDealService, Mockito.times(1)).changequantity(anyString(), anyInt());  // Corrected method name
    }
}
