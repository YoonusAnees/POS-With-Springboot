package com.example.POS.Repository;

import com.example.POS.Model.ProductReview;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductReviewRepository extends JpaRepository<ProductReview,Long> {
	
    List<ProductReview> findByProductId(Long productId);

}
