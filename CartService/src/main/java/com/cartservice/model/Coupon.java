package com.cartservice.model;



import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;


@Entity
@Table(name = "coupons")
public class Coupon {

    @Id
    @Pattern(regexp = "^(?=.*[A-Z])(?=.*[0-9])[A-Z0-9]+$", message = "Coupon ID must contain both alphabets and numbers")
    private String couponId;

    @NotBlank(message = "Category cannot be blank")
    private String category;

    @Min(value = 1, message = "Price must be greater than 0")
    private int price;

    private String offer;

    @NotBlank(message = "Company name cannot be blank")
    private String companyName;

    // Use LocalDate instead of String for expiryDate
    @Column(name = "expiry_date")
    private LocalDate expiryDate;

    public Coupon() {
        super();
    }

    public Coupon(String couponId, String category, int price, String offer, String companyName, LocalDate expiryDate) {
        super();
        this.couponId = couponId;
        this.category = category;
        this.price = price;
        this.offer = offer;
        this.companyName = companyName;
        this.expiryDate = expiryDate;
    }

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

    public LocalDate getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(LocalDate expiryDate) {
        this.expiryDate = expiryDate;
    }

    @Override
    public String toString() {
        return "Coupon [couponId=" + couponId + ", category=" + category + ", price=" + price + ", offer=" + offer
                + ", companyName=" + companyName + ", expiryDate=" + expiryDate + "]";
    }
}

