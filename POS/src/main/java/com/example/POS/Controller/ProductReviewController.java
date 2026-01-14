package com.example.POS.Controller;

import com.example.POS.DTO.ProductReviewDto;
import com.example.POS.Model.Product;
import com.example.POS.Repository.ProductRepository;
import com.example.POS.Services.ProductService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/products/review")
public class ProductReviewController {
    @Autowired
    private ProductService  productService;

    @PostMapping
    public ResponseEntity<?> addReview(@RequestBody @Valid ProductReviewDto productReviewDto) {
        productService.addReviewToProduct(productReviewDto);
        return ResponseEntity.status(HttpStatus.CREATED).body("Review added");
    }
}
