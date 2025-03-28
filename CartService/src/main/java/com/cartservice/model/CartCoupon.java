package com.cartservice.model;

import java.util.Optional;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

@Entity  
public class CartCoupon {
    
    @Id  // Specifies this field as the primary key
//    @GeneratedValue(strategy = GenerationType.IDENTITY)  // Auto-generate the couponId (if needed)
//    @Pattern(regexp = "^(?=.*[A-Z])(?=.*[0-9])[A-Z0-9]+$", message = "Coupon ID must contain both alphabets and numbers")
    private String couponId;

    @NotBlank(message = "Category is required")
    private String category;

    @Min(value = 0, message = "Price must be greater than or equal to 0")
    private int price;

    @NotBlank(message = "Offer is required")
    private String offer;

    @NotBlank(message = "Company name is required")
    @Size(max = 100, message = "Company name must be less than 100 characters")
    private String companyName;

    @Pattern(regexp = "^\\d{4}-\\d{2}-\\d{2}$", message = "Expiry date must be in the format YYYY-MM-DD")
    private String expiryDate;

    private int quantity;

    // Default constructor
    public CartCoupon() {
        super();
    }

    // Parameterized constructor
    public CartCoupon(String couponId, String category, int price, String offer, String companyName, String expiryDate, int quantity) {
        super();
        this.couponId = couponId;
        this.category = category;
        this.price = price;
        this.offer = offer;
        this.companyName = companyName;
        this.expiryDate = expiryDate;
        this.quantity = quantity;
    }

    // Getter and setter methods
    public String getCouponId() {
        return couponId;
    }

    public void setCouponId(String couponId) {
        this.couponId = couponId;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getOffer() {
        return offer;
    }

    public void setOffer(String offer) {
        this.offer = offer;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(String expiryDate) {
        this.expiryDate = expiryDate;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

	

	public boolean isPresent() {
		// TODO Auto-generated method stub
		return false;
	}

	public CartCoupon map(Object object) {
		// TODO Auto-generated method stub
		return null;
	}

//    @Override
//    public String toString() {
//        return "CartCoupon [couponId=" + couponId + ", category=" + category + ", price=" + price + ", offer=" + offer
//                + ", companyName=" + companyName + ", expiryDate=" + expiryDate + ", quantity=" + quantity + "]";
//    }
}
