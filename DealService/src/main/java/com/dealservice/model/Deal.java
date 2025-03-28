package com.dealservice.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

@Entity
@Table(name = "deals")
public class Deal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotNull(message = "Deal ID cannot be null")
    @Positive(message = "Deal ID must be a positive number")
    private Long dealId;

    @NotBlank(message = "Name cannot be blank")
    @Column(nullable = false)
    private String name;

    @Min(value = 1, message = "Price must be greater than 0")
    @Column(nullable = false)
    private int price;

    @NotBlank(message = "Company name cannot be blank")
    @Column(nullable = false)
    private String companyName;

    @Column(name = "img_url")
    private String imgUrl;

    // Default constructor
    public Deal() {
    }

    // Constructor without dealId
    public Deal(String name, int price, String companyName, String imgUrl) {
        this.name = name;
        this.price = price;
        this.companyName = companyName;
        this.imgUrl = imgUrl;
    }

    // Getter and Setter methods
    public Long getDealId() {
        return dealId;
    }

    public void setDealId(Long dealId) {
        this.dealId = dealId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    @Override
    public String toString() {
        return String.format("Deal[dealId='%d', name='%s', price='%d', companyName='%s', imgUrl='%s']", 
                             dealId, name, price, companyName, imgUrl);
    }
}