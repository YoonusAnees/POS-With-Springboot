package com.example.POS.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data                   // <-- ADD THIS
@NoArgsConstructor      // <-- Already useful
@AllArgsConstructor     // <-- Optional
@Entity
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Product Name Field is Required")
    private String name;

    @NotBlank(message = "Description Field is Required")
    private String description;

    @NotNull(message = "Price Filed is Required")
    @PositiveOrZero(message = "Value Must be Zero or Grater Than Zero")
    private double price;

    @NotNull(message = "Ratings Field is Required")
    private Double ratings = 0.0;

    @NotBlank(message = "Seller Field is Required")
    private String seller;

    @NotNull(message = "Stock Field is Required")
    private Integer stock = 0;

    private Integer numOfReviews = 0;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "product_id")
    private List<ProductImage> images;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "product_id")
    private List<ProductReview> reviews;

    // Your custom constructor
    public Product(Long id, String name, String description, double price,
                   Double ratings, String seller, Integer stock) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.ratings = ratings;
        this.seller = seller;
        this.stock = stock;
    }
}
