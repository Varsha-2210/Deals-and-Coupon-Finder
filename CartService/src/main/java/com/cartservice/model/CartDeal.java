package com.cartservice.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

@Entity 
public class CartDeal {

    @Id  // Specifies this field as the primary key
//    @GeneratedValue(strategy = GenerationType.IDENTITY)  // Auto-generate the dealId (if needed)
//    @Pattern(regexp = "^(?=.*[A-Z])(?=.*[0-9])[A-Z0-9]+$", message = "Deal ID must contain both alphabets and numbers")
    private String dealId;

    @NotBlank(message = "Name is required")
    private String name;

    @Min(value = 0, message = "Price must be greater than or equal to 0")
    private int price;

    @NotBlank(message = "Company name is required")
    private String companyName;

    @Pattern(
        regexp = "^(http|https)://[^\\s/$.?#].[^\\s]*$",
        message = "Invalid URL format"
    )
    private String imgUrl;

    private int quantity;

    // Default constructor
    public CartDeal() {
        super();
    }

//     Parameterized constructor
    public CartDeal(String dealId, String name, int price, String companyName, String imgUrl, int quantity) {
        super();
        this.dealId = dealId;
        this.name = name;
        this.price = price;
        this.companyName = companyName;
        this.imgUrl = imgUrl;
        this.quantity = quantity;
    }

    // Getter and setter methods
    public String getDealId() {
        return dealId;
    }

    public void setDealId(String dealId) {
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

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

//    @Override
//    public String toString() {
//        return "CartDeal [dealId=" + dealId + ", name=" + name + ", price=" + price + ", companyName=" + companyName
//                + ", imgUrl=" + imgUrl + ", quantity=" + quantity + "]";
//    }
}
