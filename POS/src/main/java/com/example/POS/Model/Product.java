package com.example.POS.Model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;


@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Product Name Field is Required")
    private String name;

    @NotBlank(message = "Description Field is Required")
    private String description;

    private String category;

    @NotNull(message = "Price Filed is Required")
    @PositiveOrZero(message = "Value Must be Zero or Grater Than Zero")
    private double price;

    private Double ratings = 0.0;

    @NotBlank(message = "Seller Field is Required")
    private String seller;

    @NotNull(message = "Stock Field is Required")
    private Integer stock = 0;

    private Integer numOfReviews = 0;

    // PRODUCT IMAGES (Unidirectional OK)
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "product_id")
    @JsonManagedReference

    private List<ProductImage> images;

    public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public Double getRatings() {
		return ratings;
	}

	public void setRatings(Double ratings) {
		this.ratings = ratings;
	}

	public String getSeller() {
		return seller;
	}

	public void setSeller(String seller) {
		this.seller = seller;
	}

	public Integer getStock() {
		return stock;
	}

	public void setStock(Integer stock) {
		this.stock = stock;
	}

	public Integer getNumOfReviews() {
		return numOfReviews;
	}

	public void setNumOfReviews(Integer numOfReviews) {
		this.numOfReviews = numOfReviews;
	}

	public List<ProductImage> getImages() {
		return images;
	}

	public void setImages(List<ProductImage> images) {
		this.images = images;
	}

	public List<ProductReview> getReviews() {
		return reviews;
	}

	public void setReviews(List<ProductReview> reviews) {
		this.reviews = reviews;
	}

	// PRODUCT REVIEWS (Proper Bidirectional)
    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference

    private List<ProductReview> reviews;

    // Your custom constructor
    public Product(Long id, String name, String description, String category, double price,
                   Double ratings, String seller, Integer stock, List<String> images) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.category = category;
        this.price = price;
        this.ratings = ratings;
        this.seller = seller;
        this.stock = stock;

        // Convert String URLs to ProductImage objects
        if (images != null) {
            this.images = images.stream()
                    .map(url -> new ProductImage(url,this))
                    .collect(Collectors.toList());
        }
    }

	




}





