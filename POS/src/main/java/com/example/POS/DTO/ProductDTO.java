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
