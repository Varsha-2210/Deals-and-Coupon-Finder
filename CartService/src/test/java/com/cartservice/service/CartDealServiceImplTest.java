package com.cartservice.service;
 
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
 
import java.util.Optional;
 
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
 
import com.cartservice.exception.CartDealAlreadyExistsException;
import com.cartservice.exception.CartDealNotFoundException;
import com.cartservice.model.CartDeal;
import com.cartservice.repository.CartDealRepository;
 
@ExtendWith(MockitoExtension.class)
public class CartDealServiceImplTest {
 
    @Mock
    private CartDealRepository cartDealRepository;
 
    @InjectMocks
    private CartDealServiceImpl cartDealService;
 
    // Test case for adding a new deal successfully
    @Test
    public void testAddDeal_Success() {
        // Arrange
        CartDeal cartDeal = new CartDeal("123", "Deal Name", 100, "ABC Inc.", "image.jpg", 5);
        when(cartDealRepository.existsById("123")).thenReturn(false);  // Deal doesn't exist
        when(cartDealRepository.save(cartDeal)).thenReturn(cartDeal);  // Mock save
 
        // Act
        CartDeal savedDeal = cartDealService.add(cartDeal);
 
        // Assert
        assertEquals(cartDeal, savedDeal);  // Assert that the saved deal matches the input deal
        verify(cartDealRepository, times(1)).save(cartDeal);  // Verify save was called once
    }
 
    // Test case for adding a deal that already exists
    @Test
    public void testAddDeal_AlreadyExists() {
        // Arrange
        CartDeal cartDeal = new CartDeal("123", "Deal Name", 100, "ABC Inc.", "image.jpg", 5);
        when(cartDealRepository.existsById("123")).thenReturn(true);  // Deal already exists
 
        // Act & Assert
        assertThrows(CartDealAlreadyExistsException.class, () -> cartDealService.add(cartDeal));  // Expect exception
        verify(cartDealRepository, never()).save(cartDeal);  // Verify save is never called
    }
 
    // Test case for finding a deal by ID successfully
    @Test
    public void testFindById_Success() {
        // Arrange
        CartDeal cartDeal = new CartDeal("123", "Deal Name", 100, "ABC Inc.", "image.jpg", 5);
        when(cartDealRepository.findById("123")).thenReturn(Optional.of(cartDeal));  // Deal exists
 
        // Act
        CartDeal foundDeal = cartDealService.findById("123");
 
        // Assert
        assertEquals(cartDeal, foundDeal);  // Assert that the found deal matches the expected deal
    }
 
    // Test case for finding a deal by ID when it does not exist
    @Test
    public void testFindById_NotFound() {
        // Arrange
        when(cartDealRepository.findById("123")).thenReturn(Optional.empty());  // Deal doesn't exist
 
        // Act & Assert
        assertThrows(CartDealNotFoundException.class, () -> cartDealService.findById("123"));  // Expect exception
    }
 
    // Test case for deleting a deal by ID successfully
    @Test
    public void testDeleteById_Success() {
        // Arrange
        String dealId = "123";
        when(cartDealRepository.existsById(dealId)).thenReturn(true);  // Deal exists
 
        // Act & Assert
        assertDoesNotThrow(() -> cartDealService.deleteById(dealId));  // No exception expected
        verify(cartDealRepository, times(1)).deleteById(dealId);  // Verify deleteById is called once
    }
 
    // Test case for deleting a deal by ID when it does not exist
    @Test
    public void testDeleteById_NotFound() {
        // Arrange
        String dealId = "123";
        when(cartDealRepository.existsById(dealId)).thenReturn(false);  // Deal doesn't exist
 
        // Act & Assert
        assertThrows(CartDealNotFoundException.class, () -> cartDealService.deleteById(dealId));  // Expect exception
        verify(cartDealRepository, never()).deleteById(dealId);  // Verify deleteById is never called
    }
 
    // Test case for updating a cart deal successfully
    @Test
    public void testUpdateCartDeal_Success() {
        // Arrange
        CartDeal existingDeal = new CartDeal("123", "Old Deal Name", 100, "ABC Inc.", "oldImage.jpg", 5);
        CartDeal updatedDeal = new CartDeal("123", "Updated Deal Name", 150, "XYZ Inc.", "newImage.jpg", 10);
 
        when(cartDealRepository.findById("123")).thenReturn(Optional.of(existingDeal));  // Deal exists
        when(cartDealRepository.save(existingDeal)).thenReturn(updatedDeal);  // Mock save
 
        // Act
        CartDeal result = cartDealService.updateCoupon("123", updatedDeal);
 
        // Assert
        assertEquals(updatedDeal, result);  // Assert that the updated deal is returned
        verify(cartDealRepository, times(1)).save(existingDeal);  // Verify save was called once
    }
 
    // Test case for updating a cart deal when the deal doesn't exist
    @Test
    public void testUpdateCartDeal_DealNotFound() {
        // Arrange
        CartDeal updatedDeal = new CartDeal("123", "Updated Deal Name", 150, "XYZ Inc.", "newImage.jpg", 10);
        when(cartDealRepository.findById("123")).thenReturn(Optional.empty());  // Deal doesn't exist
 
        // Act & Assert
        assertThrows(CartDealNotFoundException.class, () -> cartDealService.updateCoupon("123", updatedDeal));  // Expect exception
        verify(cartDealRepository, never()).save(updatedDeal);  // Verify save is never called
    }
 
    // Test case for changing the quantity of a cart deal successfully
    @Test
    public void testChangeQuantity_Success() {
        // Arrange
        CartDeal cartDeal = new CartDeal("123", "Deal Name", 100, "ABC Inc.", "image.jpg", 5);
        when(cartDealRepository.findById("123")).thenReturn(Optional.of(cartDeal));  // Deal exists
 
        // Act
        cartDealService.changequantity("123", 10);
 
        // Assert
        assertEquals(10, cartDeal.getQuantity());  // Assert that the quantity is updated
        verify(cartDealRepository, times(1)).save(cartDeal);  // Verify save was called once
    }
 
    // Test case for changing quantity when the deal does not exist
    @Test
    public void testChangeQuantity_DealNotFound() {
        // Arrange
        when(cartDealRepository.findById("123")).thenReturn(Optional.empty());  // Deal doesn't exist
 
        // Act & Assert
        assertThrows(CartDealNotFoundException.class, () -> cartDealService.changequantity("123", 10));  // Expect exception
        verify(cartDealRepository, never()).save(any());  // Verify save is never called
    }
 
}