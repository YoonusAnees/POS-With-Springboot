package com.example.POS.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

public class ProductReviewDto {
    @NotBlank(message = "Product Name Field is Required")
    private Long productId;

    @NotNull(message = "Rating  is Required")
    private Double rating;

    private String comment;

	

	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	public Double getRating() {
		return rating;
	}

	public void setRating(Double rating) {
		this.rating = rating;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public ProductReviewDto(@NotBlank(message = "Product Name Field is Required") Long productId,
			@NotNull(message = "Rating  is Required") Double rating, String comment) {
		super();
		this.productId = productId;
		this.rating = rating;
		this.comment = comment;
	}
	
	
	
}
