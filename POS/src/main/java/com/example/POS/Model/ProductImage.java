package com.example.POS.Model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class ProductImage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String publicId;

    private String url;

    public ProductImage() {
    }

    public ProductImage(Long id, String publicId, String url) {
        this.id = id;
        this.publicId = publicId;
        this.url = url;
    }

    @ManyToOne()
    @JoinColumn(name = "product_id")
    @JsonBackReference

    private Product product;

    public ProductImage(String url, Product product) {
        this.url = url;          // keep original URL
        this.publicId = url;     // or generate unique ID if needed
        this.product = product;
    }



}

