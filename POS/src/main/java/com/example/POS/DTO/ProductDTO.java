package com.example.POS.DTO;

import com.example.POS.Model.ProductImage;
import com.example.POS.Model.ProductReview;
import jakarta.persistence.CascadeType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor

public class ProductDTO {

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

	public List<ProductImageDTO> getImages() {
		return images;
	}

	public void setImages(List<ProductImageDTO> images) {
		this.images = images;
	}

	public List<ProductReviewDto> getReviews() {
		return reviews;
	}

	public void setReviews(List<ProductReviewDto> reviews) {
		this.reviews = reviews;
	}

	private Long id;

    @NotBlank(message = "Product Name Field is Required")
    private String name;

    @NotBlank(message = "Description Field is Required")
    private String description;

    private String category;

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

    private List<ProductImageDTO> images;

    private List<ProductReviewDto> reviews;

}
