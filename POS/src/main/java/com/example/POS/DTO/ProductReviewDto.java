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
}
